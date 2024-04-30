package entity;

import java.time.LocalDate;

// This class corresponds to the season table in the database.
public class Season {
    private int id;
    private String name;
    private int hotelId;
    private Hotel hotel;
    private LocalDate startDate;
    private LocalDate finishDate;

    public Season() {
    }

    public Season(int id, String name, int hotelId, Hotel hotel, LocalDate startDate, LocalDate finishDate) {
        this.id = id;
        this.name = name;
        this.hotelId = hotelId;
        this.hotel = hotel;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
}
