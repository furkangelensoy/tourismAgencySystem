package dao;

import core.Db;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

// This class contains queries of the season table in the database.
// This class contains create,update,delete and other methods.

public class SeasonDao {
    private final Connection connection;
    private final HotelDao hotelDao;

    public SeasonDao() {
        this.connection = Db.getInstance();
        this.hotelDao = new HotelDao();
    }

    // This method matches data from the database.
    // This method returns season, firstly creates a empty season then fills with resultset
    // values according to season table column values.
    public Season match(ResultSet resultSet) throws SQLException {
        Season object = new Season();
        object.setId(resultSet.getInt("season_id"));
        object.setName(resultSet.getString("season_name"));
        object.setHotelId(resultSet.getInt("season_hotel_id"));
        object.setHotel(this.hotelDao.getById(resultSet.getInt("season_hotel_id")));
        object.setStartDate(LocalDate.parse(resultSet.getString("season_start_date")));
        object.setFinishDate(LocalDate.parse(resultSet.getString("season_finish_date")));
        return object;
    }
    // This method returns season according to season id.
    public Season getById(int seasonId){
        String query = "SELECT * FROM public.season WHERE season_id = ?";
        Season object = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,seasonId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                object = this.match(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return object;
    }

    // This method returns all rooms as a list in the database room table.
    public ArrayList<Season> findAll() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM public.season ORDER BY season_id ASC";
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                seasonList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }

    public boolean save(Season season) {
        String query = "INSERT INTO public.season" +
                "(" +
                "season_name, " +
                "season_hotel_id, " +
                "season_start_date, " +
                "season_finish_date" +
                ")" +
                " VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1,season.getName());
            preparedStatement.setInt(2, season.getHotelId());
            preparedStatement.setDate(3, Date.valueOf(season.getStartDate()));
            preparedStatement.setDate(4, Date.valueOf(season.getFinishDate()));
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Season season) {
        String query = "UPDATE public.season SET " +
                "season_name = ? ," +
                "season_hotel_id = ? ," +
                "season_start_date = ? ," +
                "season_finish_date = ? " +
                "WHERE season_id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1,season.getName());
            preparedStatement.setInt(2, season.getHotelId());
            preparedStatement.setDate(3, Date.valueOf(season.getStartDate()));
            preparedStatement.setDate(4, Date.valueOf(season.getFinishDate()));
            preparedStatement.setInt(5,season.getId());
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int seasonId){
        String query = "DELETE FROM public.season WHERE season_id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,seasonId);
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    // This method returns season list according to query.
    public ArrayList<Season> selectByQuery(String query){
        ArrayList<Season> seasonList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                seasonList.add(this.match(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return seasonList;
    }

    // This method returns season list according to hotel id.
    public ArrayList<Season> selectByHotelId(int hotelId){
        String query = "SELECT * FROM public.season WHERE season_hotel_id = " + hotelId;
        return this.selectByQuery(query);
    }
}
