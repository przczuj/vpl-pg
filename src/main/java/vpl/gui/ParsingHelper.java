/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui;

import javax.swing.JOptionPane;

public class ParsingHelper {

    public static double parseDouble(String valueText, String fieldName) throws RuntimeException {
        try {
            return Double.parseDouble(valueText);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Field \"" + fieldName + "\" must contain numeric value", "Parsing error!", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public static boolean parseBoolean(String valueText, String fieldName) {
        try {
            return Boolean.parseBoolean(valueText);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Field \"" + fieldName + "\" must contain numeric value", "Parsing error!", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}
