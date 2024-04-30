package business;

import core.Helper;
import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;

// This class performs the necessary operations on the view layer using the methods in the DAO layer.
public class SeasonManager {
    private final SeasonDao seasonDao;

    public SeasonManager() {
        this.seasonDao = new SeasonDao();
    }

    public boolean save(Season season) {
        if (season.getId() != 0){
            Helper.showMessage("There is an another user with this id", "Error!");
            return false;
        }
        return this.seasonDao.save(season);
    }

    public boolean update(Season season) {
        if (this.getById(season.getId()) == null){
            Helper.showMessage("Season not found", "Error!");
        }
        return this.seasonDao.update(season);
    }

    public boolean delete(int seasonId) {
        if (this.getById(seasonId) == null){
            Helper.showMessage("Season not found", "Error!");
        }
        return this.seasonDao.delete(seasonId);
    }

    // This method returns season list for fill the table in view layer.
    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasons) {
        ArrayList<Object[]> seasonList = new ArrayList<>();
        for (Season object : seasons) {
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = object.getId();
            rowObject[i++] = object.getHotel().getName();
            rowObject[i++] = object.getName();
            rowObject[i++] = object.getStartDate();
            rowObject[i++] = object.getFinishDate();
            seasonList.add(rowObject);
        }
        return seasonList;
    }

    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    public Season getById(int seasonId) {
        return this.seasonDao.getById(seasonId);
    }
    public ArrayList<Season> selectByHotelId(int hotelId){
        return this.seasonDao.selectByHotelId(hotelId);
    }
}
