package view;

import business.HotelManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonView extends Layout {
    private JPanel container;
    private JLabel lbl_season_header;
    private JFormattedTextField fld_season_finish_date;
    private JFormattedTextField fld_season_start_date;
    private JLabel lbl_season_start_date;
    private JLabel lbl_season_finish_date;
    private JButton btn_season_save;
    private JComboBox<ComboItem> cmb_season_hotel;
    private JLabel lbl_season_hotel;
    private JTextField fld_season_name;
    private JLabel lbl_season_name;
    Season season;
    HotelManager hotelManager;
    SeasonManager seasonManager;

    public SeasonView(Season season) {
        this.add(container);
        this.guiInitialize(500, 300);
        this.season = season;
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_season_hotel.addItem(new ComboItem(hotel.getId(), hotel.getName()));
        }

        if (this.season.getId() != 0) {
            this.fld_season_name.setText(season.getName());
            this.fld_season_start_date.setText(this.season.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            this.fld_season_finish_date.setText(this.season.getFinishDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ComboItem defaultHotel = new ComboItem(this.season.getHotelId(), this.season.getHotel().getName());
            this.cmb_season_hotel.getModel().setSelectedItem(defaultHotel);
        }else{
            this.fld_season_start_date.setText("01/01/2024");
            this.fld_season_finish_date.setText("01/05/2024");
        }
        btn_season_save.addActionListener(e -> {
            if (Helper.isFieldEmpty(this.fld_season_name)) {
                Helper.showMessage("Please fill the all fields", "Missing Data!");
            } else {
                boolean result;
                int operation;

                ComboItem selectedHotel = (ComboItem) this.cmb_season_hotel.getSelectedItem();
                this.season.setHotelId(selectedHotel.getKey());
                this.season.setName(this.fld_season_name.getText());
                this.season.setStartDate(LocalDate.parse(this.fld_season_start_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.season.setFinishDate(LocalDate.parse(this.fld_season_finish_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                if (this.season.getId() != 0) {
                    result = this.seasonManager.update(this.season);
                    operation = 1;
                } else {
                    result = this.seasonManager.save(this.season);
                    operation = 2;
                }

                if (result) {
                    if (operation == 1) {
                        Helper.showMessage("The season has been successfully updated", "Success!");
                        dispose();
                    } else {
                        Helper.showMessage("The season has been successfully added", "Success!");
                        dispose();
                    }
                } else {
                    if (operation == 1) {
                        Helper.showMessage("An error occurred while updating season", "Error!");
                    } else {
                        Helper.showMessage("An error occurred while creating season", "Error!");
                    }
                }
            }
        });

    }

    // This method formats the jtextfield to enter the date in the desired type.
    private void createUIComponents() throws ParseException {
        this.fld_season_start_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_season_finish_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
    }
}
