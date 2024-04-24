package view;

import business.UserManager;
import core.Helper;
import entity.Role;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {

    private JPanel container;
    private JButton btn_logout;
    private JPanel pnl_top;
    private JLabel lbl_welcome;
    private JPanel pnl_user;
    private JTable tbl_user;
    private JScrollPane scrl_user;
    private JComboBox<Role> cmb_role;
    private JButton btn_clear;
    private JButton btn_search;
    private JPanel pnl_user_search;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private UserManager userManager;
    private User user;
    private JPopupMenu user_popup;
    private Object[] col_user;

    public AdminView(User user) {
        this.add(container);
        this.guiInitialize(800, 500);
        this.user = user;
        this.userManager = new UserManager();

        if (this.user == null) {
            dispose();
        }

        this.lbl_welcome.setText("Welcome " + this.user.getFirst_name() + " " + this.user.getLast_name() + " !");

        this.cmb_role.setModel(new DefaultComboBoxModel<>(Role.values()));

        loadComponent();
        loadUserTable(null);
        loadUserComponent();
        loadUserFilter();
    }

    public void loadComponent() {
        this.btn_logout.addActionListener(e -> {
            if (Helper.confirm("Are you sure want to logout?")){
                dispose();
                LoginView loginView = new LoginView();
            }
        });
    }

    public void loadUserComponent() {
        tableRowSelect(this.tbl_user);
        this.user_popup = new JPopupMenu();

        this.user_popup.add("Add").addActionListener(e -> {
            UserView userView = new UserView(new User());
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });

        this.user_popup.add("Update").addActionListener(e -> {
            int selectedUserId = this.getTableSelectedRow(this.tbl_user, 0);
            UserView userView = new UserView(this.userManager.getById(selectedUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });

        this.user_popup.add("Delete").addActionListener(e -> {
            if (Helper.confirm("Are you sure want to delete selected user?")) {
                int selectedUserId = this.getTableSelectedRow(this.tbl_user, 0);
                if (this.userManager.delete(selectedUserId)){
                    Helper.showMessage("The user has been successfully deleted","Success!");
                    loadUserTable(null);
                }else {
                    Helper.showMessage("An error occurred while deleting user","Error!");
                }
            }
        });

        btn_search.addActionListener(e -> {
            ArrayList<User> userList = this.userManager.searchForUser((Role) this.cmb_role.getSelectedItem());
            ArrayList<Object[]> userRow = this.userManager.getForTable(this.col_user.length,userList);
            loadUserTable(userRow);
        });

        btn_clear.addActionListener(e -> {
            loadUserFilter();
        });



        this.tbl_user.setComponentPopupMenu(this.user_popup);
    }

    public void loadUserTable(ArrayList<Object[]> userList) {
        this.col_user = new Object[]{"ID", "User Name", "Password", "First Name", "Last Name", "Mail", "Role"};
        if (userList == null){
            userList = this.userManager.getForTable(this.col_user.length,this.userManager.findAll());
        }
        createTable(this.tmdl_user, this.tbl_user, col_user, userList);
    }

    public void loadUserFilter(){
        this.cmb_role.setModel(new DefaultComboBoxModel<>(Role.values()));
        this.cmb_role.setSelectedItem(null);
    }
}
