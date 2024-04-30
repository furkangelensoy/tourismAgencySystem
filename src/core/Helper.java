package core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// This class contains helper static methods that can be used in different layers.
public class Helper {

    public static int getLocation(String axis, Dimension size) {
        return switch (axis) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) {
                return true;
            }
        }
        return false;
    }

    public static void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(
                null,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    public static boolean confirm(String message) {
        String title = "Are you sure?";
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION) == 0;
    }

    // This function returns a list of room types that must be in the system.
    public static ArrayList<String> getRoomTypes(){
        ArrayList roomTypes = new ArrayList<>();
        roomTypes.add("Single Room");
        roomTypes.add("Double Room");
        roomTypes.add("Junior Suite");
        roomTypes.add("Suite");
        return roomTypes;
    }

    // This function returns a list of pension types that must be in the system.
    public static ArrayList<String> getPensionTypes(){
        ArrayList<String> pension_types = new ArrayList<>();
        pension_types.add("Ultra All Inclusive");
        pension_types.add("All Inclusive");
        pension_types.add("Room Breakfast");
        pension_types.add("Full Pension");
        pension_types.add("Half Pension");
        pension_types.add("Just Bed");
        pension_types.add("Excluding Alcohol Full credit");
        return pension_types;
    }
}
