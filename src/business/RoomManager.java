package business;

import core.Helper;
import dao.ReservationDao;
import dao.RoomDao;
import entity.Reservation;
import entity.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// This class performs the necessary operations on the view layer using the methods in the DAO layer.
public class RoomManager {
    private final RoomDao roomDao;
    private final ReservationDao reservationDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
        this.reservationDao = new ReservationDao();
    }

    public Room getById(int roomId) {
        return this.roomDao.getById(roomId);
    }

    public boolean save(Room room) {
        if (room.getId() != 0) {
            Helper.showMessage("There is an another room with this id", "Error!");
            return false;
        }
        return this.roomDao.save(room);
    }

    public boolean update(Room room) {
        if (this.getById(room.getId()) == null) {
            Helper.showMessage("Room not found", "Error!");
            return false;
        }
        return this.roomDao.update(room);
    }

    public boolean delete(int roomId) {
        if (this.getById(roomId) == null) {
            Helper.showMessage("Room not found", "Error!");
        }
        return this.roomDao.delete(roomId);
    }

    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    // This method returns room list for fill the table in view layer.
    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomList = new ArrayList<>();

        for (Room object : rooms) {
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = object.getId();
            rowObject[i++] = object.getHotel().getName();
            rowObject[i++] = object.getPension().getPension_type();
            rowObject[i++] = object.getSeason().getName();
            rowObject[i++] = object.getType();
            rowObject[i++] = object.getBedCapacity();
            rowObject[i++] = object.getAreaOfRoom();
            rowObject[i++] = object.isTelevision();
            rowObject[i++] = object.isMinibar();
            rowObject[i++] = object.isGameConsole();
            rowObject[i++] = object.isCashBox();
            rowObject[i++] = object.isProjection();
            rowObject[i++] = object.getAdultPrice();
            rowObject[i++] = object.getChildPrice();
            rowObject[i++] = object.getStock();

            roomList.add(rowObject);
        }
        return roomList;
    }

    // This method performs a filtering query on the database based on the parameters entered and return a room list.
    public ArrayList<Room> searchForRoom(String hotelName, String hotelCity, String checkinDate, String checkoutDate) {
        String query = "SELECT * FROM public.room as r LEFT JOIN public.hotel as h";

        ArrayList<String> where = new ArrayList<>();
        ArrayList<String> joinWhere = new ArrayList<>();
        ArrayList<String> reservationOrWhere = new ArrayList<>();

        joinWhere.add("r.room_hotel_id = h.hotel_id");

        checkinDate = LocalDate.parse(checkinDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        checkoutDate = LocalDate.parse(checkoutDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        if (hotelName != null) where.add("h.hotel_name = '" + hotelName + "'");
        if (hotelCity != null) where.add("h.hotel_city = '" + hotelCity + "'");

        String whereStr = String.join(" AND ", where);
        String joinStr = String.join(" AND ", joinWhere);

        if (!joinStr.isEmpty()) {
            query += " ON " + joinStr;
        }
        if (!whereStr.isEmpty()) {
            query += " WHERE " + whereStr;
        }

        ArrayList<Room> searchedRoomList = this.roomDao.selectByQuery(query);

        reservationOrWhere.add("('" + checkinDate + "' BETWEEN reservation_checkin_date AND reservation_checkout_date)");
        reservationOrWhere.add("('" + checkoutDate + "' BETWEEN reservation_checkout_date AND reservation_checkin_date)");
        reservationOrWhere.add("(reservation_checkin_date BETWEEN '" + checkinDate + "' AND '" + checkoutDate + "')");
        reservationOrWhere.add("(reservation_checkout_date BETWEEN '" + checkinDate + "' AND '" + checkoutDate + "')");

        String reservationWhereStr = String.join(" OR ", reservationOrWhere);
        String reservationQuery ="SELECT * FROM public.reservation WHERE " + reservationWhereStr;

        ArrayList<Reservation> reservations = this.reservationDao.selectByQuery(reservationQuery);
        ArrayList<Integer> busyRoomId = new ArrayList<>();
        for (Reservation reservation : reservations){
            busyRoomId.add(reservation.getRoomId());
        }

        // This for loop, browses the booked rooms and if the room IDs match, it reduces the available room stock by one.
        for (Room room : searchedRoomList){
            for (Integer busyRoom : busyRoomId){
                if (room.getId() == busyRoom){
                    room.setStock(room.getStock() - 1);
                }
            }
        }

        searchedRoomList.removeIf(room -> room.getStock() == 0);

        return searchedRoomList;
    }


}
