package dao;

import core.Db;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

// This class contains queries of the reservation table in the database.
// This class contains create,update,delete and other methods.

public class ReservationDao {
    private final Connection connection;
    private final RoomDao roomDao;

    public ReservationDao() {
        this.connection = Db.getInstance();
        this.roomDao = new RoomDao();
    }

    // This method matches data from the database.
    // This method returns reservation, firstly creates a empty reservation then fills with resultset
    // values according to reservation table column values
    public Reservation match(ResultSet resultSet) throws SQLException {
        Reservation object = new Reservation();
        object.setId(resultSet.getInt("reservation_id"));
        object.setRoomId(resultSet.getInt("reservation_room_id"));
        object.setRoom(this.roomDao.getById(resultSet.getInt("reservation_room_id")));
        object.setCheckInDate(LocalDate.parse(resultSet.getString("reservation_checkin_date")));
        object.setCheckOutDate(LocalDate.parse(resultSet.getString("reservation_checkout_date")));
        object.setTotalPrice(resultSet.getInt("reservation_total_price"));
        object.setChildCount(resultSet.getInt("reservation_guest_child_count"));
        object.setAdultCount(resultSet.getInt("reservation_guest_adult_count"));
        object.setGuestName(resultSet.getString("reservation_guest_name"));
        object.setGuestNationalId(resultSet.getString("reservation_guest_national_id"));
        object.setGuestMail(resultSet.getString("reservation_guest_mail"));
        object.setGuestPhone(resultSet.getString("reservation_guest_phone"));
        return object;
    }

    // This method returns all reservations as a list in the database reservation table.
    public ArrayList<Reservation> findAll(){
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM public.reservation ORDER BY reservation_id ASC";
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                reservationList.add(this.match(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return reservationList;
    }

    public boolean save (Reservation reservation){
        String query = "INSERT INTO public.reservation " +
                "(" +
                "reservation_room_id, " +
                "reservation_checkin_date, " +
                "reservation_checkout_date, " +
                "reservation_total_price, " +
                "reservation_guest_child_count, " +
                "reservation_guest_adult_count, " +
                "reservation_guest_name, " +
                "reservation_guest_national_id, " +
                "reservation_guest_mail, " +
                "reservation_guest_phone" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,reservation.getRoomId());
            preparedStatement.setDate(2,Date.valueOf(reservation.getCheckInDate()));
            preparedStatement.setDate(3,Date.valueOf(reservation.getCheckOutDate()));
            preparedStatement.setInt(4,reservation.getTotalPrice());
            preparedStatement.setInt(5,reservation.getChildCount());
            preparedStatement.setInt(6,reservation.getAdultCount());
            preparedStatement.setString(7,reservation.getGuestName());
            preparedStatement.setString(8,reservation.getGuestNationalId());
            preparedStatement.setString(9,reservation.getGuestMail());
            preparedStatement.setString(10,reservation.getGuestPhone());
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Reservation reservation){
        String query = "Update public.reservation SET " +
                "reservation_room_id = ? ," +
                "reservation_checkin_date = ? ," +
                "reservation_checkout_date = ? ," +
                "reservation_total_price = ? ," +
                "reservation_guest_child_count = ? ," +
                "reservation_guest_adult_count = ? ," +
                "reservation_guest_name = ? ," +
                "reservation_guest_national_id = ? ," +
                "reservation_guest_mail = ? ," +
                "reservation_guest_phone = ? " +
                "WHERE reservation_id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,reservation.getRoomId());
            preparedStatement.setDate(2,Date.valueOf(reservation.getCheckInDate()));
            preparedStatement.setDate(3,Date.valueOf(reservation.getCheckOutDate()));
            preparedStatement.setInt(4,reservation.getTotalPrice());
            preparedStatement.setInt(5,reservation.getChildCount());
            preparedStatement.setInt(6,reservation.getAdultCount());
            preparedStatement.setString(7,reservation.getGuestName());
            preparedStatement.setString(8,reservation.getGuestNationalId());
            preparedStatement.setString(9,reservation.getGuestMail());
            preparedStatement.setString(10,reservation.getGuestPhone());
            preparedStatement.setInt(11,reservation.getId());
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int reservationId){
        String query = "DELETE FROM public.reservation WHERE reservation_id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,reservationId);
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    // This method returns reservation according to reservation id.
    public Reservation getById(int reservationId){
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
        Reservation object = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,reservationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                object = this.match(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return object;
    }

    // This method returns reservation list according to query.
    public ArrayList<Reservation> selectByQuery(String query){
        ArrayList<Reservation> reservationList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                reservationList.add(this.match(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return reservationList;
    }

    // This method returns reservation according to room id.
    public Reservation getByRoomId(int roomId){
        String query = "SELECT * FROM public.reservation WHERE reservation_room_id = " + roomId;
        Reservation object = null;
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            if (resultSet.next()){
                object = this.match(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return object;
    }
}
