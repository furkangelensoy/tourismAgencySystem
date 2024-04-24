package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    public Hotel getById(int hotelId){
        return this.hotelDao.getById(hotelId);
    }

    public ArrayList<Hotel> findAll(){
        return this.hotelDao.findAll();
    }

    public boolean save(Hotel hotel){
        if (hotel.getId() != 0){
            Helper.showMessage("There is an another hotel with this id","Error!");
        }
        return this.hotelDao.save(hotel);
    }

    public boolean update(Hotel hotel){
        if (this.getById(hotel.getId()) == null){
            Helper.showMessage("Hotel not found","Error!");
        }
        return this.hotelDao.update(hotel);
    }

    public boolean delete(int hotelId){
        if (this.getById(hotelId) == null){
            Helper.showMessage("Hotel not found","Error!");
            return false;
        }
        return this.hotelDao.delete(hotelId);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotels){
        ArrayList<Object[]> hotelList = new ArrayList<>();

        for (Hotel object : hotels){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = object.getId();
            rowObject[i++] = object.getCity();
            rowObject[i++] = object.getState();
            rowObject[i++] = object.getAddress();
            rowObject[i++] = object.getMail();
            rowObject[i++] = object.getPhone();
            rowObject[i++] = object.getStar();
            rowObject[i++] = object.isCarPark();
            rowObject[i++] = object.isWifi();
            rowObject[i++] = object.isPool();
            rowObject[i++] = object.isConcierge();
            rowObject[i++] = object.isFitness();
            rowObject[i++] = object.isRoomService();
            rowObject[i++] = object.isSpa();

            hotelList.add(rowObject);
        }
        return hotelList;
    }
}
