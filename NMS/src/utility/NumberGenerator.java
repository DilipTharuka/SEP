/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author Dilip
 */
public class NumberGenerator {

    public static String addSpace(String number, int size) {
        int n = size - number.length();
        for (int i = 0; i <= n; i++) {
            number = " " + number;
        }
        return number;
    }
}
