package view;

import business.*;
import core.ComboItem;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;


public class EmployeeView extends Layout {
    private JPanel container;
    private JTabbedPane pnl_employee;
    private JLabel lbl_employee_welcome;
    private JLabel lbl_employee_header;
    private JButton btn_employee_logout;
    private JTable tbl_hotel;
    private JTable tbl_pension;
    private JPanel pnl_room;
    private JPanel pnl_hotel;
    private JPanel pnl_pension;
    private JPanel pnl_season;
    private JPanel pnl_reservation;
    private JScrollPane scrl_hotel;
    private JButton btn_hotel_delete;
    private JButton btn_hotel_update;
    private JButton btn_hotel_add;
    private JButton btn_season_delete;
    private JButton btn_season_update;
    private JButton btn_season_create;
    private JPanel pnl_hotel_top;
    private JPanel pnl_season_top;
    private JTable tbl_season;
    private JScrollPane scrl_season;
    private JButton btn_pension_delete;
    private JButton btn_pension_create;
    private JPanel pnl_pension_top;
    private JScrollPane scrl_pension;
    private JTable tbl_room;
    private JScrollPane scrl_room;
    private JPanel pnl_room_top;
    private JButton btn_room_delete;
    private JButton btn_room_update;
    private JButton btn_room_create;
    private JTable tbl_reservation;
    private JScrollPane scrl_reservation;
    private JPanel pnl_reservation_top;
    private JButton btn_room_reservation;
    private JPanel pnl_room_reservation;
    private JButton btn_reservation_update;
    private JButton btn_reservation_delete;
    private JPanel pnl_room_search;
    private JComboBox cmb_room_hotel;
    private JComboBox cmb_room_city;
    private JButton btn_room_search;
    private JButton btn_room_clear;
    private JFormattedTextField fld_room_checkin_date;
    private JFormattedTextField fld_room_checkout_date;
    private JButton btn_season_clear;
    private JButton btn_season_search;
    private JComboBox cmb_season_hotel;
    private JButton btn_pension_clear;
    private JButton btn_pension_search;
    private JComboBox cmb_pension_hotel;
    private JButton btn_reservation_clear;
    private JButton btn_reservation_search;
    private JComboBox cmb_reservation_hotel;
    private JPanel pnl_season_search;
    private JPanel pnl_pension_search;
    Object[] col_hotel;
    Object[] col_season;
    Object[] col_pension;
    Object[] col_room;
    Object[] col_reservation;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_pension = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private RoomManager roomManager;
    private ReservationManager reservationManager;

    public EmployeeView(User user) {
        this.add(container);
        this.guiInitialize(1200, 600);

        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();

        this.lbl_employee_welcome.setText("Welcome " + user.getFirst_name() + " " + user.getLast_name() + " !");
        // Contains logout button listener.
        loadComponent();

        // Fills the hotel table and shows it.
        loadHotelTable();
        // Contains for hotel add delete update button listeners.
        loadHotelComponent();

        // Fills the season table and shows it.
        loadSeasonTable(null);
        // Contains for season add delete update button listeners.
        loadSeasonComponent();
        // Filters seasons according to the hotel selected from the jcombobox
        loadSeasonFilter();

        // Fills the pension table and shows it.
        loadPensionTable(null);
        // Contains for pension add delete update button listeners.
        loadPensionComponent();
        // Filters pensions according to the hotel selected from the jcombobox
        loadPensionFilter();

        // Fills the room table and shows it.
        loadRoomTable(null);
        // Contains for room add delete update button and create reservation listeners.
        loadRoomComponent();
        // Filters available rooms according to the check-in date, check-out date, hotel name and city options.
        loadRoomFilter();

        // Fills the reservation table and shows it.
        loadReservationTable(null);
        // Contains for reservation delete update button listeners.
        loadReservationComponent();

    }

    public void loadComponent(){
        btn_employee_logout.addActionListener(e -> {
            if (Helper.confirm("Are you sure want to logout?")){
                dispose();
                LoginView loginView = new LoginView();
            }
        });
    }

    public void loadHotelTable() {
        this.col_hotel = new Object[]{"ID", "Name", "City", "State", "Address", "Mail", "Phone",
                "Star", "Car Park", "Wifi", "Pool", "Concierge", "Fitness", "Room Service", "Spa"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }

    public void loadHotelComponent() {
        tableRowSelect(this.tbl_hotel);
        btn_hotel_add.addActionListener(e -> {
            HotelSaveView hotelSaveView = new HotelSaveView(new Hotel());
            hotelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });
        });

        btn_hotel_update.addActionListener(e -> {
            if (this.tbl_hotel.getSelectedRowCount() != 0) {
                int hotelId = this.getTableSelectedRow(this.tbl_hotel, 0);
                HotelSaveView hotelSaveView = new HotelSaveView(this.hotelManager.getById(hotelId));
                hotelSaveView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadHotelTable();
                    }
                });
            } else {
                Helper.showMessage("Please select a hotel first", "Error!");
            }


        });

        btn_hotel_delete.addActionListener(e -> {
            if (this.tbl_hotel.getSelectedRowCount() != 0) {
                int hotelId = this.getTableSelectedRow(this.tbl_hotel, 0);
                if (Helper.confirm("Are you sure want to delete selected hotel?")) {
                    boolean result = this.hotelManager.delete(hotelId);
                    if (result) {
                        Helper.showMessage("The hotel has been successfully deleted", "Success!");
                        loadHotelTable();
                    } else {
                        Helper.showMessage("An error occurred while deleting the hotel.", "Error!");
                    }
                }
            } else {
                Helper.showMessage("Please select a hotel first", "Error!");
            }

        });
    }

    public void loadSeasonTable(ArrayList<Object[]> seasonList) {
        this.col_season = new Object[]{"ID", "Hotel Name", "Season Name", "Start Date", "Finish Date"};
        if (seasonList == null) {
            seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        }
        createTable(this.tmdl_season, this.tbl_season, col_season, seasonList);
    }

    public void loadSeasonComponent() {
        tableRowSelect(this.tbl_season);
        btn_season_create.addActionListener(e -> {
            SeasonView seasonView = new SeasonView(new Season());
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable(null);
                }
            });
        });
        btn_season_update.addActionListener(e -> {
            if (this.tbl_season.getSelectedRowCount() != 0) {
                int seasonId = this.getTableSelectedRow(this.tbl_season, 0);
                SeasonView seasonView = new SeasonView(this.seasonManager.getById(seasonId));
                seasonView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadSeasonTable(null);
                    }
                });
            } else {
                Helper.showMessage("Please select a season first", "Error!");
            }
        });
        btn_season_delete.addActionListener(e -> {
            if (this.tbl_season.getSelectedRowCount() != 0) {
                int seasonId = this.getTableSelectedRow(this.tbl_season, 0);
                if (Helper.confirm("Are you sure want to delete selected season?")) {
                    boolean result = this.seasonManager.delete(seasonId);
                    if (result) {
                        Helper.showMessage("The season has been successfully deleted", "Success!");
                        loadSeasonTable(null);
                    } else {
                        Helper.showMessage("An error occurred while deleting the season.", "Error!");
                    }
                }
            } else {
                Helper.showMessage("Please select a season first", "Error!");
            }
        });

        btn_season_search.addActionListener(e -> {
            ComboItem selectedHotel = (ComboItem) this.cmb_season_hotel.getSelectedItem();
            if (selectedHotel != null){
                ArrayList<Season> seasonList = this.seasonManager.selectByHotelId(selectedHotel.getKey());
                ArrayList<Object[]> seasonListBySearch = this.seasonManager.getForTable(this.col_season.length, seasonList);
                loadSeasonTable(seasonListBySearch);
            }else{
                loadSeasonTable(null);
            }
        });
        btn_season_clear.addActionListener(e -> {
            loadSeasonFilter();
        });
    }

    public void loadSeasonFilter() {
        this.cmb_season_hotel.removeAllItems();

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_season_hotel.addItem(new ComboItem(hotel.getId(), hotel.getName()));
        }
        this.cmb_season_hotel.setSelectedItem(null);
    }

    public void loadPensionTable(ArrayList<Object[]> pensionList) {
        this.col_pension = new Object[]{"ID", "Hotel Name", "Pension Type"};
        if (pensionList == null) {
            pensionList = this.pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());
        }
        createTable(this.tmdl_pension, this.tbl_pension, col_pension, pensionList);
    }

    public void loadPensionComponent() {
        tableRowSelect(this.tbl_pension);
        btn_pension_create.addActionListener(e -> {
            PensionView pensionView = new PensionView(new Pension());
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable(null);
                }
            });
        });

        btn_pension_delete.addActionListener(e -> {
            if (this.tbl_pension.getSelectedRowCount() != 0) {
                int pensionId = this.getTableSelectedRow(this.tbl_pension, 0);
                if (Helper.confirm("Are you want to delete selected pension?")) {
                    boolean result = this.pensionManager.delete(pensionId);
                    if (result) {
                        Helper.showMessage("The pension has been successfully delelted", "Success!");
                        loadPensionTable(null);
                    } else {
                        Helper.showMessage("An error occurred while deleting the pension.", "Error!");
                    }
                }
            } else {
                Helper.showMessage("Please select a pension first", "Error!");
            }
        });

        btn_pension_search.addActionListener(e -> {
            ComboItem selectedHotel = (ComboItem) this.cmb_pension_hotel.getSelectedItem();
            if (selectedHotel != null){
                ArrayList<Pension> pensionList = this.pensionManager.selectByHotelId(selectedHotel.getKey());

                ArrayList<Object[]> pensionListBySearch = this.pensionManager.getForTable(this.col_pension.length, pensionList);
                loadPensionTable(pensionListBySearch);
            }else{
                loadPensionTable(null);
            }
        });
        btn_pension_clear.addActionListener(e -> {
            loadPensionFilter();
        });
    }

    public void loadPensionFilter() {
        this.cmb_pension_hotel.removeAllItems();

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_pension_hotel.addItem(new ComboItem(hotel.getId(), hotel.getName()));
        }
        this.cmb_pension_hotel.setSelectedItem(null);
    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {
        this.col_room = new Object[]{"ID", "Hotel Name", "Pension", "Season", "Room Type", "Bed Capacity"
                , "Area", "Television", "Minibar", "Game Console", "Cash Box", "Projection", "Adult Per Price",
                "Child Per Price", "Stock"};
        if (roomList == null) {
            roomList = this.roomManager.getForTable(this.col_room.length, this.roomManager.findAll());
        }
        createTable(this.tmdl_room, this.tbl_room, col_room, roomList);
    }

    public void loadRoomComponent() {
        tableRowSelect(this.tbl_room);

        btn_room_create.addActionListener(e -> {
            RoomSaveView roomSaveView = new RoomSaveView(new Room());
            roomSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
        });
        btn_room_update.addActionListener(e -> {
            if (this.tbl_room.getSelectedRowCount() != 0) {
                int roomId = this.getTableSelectedRow(this.tbl_room, 0);
                RoomSaveView roomSaveView = new RoomSaveView(this.roomManager.getById(roomId));
                roomSaveView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                    }
                });
            } else {
                Helper.showMessage("Please select a room first", "Error!");
            }
        });
        btn_room_delete.addActionListener(e -> {
            if (this.tbl_room.getSelectedRowCount() != 0) {
                int roomId = this.getTableSelectedRow(this.tbl_room, 0);
                if (Helper.confirm("Are you sure want to delete selected room?")) {
                    boolean result = this.roomManager.delete(roomId);
                    if (result) {
                        Helper.showMessage("The room has been successfully deleted", "Success!");
                        loadRoomTable(null);
                    } else {
                        Helper.showMessage("An error occurred while deleting the room", "Error!");
                    }
                }
            } else {
                Helper.showMessage("Please select a room first", "Error!");
            }
        });

        btn_room_reservation.addActionListener(e -> {
            if (this.tbl_room.getSelectedRowCount() != 0) {
                int roomId = this.getTableSelectedRow(this.tbl_room, 0);
                ReservationView reservationView = new ReservationView(this.roomManager.getById(roomId),new Reservation());
                reservationView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                        loadReservationTable(null);
                    }
                });
            } else {
                Helper.showMessage("Please select a room first", "Error!");
            }
        });

        btn_room_search.addActionListener(e -> {
            String hotelName = null;
            String city = null;
            ComboItem selectedHotelName = (ComboItem) this.cmb_room_hotel.getSelectedItem();
            ComboItem selectedCity = (ComboItem) this.cmb_room_city.getSelectedItem();
            if (this.cmb_room_hotel.getSelectedItem() != null) {
                hotelName = selectedHotelName.getValue();
            }
            if (this.cmb_room_city.getSelectedItem() != null) {
                city = selectedCity.getValue();

            }


            ArrayList<Room> roomList = this.roomManager.searchForRoom(
                    hotelName,
                    city,
                    this.fld_room_checkin_date.getText(),
                    this.fld_room_checkout_date.getText()

            );

            ArrayList<Object[]> availableRoom = this.roomManager.getForTable(this.col_room.length, roomList);
            loadRoomTable(availableRoom);
        });
        btn_room_clear.addActionListener(e -> {
            loadRoomFilter();
        });
    }

    public void loadRoomFilter() {
        this.cmb_room_hotel.removeAllItems();
        this.cmb_room_city.removeAllItems();
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_room_hotel.addItem(new ComboItem(hotel.getId(), hotel.getName()));
            this.cmb_room_city.addItem(new ComboItem(hotel.getId(), hotel.getCity()));
        }
        this.cmb_room_city.setSelectedItem(null);
        this.cmb_room_hotel.setSelectedItem(null);
    }

    public void loadReservationTable(ArrayList<Object[]> reservationList) {
        this.col_reservation = new Object[]{"ID", "Hotel Name", "Season", "Pension Type", "Room Type", "Guest Name"
                , "Guest National Id", "Guest Mail", "Guest Phone", "Adult Count", "Child Count", "Checkin Date", "Checkout Date",
                "Total Price"};
        if (reservationList == null) {
            reservationList = this.reservationManager.getForTable(col_reservation.length, this.reservationManager.findAll());
        }
        createTable(this.tmdl_reservation, this.tbl_reservation, col_reservation, reservationList);
    }

    public void loadReservationComponent() {
        tableRowSelect(this.tbl_reservation);
        btn_reservation_update.addActionListener(e -> {
            if (this.tbl_reservation.getSelectedRowCount() != 0) {
                int reservationId = this.getTableSelectedRow(this.tbl_reservation, 0);
                Reservation reservation = this.reservationManager.getById(reservationId);
                ReservationView reservationView = new ReservationView(reservation.getRoom(),reservation);
                reservationView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadReservationTable(null);
                    }
                });
            } else {
                Helper.showMessage("Please select a reservation first", "Error!");
            }
        });
        btn_reservation_delete.addActionListener(e -> {
            if (this.tbl_reservation.getSelectedRowCount() != 0) {
                int reservationId = this.getTableSelectedRow(this.tbl_reservation, 0);
                if (Helper.confirm("Are you sure want to delete selected room?")) {
                    boolean result = this.reservationManager.delete(reservationId);
                    if (result) {
                        Helper.showMessage("The reservation has been successfully deleted", "Success!");
                        loadReservationTable(null);
                    } else {
                        Helper.showMessage("An error occurred while deleting the reservation", "Error!");
                    }
                }
            } else {
                Helper.showMessage("Please select a reservation first", "Error!");
            }
        });

    }

    // This method formats the jtextfield to enter the date in the desired type.
    private void createUIComponents() throws ParseException {
        this.fld_room_checkin_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_room_checkin_date.setText("01/11/2024");
        this.fld_room_checkout_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_room_checkout_date.setText("01/12/2024");
    }
}
