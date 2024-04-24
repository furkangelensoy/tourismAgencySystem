package core;

import javax.swing.*;
import java.awt.*;

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
}
