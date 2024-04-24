package view;

import javax.swing.*;

public class ReservationView extends Layout{
    private JPanel container;
    private JPanel pnl_reservation_top;
    private JPanel pnl_reservation_middle;
    private JPanel pnl_reservation_bottom;
    private JTextField fld_reservation_hotel_name;
    private JTextField fld_reservation_city;
    private JTextField fld_reservation_state;
    private JRadioButton radio_reservation_car_park;
    private JRadioButton radio_reservation_concierge;
    private JRadioButton radio_reservation_room_service;
    private JRadioButton radio_reservation_wifi;
    private JRadioButton radio_reservation_pool;
    private JRadioButton radio_reservation_spa;
    private JRadioButton radio_reservation_fitness;
    private JTextField fld_reservation_address;
    private JTextField fld_reservation_star;
    private JTextField fld_reservation_room_type;
    private JTextField fld_reservation_pension_type;
    private JTextField fld_reservation_total_price;
    private JFormattedTextField fld_reservation_checkin_date;
    private JFormattedTextField fld_reservation_checkout_date;
    private JTextField fld_reservation_bed_capacity;
    private JTextField fld_reservation_area;
    private JRadioButton radio_reservation_television;
    private JRadioButton radio_reservation_game_console;
    private JRadioButton radio_reservation_projection;
    private JRadioButton radio_reservation_minibar;
    private JRadioButton radio_reservation_cashbox;
    private JTextField fld_reservation_guest_count;
    private JTextField fld_reservation_guest_name;
    private JTextField fld_reservation_guest_national_id;
    private JButton btn_reservation_create;
    private JTextField fld_reservation_guest_mail;
    private JTextField fld_reservation_guest_phone;
    private JLabel lbl_reservation_header;
    private JLabel lbl_reservation_h1;
    private JLabel lbl_reservation_h2;
    private JLabel lbl_reservation_h3;
    private JLabel lbl_reservation_hotel_name;
    private JLabel lbl_reservation_city;
    private JLabel lbl_reservation_state;
    private JLabel lbl_reservation_address;
    private JLabel lbl_reservation_star;
    private JLabel lbl_reservation_room_type;
    private JLabel lbl_reservation_pension_type;
    private JLabel lbl_reservation_checkin_date;
    private JLabel lbl_reservation_checkout_date;
    private JLabel lbl_reservation_total_price;
    private JLabel lbl_reservation_bed_capacity;
    private JLabel lbl_reservation_area;
    private JLabel lbl_reservation_guest_count;
    private JLabel lbl_reservation_guest_name;
    private JLabel lbl_reservation_guest_national_id;
    private JLabel lbl_reservation_guest_mail;
    private JLabel lbl_reservation_guest_phone;

    public ReservationView(){
        this.add(container);
        this.guiInitialize(700,500);
    }
}
