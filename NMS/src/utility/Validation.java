/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Dilip
 */
public class Validation {

    private static boolean isNumberInt(String s) {
        boolean isNumber;
        try {
            long value = Long.parseLong(s);
            isNumber = true;
        } catch (Exception ex) {
            isNumber = false;
        }
        return isNumber;
    }

    private static boolean isNumberDouble(String s) {
        boolean isNumber;
        try {
            double value = Double.parseDouble(s);
            isNumber = true;
        } catch (Exception ex) {
            isNumber = false;
        }
        return isNumber;
    }

    public static void ValidateTPCount(JTextField txtContact) {
        String st = txtContact.getText();
        try {
            while (!isNumberInt(st)) {
                st = st.substring(0, st.length() - 1);
            }
        }catch (Exception ex) {
        }
        txtContact.setText(st);
        String s2 = txtContact.getText();
        if (st.length() > 10) {
            txtContact.setText(st.substring(0,10));
        }
    }

    public static void ValidateTP(JTextField txtContact, JLabel warningLabel) {
        String st = txtContact.getText();
        if (st.length() < 10 && st.length() != 0) {
            warningLabel.setText("Insert Valid Contact No");
        } else if (st.length() > 10) {
            txtContact.setText(st.substring(0, 10));
            warningLabel.setText("");
        } else {
            warningLabel.setText("");
        }

        if (!st.trim().isEmpty()) {
            if (!st.substring(0, 1).equals("0")) {
                warningLabel.setText("Insert Valid Contact No");
            }
        }
    }

    public static void ValidateIntValue(JTextField txtInt) {
        String st = txtInt.getText();
        try {
            while (!isNumberInt(st)) {
                st = st.substring(0, st.length() - 1);
            }
        } catch (Exception ex) {
        }
        txtInt.setText(st);
    }

    public static void ValidateDoubleValues(JTextField txtInt) {
        String st = txtInt.getText();
        try {
            while (!isNumberDouble(st)) {
                st = st.substring(0, st.length() - 1);
            }
        } catch (Exception ex) {
        }
        txtInt.setText(st);
    }

    public static void ValidateSingleKoto(JTextField txtString) {
        String st = txtString.getText();
        for (int i = 0; i < st.length(); i++) {
            System.out.println(st.charAt(i) + "  ");
            if (st.charAt(i) == '\'') {
                st = st.substring(0, i);
            }
        }
        txtString.setText(st);
    }

    public static void ValidateCurrency(JTextField txtCurrency) {
        String value = txtCurrency.getText();

        double number = Double.parseDouble(value.trim().isEmpty() ? "0.00" : value);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        value = nf.format(number);
        txtCurrency.setText(value);
        /*boolean incoma=value.contains(".");
         if (incoma) {
         int index=value.indexOf(".");
         String st1=value.substring(0, index);
         String st2=value.substring(index,value.length());
         System.out.println(st1);
         System.out.println(st2);
         if(st2.length()>3){
         while (st2.length()>3) {                    
         st2=st2.substring(0, st2.length()-1);
         }
         txtCurrency.setText(st1+st2);
         }else{
         while (st2.length()<3) {                    
         st2+="0";
         }
         txtCurrency.setText(st1+st2);
         }
         }else{
         value+=".00";
         txtCurrency.setText(value);
         }*/
    }

    public static String ValidateCurrencyValue(String value) {
        double number = Double.parseDouble(value);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        value = nf.format(number);
        return value;
        /*boolean incoma=value.contains(".");
         if (incoma) {
         int index=value.indexOf(".");
         String st1=value.substring(0, index);
         String st2=value.substring(index,value.length());
         System.out.println(st1);
         System.out.println(st2);
         if(st2.length()>3){
         while (st2.length()>3) {                    
         st2=st2.substring(0, st2.length()-1);
         }
         txtCurrency.setText(st1+st2);
         }else{
         while (st2.length()<3) {                    
         st2+="0";
         }
         txtCurrency.setText(st1+st2);
         }
         }else{
         value+=".00";
         txtCurrency.setText(value);
         }*/
    }
}
