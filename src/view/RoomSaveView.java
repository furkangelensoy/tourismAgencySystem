package view;

import javax.swing.*;

public class RoomSaveView extends Layout{
    private JPanel container;
    private JComboBox cmb_room_hotel;
    private JComboBox cmb_room_pension;
    private JComboBox cmb_room_season;
    private JComboBox cmb_room_room_type;
    private JTextField fld_room_stock;
    private JTextField fld_room_adult_price;
    private JTextField fld_room_child_price;
    private JTextField fld_room_bed_capacity;
    private JTextField fld_room_area;
    private JRadioButton radio_room_television;
    private JRadioButton radio_room_minibar;
    private JRadioButton radio_room_game_console;
    private JRadioButton radio_room_projection;
    private JButton btn_room_save_save;
    private JPanel pnl_room_left;
    private JPanel pnl_room_top;
    private JPanel pnl_room_save_right;
    private JLabel lbl_room_header;
    private JLabel lbl_room_hotel;
    private JLabel lbl_room_pension;
    private JLabel lbl_room_season;
    private JLabel lbl_room_room_type;
    private JLabel lbl_room_stock;
    private JLabel lbl_room_adult_price;
    private JLabel lbl_room_child_price;
    private JLabel lbl_room_bed_capacity;
    private JLabel lbl_room_area;
    private JLabel lbl_room_television;
    private JLabel lbl_room_minibar;
    private JLabel lbl_room_game_console;
    private JLabel lbl_room_projection;

    public RoomSaveView(){
        this.add(container);
        this.guiInitialize(500,500);
    }
}
