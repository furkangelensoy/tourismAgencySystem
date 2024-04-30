package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;

import java.util.ArrayList;

// This class performs the necessary operations on the view layer using the methods in the DAO layer.
public class PensionManager {
    private final PensionDao pensionDao;

    public PensionManager() {
        this.pensionDao = new PensionDao();
    }

    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    public boolean save(Pension pension) {
        if (pension.getId() != 0) {
            Helper.showMessage("There is an another pension with this id", "Error!");
            return false;
        }
        ArrayList<Pension> pensionList = this.pensionDao.selectByQuery("SELECT * FROM public.pension WHERE pension_hotel_id = " +
                pension.getHotelId() + " AND pension_type = '" + pension.getPension_type() + "'");
        if (!pensionList.isEmpty()) {
            Helper.showMessage("This pension type aldready exists", "Already available!");
            return false;
        }
        return this.pensionDao.save(pension);
    }

    public boolean delete(int pensionId) {
        if (this.getById(pensionId) == null) {
            Helper.showMessage("Pension not found", "Error!");
            return false;
        }
        return this.pensionDao.delete(pensionId);
    }

    public Pension getById(int pensionId) {
        return this.pensionDao.getById(pensionId);
    }

    // This method returns pension list for fill the table in view layer.
    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pensions) {
        ArrayList<Object[]> pensionList = new ArrayList<>();

        for (Pension object : pensions) {
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = object.getId();
            rowObject[i++] = object.getHotel().getName();
            rowObject[i++] = object.getPension_type();
            pensionList.add(rowObject);
        }
        return pensionList;
    }


    public ArrayList<Pension> selectByHotelId(int hotelId){
        return this.pensionDao.selectByHotelId(hotelId);
    }


}
