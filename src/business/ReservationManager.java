package business;

import core.Helper;
import dao.ReservationDao;
import entity.Reservation;

import java.util.ArrayList;

// This class performs the necessary operations on the view layer using the methods in the DAO layer.
public class ReservationManager {
    private final ReservationDao reservationDao;

    public ReservationManager() {
        this.reservationDao = new ReservationDao();
    }

    public Reservation getById(int reservationId){
        return this.reservationDao.getById(reservationId);
    }
    public Reservation getByRoomId(int roomId){
        return this.reservationDao.getByRoomId(roomId);
    }
    public ArrayList<Reservation> findAll(){
        return this.reservationDao.findAll();
    }
    public boolean save(Reservation reservation){
        if (reservation.getId() != 0){
            Helper.showMessage("There is an another reservation with this id","Error!");
            return false;
        }
        return this.reservationDao.save(reservation);
    }

    public boolean update(Reservation reservation){
        if (this.getById(reservation.getId()) == null){
            Helper.showMessage("Reservation not found","Error!");
            return false;
        }
        return this.reservationDao.update(reservation);
    }

    public boolean delete(int reservationId){
        if (this.getById(reservationId) == null){
            Helper.showMessage("Reservation not found","Error!");
            return false;
        }
        return this.reservationDao.delete(reservationId);
    }

    // This method returns reservation list for fill the table in view layer.
    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservations){
        ArrayList<Object[]> reservationList = new ArrayList<>();

        for (Reservation object : reservations){
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = object.getId();
            rowObject[i++] = object.getRoom().getHotel().getName();
            rowObject[i++] = object.getRoom().getSeason().getName();
            rowObject[i++] = object.getRoom().getPension().getPension_type();
            rowObject[i++] = object.getRoom().getType();
            rowObject[i++] = object.getGuestName();
            rowObject[i++] = object.getGuestNationalId();
            rowObject[i++] = object.getGuestMail();
            rowObject[i++] = object.getGuestPhone();
            rowObject[i++] = object.getAdultCount();
            rowObject[i++] = object.getChildCount();
            rowObject[i++] = object.getCheckInDate();
            rowObject[i++] = object.getCheckOutDate();
            rowObject[i++] = object.getTotalPrice();
            reservationList.add(rowObject);
        }
        return reservationList;
    }

}
