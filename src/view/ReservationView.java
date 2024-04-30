package view;

import business.ReservationManager;
import core.Helper;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ReservationView extends Layout {
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
    private JTextField fld_reservation_adult_price;
    private JTextField fld_reservation_bed_capacity;
    private JTextField fld_reservation_area;
    private JRadioButton radio_reservation_television;
    private JRadioButton radio_reservation_game_console;
    private JRadioButton radio_reservation_projection;
    private JRadioButton radio_reservation_minibar;
    private JRadioButton radio_reservation_cashbox;
    private JTextField fld_reservation_adult_count;
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
    private JLabel lbl_reservation_season;
    private JLabel lbl_reservation_adult_price;
    private JLabel lbl_reservation_bed_capacity;
    private JLabel lbl_reservation_area;
    private JLabel lbl_reservation_adult_count;
    private JLabel lbl_reservation_guest_name;
    private JLabel lbl_reservation_guest_national_id;
    private JLabel lbl_reservation_guest_mail;
    private JLabel lbl_reservation_guest_phone;
    private JTextField fld_reservation_child_price;
    private JLabel lbl_reservation_child_price;
    private JLabel lbl_reservation_child_count;
    private JTextField fld_reservation_child_count;
    private JTextField fld_reservation_season;
    private JFormattedTextField fld_reservation_checkin_date;
    private JFormattedTextField fld_reservation_checkout_date;
    private JLabel lbl_reservation_checkin_date;
    private JLabel lbl_reservation_checkout_date;
    private JLabel lbl_reservation_total_price;
    private JTextField fld_reservation_total_price;
    private JButton btn_reservation_calculate;
    private final Reservation reservation;
    private final Room room;
    private final ReservationManager reservationManager;

    public ReservationView(Room room, Reservation reservation) {
        this.add(container);
        this.guiInitialize(900, 800);
        keyListenerNumber();

        this.reservationManager = new ReservationManager();
        this.room = room;
        this.reservation = reservation;

        this.fld_reservation_hotel_name.setText(this.room.getHotel().getName());
        this.fld_reservation_city.setText(this.room.getHotel().getCity());
        this.fld_reservation_state.setText(this.room.getHotel().getState());
        this.fld_reservation_address.setText(this.room.getHotel().getAddress());
        this.fld_reservation_star.setText(this.room.getHotel().getStar());
        this.radio_reservation_car_park.setSelected(this.room.getHotel().isCarPark());
        this.radio_reservation_room_service.setSelected(this.room.getHotel().isRoomService());
        this.radio_reservation_pool.setSelected(this.room.getHotel().isPool());
        this.radio_reservation_fitness.setSelected(this.room.getHotel().isFitness());
        this.radio_reservation_concierge.setSelected(this.room.getHotel().isConcierge());
        this.radio_reservation_wifi.setSelected(this.room.getHotel().isWifi());
        this.radio_reservation_spa.setSelected(this.room.getHotel().isSpa());

        this.fld_reservation_room_type.setText(this.room.getType());
        this.fld_reservation_pension_type.setText(this.room.getPension().getPension_type());
        this.fld_reservation_season.setText(this.room.getSeason().getName());
        this.fld_reservation_adult_price.setText(String.valueOf(this.room.getAdultPrice()));
        this.fld_reservation_child_price.setText(String.valueOf(this.room.getChildPrice()));
        this.fld_reservation_bed_capacity.setText(String.valueOf(this.room.getBedCapacity()));
        this.fld_reservation_area.setText(String.valueOf(this.room.getAreaOfRoom()));
        this.radio_reservation_television.setSelected(this.room.isTelevision());
        this.radio_reservation_projection.setSelected(this.room.isProjection());
        this.radio_reservation_cashbox.setSelected(this.room.isCashBox());
        this.radio_reservation_minibar.setSelected(this.room.isMinibar());
        this.radio_reservation_game_console.setSelected(this.room.isGameConsole());

        if (this.reservation.getId() != 0) {
            this.fld_reservation_guest_name.setText(this.reservation.getGuestName());
            this.fld_reservation_guest_mail.setText(this.reservation.getGuestMail());
            this.fld_reservation_guest_national_id.setText(this.reservation.getGuestNationalId());
            this.fld_reservation_guest_phone.setText(this.reservation.getGuestPhone());
            this.fld_reservation_adult_count.setText(String.valueOf(this.reservation.getAdultCount()));
            this.fld_reservation_child_count.setText(String.valueOf(this.reservation.getChildCount()));
            this.fld_reservation_checkin_date.setText(this.reservation.getCheckInDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            this.fld_reservation_checkout_date.setText(this.reservation.getCheckOutDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            this.fld_reservation_total_price.setText(String.valueOf(this.reservation.getTotalPrice()));
        } else {
            this.fld_reservation_checkin_date.setText("01/01/2024");
            this.fld_reservation_checkout_date.setText("01/01/2024");
        }


        btn_reservation_create.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_reservation_guest_name, this.fld_reservation_guest_mail,
                    this.fld_reservation_guest_national_id, this.fld_reservation_guest_phone, this.fld_reservation_adult_count,
                    this.fld_reservation_child_count, this.fld_reservation_checkin_date, this.fld_reservation_checkout_date})) {
                Helper.showMessage("Please fill the all fields", "Missing Data!");
            } else {
                boolean result;
                int operation;

                this.reservation.setRoom(this.room);
                this.reservation.setRoomId(this.room.getId());

                this.reservation.setGuestName(this.fld_reservation_guest_name.getText());
                this.reservation.setGuestMail(this.fld_reservation_guest_mail.getText());
                this.reservation.setGuestNationalId(this.fld_reservation_guest_national_id.getText());
                this.reservation.setGuestPhone(this.fld_reservation_guest_phone.getText());
                this.reservation.setAdultCount(Integer.parseInt(this.fld_reservation_adult_count.getText()));
                this.reservation.setChildCount(Integer.parseInt(this.fld_reservation_child_count.getText()));
                this.reservation.setCheckInDate(LocalDate.parse(this.fld_reservation_checkin_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.reservation.setCheckOutDate(LocalDate.parse(this.fld_reservation_checkout_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.reservation.setTotalPrice(calculateTotalPrice());

                if (this.room.getStock() > 0){
                    if (this.reservation.getId() != 0) {
                        result = this.reservationManager.update(this.reservation);
                        operation = 1;
                    } else {
                        result = this.reservationManager.save(this.reservation);
                        operation = 2;
                    }

                    if (result) {
                        if (operation == 1) {
                            Helper.showMessage("The reservation has been successfully updated", "Success!");
                            dispose();
                        } else {
                            Helper.showMessage("The reservation has been successfully created", "Success!");
                            dispose();
                        }
                    } else {
                        if (operation == 1) {
                            Helper.showMessage("An error occurred while updating reservation", "Error!");
                        } else {
                            Helper.showMessage("An error occurred while creating reservation", "Error!");
                        }
                    }
                }else{
                    Helper.showMessage("You can not create reservation for this room because does not have any stock","Stock Error!");
                }
            }
        });

        btn_reservation_calculate.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_reservation_guest_name, this.fld_reservation_guest_mail,
                    this.fld_reservation_guest_national_id, this.fld_reservation_guest_phone, this.fld_reservation_adult_count,
                    this.fld_reservation_child_count, this.fld_reservation_checkin_date, this.fld_reservation_checkout_date})) {
                Helper.showMessage("Please fill the all fields", "Missing Data!");
            } else{
                this.fld_reservation_total_price.setText(String.valueOf(calculateTotalPrice()));
            }
        });
    }

    // This function calculates total prices according to entered adult count, child count and room's adult per price,
    // child per price and check-in date, check-out date.
    public int calculateTotalPrice(){
        int countOfDay =(int) ChronoUnit.DAYS.between(LocalDate.parse(this.fld_reservation_checkin_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalDate.parse(this.fld_reservation_checkout_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        return ((this.room.getAdultPrice() * Integer.parseInt(this.fld_reservation_adult_count.getText())) +
                (this.room.getChildPrice() * Integer.parseInt(this.fld_reservation_child_count.getText()))) * countOfDay;
    }

    // This method formats the jtextfield to enter the date in the desired type.
    private void createUIComponents() throws ParseException {
        this.fld_reservation_checkin_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_reservation_checkout_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
    }

    // This method only allows the user to enter numbers.
    private void keyListenerNumber() {
        fld_reservation_adult_count.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                keyFormat(e);
            }
        });
        fld_reservation_child_count.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                keyFormat(e);
            }
        });
        fld_reservation_checkin_date.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                keyFormat(e);
            }
        });
        fld_reservation_checkout_date.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                keyFormat(e);
            }
        });
    }

    // This method checks whether the value entered by the user is a number. If it is not a number, it does not take the value
    private void keyFormat(KeyEvent e) {
        char c = e.getKeyChar();

        if (!Character.isDigit(c)) {
            e.consume();
        }
    }
}
