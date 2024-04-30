package entity;

import java.time.LocalDate;

// This class corresponds to the reservation table in the database.
public class Reservation {
    private int id;
    private int roomId;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int totalPrice;
    private int childCount;
    private int adultCount;
    private String guestName;
    private String guestNationalId;
    private String guestMail;
    private String guestPhone;

    public Reservation() {

    }

    public Reservation(int id, int roomId, Room room, LocalDate checkInDate, LocalDate checkOutDate, int totalPrice,
                       int childCount, int adultCount, String guestName,
                       String guestNationalId, String guestMail, String guestPhone) {
        this.id = id;
        this.roomId = roomId;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.childCount = childCount;
        this.adultCount = adultCount;
        this.guestName = guestName;
        this.guestNationalId = guestNationalId;
        this.guestMail = guestMail;
        this.guestPhone = guestPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestNationalId() {
        return guestNationalId;
    }

    public void setGuestNationalId(String guestNationalId) {
        this.guestNationalId = guestNationalId;
    }

    public String getGuestMail() {
        return guestMail;
    }

    public void setGuestMail(String guestMail) {
        this.guestMail = guestMail;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }
}
