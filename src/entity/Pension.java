package entity;

// This class corresponds to the pension table in the database.
public class Pension {
    private int id;
    private int hotelId;
    private Hotel hotel;
    private String pension_type;

    public Pension() {
    }

    public Pension(int id, int hotelId, Hotel hotel, String pension_type) {
        this.id = id;
        this.hotelId = hotelId;
        this.hotel = hotel;
        this.pension_type = pension_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getPension_type() {
        return pension_type;
    }

    public void setPension_type(String pension_type) {
        this.pension_type = pension_type;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
