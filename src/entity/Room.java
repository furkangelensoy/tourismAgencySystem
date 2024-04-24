package entity;

public class Room {
    private int id;
    private int hotelId;
    private int pensionId;
    private int seasonId;
    private String type;
    private int stock;
    private double adultPrice;
    private double childPrice;
    private int bedCapacity;
    private int areaOfRoom;
    private boolean television;
    private boolean minibar;
    private boolean gameConsole;
    private boolean cashBox;
    private boolean projection;

    public Room() {
    }

    public Room(int hotelId, int pensionId, int seasonId, String type, int stock, double adultPrice,
                double childPrice, int bedCapacity, int areaOfRoom, boolean television, boolean minibar,
                boolean gameConsole, boolean cashBox, boolean projection) {
        this.hotelId = hotelId;
        this.pensionId = pensionId;
        this.seasonId = seasonId;
        this.type = type;
        this.stock = stock;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.bedCapacity = bedCapacity;
        this.areaOfRoom = areaOfRoom;
        this.television = television;
        this.minibar = minibar;
        this.gameConsole = gameConsole;
        this.cashBox = cashBox;
        this.projection = projection;
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

    public int getPensionId() {
        return pensionId;
    }

    public void setPensionId(int pensionId) {
        this.pensionId = pensionId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    public int getBedCapacity() {
        return bedCapacity;
    }

    public void setBedCapacity(int bedCapacity) {
        this.bedCapacity = bedCapacity;
    }

    public int getAreaOfRoom() {
        return areaOfRoom;
    }

    public void setAreaOfRoom(int areaOfRoom) {
        this.areaOfRoom = areaOfRoom;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(boolean gameConsole) {
        this.gameConsole = gameConsole;
    }

    public boolean isCashBox() {
        return cashBox;
    }

    public void setCashBox(boolean cashBox) {
        this.cashBox = cashBox;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }
}