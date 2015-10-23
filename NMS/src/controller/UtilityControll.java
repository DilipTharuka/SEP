/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Read;
import java.awt.event.FocusAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Dilip
 */
public class UtilityControll {

    public static void setComboItem(JComboBox comboBox, String table, String column) {
        try {
            ResultSet rst = Read.withoutWhere(column, table);
            //String blank = "";
            //comboBox.addItem(blank);
            if (rst.next()) {
                do {
                    String item = rst.getString(1);
                    comboBox.addItem(item);
                } while (rst.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilityControll.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setComboItemSpecific(JComboBox comboBox, String column, String table, String field, String fieldData) {
        try {
            ResultSet rst = Read.withWhere(column, table, field, fieldData);
            //String blank = "";
            //comboBox.addItem(blank);
            if (rst.next()) {
                do {
                    String item = rst.getString(1);
                    comboBox.addItem(item);
                } while (rst.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilityControll.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setSearchableCombo(JComboBox cmb, boolean mustSort, final String noResultsText) {
        final ArrayList<String> ar = new ArrayList<String>();
        JTextField txt;
        final int s = cmb.getItemCount();
        for (int i = 0; i < s; i++) {
            boolean exists = false;
            for (int j = 0; j < ar.size(); j++) {
                if (ar.get(j).equalsIgnoreCase(cmb.getItemAt(i) + "")) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                ar.add(cmb.getItemAt(i) + "");
            }
        }
        if (mustSort) {
            Collections.sort(ar);
        }

        txt = (JTextField) cmb.getEditor().getEditorComponent();
        txt.addFocusListener(new FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt.selectAll();
            }
        });
        txt.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent evt) {
                int key = evt.getKeyCode();
                if (!(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_ENTER || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP)) {
                    String s = txt.getText();
                    int caret = txt.getCaretPosition();
                    cmb.hidePopup();
                    cmb.removeAllItems();
                    for (int i = 0; i < ar.size(); i++) {
                        if (ar.get(i).toUpperCase().startsWith(s.substring(0, caret).toUpperCase())) {
                            cmb.addItem(ar.get(i));
                        }
                    }
                    cmb.showPopup();
                    if (cmb.getItemCount() == 0) {
                        cmb.addItem(noResultsText);
                    }
                    txt.setText(s);
                    txt.setCaretPosition(caret);
                } else if (key == KeyEvent.VK_ESCAPE) {
                    cmb.setSelectedItem(txt.getText());
                    cmb.hidePopup();
                } else if (key == KeyEvent.VK_ENTER && cmb.getSelectedIndex() == -1) {
                    if (cmb.getItemCount() == 1 && !cmb.getItemAt(0).equals(noResultsText)) {
                        cmb.setSelectedIndex(s);
                    } else if (cmb.getItemCount() > 1) {
                        cmb.setSelectedIndex(0);
                    }
                }
            }
        });
    }

    public static ImageIcon getImageIcon(String msg) {
        ImageIcon temp = null;
        if (msg.equals("success")) {
            temp = new ImageIcon("src/resourse/yes.png");
        } else if (msg.equals("fail")) {
            temp = new ImageIcon("src/resourse/no.png");
        }
        return temp;
    }

}
