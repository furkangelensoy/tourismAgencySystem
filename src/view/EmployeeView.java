package view;

import entity.User;

import javax.swing.*;

public class EmployeeView extends Layout{
    private JPanel container;
    private JTabbedPane pnl_employee;
    private JLabel lbl_employee_welcome;
    private JLabel lbl_employee_header;
    private JButton btn_employee_logout;
    private JTable tbl_hotel;

    public EmployeeView(User user) {
        this.add(container);
        this.guiInitialize(1000,800);

        this.lbl_employee_welcome.setText("Welcome " + user.getFirst_name() + " " + user.getLast_name() + " !");
    }
}
