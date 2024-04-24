package view;

import javax.swing.*;

public class SeasonView extends Layout{
    private JPanel container;
    private JLabel lbl_season_header;
    private JFormattedTextField fld_season_finish_date;
    private JFormattedTextField fld_season_start_date;
    private JLabel lbl_season_start_date;
    private JLabel lbl_season_finish_date;
    private JButton btn_season_save;
    private JComboBox cmb_season_hotel;
    private JLabel lbl_season_hotel;

    public SeasonView() {
        this.add(container);
        this.guiInitialize(300,300);
    }
}
