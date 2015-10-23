/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.EditorController;
import controller.EmployeeController;
import controller.IOController;
import controller.LoginController;
import controller.NewsController;
import controller.UtilityControll;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.News;
import utility.NumberGenerator;
import utility.Validation;

/**
 *
 * @author Dilip
 */
public class Editor extends javax.swing.JFrame {

    /**
     * Creates new form Editor
     */
    public Editor() {
        initComponents();
        jpDownload.setVisible(false);
        jpSubmit.setVisible(false);
        this.setIconImage(IOController.getFrameImage());
        imageList.setModel(new DefaultListModel());
        videoList.setModel(new DefaultListModel());
        mobileList.setModel(new DefaultListModel());
        updateTable();
        updateSettings();
        btnDownload.setEnabled(false);
        btnSubmit.setEnabled(false);

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    updateTable();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Journalist.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }).start();
        
        
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    DefaultListModel model = (DefaultListModel) mobileList.getModel();
                    if(txtFirstName.getText().equals("") || txtLastName.getText().equals("") || txtStreet.getText().equals("") || txtCity.getText().equals("") || txtCountry.getText().equals(""))
                        btnSaveEditor.setEnabled(false);
                    else if(model.isEmpty())
                        btnSaveEditor.setEnabled(false);
                    else
                        btnSaveEditor.setEnabled(true);
                    if(txtMobile.getText().length()!= 10)
                        btnAdd.setEnabled(false);
                    else
                        btnAdd.setEnabled(true);
                    if(mobileList.isSelectionEmpty())
                        btnDelete.setEnabled(false);
                    else
                        btnDelete.setEnabled(true);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Journalist.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (imageList.isSelectionEmpty()) {
                        btnDeleteImage.setEnabled(false);
                    } else {
                        btnDeleteImage.setEnabled(true);
                    }
                    if (videoList.isSelectionEmpty()) {
                        btnDeleteVideo.setEnabled(false);
                    } else {
                        btnDeleteVideo.setEnabled(true);
                    }
                    if (txtVideo.getText().equals("")) {
                        btnAddVideo.setEnabled(false);
                    } else {
                        btnAddVideo.setEnabled(true);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Journalist.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }).start();

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (txtCurrentPassword.getText().equals("") || txtNewPassword.getText().equals("") || txtConfirmPassword.getText().equals("") || passwordMismatch) {
                        btnSavePassword.setEnabled(false);
                    } else {
                        btnSavePassword.setEnabled(true);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Journalist.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure to close this window ?", "Really Closing ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    EmployeeController.stateChange("1", LoginController.getUsername());
                    System.exit(0);
                }
            }
        });
    }

    class ImageRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel();
            if (value != null) {
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setIcon(new ImageIcon((byte[]) value));
            }
            return label;
        }
    }

    private void updateSettings() {
        model.Editor editor = EditorController.getEditorByName(LoginController.getUsername());
        txtFirstName.setText(editor.getFirstName());
        txtLastName.setText(editor.getLastName());
        txtStreet.setText(editor.getStreet());
        txtCity.setText(editor.getCity());
        txtCountry.setText(editor.getCountry());
        txtUsername.setText(editor.getUsername());
        ArrayList<String> mobiles = editor.getMobileList();
        DefaultListModel model = (DefaultListModel) mobileList.getModel();
        for (int i = 0; i < mobiles.size(); i++) {
            model.addElement(mobiles.get(i));
        }
    }

    private void updateTable() {
        ((DefaultTableModel) tblNews.getModel()).setNumRows(0);
        tblNews.getColumnModel().getColumn(2).setCellRenderer(new Editor.ImageRenderer());
        DefaultTableModel dtm = (DefaultTableModel) tblNews.getModel();
        byte[] yes = IOController.getStateImage("yes");
        byte[] no = IOController.getStateImage("no");
        byte[] pending = IOController.getStateImage("pending");
        byte[] reedit = IOController.getStateImage("reedit");
        byte[] edit = IOController.getStateImage("edit");
        byte[] temp = null;
        ResultSet rs = NewsController.getAllNews();
        int tempState;
        int i = 1;
        try {
            while (rs.next()) {
                tempState = rs.getInt("state");
                if (tempState == 1 || tempState == 2 || tempState == 4) {
                    continue;
                } else if (tempState == 3) {
                    temp = edit;
                } else if (tempState == 5) {
                    temp = pending;
                } else if (tempState == 6) {
                    temp = reedit;
                } else {
                    temp = yes;
                }

                Object raw[] = {NumberGenerator.addSpace(i + "", 5), " " + rs.getString("title"), temp};
                dtm.addRow(raw);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Journalist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane6 = new javax.swing.JScrollPane();
        tbpMain = new javax.swing.JTabbedPane();
        pnlNewsList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNews = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        txtTitle = new javax.swing.JTextField();
        lblTitleNew = new javax.swing.JLabel();
        jspDescriptionNew = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        lblDescriptionNew = new javax.swing.JLabel();
        jspCommentNew = new javax.swing.JScrollPane();
        txtComment = new javax.swing.JTextArea();
        lblCommentNew1 = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        lblTitleNew12 = new javax.swing.JLabel();
        btnAddImage = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        imageList = new javax.swing.JList();
        btnDeleteImage = new javax.swing.JButton();
        btnAddVideo = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        videoList = new javax.swing.JList();
        btnDeleteVideo = new javax.swing.JButton();
        lblTitleNew13 = new javax.swing.JLabel();
        btnDownload = new javax.swing.JButton();
        lblCommentNew = new javax.swing.JLabel();
        txtVideo = new javax.swing.JTextField();
        jpDownload = new javax.swing.JProgressBar();
        jpSubmit = new javax.swing.JProgressBar();
        pnlSettings = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblFirstName = new javax.swing.JLabel();
        lblTitleNew11 = new javax.swing.JLabel();
        lblLastName = new javax.swing.JLabel();
        txtMobile = new javax.swing.JTextField();
        txtCountry = new javax.swing.JTextField();
        txtCity = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        mobileList = new javax.swing.JList();
        txtFirstName = new javax.swing.JTextField();
        lblTitleNew6 = new javax.swing.JLabel();
        lblTitleNew7 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        lblTitleNew5 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        txtStreet = new javax.swing.JTextField();
        btnSaveEditor = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblCurrentPassword = new javax.swing.JLabel();
        txtCurrentPassword = new javax.swing.JPasswordField();
        txtNewPassword = new javax.swing.JPasswordField();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblNewPassword = new javax.swing.JLabel();
        btnSavePassword = new javax.swing.JButton();
        txtConfirmPassword = new javax.swing.JPasswordField();
        lblConfirmPassword = new javax.swing.JLabel();
        lblPasswordWn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Editor");

        jScrollPane6.setBorder(null);

        tbpMain.setToolTipText("new news");
        tbpMain.setFocusable(false);
        tbpMain.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbpMain.setPreferredSize(new java.awt.Dimension(1240, 600));

        pnlNewsList.setBackground(new java.awt.Color(255, 255, 255));

        tblNews.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 14));
        tblNews.getTableHeader().setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
        ((DefaultTableCellRenderer)tblNews.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblNews.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Number", "Title", "Accept"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNews.setFocusable(false);
        tblNews.setRowHeight(40);
        tblNews.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblNews.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNewsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNews);
        if (tblNews.getColumnModel().getColumnCount() > 0) {
            tblNews.getColumnModel().getColumn(0).setMinWidth(80);
            tblNews.getColumnModel().getColumn(0).setMaxWidth(80);
            tblNews.getColumnModel().getColumn(2).setMinWidth(100);
            tblNews.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        jScrollPane3.setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtTitle.setEditable(false);
        txtTitle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew.setText("Title :");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jspDescriptionNew.setViewportView(txtDescription);

        lblDescriptionNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDescriptionNew.setText("Description :");

        txtComment.setColumns(20);
        txtComment.setRows(5);
        jspCommentNew.setViewportView(txtComment);

        lblCommentNew1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCommentNew1.setText("Comment :");

        btnSubmit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        lblTitleNew12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew12.setText("Image :");

        btnAddImage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAddImage.setText("Add");
        btnAddImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddImageActionPerformed(evt);
            }
        });

        imageList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane4.setViewportView(imageList);

        btnDeleteImage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDeleteImage.setText("Delete");
        btnDeleteImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteImageActionPerformed(evt);
            }
        });

        btnAddVideo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAddVideo.setText("Add");
        btnAddVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddVideoActionPerformed(evt);
            }
        });

        videoList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane5.setViewportView(videoList);

        btnDeleteVideo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDeleteVideo.setText("Delete");
        btnDeleteVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteVideoActionPerformed(evt);
            }
        });

        lblTitleNew13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew13.setText("Video :");

        btnDownload.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDownload.setText("Download");
        btnDownload.setFocusable(false);
        btnDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadActionPerformed(evt);
            }
        });

        lblCommentNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCommentNew.setText("Attachment :");

        txtVideo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jpDownload.setForeground(new java.awt.Color(0, 204, 0));
        jpDownload.setString("");
        jpDownload.setStringPainted(true);

        jpSubmit.setForeground(new java.awt.Color(0, 204, 0));
        jpSubmit.setString("");
        jpSubmit.setStringPainted(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(52, 52, 52)
                                    .addComponent(lblTitleNew))
                                .addComponent(lblDescriptionNew, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(lblTitleNew13, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                                            .addComponent(txtVideo))
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane4)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDeleteImage, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAddImage, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAddVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDeleteVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtTitle)
                            .addComponent(jspDescriptionNew, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lblTitleNew12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCommentNew, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCommentNew1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnDownload, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jpDownload, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jspCommentNew)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jpSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspDescriptionNew, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescriptionNew, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAddImage, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteImage, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitleNew13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAddVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCommentNew1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jspCommentNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpDownload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnDownload, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCommentNew, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jScrollPane3.setViewportView(jPanel1);

        javax.swing.GroupLayout pnlNewsListLayout = new javax.swing.GroupLayout(pnlNewsList);
        pnlNewsList.setLayout(pnlNewsListLayout);
        pnlNewsListLayout.setHorizontalGroup(
            pnlNewsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewsListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlNewsListLayout.setVerticalGroup(
            pnlNewsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewsListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNewsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        tbpMain.addTab("  News List  ", new javax.swing.ImageIcon(getClass().getResource("/resourse/news_list.png")), pnlNewsList); // NOI18N

        pnlSettings.setBackground(new java.awt.Color(255, 255, 255));
        pnlSettings.setPreferredSize(new java.awt.Dimension(1422, 550));
        pnlSettings.setRequestFocusEnabled(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "About", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lblFirstName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFirstName.setText("First Name :");

        lblTitleNew11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew11.setText("      Mobile :");

        lblLastName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblLastName.setText("Last Name :");

        txtMobile.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMobileKeyReleased(evt);
            }
        });

        txtCountry.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtCity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        mobileList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(mobileList);

        txtFirstName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew6.setText("      Street :");

        lblTitleNew7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew7.setText("Country :");

        txtLastName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew5.setText("City :");

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        txtStreet.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnSaveEditor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSaveEditor.setText("Save");
        btnSaveEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveEditorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSaveEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitleNew5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTitleNew7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblFirstName)
                                        .addComponent(lblLastName))
                                    .addComponent(lblTitleNew6)))
                            .addComponent(lblTitleNew11, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCountry, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(txtCity)
                            .addComponent(txtStreet, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtLastName)
                            .addComponent(txtMobile))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitleNew11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSaveEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Change Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lblCurrentPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCurrentPassword.setText(" Current Password :");

        lblUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUsername.setText(" Username :");

        txtUsername.setEditable(false);
        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblNewPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNewPassword.setText(" New Password :");

        btnSavePassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSavePassword.setText("Save");
        btnSavePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavePasswordActionPerformed(evt);
            }
        });

        txtConfirmPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConfirmPasswordKeyReleased(evt);
            }
        });

        lblConfirmPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblConfirmPassword.setText(" Confirm Password :");

        lblPasswordWn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPasswordWn.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblPasswordWn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(btnSavePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblCurrentPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNewPassword)
                            .addComponent(lblUsername)
                            .addComponent(lblConfirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtConfirmPassword)
                            .addComponent(txtNewPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(txtCurrentPassword)
                            .addComponent(txtUsername))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCurrentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCurrentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSavePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPasswordWn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlSettingsLayout = new javax.swing.GroupLayout(pnlSettings);
        pnlSettings.setLayout(pnlSettingsLayout);
        pnlSettingsLayout.setHorizontalGroup(
            pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSettingsLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        pnlSettingsLayout.setVerticalGroup(
            pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSettingsLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );

        tbpMain.addTab("   Settings    ", new javax.swing.ImageIcon(getClass().getResource("/resourse/settings.png")), pnlSettings); // NOI18N

        jScrollPane6.setViewportView(tbpMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblNewsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNewsMouseClicked
        // TODO add your handling code here:
        int row = tblNews.getSelectedRow();
        String clickData = tblNews.getModel().getValueAt(row, 1).toString();
        String title = clickData.substring(1, clickData.length());
        News news = NewsController.getNewsByTitle(format(title));
        txtTitle.setText(news.getTitle());
        txtDescription.setText(news.getDescription());
        txtComment.setText(news.getComment());
        newsID = news.getNewsID();
        ArrayList<String> images = news.getImageList();
        ArrayList<String> videos = news.getVideoList();
        DefaultListModel imageModel = (DefaultListModel) imageList.getModel();
        imageModel.removeAllElements();
        DefaultListModel videoModel = (DefaultListModel) videoList.getModel();
        videoModel.removeAllElements();
        for (int i = 0; i < images.size(); i++) {
            imageModel.addElement(images.get(i).replace("\\", "\\\\"));
        }
        for (int i = 0; i < videos.size(); i++) {
            videoModel.addElement(videos.get(i));
        }
        btnDownload.setEnabled(true);
        int state = news.getState();
        if (state == 3 || state == 6) {
            btnSubmit.setEnabled(true);
        }
    }//GEN-LAST:event_tblNewsMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        DefaultListModel model = (DefaultListModel) mobileList.getModel();
        model.remove(mobileList.getSelectedIndex());
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        DefaultListModel model = (DefaultListModel) mobileList.getModel();
        model.addElement(txtMobile.getText());
        txtMobile.setText("");
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveEditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveEditorActionPerformed
        // TODO add your handling code here:
        model.Editor editor = EmployeeController.getEditor();
        ArrayList<String> mobiles = new ArrayList<String>();
        ListModel model = mobileList.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            mobiles.add(model.getElementAt(i).toString());
        }
        editor.setMobileList(mobiles);
        editor.setFirstName(txtFirstName.getText());
        editor.setLastName(txtLastName.getText());
        editor.setStreet(txtStreet.getText());
        editor.setCity(txtCity.getText());
        editor.setCountry(txtCountry.getText());
        if (EditorController.updateEditor(editor)) {
            JOptionPane.showMessageDialog(null, "Editor updated successfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update editor", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
        }
    }//GEN-LAST:event_btnSaveEditorActionPerformed

    private String format(String text) {
        text = text.replace("\\", "\\\\");
        text = text.replace("\'", "\\'");
        return text;
    }

    private void btnSavePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePasswordActionPerformed
        // TODO add your handling code here:
        if (EmployeeController.passwordReset(txtUsername.getText(), txtCurrentPassword.getText(), txtNewPassword.getText())) {
            JOptionPane.showMessageDialog(null, "Password updated sucessfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update password", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
        }
        txtCurrentPassword.setText("");
        txtNewPassword.setText("");
        txtConfirmPassword.setText("");
    }//GEN-LAST:event_btnSavePasswordActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        News news = NewsController.getNewsObject();
        news.setTitle(format(txtTitle.getText()));
        news.setDescription(format(txtDescription.getText()));
        news.setComment(format(txtComment.getText()));
        ListModel model;
        ArrayList<String> images = new ArrayList<String>();
        model = imageList.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            images.add(model.getElementAt(i).toString());
        }
        ArrayList<String> videos = new ArrayList<String>();
        model = videoList.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            videos.add(model.getElementAt(i).toString());
        }
        news.setVideoList(videos);
        news.setImageList(images);
        new Thread(new Runnable() {
            public void run() {
                jpSubmit.setVisible(true);
                if (EditorController.submitNews(news, jpSubmit)) {
                    JOptionPane.showMessageDialog(null, "News submitted successfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to submit news", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
                }
                jpSubmit.setVisible(false);
            }
        }).start();
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnAddImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddImageActionPerformed

        DefaultListModel model = (DefaultListModel) imageList.getModel();
        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        int response = fc.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File[] files = fc.getSelectedFiles();
            for (int i = 0; i < files.length; i++) {
                model.addElement(files[i].getAbsolutePath().replace("\\", "\\\\"));
            }

        }
    }//GEN-LAST:event_btnAddImageActionPerformed

    private void btnDeleteImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteImageActionPerformed

        DefaultListModel model = (DefaultListModel) imageList.getModel();
        model.remove(imageList.getSelectedIndex());
    }//GEN-LAST:event_btnDeleteImageActionPerformed

    private void btnAddVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddVideoActionPerformed

        DefaultListModel model = (DefaultListModel) videoList.getModel();
        model.addElement(txtVideo.getText());
        txtVideo.setText("");
    }//GEN-LAST:event_btnAddVideoActionPerformed

    private void btnDeleteVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteVideoActionPerformed

        DefaultListModel model = (DefaultListModel) videoList.getModel();
        model.remove(videoList.getSelectedIndex());
    }//GEN-LAST:event_btnDeleteVideoActionPerformed

    private void btnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadActionPerformed

        new Thread(new Runnable() {
            public void run() {
                jpDownload.setVisible(true);
                if (EditorController.downloadNews(newsID, jpDownload)) {
                    JOptionPane.showMessageDialog(null, "Downloaded successfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to download", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
                }
                jpDownload.setVisible(false);
            }
        }).start();
    }//GEN-LAST:event_btnDownloadActionPerformed

    private void txtConfirmPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmPasswordKeyReleased
        // TODO add your handling code here:
        if (txtNewPassword.getText().equals(txtConfirmPassword.getText())) {
            passwordMismatch = false;
            lblPasswordWn.setText("");
        } else {
            passwordMismatch = true;
            lblPasswordWn.setText("New and confirm passwords are not equal");
        }
    }//GEN-LAST:event_txtConfirmPasswordKeyReleased

    private void txtMobileKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMobileKeyReleased
        // TODO add your handling code here:
        Validation.ValidateTPCount(txtMobile);
    }//GEN-LAST:event_txtMobileKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddImage;
    private javax.swing.JButton btnAddVideo;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteImage;
    private javax.swing.JButton btnDeleteVideo;
    private javax.swing.JButton btnDownload;
    private javax.swing.JButton btnSaveEditor;
    private javax.swing.JButton btnSavePassword;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JList imageList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JProgressBar jpDownload;
    private javax.swing.JProgressBar jpSubmit;
    private javax.swing.JScrollPane jspCommentNew;
    private javax.swing.JScrollPane jspDescriptionNew;
    private javax.swing.JLabel lblCommentNew;
    private javax.swing.JLabel lblCommentNew1;
    private javax.swing.JLabel lblConfirmPassword;
    private javax.swing.JLabel lblCurrentPassword;
    private javax.swing.JLabel lblDescriptionNew;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblNewPassword;
    private javax.swing.JLabel lblPasswordWn;
    private javax.swing.JLabel lblTitleNew;
    private javax.swing.JLabel lblTitleNew11;
    private javax.swing.JLabel lblTitleNew12;
    private javax.swing.JLabel lblTitleNew13;
    private javax.swing.JLabel lblTitleNew5;
    private javax.swing.JLabel lblTitleNew6;
    private javax.swing.JLabel lblTitleNew7;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JList mobileList;
    private javax.swing.JPanel pnlNewsList;
    private javax.swing.JPanel pnlSettings;
    private javax.swing.JTable tblNews;
    private javax.swing.JTabbedPane tbpMain;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextArea txtComment;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtCountry;
    private javax.swing.JPasswordField txtCurrentPassword;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMobile;
    private javax.swing.JPasswordField txtNewPassword;
    private javax.swing.JTextField txtStreet;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTextField txtVideo;
    private javax.swing.JList videoList;
    // End of variables declaration//GEN-END:variables
    private int newsID;
    private boolean passwordMismatch = false;
}
