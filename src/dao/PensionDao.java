package dao;

import core.Db;
import entity.Pension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// This class contains queries of the pension table in the database.
// This class contains create,update,delete and other methods.

public class PensionDao {
    private final Connection connection;
    private final HotelDao hotelDao;

    public PensionDao(){
        this.connection = Db.getInstance();
        this.hotelDao = new HotelDao();
    }

    // This method matches data from the database.
    // This method returns pension, firstly creates a empty pension then fills with resultset
    // values according to pension table column values.
    public Pension match(ResultSet resultSet) throws SQLException {
        Pension object = new Pension();
        object.setId(resultSet.getInt("pension_id"));
        object.setHotelId(resultSet.getInt("pension_hotel_id"));
        object.setHotel(this.hotelDao.getById(resultSet.getInt("pension_hotel_id")));
        object.setPension_type(resultSet.getString("pension_type"));
        return object;
    }

    // This method returns all pensions as a list in the database pension table.
    public ArrayList<Pension> findAll(){
        ArrayList<Pension> pensionList = new ArrayList<>();
        String query = "SELECT * FROM public.pension ORDER BY pension_id ASC";
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                pensionList.add(this.match(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pensionList;
    }

    public boolean save(Pension pension){
        String query = "INSERT INTO public.pension" +
                "(" +
                "pension_hotel_id, " +
                "pension_type" +
                ")" +
                " VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,pension.getHotelId());
            preparedStatement.setString(2,pension.getPension_type());
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Pension pension){
        String query = "UPDATE public.pension SET " +
                "pension_hotel_id = ? ," +
                "pension_type = ? " +
                "WHERE pension_id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,pension.getHotelId());
            preparedStatement.setString(2,pension.getPension_type());
            preparedStatement.setInt(3,pension.getId());
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int pensionId){
        String query = "DELETE FROM public.pension WHERE pension_id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,pensionId);
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    // This method returns pension according to pension id.
    public Pension getById(int pensionId){
        String query = "SELECT * FROM public.pension WHERE pension_id = ?";
        Pension object = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,pensionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                object = this.match(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return object;
    }

    // This method returns pension list according to query.
    public ArrayList<Pension> selectByQuery(String query) {
        ArrayList<Pension> pensionList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                pensionList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionList;
    }

    // This method returns pension according to hotel id.
    public ArrayList<Pension> selectByHotelId(int hotelId){
        String query = "SELECT * FROM public.pension WHERE pension_hotel_id = " + hotelId;
        return this.selectByQuery(query);
    }
}
