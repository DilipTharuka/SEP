/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Dilip
 */
import it.sauronsoftware.ftp4j.FTPClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import utility.FileTransferListener;

/**
 *
 * @author Dilip
 */
public final class FTPConnector {

    private static FTPClient client = null;
    private static FTPConnector remoteConnection = null;
    private static String user = null;
    private static String host = null;
    private static String pwd = null;

    private FTPConnector() throws Exception {
        read_ftp_file();
        client = new FTPClient();
        client.connect(host);
        client.login(user, pwd);
        client.setPassive(true);
        client.setType(FTPClient.TYPE_BINARY);
    }

    public static FTPConnector getConnection() throws Exception {
//        if (remoteConnection == null) {
//            remoteConnection = new FTPConnector();
//        }
//        return remoteConnection;
        return new FTPConnector();
    }

    private static void read_ftp_file() {
        try {
            File file = new File("C:\\Users\\Dilip\\Google Drive\\NMS\\ftp.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            host = bufferedReader.readLine();
            user = bufferedReader.readLine();
            pwd = bufferedReader.readLine();
            fileReader.close();
        } catch (IOException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void uploadFile(String localPath, String fileName, String hostDir, FileTransferListener ftpls) throws Exception {
        System.out.println("Start Uploading");
        if (!client.currentDirectory().equals(hostDir)) {
            client.changeDirectory(hostDir);
        }
        File file = new File(localPath);
        client.upload(file, ftpls);
        String temp[] = localPath.split("\\\\");
        client.rename(temp[temp.length - 1], fileName);
        System.out.println(fileName + " Upload Successfully");
    }

    public void downloadFile(String remoteFilePath, String localFilePath, JProgressBar jp) throws Exception {
        System.out.println("Start Downloading");
        System.out.println(remoteFilePath);
        long fileSize = client.fileSize(remoteFilePath);
        client.download(remoteFilePath, new java.io.File(localFilePath), new FileTransferListener(jp, fileSize));
        System.out.println(remoteFilePath + " Download Successfully");
    }

    public void disconnect() throws Exception {
        client.disconnect(true);
    }

    public void deleteImageList(String hostDir, String ID) throws Exception {
        if (!client.currentDirectory().equals(hostDir)) {
            client.changeDirectory(hostDir);
        }
        String[] list = client.listNames();
        String[] temp;

        for (int i = 0; i < list.length; i++) {
            temp = list[i].split("_");
            if (temp[1].equals(ID)) {
                client.deleteFile(list[i]);
            }
        }
    }

}
