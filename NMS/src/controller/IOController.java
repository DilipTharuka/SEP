/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import view.Journalist;

/**
 *
 * @author Dilip
 */
public class IOController {

    public static byte[] getStateImage(String title) {
        File file = new File("src/resourse/" + title + ".png");
        byte[] fileContent = null;
        try {
            fileContent = Files.readAllBytes(file.toPath());
        } catch (IOException ex) {
            Logger.getLogger(Journalist.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileContent;
    }

    public static Image getFrameImage() {
        ImageIcon image = new ImageIcon("src/resourse/logo.png");
        return image.getImage();
    }
}
