package view;

import javax.swing.*;

public class HotelSaveView extends Layout{
    private JPanel container;
    private JLabel lbl_hotel_header;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_state;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_mail;
    private JTextField fld_hotel_phone;
    private JTextField fld_hotel_star;
    private JRadioButton radio_hotel_carp_park;
    private JRadioButton radio_hotel_wifi;
    private JRadioButton radio_hotel_pool;
    private JRadioButton radio_hotel_fitness;
    private JRadioButton radio_hotel_concierge;
    private JRadioButton radio_hotel_spa;
    private JRadioButton radio_hotel_room_service;
    private JButton btn_hotel_save;
    private JPanel pnl_hotel_right;
    private JPanel pnl_hotel_top;
    private JPanel pnl_hotel_left;

    public HotelSaveView(){
        this.add(container);
        this.guiInitialize(1000,700);
    }
}
