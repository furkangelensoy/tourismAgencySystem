package dao;

import core.Db;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// This class contains queries of the room table in the database.
// This class contains create,update,delete and other methods.

public class RoomDao {
    private final Connection connection;
    private final HotelDao hotelDao;
    private final PensionDao pensionDao;
    private final SeasonDao seasonDao;

    public RoomDao() {
        this.connection = Db.getInstance();
        this.hotelDao = new HotelDao();
        this.pensionDao = new PensionDao();
        this.seasonDao = new SeasonDao();
    }

    // This method matches data from the database.
    // This method returns room, firstly creates a empty room then fills with resultset
    // values according to room table column values.
    public Room match(ResultSet resultSet) throws SQLException {
        Room object = new Room();
        object.setId(resultSet.getInt("room_id"));
        object.setHotelId(resultSet.getInt("room_hotel_id"));
        object.setHotel(this.hotelDao.getById(resultSet.getInt("room_hotel_id")));
        object.setPensionId(resultSet.getInt("room_pension_id"));
        object.setPension(this.pensionDao.getById(resultSet.getInt("room_pension_id")));
        object.setSeasonId(resultSet.getInt("room_season_id"));
        object.setSeason(this.seasonDao.getById(resultSet.getInt("room_season_id")));
        object.setType(resultSet.getString("room_type"));
        object.setStock(resultSet.getInt("room_stock"));
        object.setAdultPrice(resultSet.getInt("room_adult_price"));
        object.setChildPrice(resultSet.getInt("room_child_price"));
        object.setBedCapacity(resultSet.getInt("room_bed_capacity"));
        object.setAreaOfRoom(resultSet.getInt("room_area"));
        object.setTelevision(resultSet.getBoolean("room_television"));
        object.setMinibar(resultSet.getBoolean("room_minibar"));
        object.setGameConsole(resultSet.getBoolean("room_game_console"));
        object.setCashBox(resultSet.getBoolean("room_cashbox"));
        object.setProjection(resultSet.getBoolean("room_projection"));
        return object;
    }

    // This method returns all rooms as a list in the database room table.
    public ArrayList<Room> findAll(){
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM public.room ORDER BY room_id ASC";
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while(resultSet.next()){
                roomList.add(this.match(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return roomList;
    }

    public boolean save (Room room){
        String query = "INSERT INTO public.room " +
                "(" +
                "room_hotel_id, " +
                "room_pension_id, " +
                "room_season_id, " +
                "room_type, " +
                "room_stock, " +
                "room_adult_price, " +
                "room_child_price, " +
                "room_bed_capacity, " +
                "room_area, " +
                "room_television, " +
                "room_minibar, " +
                "room_game_console, " +
                "room_cashbox, " +
                "room_projection" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,room.getHotelId());
            preparedStatement.setInt(2,room.getPensionId());
            preparedStatement.setInt(3,room.getSeasonId());
            preparedStatement.setString(4,room.getType());
            preparedStatement.setInt(5,room.getStock());
            preparedStatement.setInt(6,room.getAdultPrice());
            preparedStatement.setInt(7,room.getChildPrice());
            preparedStatement.setInt(8,room.getBedCapacity());
            preparedStatement.setInt(9,room.getAreaOfRoom());
            preparedStatement.setBoolean(10,room.isTelevision());
            preparedStatement.setBoolean(11,room.isMinibar());
            preparedStatement.setBoolean(12,room.isGameConsole());
            preparedStatement.setBoolean(13,room.isCashBox());
            preparedStatement.setBoolean(14,room.isProjection());
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Room room){
        String query = "UPDATE public.room SET " +
                "room_hotel_id = ? ," +
                "room_pension_id = ? ," +
                "room_season_id = ? ," +
                "room_type = ? ," +
                "room_stock = ? ," +
                "room_adult_price = ? ," +
                "room_child_price = ? ," +
                "room_bed_capacity = ? ," +
                "room_area = ? ," +
                "room_television = ? ," +
                "room_minibar = ? ," +
                "room_game_console = ? ," +
                "room_cashbox = ? ," +
                "room_projection = ? " +
                "WHERE room_id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,room.getHotelId());
            preparedStatement.setInt(2,room.getPensionId());
            preparedStatement.setInt(3,room.getSeasonId());
            preparedStatement.setString(4,room.getType());
            preparedStatement.setInt(5,room.getStock());
            preparedStatement.setInt(6,room.getAdultPrice());
            preparedStatement.setInt(7,room.getChildPrice());
            preparedStatement.setInt(8,room.getBedCapacity());
            preparedStatement.setInt(9,room.getAreaOfRoom());
            preparedStatement.setBoolean(10,room.isTelevision());
            preparedStatement.setBoolean(11,room.isMinibar());
            preparedStatement.setBoolean(12,room.isGameConsole());
            preparedStatement.setBoolean(13,room.isCashBox());
            preparedStatement.setBoolean(14,room.isProjection());
            preparedStatement.setInt(15,room.getId());

            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete (int roomId){
        String query = "DELETE FROM public.room WHERE room_id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,roomId);
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    // This method returns room according to room id.
    public Room getById(int roomId){
        String query = "SELECT * FROM public.room WHERE room_id = ?";
        Room object = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                object = this.match(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return object;
    }

    // This method returns season list according to query.
    public ArrayList<Room> selectByQuery(String query){
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                roomList.add(this.match(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return roomList;
    }
}
