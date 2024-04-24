package view;

import javax.swing.*;

public class PensionView extends Layout{
    private JPanel container;
    private JComboBox cmb_pension_type;
    private JButton btn_pension_save;
    private JLabel lbl_pension_header;
    private JLabel lbl_pension_type;

    public PensionView(){
        this.add(container);
        this.guiInitialize(300,300);
    }
}
