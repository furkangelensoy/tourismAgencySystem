package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RoomSaveView extends Layout {
    private JPanel container;
    private JComboBox<ComboItem> cmb_room_hotel;
    private JComboBox<ComboItem> cmb_room_pension;
    private JComboBox<ComboItem> cmb_room_season;
    private JComboBox<String> cmb_room_room_type;
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
    private JRadioButton radio_room_cashbox;
    private JLabel lbl_room_cashbox;
    private JButton btn_confirm_hotel;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private Room room;

    public RoomSaveView(Room room) {
        this.add(container);
        this.guiInitialize(700, 400);
        keyListenerNumber();

        this.room = room;
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();


        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_room_hotel.addItem(new ComboItem(hotel.getId(), hotel.getName()));
        }

        for (String roomTypes : Helper.getRoomTypes()) {
            this.cmb_room_room_type.addItem(roomTypes);
        }

        btn_confirm_hotel.addActionListener(e -> {
            if (this.cmb_room_hotel.getSelectedItem() != null) {
                this.cmb_room_season.removeAllItems();
                this.cmb_room_pension.removeAllItems();

                ComboItem selectedHotel = (ComboItem) this.cmb_room_hotel.getSelectedItem();
                for (Pension pension : this.pensionManager.selectByHotelId(selectedHotel.getKey())) {
                    this.cmb_room_pension.addItem(new ComboItem(pension.getId(), pension.getPension_type()));
                }

                for (Season season : this.seasonManager.selectByHotelId(selectedHotel.getKey())) {
                    this.cmb_room_season.addItem(new ComboItem(season.getId(), season.getName()));
                }
            } else {
                Helper.showMessage("Select a hotel first", "Missing Data!");
            }
        });


        if (this.room.getId() != 0) {
            ComboItem defaultHotel = new ComboItem(this.room.getHotelId(), this.room.getHotel().getName());
            this.cmb_room_hotel.getModel().setSelectedItem(defaultHotel);

            ComboItem defaultPension = new ComboItem(this.room.getPensionId(), this.room.getPension().getPension_type());
            this.cmb_room_pension.getModel().setSelectedItem(defaultPension);

            ComboItem defaultSeason = new ComboItem(this.room.getSeasonId(), this.room.getSeason().getName());
            this.cmb_room_season.getModel().setSelectedItem(defaultSeason);

            this.cmb_room_room_type.getModel().setSelectedItem(this.room.getType());

            this.fld_room_area.setText(String.valueOf(this.room.getAreaOfRoom()));
            this.fld_room_stock.setText(String.valueOf(this.room.getStock()));
            this.fld_room_adult_price.setText(String.valueOf(this.room.getAdultPrice()));
            this.fld_room_child_price.setText(String.valueOf(this.room.getChildPrice()));
            this.fld_room_bed_capacity.setText(String.valueOf(this.room.getBedCapacity()));

            this.radio_room_minibar.setSelected(this.room.isMinibar());
            this.radio_room_cashbox.setSelected(this.room.isCashBox());
            this.radio_room_projection.setSelected(this.room.isProjection());
            this.radio_room_television.setSelected(this.room.isTelevision());
            this.radio_room_game_console.setSelected(this.room.isGameConsole());
        }


        btn_room_save_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_room_area, this.fld_room_stock,
                    this.fld_room_child_price, this.fld_room_adult_price, this.fld_room_bed_capacity})
                    || this.cmb_room_pension.getSelectedItem() == null
                    || this.cmb_room_season.getSelectedItem() == null) {
                Helper.showMessage("Please fill the all fields", "Missing Data!");
            } else {
                boolean result;
                int operation;

                ComboItem selectedHotel = (ComboItem) this.cmb_room_hotel.getSelectedItem();
                this.room.setHotelId(selectedHotel.getKey());
                this.room.setHotel(this.hotelManager.getById(selectedHotel.getKey()));

                ComboItem selectedPension = (ComboItem) this.cmb_room_pension.getSelectedItem();
                this.room.setPensionId(selectedPension.getKey());
                this.room.setPension(this.pensionManager.getById(selectedPension.getKey()));

                ComboItem selectedSeason = (ComboItem) this.cmb_room_season.getSelectedItem();
                this.room.setSeasonId(selectedSeason.getKey());
                this.room.setSeason(this.seasonManager.getById(selectedSeason.getKey()));

                this.room.setType(this.cmb_room_room_type.getSelectedItem().toString());

                this.room.setStock(Integer.parseInt(this.fld_room_stock.getText()));
                this.room.setAreaOfRoom(Integer.parseInt(this.fld_room_area.getText()));
                this.room.setAdultPrice(Integer.parseInt(this.fld_room_adult_price.getText()));
                this.room.setChildPrice(Integer.parseInt(this.fld_room_child_price.getText()));
                this.room.setBedCapacity(Integer.parseInt(this.fld_room_bed_capacity.getText()));

                this.room.setMinibar(this.radio_room_minibar.isSelected());
                this.room.setProjection(this.radio_room_projection.isSelected());
                this.room.setGameConsole(this.radio_room_game_console.isSelected());
                this.room.setTelevision(this.radio_room_television.isSelected());
                this.room.setCashBox(this.radio_room_cashbox.isSelected());

                if (this.room.getId() != 0) {
                    result = this.roomManager.update(this.room);
                    operation = 1;
                } else {
                    result = this.roomManager.save(this.room);
                    operation = 2;
                }

                if (result) {
                    if (operation == 1) {
                        Helper.showMessage("The room has been successfully updated", "Success!");
                        dispose();
                    } else {
                        Helper.showMessage("The room has been successfully created.", "Success!");
                        dispose();
                    }
                } else {
                    if (operation == 1) {
                        Helper.showMessage("An error occurred while updating room", "Error!");
                    } else {
                        Helper.showMessage("An error occurred while creating room", "Error!");
                    }
                }
            }
        });
    }

    // This method only allows the user to enter numbers.
    private void keyListenerNumber(){
        fld_room_stock.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                keyFormat(e);
            }
        });

        fld_room_adult_price.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                keyFormat(e);
            }
        });
        fld_room_child_price.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                keyFormat(e);
            }
        });
        fld_room_bed_capacity.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                keyFormat(e);
            }
        });
        fld_room_area.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                keyFormat(e);
            }
        });
    }

    // This method checks whether the value entered by the user is a number. If it is not a number, it does not take the value
    private void keyFormat(KeyEvent e){
        char c = e.getKeyChar();

        if (!Character.isDigit(c)){
            e.consume();
        }
    }

}
