package view;

import business.UserManager;
import core.Helper;
import entity.Role;
import entity.User;

import javax.swing.*;

public class UserView extends Layout{
    private JPanel container;
    private JTextField fld_username;
    private JTextField fld_password;
    private JTextField fld_first_name;
    private JTextField fld_last_name;
    private JComboBox<Role> cmb_role;
    private JButton btn_save;
    private JLabel lbl_header;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private JLabel lbl_first_name;
    private JLabel lbl_last_name;
    private JLabel lbl_role;
    private JTextField fld_mail;
    private JLabel lbl_mail;
    private User user;
    private UserManager userManager;

    public UserView(User user){
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialize(300,300);
        this.user = user;

        this.cmb_role.setModel(new DefaultComboBoxModel<>(Role.values()));

        if (user != null){
            this.fld_first_name.setText(user.getFirst_name());
            this.fld_last_name.setText(user.getLast_name());
            this.fld_mail.setText(user.getMail());
            this.fld_username.setText(user.getUserName());
            this.fld_password.setText(user.getPassWord());
            this.cmb_role.getModel().setSelectedItem(user.getRole());
        }


        btn_save.addActionListener(e -> {
            JTextField[] checkList = {this.fld_username,this.fld_password,this.fld_first_name,this.fld_last_name,this.fld_mail};
            if (Helper.isFieldListEmpty(checkList) || this.cmb_role.getModel().getSelectedItem() == null){
                Helper.showMessage("Please fill the all fields","Missing Data!");
            }else{
                boolean result;
                int operation;

                this.user.setUserName(this.fld_username.getText());
                this.user.setPassWord(this.fld_password.getText());
                this.user.setFirst_name(this.fld_first_name.getText());
                this.user.setLast_name(this.fld_last_name.getText());
                this.user.setMail(this.fld_mail.getText());
                this.user.setRole((Role) this.cmb_role.getSelectedItem());

                if (this.user.getId() != 0){
                    result = this.userManager.update(this.user);
                    operation = 1;
                }else{
                    result = this.userManager.save(this.user);
                    operation = 2;
                }

                if (result){
                    if (operation == 1){
                        Helper.showMessage("User has been updated successfully","Success!");
                        dispose();
                    }else {
                        Helper.showMessage("User has been updated successfully","Success!");
                        dispose();
                    }
                }else {
                    Helper.showMessage("An error occurred while updating/adding user","Error!");
                }
            }
        });


    }
}
