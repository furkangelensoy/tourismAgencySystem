package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class LoginView extends Layout {
    private JPanel container;
    private JLabel lbl_header1;
    private JLabel lbl_header2;
    private JPanel login_top;
    private JPanel login_bottom;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private final UserManager userManager;

    public LoginView() {
        this.add(container);
        guiInitialize(400, 400);
        this.userManager = new UserManager();

        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username, this.fld_password};
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMessage("Please fill the all fields.", "Missing Data!");
            } else {
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(), this.fld_password.getText());
                if (loginUser == null) {
                    Helper.showMessage("Invalid username or password.", "Error!");
                } else {
                    if (loginUser.getRole().toString().equals("ADMIN")) {
                        AdminView adminView = new AdminView(loginUser);
                        dispose();
                    } else {
                        EmployeeView employeeView = new EmployeeView(loginUser);
                        dispose();
                    }
                }
            }
        });
    }
}
