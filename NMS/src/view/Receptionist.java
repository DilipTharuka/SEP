/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.EmployeeController;
import controller.IOController;
import controller.LoginController;
import controller.NewsController;
import controller.ReceptionistController;
import controller.UtilityControll;
import java.awt.Component;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
public class Receptionist extends javax.swing.JFrame {

    /**
     * Creates new form Receptionist
     */
    public Receptionist() {
        initComponents();
        jp.setVisible(false);
        this.setIconImage(IOController.getFrameImage());
        mobileList.setModel(new DefaultListModel());
        updateTable();
        updateSettings();

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
                        btnSaveReceptionist.setEnabled(false);
                    else if(model.isEmpty())
                        btnSaveReceptionist.setEnabled(false);
                    else
                        btnSaveReceptionist.setEnabled(true);
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

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (txtTitle.getText().equals("")) {
                        btnAccept.setEnabled(false);
                        btnReject.setEnabled(false);
                    } else {
                        btnAccept.setEnabled(true);
                        btnReject.setEnabled(true);
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
                if (JOptionPane.showConfirmDialog(null, "Are you sure to close this window?", "Really Closing?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    EmployeeController.stateChange("1", LoginController.getUsername());
                    System.exit(0);
                }
            }
        });

        btnAccept.setEnabled(false);
        btnReject.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        tbpMain = new javax.swing.JTabbedPane();
        pnlNewsList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNews = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtTitle = new javax.swing.JTextField();
        lblTitleNew = new javax.swing.JLabel();
        jspDescriptionNew = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        lblDescriptionNew = new javax.swing.JLabel();
        lblCommentNew = new javax.swing.JLabel();
        jspCommentNew = new javax.swing.JScrollPane();
        txtComment = new javax.swing.JTextArea();
        lblCommentNew1 = new javax.swing.JLabel();
        btnDownload = new javax.swing.JButton();
        btnReject = new javax.swing.JButton();
        btnAccept = new javax.swing.JButton();
        jp = new javax.swing.JProgressBar();
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
        btnSaveReceptionist = new javax.swing.JButton();
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
        setTitle("Receptionist");

        jScrollPane3.setBorder(null);

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
            tblNews.getColumnModel().getColumn(2).setMinWidth(120);
            tblNews.getColumnModel().getColumn(2).setMaxWidth(120);
        }

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "News Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtTitle.setEditable(false);
        txtTitle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew.setText("Title :");

        txtDescription.setEditable(false);
        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jspDescriptionNew.setViewportView(txtDescription);

        lblDescriptionNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDescriptionNew.setText("Description :");

        lblCommentNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCommentNew.setText("Attachment :");

        txtComment.setEditable(false);
        txtComment.setColumns(20);
        txtComment.setRows(5);
        jspCommentNew.setViewportView(txtComment);

        lblCommentNew1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCommentNew1.setText("Comment :");

        btnDownload.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDownload.setText("Download");
        btnDownload.setFocusable(false);
        btnDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadActionPerformed(evt);
            }
        });

        btnReject.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnReject.setText("Reject");
        btnReject.setFocusable(false);
        btnReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectActionPerformed(evt);
            }
        });

        btnAccept.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAccept.setText("Accept");
        btnAccept.setFocusable(false);
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });

        jp.setForeground(new java.awt.Color(0, 204, 0));
        jp.setFocusable(false);
        jp.setString("");
        jp.setStringPainted(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitleNew)
                    .addComponent(lblCommentNew)
                    .addComponent(lblDescriptionNew)
                    .addComponent(lblCommentNew1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDownload, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnReject, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnAccept, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(22, 22, 22))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jspDescriptionNew, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTitle, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspCommentNew, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jspCommentNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCommentNew1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDownload, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(lblCommentNew, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jp, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAccept, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReject, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout pnlNewsListLayout = new javax.swing.GroupLayout(pnlNewsList);
        pnlNewsList.setLayout(pnlNewsListLayout);
        pnlNewsListLayout.setHorizontalGroup(
            pnlNewsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewsListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlNewsListLayout.setVerticalGroup(
            pnlNewsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewsListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNewsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleDescription("");

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

        btnSaveReceptionist.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSaveReceptionist.setText("Save");
        btnSaveReceptionist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveReceptionistActionPerformed(evt);
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
                        .addComponent(btnSaveReceptionist, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(btnSaveReceptionist, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

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
                        .addGap(18, 18, 18)
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSavePassword, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
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
                .addGap(78, 78, 78)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
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

        tbpMain.addTab("    Settings    ", new javax.swing.ImageIcon(getClass().getResource("/resourse/settings.png")), pnlSettings); // NOI18N

        jScrollPane3.setViewportView(tbpMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
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
        model.Receptionist receptionist = ReceptionistController.getReceptionistByName(LoginController.getUsername());
        txtFirstName.setText(receptionist.getFirstName());
        txtLastName.setText(receptionist.getLastName());
        txtStreet.setText(receptionist.getStreet());
        txtCity.setText(receptionist.getCity());
        txtCountry.setText(receptionist.getCountry());
        txtUsername.setText(receptionist.getUsername());
        ArrayList<String> mobiles = receptionist.getMobileList();
        DefaultListModel model = (DefaultListModel) mobileList.getModel();
        for (int i = 0; i < mobiles.size(); i++) {
            model.addElement(mobiles.get(i));
        }
    }

    private void updateTable() {
        ((DefaultTableModel) tblNews.getModel()).setNumRows(0);
        tblNews.getColumnModel().getColumn(2).setCellRenderer(new Receptionist.ImageRenderer());
        DefaultTableModel dtm = (DefaultTableModel) tblNews.getModel();
        byte[] yes = IOController.getStateImage("yes");
        byte[] no = IOController.getStateImage("no");
        byte[] pending = IOController.getStateImage("pending");
        byte[] temp = null;
        ResultSet rs = NewsController.getAllNews();
        int tempState;
        int i = 1;
        try {
            while (rs.next()) {
                tempState = rs.getInt("state");
                if (tempState == 1) {
                    continue;
                } else if (tempState == 2) {
                    temp = pending;
                } else if (tempState == 4) {
                    temp = no;
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

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void btnSaveReceptionistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveReceptionistActionPerformed
        // TODO add your handling code here:
        model.Receptionist receptionist = EmployeeController.getReceptionist();
        ArrayList<String> mobiles = new ArrayList<String>();
        ListModel model = mobileList.getModel();

        for (int i = 0; i < model.getSize(); i++) {
            mobiles.add(model.getElementAt(i).toString());
        }
        receptionist.setMobileList(mobiles);
        receptionist.setFirstName(txtFirstName.getText());
        receptionist.setLastName(txtLastName.getText());
        receptionist.setStreet(txtStreet.getText());
        receptionist.setCity(txtCity.getText());
        receptionist.setCountry(txtCountry.getText());
        if (ReceptionistController.updateReceptionist(receptionist)) {
            JOptionPane.showMessageDialog(null, "Journalist updated sucessfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
        } else {
            JOptionPane.showMessageDialog(null, "Journalist not updated sucessfully", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
        }
    }//GEN-LAST:event_btnSaveReceptionistActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        DefaultListModel model = (DefaultListModel) mobileList.getModel();
        model.addElement(txtMobile.getText());
        txtMobile.setText("");
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        DefaultListModel model = (DefaultListModel) mobileList.getModel();
        model.remove(mobileList.getSelectedIndex());
    }//GEN-LAST:event_btnDeleteActionPerformed

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
        int state = news.getState();
        if (state == 2) {
            btnAccept.setEnabled(true);
            btnReject.setEnabled(true);
        } else {
            btnReject.setEnabled(false);
            btnAccept.setEnabled(false);
        }

    }//GEN-LAST:event_tblNewsMouseClicked

    private void btnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadActionPerformed
        // TODO add your handling code here:

        new Thread(new Runnable() {
            public void run() {
                jp.setVisible(true);
                if (ReceptionistController.downloadNews(newsID, jp)) {
                    JOptionPane.showMessageDialog(null, "Downloaded successfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to download", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
                }
                jp.setVisible(false);
            }
        }).start();

    }//GEN-LAST:event_btnDownloadActionPerformed

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        // TODO add your handling code here:
//        int row = tblNews.getSelectedRow();
//        String clickData = tblNews.getModel().getValueAt(row, 1).toString();
//        String title = clickData.substring(1,clickData.length());
        String title = txtTitle.getText();
        if (ReceptionistController.assignEditor(format(title))) {
            ReceptionistController.acceptNews(format(title));
            updateTable();
            JOptionPane.showMessageDialog(null, "Editor assigned successfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
        } else {
            JOptionPane.showMessageDialog(null, "Failed to assign editor", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
        }

    }//GEN-LAST:event_btnAcceptActionPerformed

    private void btnRejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejectActionPerformed
        // TODO add your handling code here:
//        int row = tblNews.getSelectedRow();
//        String clickData = tblNews.getModel().getValueAt(row, 1).toString();
//        String title = clickData.substring(1,clickData.length());
        String title = txtTitle.getText();
        if (ReceptionistController.rejectNews(format(title))) {
            updateTable();
        }
    }//GEN-LAST:event_btnRejectActionPerformed

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
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDownload;
    private javax.swing.JButton btnReject;
    private javax.swing.JButton btnSavePassword;
    private javax.swing.JButton btnSaveReceptionist;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JProgressBar jp;
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
    // End of variables declaration//GEN-END:variables
    private int newsID;
    private boolean passwordMismatch = false;
}
