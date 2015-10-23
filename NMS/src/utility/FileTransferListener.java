/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import javax.swing.JProgressBar;

/**
 *
 * @author Dilip
 */
public class FileTransferListener implements FTPDataTransferListener {

    JProgressBar jp;
    long transfBytes = 0;
    long fileSize;
    int pecentage;

    public FileTransferListener(JProgressBar jp, long fileSize) throws InterruptedException {
        this.jp = jp;
        this.fileSize = fileSize;
    }

    @Override
    public void started() {
        // Transfer started
        pecentage = (int) (((double) transfBytes / fileSize) * 100);
        jp.setValue(pecentage);
        jp.setString(pecentage + "%");
    }

    @Override
    public void transferred(int length) {
        // Yet other length bytes has been transferred since the last time this
        transfBytes += length;
        pecentage = (int) (((double) transfBytes / fileSize) * 100);
        jp.setValue(pecentage);
        jp.setString(pecentage + "%");

    }

    @Override
    public void completed() {

    }

    @Override
    public void aborted() {
        // Transfer aborted
    }

    @Override
    public void failed() {
        // Transfer failed
        jp.setValue(0);
        jp.setString("failed");
    }

}
