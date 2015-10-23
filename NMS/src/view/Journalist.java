/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.EmployeeController;
import controller.IOController;
import controller.JournalistController;
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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import utility.NumberGenerator;
import utility.Validation;

/**
 *
 * @author Dilip
 */
public class Journalist extends javax.swing.JFrame {

    /**
     * Creates new form JournalistMain
     */
    public Journalist() {
        initComponents();
        this.setIconImage(IOController.getFrameImage());
        mobileList.setModel(new DefaultListModel());
        jpNew.setVisible(false);
        updateTable();
        updateSettings();
        lblTitleWn.setText("");

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
                        btnSaveJournalist.setEnabled(false);
                    else if(model.isEmpty())
                        btnSaveJournalist.setEnabled(false);
                    else
                        btnSaveJournalist.setEnabled(true);
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
                    if (txtDescriptionNew.getText().equals("") || txtTitleNew.getText().equals("") || txtLinkNew.getText().equals("") || titleExist) {
                        btnSaveSubmit.setEnabled(false);
                    } else {
                        btnSaveSubmit.setEnabled(true);
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
        pnlNewNews = new javax.swing.JPanel();
        pnlContainerNew = new javax.swing.JPanel();
        lblTitleNew = new javax.swing.JLabel();
        lblDescriptionNew = new javax.swing.JLabel();
        lblCommentNew = new javax.swing.JLabel();
        lblAttachMew = new javax.swing.JLabel();
        txtTitleNew = new javax.swing.JTextField();
        txtLinkNew = new javax.swing.JTextField();
        jspDescriptionNew = new javax.swing.JScrollPane();
        txtDescriptionNew = new javax.swing.JTextArea();
        jspCommentNew = new javax.swing.JScrollPane();
        txtCommentNew = new javax.swing.JTextArea();
        btnChooseNew = new javax.swing.JButton();
        btnSaveSubmit = new javax.swing.JButton();
        btnClearNew = new javax.swing.JButton();
        jpNew = new javax.swing.JProgressBar();
        lblTitleWn = new javax.swing.JLabel();
        pnlNewsList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNews = new javax.swing.JTable();
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
        btnSaveJournalist = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Journalist");
        setBackground(new java.awt.Color(204, 204, 204));
        setFocusable(false);
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));

        jScrollPane3.setBorder(null);

        tbpMain.setToolTipText("new news");
        tbpMain.setFocusable(false);
        tbpMain.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbpMain.setPreferredSize(new java.awt.Dimension(1240, 600));

        pnlNewNews.setBackground(new java.awt.Color(255, 255, 255));
        pnlNewNews.setPreferredSize(new java.awt.Dimension(1200, 600));

        pnlContainerNew.setBackground(new java.awt.Color(255, 255, 255));

        lblTitleNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew.setText("Title :");

        lblDescriptionNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDescriptionNew.setText("Description :");

        lblCommentNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCommentNew.setText("Comment :");

        lblAttachMew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAttachMew.setText("Attach :");

        txtTitleNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTitleNew.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTitleNewKeyReleased(evt);
            }
        });

        txtLinkNew.setEditable(false);
        txtLinkNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtDescriptionNew.setColumns(20);
        txtDescriptionNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDescriptionNew.setRows(5);
        jspDescriptionNew.setViewportView(txtDescriptionNew);

        txtCommentNew.setColumns(20);
        txtCommentNew.setRows(5);
        jspCommentNew.setViewportView(txtCommentNew);

        btnChooseNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnChooseNew.setText("Choose");
        btnChooseNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseNewActionPerformed(evt);
            }
        });

        btnSaveSubmit.setBackground(new java.awt.Color(255, 255, 255));
        btnSaveSubmit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSaveSubmit.setText("Submit News");
        btnSaveSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSubmitActionPerformed(evt);
            }
        });

        btnClearNew.setBackground(new java.awt.Color(255, 255, 255));
        btnClearNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnClearNew.setText("Clear All");
        btnClearNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearNewActionPerformed(evt);
            }
        });

        jpNew.setBackground(new java.awt.Color(255, 255, 255));
        jpNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jpNew.setForeground(new java.awt.Color(0, 204, 0));
        jpNew.setBorderPainted(false);
        jpNew.setFocusable(false);
        jpNew.setPreferredSize(new java.awt.Dimension(146, 10));
        jpNew.setRequestFocusEnabled(false);
        jpNew.setString("");
        jpNew.setStringPainted(true);

        lblTitleWn.setBackground(new java.awt.Color(255, 255, 255));
        lblTitleWn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleWn.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout pnlContainerNewLayout = new javax.swing.GroupLayout(pnlContainerNew);
        pnlContainerNew.setLayout(pnlContainerNewLayout);
        pnlContainerNewLayout.setHorizontalGroup(
            pnlContainerNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerNewLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnlContainerNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAttachMew)
                    .addComponent(lblCommentNew)
                    .addComponent(lblTitleNew)
                    .addComponent(lblDescriptionNew))
                .addGap(18, 18, 18)
                .addGroup(pnlContainerNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContainerNewLayout.createSequentialGroup()
                        .addComponent(txtTitleNew)
                        .addGap(18, 18, 18)
                        .addComponent(lblTitleWn, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspCommentNew, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jspDescriptionNew)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContainerNewLayout.createSequentialGroup()
                        .addComponent(txtLinkNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChooseNew, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(128, 128, 128)
                .addGroup(pnlContainerNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContainerNewLayout.createSequentialGroup()
                        .addComponent(btnClearNew, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSaveSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                    .addComponent(jpNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(69, 69, 69))
        );
        pnlContainerNewLayout.setVerticalGroup(
            pnlContainerNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerNewLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlContainerNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitleNew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleWn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlContainerNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspDescriptionNew, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescriptionNew, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlContainerNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspCommentNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCommentNew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlContainerNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLinkNew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContainerNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnChooseNew, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAttachMew, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jpNew, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70)
                .addGroup(pnlContainerNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaveSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearNew, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout pnlNewNewsLayout = new javax.swing.GroupLayout(pnlNewNews);
        pnlNewNews.setLayout(pnlNewNewsLayout);
        pnlNewNewsLayout.setHorizontalGroup(
            pnlNewNewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewNewsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlContainerNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlNewNewsLayout.setVerticalGroup(
            pnlNewNewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNewNewsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlContainerNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpMain.addTab("  New News  ", new javax.swing.ImageIcon(getClass().getResource("/resourse/new_news.png")), pnlNewNews); // NOI18N

        pnlNewsList.setBackground(new java.awt.Color(255, 255, 255));

        tblNews.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 14));
        tblNews.getTableHeader().setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
        ((DefaultTableCellRenderer)tblNews.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblNews.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Number", "Title", "Submit", "Accept"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNews.setFocusable(false);
        tblNews.setRowHeight(40);
        tblNews.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tblNews);
        if (tblNews.getColumnModel().getColumnCount() > 0) {
            tblNews.getColumnModel().getColumn(0).setMinWidth(100);
            tblNews.getColumnModel().getColumn(0).setMaxWidth(100);
            tblNews.getColumnModel().getColumn(2).setMinWidth(250);
            tblNews.getColumnModel().getColumn(2).setMaxWidth(250);
            tblNews.getColumnModel().getColumn(3).setMinWidth(250);
            tblNews.getColumnModel().getColumn(3).setMaxWidth(250);
        }

        javax.swing.GroupLayout pnlNewsListLayout = new javax.swing.GroupLayout(pnlNewsList);
        pnlNewsList.setLayout(pnlNewsListLayout);
        pnlNewsListLayout.setHorizontalGroup(
            pnlNewsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewsListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1235, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlNewsListLayout.setVerticalGroup(
            pnlNewsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewsListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
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

        btnSaveJournalist.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSaveJournalist.setText("Save");
        btnSaveJournalist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveJournalistActionPerformed(evt);
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
                        .addComponent(btnSaveJournalist, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(btnSaveJournalist, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        btnSavePassword.setFocusable(false);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap(94, Short.MAX_VALUE))
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

        jScrollPane3.setViewportView(tbpMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void updateSettings() {
        model.Journalist journalist = JournalistController.getJournalistByName(LoginController.getUsername());
        txtFirstName.setText(journalist.getFirstName());
        txtLastName.setText(journalist.getLastName());
        txtStreet.setText(journalist.getStreet());
        txtCity.setText(journalist.getCity());
        txtCountry.setText(journalist.getCountry());
        txtUsername.setText(journalist.getUsername());
        ArrayList<String> mobiles = journalist.getMobileList();
        DefaultListModel model = (DefaultListModel) mobileList.getModel();
        for (int i = 0; i < mobiles.size(); i++) {
            model.addElement(mobiles.get(i));
        }
    }

    private boolean checkTitle() {
        ArrayList<String> titles = NewsController.getNewsTitles();
        if (titles.contains(txtTitleNew.getText())) {
            return true;
        } else {
            return false;
        }
    }

    private void updateTable() {
        ((DefaultTableModel) tblNews.getModel()).setNumRows(0);
        tblNews.getColumnModel().getColumn(2).setCellRenderer(new ImageRenderer());
        tblNews.getColumnModel().getColumn(3).setCellRenderer(new ImageRenderer());
        DefaultTableModel dtm = (DefaultTableModel) tblNews.getModel();
        byte[] yes = IOController.getStateImage("yes");
        byte[] no = IOController.getStateImage("no");
        byte[] temp1 = null;
        byte[] temp2 = null;
        ResultSet rs = NewsController.getAllNews();
        int ID = LoginController.getEmployeeID();
        int i = 1;
        try {
            while (rs.next()) {
                if (rs.getInt("Journalist_ID") != ID) {
                    continue;
                }
                if (rs.getInt("state") == 1) {
                    temp1 = no;
                    temp2 = no;
                } else if (rs.getInt("state") == 2) {
                    temp1 = yes;
                    temp2 = no;
                } else if (rs.getInt("state") > 2) {
                    temp1 = yes;
                    temp2 = yes;
                }

                Object raw[] = {NumberGenerator.addSpace(i + "", 5), " " + rs.getString("title"), temp1, temp2};
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

    private void btnSaveJournalistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveJournalistActionPerformed
        // TODO add your handling code here:
        model.Journalist journalist = EmployeeController.getJournalist();
        ArrayList<String> mobiles = new ArrayList<String>();
        ListModel model = mobileList.getModel();

        for (int i = 0; i < model.getSize(); i++) {
            mobiles.add(model.getElementAt(i).toString());
        }
        journalist.setMobileList(mobiles);
        journalist.setFirstName(txtFirstName.getText());
        journalist.setLastName(txtLastName.getText());
        journalist.setStreet(txtStreet.getText());
        journalist.setCity(txtCity.getText());
        journalist.setCountry(txtCountry.getText());
        if (JournalistController.updateJournalist(journalist)) {
            JOptionPane.showMessageDialog(null, "Journalist updated sucessfully","Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update Journalist", "Fail",JOptionPane.ERROR_MESSAGE,UtilityControll.getImageIcon("fail"));
        }

    }//GEN-LAST:event_btnSaveJournalistActionPerformed

    private void btnSavePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePasswordActionPerformed
        // TODO add your handling code here:
        if (EmployeeController.passwordReset(txtUsername.getText(), txtCurrentPassword.getText(), txtNewPassword.getText())) {
            JOptionPane.showMessageDialog(null,"Password updated sucessfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update password", "Fail", JOptionPane.ERROR_MESSAGE,UtilityControll.getImageIcon("fail"));
        }

        txtCurrentPassword.setText("");
        txtNewPassword.setText("");
        txtConfirmPassword.setText("");
    }//GEN-LAST:event_btnSavePasswordActionPerformed

    private void btnClearNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearNewActionPerformed
        // TODO add your handling code here:
        txtCommentNew.setText("");
        txtDescriptionNew.setText("");
        txtTitleNew.setText("");
        txtLinkNew.setText("");
    }//GEN-LAST:event_btnClearNewActionPerformed

    private void btnSaveSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSubmitActionPerformed
        // TODO add your handling code here:
        new Thread(new Runnable() {
            public void run() {
                jpNew.setVisible(true);
                if (JournalistController.saveSubmitNews(format(txtTitleNew.getText()), format(txtDescriptionNew.getText()), format(txtCommentNew.getText()), format(txtLinkNew.getText()), jpNew)) {
                    updateTable();
                    btnClearNewActionPerformed(null);
                    JOptionPane.showMessageDialog(null, "News Submitted Successfully","Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to Submit news","Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
                }
                jpNew.setVisible(false);
            }
        }).start();
    }//GEN-LAST:event_btnSaveSubmitActionPerformed

    private void btnChooseNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseNewActionPerformed

        FileFilter filter = new FileNameExtensionFilter(null, "zip");
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filter);
        int response = fc.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File zipfile = fc.getSelectedFile();
            txtLinkNew.setText(zipfile.getAbsolutePath());
        }
    }//GEN-LAST:event_btnChooseNewActionPerformed

    private void txtTitleNewKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTitleNewKeyReleased
        // TODO add your handling code here:
        if (checkTitle()) {
            titleExist = true;
            lblTitleWn.setText("News title already exist");
        } else {
            titleExist = false;
            lblTitleWn.setText("");
        }
    }//GEN-LAST:event_txtTitleNewKeyReleased

    private void txtConfirmPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmPasswordKeyReleased
        // TODO add your handling code here:
        if(txtNewPassword.getText().equals(txtConfirmPassword.getText()))
        {
            passwordMismatch = false;
            lblPasswordWn.setText("");  
        }
        else
        {
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
    private javax.swing.JButton btnChooseNew;
    private javax.swing.JButton btnClearNew;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSaveJournalist;
    private javax.swing.JButton btnSavePassword;
    private javax.swing.JButton btnSaveSubmit;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JProgressBar jpNew;
    private javax.swing.JScrollPane jspCommentNew;
    private javax.swing.JScrollPane jspDescriptionNew;
    private javax.swing.JLabel lblAttachMew;
    private javax.swing.JLabel lblCommentNew;
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
    private javax.swing.JLabel lblTitleWn;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JList mobileList;
    private javax.swing.JPanel pnlContainerNew;
    private javax.swing.JPanel pnlNewNews;
    private javax.swing.JPanel pnlNewsList;
    private javax.swing.JPanel pnlSettings;
    private javax.swing.JTable tblNews;
    private javax.swing.JTabbedPane tbpMain;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextArea txtCommentNew;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtCountry;
    private javax.swing.JPasswordField txtCurrentPassword;
    private javax.swing.JTextArea txtDescriptionNew;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtLinkNew;
    private javax.swing.JTextField txtMobile;
    private javax.swing.JPasswordField txtNewPassword;
    private javax.swing.JTextField txtStreet;
    private javax.swing.JTextField txtTitleNew;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
    private boolean titleExist = false;
    private boolean passwordMismatch= false;
}
