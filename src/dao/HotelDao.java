package dao;

import core.Db;
import entity.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {
    private final Connection connection;

    public HotelDao() {
        this.connection = Db.getInstance();
    }

    public Hotel match(ResultSet resultSet) throws SQLException {
        Hotel object = new Hotel();
        object.setId(resultSet.getInt("hotel_id"));
        object.setName(resultSet.getString("hotel_name"));
        object.setCity(resultSet.getString("hotel_city"));
        object.setState(resultSet.getString("hotel_state"));
        object.setAddress(resultSet.getString("hotel_address"));
        object.setMail(resultSet.getString("hotel_mail"));
        object.setPhone(resultSet.getString("hotel_phone"));
        object.setStar(resultSet.getString("hotel_star"));
        object.setCarPark(resultSet.getBoolean("hotel_car_park"));
        object.setWifi(resultSet.getBoolean("hotel_wifi"));
        object.setPool(resultSet.getBoolean("hotel_pool"));
        object.setConcierge(resultSet.getBoolean("hotel_concierge"));
        object.setFitness(resultSet.getBoolean("hotel_fitness"));
        object.setRoomService(resultSet.getBoolean("hotel_room_service"));
        object.setSpa(resultSet.getBoolean("hotel_spa"));
        return object;
    }

    public ArrayList<Hotel> findAll(){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM public.hotel ORDER BY hotel_id ASC";
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                hotelList.add(this.match(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return hotelList;
    }

    public Hotel getById(int hotelId){
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ?";
        Hotel object = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,hotelId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                object = this.match(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return object;
    }

    public ArrayList<Hotel> selectByQuery(String query){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                hotelList.add(this.match(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return hotelList;
    }

    public boolean save(Hotel hotel){
        String query = "INSERT INTO public.hotel" +
                "(" +
                "hotel_name, " +
                "hotel_city, " +
                "hotel_state, " +
                "hotel_address, " +
                "hotel_mail, " +
                "hotel_phone, " +
                "hotel_star, " +
                "hotel_car_park, " +
                "hotel_wifi, " +
                "hotel_pool, " +
                "hotel_concierge, " +
                "hotel_fitness, " +
                "hotel_room_service, " +
                "hotel_spa" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1,hotel.getName());
            preparedStatement.setString(2,hotel.getCity());
            preparedStatement.setString(3,hotel.getState());
            preparedStatement.setString(4,hotel.getAddress());
            preparedStatement.setString(5,hotel.getMail());
            preparedStatement.setString(6,hotel.getPhone());
            preparedStatement.setString(7,hotel.getStar());
            preparedStatement.setBoolean(8,hotel.isCarPark());
            preparedStatement.setBoolean(9,hotel.isWifi());
            preparedStatement.setBoolean(10,hotel.isPool());
            preparedStatement.setBoolean(11,hotel.isConcierge());
            preparedStatement.setBoolean(12,hotel.isFitness());
            preparedStatement.setBoolean(13,hotel.isRoomService());
            preparedStatement.setBoolean(14,hotel.isSpa());
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Hotel hotel){
        String query = "UPDATE public.hotel SET" +
                "hotel_name = ? ," +
                "hotel_city = ? ," +
                "hotel_state = ? ," +
                "hotel_address = ? ," +
                "hotel_mail = ? ," +
                "hotel_phone = ? ," +
                "hotel_star = ? ," +
                "hotel_car_park = ? ," +
                "hotel_wifi = ? ," +
                "hotel_pool = ? ," +
                "hotel_concierge = ? ," +
                "hotel_fitness = ? ," +
                "hotel_room_service = ? ," +
                "hotel_spa = ? " +
                "WHERE hotel_id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1,hotel.getName());
            preparedStatement.setString(2,hotel.getCity());
            preparedStatement.setString(3,hotel.getState());
            preparedStatement.setString(4,hotel.getAddress());
            preparedStatement.setString(5,hotel.getMail());
            preparedStatement.setString(6,hotel.getPhone());
            preparedStatement.setString(7,hotel.getStar());
            preparedStatement.setBoolean(8,hotel.isCarPark());
            preparedStatement.setBoolean(9,hotel.isWifi());
            preparedStatement.setBoolean(10,hotel.isPool());
            preparedStatement.setBoolean(11,hotel.isConcierge());
            preparedStatement.setBoolean(12,hotel.isFitness());
            preparedStatement.setBoolean(13,hotel.isRoomService());
            preparedStatement.setBoolean(14,hotel.isSpa());
            preparedStatement.setInt(15,hotel.getId());
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    public boolean delete(int hotelId){
        String query = "DELETE FROM public.hotel WHERE hotel_id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,hotelId);
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }


}
