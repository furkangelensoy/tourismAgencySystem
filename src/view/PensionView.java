package view;

import business.HotelManager;
import business.PensionManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;

import javax.swing.*;

public class PensionView extends Layout {
    private JPanel container;
    private JComboBox<ComboItem> cmb_pension_hotel;
    private JButton btn_pension_save;
    private JLabel lbl_pension_header;
    private JLabel lbl_pension_type;
    private JComboBox<String> cmb_pension;
    private JLabel lbl_pension_hotel;
    private JTextField fld_pension_type;
    private final PensionManager pensionManager;
    private final Pension pension;

    public PensionView(Pension pension) {
        this.add(container);
        this.guiInitialize(300, 300);
        this.pension = pension;
        HotelManager hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();

        for (Hotel hotel : hotelManager.findAll()) {
            this.cmb_pension_hotel.addItem(new ComboItem(hotel.getId(), hotel.getName()));
        }


        for (String pensionType : Helper.getPensionTypes()) {
            this.cmb_pension.addItem(pensionType);
        }


        btn_pension_save.addActionListener(e -> {
            if (this.cmb_pension.getSelectedItem() == null || this.cmb_pension_hotel.getSelectedItem() == null) {
                Helper.showMessage("Please fill the all fields", "Missing Data");
            }
            boolean result;

            ComboItem selectedHotel = (ComboItem) this.cmb_pension_hotel.getSelectedItem();
            if (selectedHotel != null) {
                this.pension.setHotelId(selectedHotel.getKey());
            }
            this.pension.setPension_type(this.cmb_pension.getSelectedItem().toString());

            result = this.pensionManager.save(this.pension);

            if (result) {
                Helper.showMessage("The pension has been successfully added", "Success!");
                dispose();
            } else {
                Helper.showMessage("An error occurred while adding pension", "Error!");
            }

        });
    }
}
