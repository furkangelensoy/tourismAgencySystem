package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;

import javax.swing.*;

public class HotelSaveView extends Layout {
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
    private JRadioButton radio_hotel_concierge;
    private JRadioButton radio_hotel_fitness;
    private JRadioButton radio_hotel_room_service;
    private JRadioButton radio_hotel_spa;
    private JButton btn_hotel_save;
    private JPanel pnl_hotel_right;
    private JPanel pnl_hotel_top;
    private JPanel pnl_hotel_left;
    private Hotel hotel;
    private HotelManager hotelManager;

    public HotelSaveView(Hotel hotel) {
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        this.add(container);
        this.guiInitialize(400, 500);

        if (this.hotel != null) {
            this.fld_hotel_name.setText(this.hotel.getName());
            this.fld_hotel_city.setText(this.hotel.getCity());
            this.fld_hotel_state.setText(this.hotel.getState());
            this.fld_hotel_address.setText(this.hotel.getAddress());
            this.fld_hotel_mail.setText(this.hotel.getMail());
            this.fld_hotel_phone.setText(this.hotel.getPhone());
            this.fld_hotel_star.setText(this.hotel.getStar());
            this.radio_hotel_carp_park.setSelected(this.hotel.isCarPark());
            this.radio_hotel_wifi.setSelected(this.hotel.isWifi());
            this.radio_hotel_pool.setSelected(this.hotel.isPool());
            this.radio_hotel_concierge.setSelected(this.hotel.isConcierge());
            this.radio_hotel_fitness.setSelected(this.hotel.isFitness());
            this.radio_hotel_room_service.setSelected(this.hotel.isRoomService());
            this.radio_hotel_spa.setSelected(this.hotel.isSpa());
        }

        btn_hotel_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_hotel_name, this.fld_hotel_city, this.fld_hotel_state,
                    this.fld_hotel_address, this.fld_hotel_mail, this.fld_hotel_phone, this.fld_hotel_star})) {
                Helper.showMessage("Please fill the all fields", "Error!");
            } else {
                boolean result;
                int operation;

                this.hotel.setName(this.fld_hotel_name.getText());
                this.hotel.setCity(this.fld_hotel_city.getText());
                this.hotel.setState(this.fld_hotel_state.getText());
                this.hotel.setAddress(this.fld_hotel_address.getText());
                this.hotel.setMail(this.fld_hotel_mail.getText());
                this.hotel.setPhone(this.fld_hotel_phone.getText());
                this.hotel.setStar(this.fld_hotel_star.getText());
                this.hotel.setCarPark(this.radio_hotel_carp_park.isSelected());
                this.hotel.setWifi(this.radio_hotel_wifi.isSelected());
                this.hotel.setPool(this.radio_hotel_pool.isSelected());
                this.hotel.setConcierge(this.radio_hotel_concierge.isSelected());
                this.hotel.setFitness(this.radio_hotel_fitness.isSelected());
                this.hotel.setRoomService(this.radio_hotel_room_service.isSelected());
                this.hotel.setSpa(this.radio_hotel_spa.isSelected());

                if (this.hotel.getId() != 0) {
                    result = this.hotelManager.update(this.hotel);
                    operation = 1;
                } else {
                    result = this.hotelManager.save(this.hotel);
                    operation = 2;
                }

                if (result) {
                    if (operation == 1) {
                        Helper.showMessage("The hotel has been successfully updated", "Success!");
                        dispose();
                    } else {
                        Helper.showMessage("The hotel has been successfully created.", "Success!");
                        dispose();
                    }
                } else {
                    if (operation == 1) {
                        Helper.showMessage("An error occurred while updating hotel", "Error!");
                    } else {
                        Helper.showMessage("An error occurred while creating hotel", "Error!");
                    }
                }
            }
        });
    }
}
