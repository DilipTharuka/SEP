/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AdminController;
import controller.ApproverController;
import controller.EditorController;
import controller.EmployeeController;
import controller.IOController;
import controller.JournalistController;
import controller.LoginController;
import controller.NewsController;
import controller.ReceptionistController;
import controller.UtilityControll;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import model.News;
import utility.Validation;

/**
 *
 * @author Dilip
 */
public class Admin extends javax.swing.JFrame {

    /**
     * Creates new form JournalistMain
     */
    public Admin() {
        initComponents();
        updateNewsCombo();
        updateEmployeeIDCombo();
        updateEmployeeNameCombo();
        this.setIconImage(IOController.getFrameImage());
        mobileListAdd.setModel(new DefaultListModel());
        mobileListSearch.setModel(new DefaultListModel());
        mobileListEditing.setModel(new DefaultListModel());
        txtWorkCityAdd.setEnabled(false);
        txtWorkDivisionAdd.setEnabled(false);
        txtWorkProvinceAdd.setEnabled(false);
        ListModel modelAdd = mobileListAdd.getModel();
        ListModel modelEditting = mobileListEditing.getModel();

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    updateNewsCombo();
                    updateEmployeeIDCombo();
                    updateEmployeeNameCombo();
                    try {
                        Thread.sleep(100000);
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

        new Thread() {
            public void run() {

                while (true) {
                    if (txtPasswordAdd.getText().equals("") || txtUsernameAdd.getText().equals("") || txtFirstNameAdd.getText().equals("")
                            || txtLastNameAdd.getText().equals("") || txtCityAdd.getText().equals("") || txtStreetAdd.getText().equals("")
                            || txtCountryAdd.getText().equals("") || modelAdd.getSize() == 0 || cmbEmployeeTypeAdd.getSelectedItem().toString().equals("Select Designation")) {
                        btnSaveAdd.setEnabled(false);
                    } else if (cmbEmployeeTypeAdd.getSelectedItem().toString().equals("Journalist") && (txtWorkCityAdd.getText().equals("")
                            || txtWorkProvinceAdd.getText().equals("") || txtWorkDivisionAdd.getText().equals(""))) {
                        btnSaveAdd.setEnabled(false);
                    } else {
                        btnSaveAdd.setEnabled(true);
                    }

                    if (txtMobileAdd.getText().length() != 10) {
                        btnAddAdd.setEnabled(false);
                    } else {
                        btnAddAdd.setEnabled(true);
                    }
                    if (mobileListAdd.getSelectedValue() == null) {
                        btnDeleteAdd.setEnabled(false);
                    } else {
                        btnDeleteAdd.setEnabled(true);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
        
        
        new Thread() {
            public void run() {
                while (true) {
                    if (txtUsernameEditing.getText().equals("") || txtFirstNameEditing.getText().equals("")
                            || txtLastNameEditing.getText().equals("") || txtCityEditing.getText().equals("") || txtStreetEditing.getText().equals("")
                            || txtCountryEditing.getText().equals("") || modelEditting.getSize() == 0 || cmbEmployeeTypeEditing.getSelectedItem().toString().equals("Select Designation")) {
                        btnSaveEditing.setEnabled(false);
                    } 
                       else if (cmbEmployeeTypeEditing.getSelectedItem().toString().equals("Journalist") && (txtWorkCityEditing.getText().equals("")
                            || txtWorkProvinceEditing.getText().equals("") || txtWorkDivisionEditing.getText().equals(""))) {
                        btnSaveEditing.setEnabled(false);
                    } 
                    else {
                        btnSaveEditing.setEnabled(true);
                    }
                    if(cmbEmployeeTypeEditing.getSelectedItem().toString().equals("Journalist"))
                    {
                        txtWorkCityEditing.setEnabled(true);
                        txtWorkDivisionEditing.setEnabled(true);
                        txtWorkProvinceEditing.setEnabled(true);                       
                    }
                    else
                    {
                        txtWorkCityEditing.setEnabled(false);
                        txtWorkDivisionEditing.setEnabled(false);
                        txtWorkProvinceEditing.setEnabled(false);
                    }
                    if (txtMobileEditing.getText().length() != 10) {
                        btnAddEditing.setEnabled(false);
                    } else {
                        btnAddEditing.setEnabled(true);
                    }
                    if (mobileListEditing.getSelectedValue() == null) {
                        btnDeleteEditing.setEnabled(false);
                    } else {
                        btnDeleteEditing.setEnabled(true);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }

    private void updateNewsCombo() {
        UtilityControll.setComboItemSpecific(cmbSearchNews, "title", "news", "state", "7");
    }

    private void updateEmployeeIDCombo() {
        cmbSearchEmployeeID.removeAllItems();
        cmbSearchEmployeeID.addItem("Search by ID");
        UtilityControll.setComboItem(cmbSearchEmployeeID, "employee", "ID");
        cmbEditEmployeeID.removeAllItems();
        cmbEditEmployeeID.addItem("Search by ID");
        UtilityControll.setComboItem(cmbEditEmployeeID, "employee", "ID");
    }

    private void updateEmployeeNameCombo() {
        cmbSearchEmployeeUsername.removeAllItems();
        cmbSearchEmployeeUsername.addItem("Search by Name");
        UtilityControll.setComboItem(cmbSearchEmployeeUsername, "employee", "username");
        cmbEditEmployeeUsername.removeAllItems();
        cmbEditEmployeeUsername.addItem("Search by Name");
        UtilityControll.setComboItem(cmbEditEmployeeUsername, "employee", "username");
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
        pnlAddEmployee = new javax.swing.JPanel();
        pnlContainerNew = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblTitleNew7 = new javax.swing.JLabel();
        lblTitleNew6 = new javax.swing.JLabel();
        lblTitleNew5 = new javax.swing.JLabel();
        lblTitleNew3 = new javax.swing.JLabel();
        txtUsernameAdd = new javax.swing.JTextField();
        txtCountryAdd = new javax.swing.JTextField();
        lblTitleNew4 = new javax.swing.JLabel();
        lblLastName = new javax.swing.JLabel();
        txtCityAdd = new javax.swing.JTextField();
        lblFirstName = new javax.swing.JLabel();
        txtLastNameAdd = new javax.swing.JTextField();
        txtStreetAdd = new javax.swing.JTextField();
        lblTitleNew2 = new javax.swing.JLabel();
        txtFirstNameAdd = new javax.swing.JTextField();
        txtPasswordAdd = new javax.swing.JPasswordField();
        cmbEmployeeTypeAdd = new javax.swing.JComboBox();
        btnClearAdd = new javax.swing.JButton();
        btnSaveAdd = new javax.swing.JButton();
        txtWorkDivisionAdd = new javax.swing.JTextField();
        lblTitleNew8 = new javax.swing.JLabel();
        txtWorkCityAdd = new javax.swing.JTextField();
        txtWorkProvinceAdd = new javax.swing.JTextField();
        lblTitleNew9 = new javax.swing.JLabel();
        lblTitleNew10 = new javax.swing.JLabel();
        lblTitleNew11 = new javax.swing.JLabel();
        txtMobileAdd = new javax.swing.JTextField();
        btnAddAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mobileListAdd = new javax.swing.JList();
        btnDeleteAdd = new javax.swing.JButton();
        pnlEditEmployee = new javax.swing.JPanel();
        pnlContainerNew1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lblTitleNew12 = new javax.swing.JLabel();
        lblTitleNew43 = new javax.swing.JLabel();
        lblTitleNew44 = new javax.swing.JLabel();
        lblTitleNew45 = new javax.swing.JLabel();
        txtUsernameEditing = new javax.swing.JTextField();
        txtCountryEditing = new javax.swing.JTextField();
        lblTitleNew46 = new javax.swing.JLabel();
        lblLastName4 = new javax.swing.JLabel();
        txtCityEditing = new javax.swing.JTextField();
        lblFirstName4 = new javax.swing.JLabel();
        txtLastNameEditing = new javax.swing.JTextField();
        txtStreetEditing = new javax.swing.JTextField();
        txtFirstNameEditing = new javax.swing.JTextField();
        cmbEmployeeTypeEditing = new javax.swing.JComboBox();
        btnClearEditing = new javax.swing.JButton();
        btnSaveEditing = new javax.swing.JButton();
        txtWorkDivisionEditing = new javax.swing.JTextField();
        lblTitleNew48 = new javax.swing.JLabel();
        txtWorkCityEditing = new javax.swing.JTextField();
        txtWorkProvinceEditing = new javax.swing.JTextField();
        lblTitleNew49 = new javax.swing.JLabel();
        lblTitleNew50 = new javax.swing.JLabel();
        lblTitleNew51 = new javax.swing.JLabel();
        txtMobileEditing = new javax.swing.JTextField();
        btnAddEditing = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        mobileListEditing = new javax.swing.JList();
        btnDeleteEditing = new javax.swing.JButton();
        lblFirstName5 = new javax.swing.JLabel();
        cmbEditEmployeeUsername = new javax.swing.JComboBox();
        cmbEditEmployeeID = new javax.swing.JComboBox();
        pnlSearchNews = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblFirstName8 = new javax.swing.JLabel();
        cmbSearchNews = new javax.swing.JComboBox();
        txtPublishedDate = new javax.swing.JTextField();
        lblFirstName10 = new javax.swing.JLabel();
        txtPublishedTime = new javax.swing.JTextField();
        lblFirstName11 = new javax.swing.JLabel();
        txtJournalistID = new javax.swing.JTextField();
        txtReceptionistID = new javax.swing.JTextField();
        lblFirstName12 = new javax.swing.JLabel();
        lblFirstName13 = new javax.swing.JLabel();
        txtApproverID = new javax.swing.JTextField();
        txtEditorID = new javax.swing.JTextField();
        lblFirstName14 = new javax.swing.JLabel();
        lblFirstName15 = new javax.swing.JLabel();
        lblFirstName16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        pnlSearchEmployee = new javax.swing.JPanel();
        pnlContainerEdit = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        lblTitleNew52 = new javax.swing.JLabel();
        lblTitleNew53 = new javax.swing.JLabel();
        lblTitleNew54 = new javax.swing.JLabel();
        lblTitleNew55 = new javax.swing.JLabel();
        txtUsernameSearch = new javax.swing.JTextField();
        txtCountrySearch = new javax.swing.JTextField();
        lblTitleNew56 = new javax.swing.JLabel();
        lblLastName5 = new javax.swing.JLabel();
        txtCitySearch = new javax.swing.JTextField();
        lblFirstName6 = new javax.swing.JLabel();
        txtLastNameSearch = new javax.swing.JTextField();
        txtStreetSearch = new javax.swing.JTextField();
        txtFirstNameSearch = new javax.swing.JTextField();
        txtWorkDivisionSearch = new javax.swing.JTextField();
        lblTitleNew58 = new javax.swing.JLabel();
        txtWorkCitySearch = new javax.swing.JTextField();
        txtWorkProvinceSearch = new javax.swing.JTextField();
        lblTitleNew59 = new javax.swing.JLabel();
        lblTitleNew60 = new javax.swing.JLabel();
        lblTitleNew61 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        mobileListSearch = new javax.swing.JList();
        lblFirstName7 = new javax.swing.JLabel();
        cmbSearchEmployeeID = new javax.swing.JComboBox();
        txtEmployeeDesignationSearch = new javax.swing.JTextField();
        cmbSearchEmployeeUsername = new javax.swing.JComboBox();
        txtJoinDate = new javax.swing.JTextField();
        lblTitleNew62 = new javax.swing.JLabel();
        txtLeftDate = new javax.swing.JTextField();
        lblTitleNew63 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin");

        jScrollPane3.setBorder(null);

        tbpMain.setToolTipText("new news");
        tbpMain.setFocusable(false);
        tbpMain.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        pnlAddEmployee.setBackground(new java.awt.Color(255, 255, 255));
        pnlAddEmployee.setPreferredSize(new java.awt.Dimension(1225, 603));

        pnlContainerNew.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblTitleNew7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew7.setText("Country :");

        lblTitleNew6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew6.setText("      Street :");

        lblTitleNew5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew5.setText("City :");

        lblTitleNew3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew3.setText(" Username :");

        txtUsernameAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtCountryAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew4.setText("Employee Type :");

        lblLastName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblLastName.setText("Last Name :");

        txtCityAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblFirstName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFirstName.setText("First Name :");

        txtLastNameAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtStreetAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew2.setText(" Password :");

        txtFirstNameAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cmbEmployeeTypeAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbEmployeeTypeAdd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Designation", "Journalist", "Receptionist", "Approver", "Editor", "Admin" }));
        cmbEmployeeTypeAdd.setFocusable(false);
        cmbEmployeeTypeAdd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEmployeeTypeAddItemStateChanged(evt);
            }
        });

        btnClearAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnClearAdd.setText("Clear");
        btnClearAdd.setFocusable(false);
        btnClearAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAddActionPerformed(evt);
            }
        });

        btnSaveAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSaveAdd.setText("Save");
        btnSaveAdd.setFocusable(false);
        btnSaveAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAddActionPerformed(evt);
            }
        });

        txtWorkDivisionAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew8.setText("Division :");

        txtWorkCityAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtWorkProvinceAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew9.setText("City :");

        lblTitleNew10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew10.setText("Provience :");
        lblTitleNew10.setAlignmentX(0.5F);

        lblTitleNew11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew11.setText("      Mobile :");

        txtMobileAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMobileAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMobileAddKeyReleased(evt);
            }
        });

        btnAddAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAddAdd.setText("Add");
        btnAddAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAddActionPerformed(evt);
            }
        });

        mobileListAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(mobileListAdd);

        btnDeleteAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDeleteAdd.setText("Delete");
        btnDeleteAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTitleNew3)
                            .addComponent(lblFirstName)
                            .addComponent(lblTitleNew6)
                            .addComponent(lblTitleNew11))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(lblTitleNew4)
                            .addGap(18, 18, 18))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(lblTitleNew10)
                            .addGap(18, 18, 18))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtWorkProvinceAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cmbEmployeeTypeAdd, javax.swing.GroupLayout.Alignment.LEADING, 0, 210, Short.MAX_VALUE)
                        .addComponent(txtMobileAdd, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtStreetAdd, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtUsernameAdd, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtFirstNameAdd, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAddAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTitleNew5)
                    .addComponent(lblTitleNew2)
                    .addComponent(lblLastName)
                    .addComponent(lblTitleNew9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtWorkCityAdd)
                                .addGap(23, 23, 23)
                                .addComponent(lblTitleNew8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCityAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTitleNew7)
                                .addGap(11, 11, 11)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtWorkDivisionAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCountryAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(282, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPasswordAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClearAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSaveAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtFirstNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsernameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitleNew3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStreetAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitleNew6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTitleNew2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPasswordAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTitleNew5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCityAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitleNew7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCountryAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMobileAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitleNew11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbEmployeeTypeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtWorkProvinceAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWorkCityAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWorkDivisionAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout pnlContainerNewLayout = new javax.swing.GroupLayout(pnlContainerNew);
        pnlContainerNew.setLayout(pnlContainerNewLayout);
        pnlContainerNewLayout.setHorizontalGroup(
            pnlContainerNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerNewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlContainerNewLayout.setVerticalGroup(
            pnlContainerNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerNewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlAddEmployeeLayout = new javax.swing.GroupLayout(pnlAddEmployee);
        pnlAddEmployee.setLayout(pnlAddEmployeeLayout);
        pnlAddEmployeeLayout.setHorizontalGroup(
            pnlAddEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddEmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlContainerNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlAddEmployeeLayout.setVerticalGroup(
            pnlAddEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddEmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlContainerNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpMain.addTab("  Add Employee    ", new javax.swing.ImageIcon(getClass().getResource("/resourse/add_employee.png")), pnlAddEmployee); // NOI18N

        pnlEditEmployee.setBackground(new java.awt.Color(255, 255, 255));

        pnlContainerNew1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        lblTitleNew12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew12.setText("Country :");

        lblTitleNew43.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew43.setText("      Street :");

        lblTitleNew44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew44.setText("City :");

        lblTitleNew45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew45.setText(" Username :");

        txtUsernameEditing.setEditable(false);
        txtUsernameEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtCountryEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew46.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew46.setText("Employee Type :");

        lblLastName4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblLastName4.setText("Last Name :");

        txtCityEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblFirstName4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFirstName4.setText("First Name :");

        txtLastNameEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtStreetEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtFirstNameEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cmbEmployeeTypeEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbEmployeeTypeEditing.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Designation", "Journalist", "Receptionist", "Approver", "Editor", "Admin" }));
        cmbEmployeeTypeEditing.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEmployeeTypeEditingItemStateChanged(evt);
            }
        });

        btnClearEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnClearEditing.setText("Clear");
        btnClearEditing.setFocusable(false);
        btnClearEditing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearEditingActionPerformed(evt);
            }
        });

        btnSaveEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSaveEditing.setText("Save");
        btnSaveEditing.setFocusable(false);
        btnSaveEditing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveEditingActionPerformed(evt);
            }
        });

        txtWorkDivisionEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew48.setText("Division :");

        txtWorkCityEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtWorkProvinceEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew49.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew49.setText("City :");

        lblTitleNew50.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew50.setText("Provience :");
        lblTitleNew50.setAlignmentX(0.5F);

        lblTitleNew51.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew51.setText("      Mobile :");

        txtMobileEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMobileEditing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMobileEditingKeyReleased(evt);
            }
        });

        btnAddEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAddEditing.setText("Add");
        btnAddEditing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEditingActionPerformed(evt);
            }
        });

        mobileListEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane6.setViewportView(mobileListEditing);

        btnDeleteEditing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDeleteEditing.setText("Delete");
        btnDeleteEditing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteEditingActionPerformed(evt);
            }
        });

        lblFirstName5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFirstName5.setText("Search Employee :");

        cmbEditEmployeeUsername.setEditable(true);
        cmbEditEmployeeUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbEditEmployeeUsername.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Search by Name" }));
        cmbEditEmployeeUsername.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEditEmployeeUsernameItemStateChanged(evt);
            }
        });

        cmbEditEmployeeID.setEditable(true);
        cmbEditEmployeeID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbEditEmployeeID.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Search by ID" }));
        cmbEditEmployeeID.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEditEmployeeIDItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(994, Short.MAX_VALUE)
                .addComponent(btnClearEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSaveEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblFirstName5)
                        .addGap(18, 18, 18)
                        .addComponent(cmbEditEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTitleNew45)
                                    .addComponent(lblFirstName4)
                                    .addComponent(lblTitleNew43)
                                    .addComponent(lblTitleNew51))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                    .addComponent(lblTitleNew46)
                                    .addGap(18, 18, 18))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                    .addComponent(lblTitleNew50)
                                    .addGap(18, 18, 18))))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtWorkProvinceEditing, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cmbEmployeeTypeEditing, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMobileEditing, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtStreetEditing, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtUsernameEditing, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtFirstNameEditing, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAddEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTitleNew44)
                    .addComponent(lblLastName4)
                    .addComponent(lblTitleNew49))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtWorkCityEditing)
                                .addGap(23, 23, 23)
                                .addComponent(lblTitleNew48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtCityEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTitleNew12)
                                .addGap(11, 11, 11)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtWorkDivisionEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCountryEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastNameEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEditEmployeeUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstName5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEditEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEditEmployeeUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFirstName4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtFirstNameEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsernameEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitleNew45, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStreetEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitleNew43, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLastName4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastNameEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTitleNew44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCityEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitleNew12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCountryEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMobileEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitleNew51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbEmployeeTypeEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew46, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtWorkProvinceEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWorkCityEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew48, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWorkDivisionEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearEditing, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout pnlContainerNew1Layout = new javax.swing.GroupLayout(pnlContainerNew1);
        pnlContainerNew1.setLayout(pnlContainerNew1Layout);
        pnlContainerNew1Layout.setHorizontalGroup(
            pnlContainerNew1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerNew1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlContainerNew1Layout.setVerticalGroup(
            pnlContainerNew1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerNew1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlEditEmployeeLayout = new javax.swing.GroupLayout(pnlEditEmployee);
        pnlEditEmployee.setLayout(pnlEditEmployeeLayout);
        pnlEditEmployeeLayout.setHorizontalGroup(
            pnlEditEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditEmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlContainerNew1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlEditEmployeeLayout.setVerticalGroup(
            pnlEditEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditEmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlContainerNew1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpMain.addTab("  Edit Employee    ", new javax.swing.ImageIcon(getClass().getResource("/resourse/edit_employee.png")), pnlEditEmployee); // NOI18N

        pnlSearchNews.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lblFirstName8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFirstName8.setText("Search News :");

        cmbSearchNews.setEditable(true);
        cmbSearchNews.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbSearchNews.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSearchNewsItemStateChanged(evt);
            }
        });

        txtPublishedDate.setEditable(false);
        txtPublishedDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblFirstName10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFirstName10.setText("Published Date :");

        txtPublishedTime.setEditable(false);
        txtPublishedTime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblFirstName11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFirstName11.setText("Published Time :");

        txtJournalistID.setEditable(false);
        txtJournalistID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtReceptionistID.setEditable(false);
        txtReceptionistID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblFirstName12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFirstName12.setText("Journalist ID :");

        lblFirstName13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFirstName13.setText("Receptionist ID :");

        txtApproverID.setEditable(false);
        txtApproverID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtEditorID.setEditable(false);
        txtEditorID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblFirstName14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFirstName14.setText("Editor ID :");

        lblFirstName15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFirstName15.setText("Approver ID :");

        lblFirstName16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFirstName16.setText("Description :");

        txtDescription.setEditable(false);
        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lblFirstName10)
                                .addGap(18, 18, 18)
                                .addComponent(txtPublishedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lblFirstName11)
                                .addGap(18, 18, 18)
                                .addComponent(txtPublishedTime, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFirstName16)
                            .addComponent(lblFirstName8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(cmbSearchNews, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(120, 120, 120))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(lblFirstName13)
                                .addGap(18, 18, 18)
                                .addComponent(txtReceptionistID, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(lblFirstName12)
                                .addGap(18, 18, 18)
                                .addComponent(txtJournalistID, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFirstName14, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblFirstName15, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEditorID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtApproverID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(182, 182, 182))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFirstName12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtJournalistID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFirstName13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtReceptionistID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFirstName14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEditorID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFirstName15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApproverID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFirstName8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbSearchNews, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFirstName16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPublishedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFirstName10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPublishedTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFirstName11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlSearchNewsLayout = new javax.swing.GroupLayout(pnlSearchNews);
        pnlSearchNews.setLayout(pnlSearchNewsLayout);
        pnlSearchNewsLayout.setHorizontalGroup(
            pnlSearchNewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchNewsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSearchNewsLayout.setVerticalGroup(
            pnlSearchNewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchNewsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpMain.addTab(" Search News  ", new javax.swing.ImageIcon(getClass().getResource("/resourse/search-news.png")), pnlSearchNews); // NOI18N

        pnlSearchEmployee.setBackground(new java.awt.Color(255, 255, 255));

        pnlContainerEdit.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        lblTitleNew52.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew52.setText("Country :");

        lblTitleNew53.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew53.setText("      Street :");

        lblTitleNew54.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew54.setText("City :");

        lblTitleNew55.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew55.setText(" Username :");

        txtUsernameSearch.setEditable(false);
        txtUsernameSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtCountrySearch.setEditable(false);
        txtCountrySearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew56.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew56.setText("Employee Designation :");

        lblLastName5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblLastName5.setText("Last Name :");

        txtCitySearch.setEditable(false);
        txtCitySearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblFirstName6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFirstName6.setText("First Name :");

        txtLastNameSearch.setEditable(false);
        txtLastNameSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtStreetSearch.setEditable(false);
        txtStreetSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtFirstNameSearch.setEditable(false);
        txtFirstNameSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtWorkDivisionSearch.setEditable(false);
        txtWorkDivisionSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew58.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew58.setText("Division :");

        txtWorkCitySearch.setEditable(false);
        txtWorkCitySearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtWorkProvinceSearch.setEditable(false);
        txtWorkProvinceSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew59.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew59.setText("City :");

        lblTitleNew60.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew60.setText("Provience :");
        lblTitleNew60.setAlignmentX(0.5F);

        lblTitleNew61.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew61.setText("      Mobile :");

        mobileListSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane7.setViewportView(mobileListSearch);

        lblFirstName7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFirstName7.setText("Search Employee :");

        cmbSearchEmployeeID.setEditable(true);
        cmbSearchEmployeeID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbSearchEmployeeID.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Search by ID" }));
        cmbSearchEmployeeID.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSearchEmployeeIDItemStateChanged(evt);
            }
        });
        cmbSearchEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSearchEmployeeIDActionPerformed(evt);
            }
        });

        txtEmployeeDesignationSearch.setEditable(false);
        txtEmployeeDesignationSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cmbSearchEmployeeUsername.setEditable(true);
        cmbSearchEmployeeUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbSearchEmployeeUsername.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Search by Name" }));
        cmbSearchEmployeeUsername.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSearchEmployeeUsernameItemStateChanged(evt);
            }
        });

        txtJoinDate.setEditable(false);
        txtJoinDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew62.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew62.setText("Start Date :");
        lblTitleNew62.setAlignmentX(0.5F);

        txtLeftDate.setEditable(false);
        txtLeftDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTitleNew63.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleNew63.setText("Left Date :");
        lblTitleNew63.setAlignmentX(0.5F);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTitleNew55)
                                    .addComponent(lblFirstName6)
                                    .addComponent(lblTitleNew53)
                                    .addComponent(lblTitleNew61)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(138, 138, 138)
                                .addComponent(lblTitleNew60))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(lblTitleNew56)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtWorkProvinceSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtStreetSearch, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtUsernameSearch, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtFirstNameSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEmployeeDesignationSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(lblFirstName7)
                        .addGap(18, 18, 18)
                        .addComponent(cmbSearchEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(lblTitleNew62)
                        .addGap(18, 18, 18)
                        .addComponent(txtJoinDate, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitleNew54)
                    .addComponent(lblLastName5)
                    .addComponent(lblTitleNew59)
                    .addComponent(lblTitleNew63))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbSearchEmployeeUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(txtWorkCitySearch)
                                .addGap(23, 23, 23)
                                .addComponent(lblTitleNew58)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(txtCitySearch, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTitleNew52)
                                .addGap(11, 11, 11)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtWorkDivisionSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCountrySearch, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtLastNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLeftDate, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstName7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSearchEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSearchEmployeeUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFirstName6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtFirstNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsernameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitleNew55, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStreetSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitleNew53, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLastName5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTitleNew54, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCitySearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitleNew52, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCountrySearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitleNew61, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitleNew56, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmployeeDesignationSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtWorkProvinceSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew60, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew59, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWorkCitySearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNew58, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWorkDivisionSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLeftDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTitleNew63, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtJoinDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTitleNew62, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContainerEditLayout = new javax.swing.GroupLayout(pnlContainerEdit);
        pnlContainerEdit.setLayout(pnlContainerEditLayout);
        pnlContainerEditLayout.setHorizontalGroup(
            pnlContainerEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlContainerEditLayout.setVerticalGroup(
            pnlContainerEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlSearchEmployeeLayout = new javax.swing.GroupLayout(pnlSearchEmployee);
        pnlSearchEmployee.setLayout(pnlSearchEmployeeLayout);
        pnlSearchEmployeeLayout.setHorizontalGroup(
            pnlSearchEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1274, Short.MAX_VALUE)
            .addGroup(pnlSearchEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlSearchEmployeeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlContainerEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlSearchEmployeeLayout.setVerticalGroup(
            pnlSearchEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
            .addGroup(pnlSearchEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlSearchEmployeeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlContainerEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        tbpMain.addTab("  Search Employee    ", new javax.swing.ImageIcon(getClass().getResource("/resourse/search-employee.png")), pnlSearchEmployee); // NOI18N

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
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAddActionPerformed
        // TODO add your handling code here:
        txtUsernameAdd.setText("");
        txtPasswordAdd.setText("");
        txtFirstNameAdd.setText("");
        txtLastNameAdd.setText("");
        txtStreetAdd.setText("");
        txtCityAdd.setText("");
        txtCountryAdd.setText("");
        txtWorkDivisionAdd.setText("");
        txtWorkCityAdd.setText("");
        txtWorkProvinceAdd.setText("");
        txtMobileAdd.setText("");
        cmbEmployeeTypeAdd.setSelectedIndex(0);
        DefaultListModel model = (DefaultListModel) mobileListAdd.getModel();
        model.removeAllElements();
    }//GEN-LAST:event_btnClearAddActionPerformed

    private void btnSaveAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAddActionPerformed
        // TODO add your handling code here:

        String select = cmbEmployeeTypeAdd.getSelectedItem().toString();
        if (select.equals("Journalist")) {
            model.Journalist journalist = EmployeeController.getJournalist();
            ArrayList<String> mobiles = new ArrayList<String>();
            ListModel model = mobileListAdd.getModel();

            for (int i = 0; i < model.getSize(); i++) {
                mobiles.add(model.getElementAt(i).toString());
            }
            journalist.setUsername(txtUsernameAdd.getText());
            journalist.setPassword(txtPasswordAdd.getText());
            journalist.setFirstName(txtFirstNameAdd.getText());
            journalist.setLastName(txtLastNameAdd.getText());
            journalist.setStreet(txtStreetAdd.getText());
            journalist.setCity(txtCityAdd.getText());
            journalist.setCountry(txtCountryAdd.getText());
            journalist.setMobileList(mobiles);
            journalist.setWorkdivision(txtWorkDivisionAdd.getText());
            journalist.setWorkCity(txtWorkCityAdd.getText());
            journalist.setWorkProvince(txtWorkProvinceAdd.getText());

            if (AdminController.addEmployee(journalist)) {
                JOptionPane.showMessageDialog(null, "Employee saved sucessfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save employee", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
            }
        } else if (select.equals("Admin")) {
            model.Admin admin = EmployeeController.getAdmin();
            ArrayList<String> mobiles = new ArrayList<String>();
            ListModel model = mobileListAdd.getModel();

            for (int i = 0; i < model.getSize(); i++) {
                mobiles.add(model.getElementAt(i).toString());
            }
            admin.setUsername(txtUsernameAdd.getText());
            admin.setPassword(txtPasswordAdd.getText());
            admin.setFirstName(txtFirstNameAdd.getText());
            admin.setLastName(txtLastNameAdd.getText());
            admin.setStreet(txtStreetAdd.getText());
            admin.setCity(txtCityAdd.getText());
            admin.setCountry(txtCountryAdd.getText());
            admin.setMobileList(mobiles);
            if (AdminController.addEmployee(admin)) {
                JOptionPane.showMessageDialog(null, "Employee saved sucessfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save employee", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
            }
        } else if (select.equals("Receptionist")) {
            model.Receptionist receptionist = EmployeeController.getReceptionist();
            ArrayList<String> mobiles = new ArrayList<String>();
            ListModel model = mobileListAdd.getModel();

            for (int i = 0; i < model.getSize(); i++) {
                mobiles.add(model.getElementAt(i).toString());
            }
            receptionist.setUsername(txtUsernameAdd.getText());
            receptionist.setPassword(txtPasswordAdd.getText());
            receptionist.setFirstName(txtFirstNameAdd.getText());
            receptionist.setLastName(txtLastNameAdd.getText());
            receptionist.setStreet(txtStreetAdd.getText());
            receptionist.setCity(txtCityAdd.getText());
            receptionist.setCountry(txtCountryAdd.getText());
            receptionist.setMobileList(mobiles);
            if (AdminController.addEmployee(receptionist)) {
                JOptionPane.showMessageDialog(null, "Employee saved sucessfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save employee", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
            }
        } else if (select.equals("Approver")) {
            model.Approver approver = EmployeeController.getApprover();
            ArrayList<String> mobiles = new ArrayList<String>();
            ListModel model = mobileListAdd.getModel();

            for (int i = 0; i < model.getSize(); i++) {
                mobiles.add(model.getElementAt(i).toString());
            }

            approver.setUsername(txtUsernameAdd.getText());
            approver.setPassword(txtPasswordAdd.getText());
            approver.setFirstName(txtFirstNameAdd.getText());
            approver.setLastName(txtLastNameAdd.getText());
            approver.setStreet(txtStreetAdd.getText());
            approver.setCity(txtCityAdd.getText());
            approver.setCountry(txtCountryAdd.getText());
            approver.setMobileList(mobiles);
            if (AdminController.addEmployee(approver)) {
                JOptionPane.showMessageDialog(null, "Employee saved sucessfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save employee", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
            }
        } else {
            model.Editor editor = EmployeeController.getEditor();
            ArrayList<String> mobiles = new ArrayList<String>();
            ListModel model = mobileListAdd.getModel();

            for (int i = 0; i < model.getSize(); i++) {
                mobiles.add(model.getElementAt(i).toString());
            }
            editor.setUsername(txtUsernameAdd.getText());
            editor.setPassword(txtPasswordAdd.getText());
            editor.setFirstName(txtFirstNameAdd.getText());
            editor.setLastName(txtLastNameAdd.getText());
            editor.setStreet(txtStreetAdd.getText());
            editor.setCity(txtCityAdd.getText());
            editor.setCountry(txtCountryAdd.getText());
            editor.setMobileList(mobiles);
            if (AdminController.addEmployee(editor)) {
                JOptionPane.showMessageDialog(null, "Employee saved sucessfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save employee", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
            }            
            btnClearAddActionPerformed(null) ;
        }
    }//GEN-LAST:event_btnSaveAddActionPerformed

    private void btnAddAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAddActionPerformed
        // TODO add your handling code here:
        DefaultListModel model = (DefaultListModel) mobileListAdd.getModel();
        model.addElement(txtMobileAdd.getText());
        txtMobileAdd.setText("");
    }//GEN-LAST:event_btnAddAddActionPerformed

    private void btnDeleteAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAddActionPerformed
        // TODO add your handling code here:
        DefaultListModel model = (DefaultListModel) mobileListAdd.getModel();
        model.remove(mobileListAdd.getSelectedIndex());
    }//GEN-LAST:event_btnDeleteAddActionPerformed

    private void cmbEmployeeTypeAddItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEmployeeTypeAddItemStateChanged
        // TODO add your handling code here:
        if (cmbEmployeeTypeAdd.getSelectedItem().toString().equals("Journalist")) {
            txtWorkCityAdd.setEnabled(true);
            txtWorkDivisionAdd.setEnabled(true);
            txtWorkProvinceAdd.setEnabled(true);
        } else {
            txtWorkCityAdd.setEnabled(false);
            txtWorkDivisionAdd.setEnabled(false);
            txtWorkProvinceAdd.setEnabled(false);
        }
    }//GEN-LAST:event_cmbEmployeeTypeAddItemStateChanged

    private void cmbEmployeeTypeEditingItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEmployeeTypeEditingItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEmployeeTypeEditingItemStateChanged

    private void btnClearEditingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearEditingActionPerformed
        // TODO add your handling code here:
        txtUsernameEditing.setText("");
        txtFirstNameEditing.setText("");
        txtLastNameEditing.setText("");
        txtStreetEditing.setText("");
        txtCityEditing.setText("");
        txtCountryEditing.setText("");
        txtWorkDivisionEditing.setText("");
        txtWorkCityEditing.setText("");
        txtWorkProvinceEditing.setText("");
        txtMobileEditing.setText("");
        cmbEmployeeTypeEditing.setSelectedIndex(0);
        DefaultListModel model = (DefaultListModel) mobileListEditing.getModel();
        model.removeAllElements();
    }//GEN-LAST:event_btnClearEditingActionPerformed

    private void btnSaveEditingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveEditingActionPerformed
        // TODO add your handling code here:
        String select = cmbEmployeeTypeEditing.getSelectedItem().toString();
        if (select.equals("Journalist")) {
            model.Journalist journalist = EmployeeController.getJournalist();
            ArrayList<String> mobiles = new ArrayList<String>();
            ListModel model = mobileListEditing.getModel();

            for (int i = 0; i < model.getSize(); i++) {
                mobiles.add(model.getElementAt(i).toString());
            }
            journalist.setUsername(txtUsernameEditing.getText());
            journalist.setFirstName(txtFirstNameEditing.getText());
            journalist.setLastName(txtLastNameEditing.getText());
            journalist.setStreet(txtStreetEditing.getText());
            journalist.setCity(txtCityEditing.getText());
            journalist.setCountry(txtCountryEditing.getText());
            journalist.setMobileList(mobiles);
            journalist.setWorkdivision(txtWorkDivisionEditing.getText());
            journalist.setWorkCity(txtWorkCityEditing.getText());
            journalist.setWorkProvince(txtWorkProvinceEditing.getText());

            if (AdminController.updateEmployee(journalist)) {
                JOptionPane.showMessageDialog(null, "Employee saved sucessfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save employee", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
            }
        } else if (select.equals("Admin")) {
            model.Admin admin = EmployeeController.getAdmin();
            ArrayList<String> mobiles = new ArrayList<String>();
            ListModel model = mobileListEditing.getModel();

            for (int i = 0; i < model.getSize(); i++) {
                mobiles.add(model.getElementAt(i).toString());
            }
            admin.setUsername(txtUsernameEditing.getText());
            admin.setFirstName(txtFirstNameEditing.getText());
            admin.setLastName(txtLastNameEditing.getText());
            admin.setStreet(txtStreetEditing.getText());
            admin.setCity(txtCityEditing.getText());
            admin.setCountry(txtCountryEditing.getText());
            admin.setMobileList(mobiles);
            if (AdminController.updateEmployee(admin)) {
                JOptionPane.showMessageDialog(null, "Employee saved sucessfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save employee", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
            }
        } else if (select.equals("Receptionist")) {
            model.Receptionist receptionist = EmployeeController.getReceptionist();
            ArrayList<String> mobiles = new ArrayList<String>();
            ListModel model = mobileListEditing.getModel();

            for (int i = 0; i < model.getSize(); i++) {
                mobiles.add(model.getElementAt(i).toString());
            }
            receptionist.setUsername(txtUsernameEditing.getText());
            receptionist.setFirstName(txtFirstNameEditing.getText());
            receptionist.setLastName(txtLastNameEditing.getText());
            receptionist.setStreet(txtStreetEditing.getText());
            receptionist.setCity(txtCityEditing.getText());
            receptionist.setCountry(txtCountryEditing.getText());
            receptionist.setMobileList(mobiles);
            if (AdminController.updateEmployee(receptionist)) {
                JOptionPane.showMessageDialog(null, "Employee saved sucessfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save employee", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
            }
        } else if (select.equals("Approver")) {
            model.Approver approver = EmployeeController.getApprover();
            ArrayList<String> mobiles = new ArrayList<String>();
            ListModel model = mobileListEditing.getModel();

            for (int i = 0; i < model.getSize(); i++) {
                mobiles.add(model.getElementAt(i).toString());
            }

            approver.setUsername(txtUsernameEditing.getText());
            approver.setFirstName(txtFirstNameEditing.getText());
            approver.setLastName(txtLastNameEditing.getText());
            approver.setStreet(txtStreetEditing.getText());
            approver.setCity(txtCityEditing.getText());
            approver.setCountry(txtCountryEditing.getText());
            approver.setMobileList(mobiles);
            if (AdminController.updateEmployee(approver)) {
                JOptionPane.showMessageDialog(null, "Employee saved sucessfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save employee", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
            }
        } else {
            model.Editor editor = EmployeeController.getEditor();
            ArrayList<String> mobiles = new ArrayList<String>();
            ListModel model = mobileListEditing.getModel();

            for (int i = 0; i < model.getSize(); i++) {
                mobiles.add(model.getElementAt(i).toString());
            }
            editor.setUsername(txtUsernameEditing.getText());
            editor.setFirstName(txtFirstNameEditing.getText());
            editor.setLastName(txtLastNameEditing.getText());
            editor.setStreet(txtStreetEditing.getText());
            editor.setCity(txtCityEditing.getText());
            editor.setCountry(txtCountryEditing.getText());
            editor.setMobileList(mobiles);
            if (AdminController.updateEmployee(editor)) {
                JOptionPane.showMessageDialog(null, "Employee updated sucessfully", "Success", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("success"));
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update employee", "Fail", JOptionPane.ERROR_MESSAGE, UtilityControll.getImageIcon("fail"));
            }            
        }
    }//GEN-LAST:event_btnSaveEditingActionPerformed

    private void btnAddEditingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEditingActionPerformed
        // TODO add your handling code here:
        DefaultListModel model = (DefaultListModel) mobileListEditing.getModel();
        model.addElement(txtMobileEditing.getText());
        txtMobileEditing.setText("");
    }//GEN-LAST:event_btnAddEditingActionPerformed

    private void btnDeleteEditingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteEditingActionPerformed
        // TODO add your handling code here:
        DefaultListModel model = (DefaultListModel) mobileListEditing.getModel();
        model.remove(mobileListEditing.getSelectedIndex());
    }//GEN-LAST:event_btnDeleteEditingActionPerformed

    private void cmbSearchNewsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSearchNewsItemStateChanged
        // TODO add your handling code here:
        if (cmbSearchNews.getSelectedItem().toString() != "") {
            News news = NewsController.getNewsByTitle(cmbSearchNews.getSelectedItem().toString());
            txtDescription.setText(news.getDescription());
            txtJournalistID.setText(news.getJournalistID() + "");
            txtReceptionistID.setText(news.getReceptionistID() + "");
            txtApproverID.setText(news.getApproverID() + "");
            txtEditorID.setText(news.getEditorID() + "");
            txtPublishedDate.setText(news.getPublishedDate());
            txtPublishedTime.setText(news.getPublishedTime());
        }
    }//GEN-LAST:event_cmbSearchNewsItemStateChanged

    private void cmbSearchEmployeeIDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSearchEmployeeIDItemStateChanged
        // TODO add your handling code here:
        
        if (cmbSearchEmployeeID.getSelectedItem() != null) {
            cmbSearchEmployeeUsername.setSelectedIndex(0);
            String ID = cmbSearchEmployeeID.getSelectedItem().toString();
            if (!ID.equals("Search by ID")) {
                String type = EmployeeController.getEmployeeTypeByID(ID);
                if (type.equals("1")) {
                    model.Admin admin = AdminController.getAdminByID(ID);
                    txtFirstNameSearch.setText(admin.getFirstName());
                    txtLastNameSearch.setText(admin.getLastName());
                    txtFirstNameSearch.setText(admin.getFirstName());
                    txtUsernameSearch.setText(admin.getUsername());
                    txtStreetSearch.setText(admin.getStreet());
                    txtCitySearch.setText(admin.getCity());
                    txtCountrySearch.setText(admin.getCountry());
                    txtWorkCitySearch.setText("");
                    txtWorkDivisionSearch.setText("");
                    txtWorkProvinceSearch.setText("");
                    txtJoinDate.setText(admin.getJoinDate());
                    txtLeftDate.setText(admin.getLeftDate());
                    txtEmployeeDesignationSearch.setText("Receptionist");
                    ArrayList<String> mobiles = admin.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListSearch.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("2")) {
                    model.Journalist journalist = JournalistController.getJournalistByID(ID);
                    txtFirstNameSearch.setText(journalist.getFirstName());
                    txtLastNameSearch.setText(journalist.getLastName());
                    txtFirstNameSearch.setText(journalist.getFirstName());
                    txtUsernameSearch.setText(journalist.getUsername());
                    txtStreetSearch.setText(journalist.getStreet());
                    txtCitySearch.setText(journalist.getCity());
                    txtCountrySearch.setText(journalist.getCountry());
                    txtWorkCitySearch.setText(journalist.getWorkCity());
                    txtWorkDivisionSearch.setText(journalist.getWorkdivision());
                    txtWorkProvinceSearch.setText(journalist.getWorkProvince());
                    txtJoinDate.setText(journalist.getJoinDate());
                    txtLeftDate.setText(journalist.getLeftDate());
                    txtEmployeeDesignationSearch.setText("Journalist");
                    ArrayList<String> mobiles = journalist.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListSearch.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("3")) {
                    model.Receptionist receptionist = ReceptionistController.getReceptionistByID(ID);
                    txtFirstNameSearch.setText(receptionist.getFirstName());
                    txtLastNameSearch.setText(receptionist.getLastName());
                    txtFirstNameSearch.setText(receptionist.getFirstName());
                    txtUsernameSearch.setText(receptionist.getUsername());
                    txtStreetSearch.setText(receptionist.getStreet());
                    txtCitySearch.setText(receptionist.getCity());
                    txtCountrySearch.setText(receptionist.getCountry());
                    txtWorkCitySearch.setText("");
                    txtWorkDivisionSearch.setText("");
                    txtWorkProvinceSearch.setText("");
                    txtJoinDate.setText(receptionist.getJoinDate());
                    txtLeftDate.setText(receptionist.getLeftDate());
                    txtEmployeeDesignationSearch.setText("Receptionist");
                    ArrayList<String> mobiles = receptionist.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListSearch.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("4")) {
                    model.Editor editor = EditorController.getEditorByID(ID);
                    txtFirstNameSearch.setText(editor.getFirstName());
                    txtLastNameSearch.setText(editor.getLastName());
                    txtFirstNameSearch.setText(editor.getFirstName());
                    txtUsernameSearch.setText(editor.getUsername());
                    txtStreetSearch.setText(editor.getStreet());
                    txtCitySearch.setText(editor.getCity());
                    txtCountrySearch.setText(editor.getCountry());
                    txtWorkCitySearch.setText("");
                    txtWorkDivisionSearch.setText("");
                    txtWorkProvinceSearch.setText("");
                    txtJoinDate.setText(editor.getJoinDate());
                    txtLeftDate.setText(editor.getLeftDate());
                    txtEmployeeDesignationSearch.setText("Editor");
                    ArrayList<String> mobiles = editor.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListSearch.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("5")) {
                    model.Approver approver = ApproverController.getApproverByID(ID);
                    txtFirstNameSearch.setText(approver.getFirstName());
                    txtLastNameSearch.setText(approver.getLastName());
                    txtFirstNameSearch.setText(approver.getFirstName());
                    txtUsernameSearch.setText(approver.getUsername());
                    txtStreetSearch.setText(approver.getStreet());
                    txtCitySearch.setText(approver.getCity());
                    txtCountrySearch.setText(approver.getCountry());
                    txtWorkCitySearch.setText("");
                    txtWorkDivisionSearch.setText("");
                    txtWorkProvinceSearch.setText("");
                    txtJoinDate.setText(approver.getJoinDate());
                    txtLeftDate.setText(approver.getLeftDate());
                    txtEmployeeDesignationSearch.setText("Approver");
                    ArrayList<String> mobiles = approver.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListSearch.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                }

            }
        }
    }//GEN-LAST:event_cmbSearchEmployeeIDItemStateChanged

    private void cmbSearchEmployeeUsernameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSearchEmployeeUsernameItemStateChanged
        // TODO add your handling code here
        
        if (cmbSearchEmployeeUsername.getSelectedItem() != null) {
            cmbSearchEmployeeID.setSelectedIndex(0);
            String username = cmbSearchEmployeeUsername.getSelectedItem().toString();
            if (!username.equals("Search by Name")) {
                String type = EmployeeController.getEmployeeTypeByUsername(username);
                if (type.equals("1")) {
                    model.Admin admin = AdminController.getAdminByName(username);
                    txtFirstNameSearch.setText(admin.getFirstName());
                    txtLastNameSearch.setText(admin.getLastName());
                    txtFirstNameSearch.setText(admin.getFirstName());
                    txtUsernameSearch.setText(admin.getUsername());
                    txtStreetSearch.setText(admin.getStreet());
                    txtCitySearch.setText(admin.getCity());
                    txtCountrySearch.setText(admin.getCountry());
                    txtWorkCitySearch.setText("");
                    txtWorkDivisionSearch.setText("");
                    txtWorkProvinceSearch.setText("");
                    txtJoinDate.setText(admin.getJoinDate());
                    txtLeftDate.setText(admin.getLeftDate());
                    txtEmployeeDesignationSearch.setText("Receptionist");
                    ArrayList<String> mobiles = admin.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListSearch.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("2")) {
                    model.Journalist journalist = JournalistController.getJournalistByName(username);
                    txtFirstNameSearch.setText(journalist.getFirstName());
                    txtLastNameSearch.setText(journalist.getLastName());
                    txtFirstNameSearch.setText(journalist.getFirstName());
                    txtUsernameSearch.setText(journalist.getUsername());
                    txtStreetSearch.setText(journalist.getStreet());
                    txtCitySearch.setText(journalist.getCity());
                    txtCountrySearch.setText(journalist.getCountry());
                    txtWorkCitySearch.setText(journalist.getWorkCity());
                    txtWorkDivisionSearch.setText(journalist.getWorkdivision());
                    txtWorkProvinceSearch.setText(journalist.getWorkProvince());
                    txtJoinDate.setText(journalist.getJoinDate());
                    txtLeftDate.setText(journalist.getLeftDate());
                    txtEmployeeDesignationSearch.setText("Journalist");
                    ArrayList<String> mobiles = journalist.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListSearch.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("3")) {
                    model.Receptionist receptionist = ReceptionistController.getReceptionistByName(username);
                    txtFirstNameSearch.setText(receptionist.getFirstName());
                    txtLastNameSearch.setText(receptionist.getLastName());
                    txtFirstNameSearch.setText(receptionist.getFirstName());
                    txtUsernameSearch.setText(receptionist.getUsername());
                    txtStreetSearch.setText(receptionist.getStreet());
                    txtCitySearch.setText(receptionist.getCity());
                    txtCountrySearch.setText(receptionist.getCountry());
                    txtWorkCitySearch.setText("");
                    txtWorkDivisionSearch.setText("");
                    txtWorkProvinceSearch.setText("");
                    txtJoinDate.setText(receptionist.getJoinDate());
                    txtLeftDate.setText(receptionist.getLeftDate());
                    txtEmployeeDesignationSearch.setText("Receptionist");
                    ArrayList<String> mobiles = receptionist.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListSearch.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("4")) {
                    model.Editor editor = EditorController.getEditorByName(username);
                    txtFirstNameSearch.setText(editor.getFirstName());
                    txtLastNameSearch.setText(editor.getLastName());
                    txtFirstNameSearch.setText(editor.getFirstName());
                    txtUsernameSearch.setText(editor.getUsername());
                    txtStreetSearch.setText(editor.getStreet());
                    txtCitySearch.setText(editor.getCity());
                    txtCountrySearch.setText(editor.getCountry());
                    txtWorkCitySearch.setText("");
                    txtWorkDivisionSearch.setText("");
                    txtWorkProvinceSearch.setText("");
                    txtJoinDate.setText(editor.getJoinDate());
                    txtLeftDate.setText(editor.getLeftDate());
                    txtEmployeeDesignationSearch.setText("Editor");
                    ArrayList<String> mobiles = editor.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListSearch.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("5")) {
                    model.Approver approver = ApproverController.getApproverByName(username);
                    txtFirstNameSearch.setText(approver.getFirstName());
                    txtLastNameSearch.setText(approver.getLastName());
                    txtFirstNameSearch.setText(approver.getFirstName());
                    txtUsernameSearch.setText(approver.getUsername());
                    txtStreetSearch.setText(approver.getStreet());
                    txtCitySearch.setText(approver.getCity());
                    txtCountrySearch.setText(approver.getCountry());
                    txtWorkCitySearch.setText("");
                    txtWorkDivisionSearch.setText("");
                    txtWorkProvinceSearch.setText("");
                    txtJoinDate.setText(approver.getJoinDate());
                    txtLeftDate.setText(approver.getLeftDate());
                    txtEmployeeDesignationSearch.setText("Approver");
                    ArrayList<String> mobiles = approver.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListSearch.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                }
            }
        }
    }//GEN-LAST:event_cmbSearchEmployeeUsernameItemStateChanged

    private void txtMobileAddKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMobileAddKeyReleased
        // TODO add your handling code here:
        Validation.ValidateTPCount(txtMobileAdd);
    }//GEN-LAST:event_txtMobileAddKeyReleased

    private void txtMobileEditingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMobileEditingKeyReleased
        // TODO add your handling code here:
        Validation.ValidateTPCount(txtMobileEditing);
    }//GEN-LAST:event_txtMobileEditingKeyReleased

    private void cmbEditEmployeeUsernameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEditEmployeeUsernameItemStateChanged
        // TODO add your handling code here:
        
        if (cmbEditEmployeeUsername.getSelectedItem() != null) {
            cmbEditEmployeeID.setSelectedIndex(0);
            String username = cmbEditEmployeeUsername.getSelectedItem().toString();
            if (!username.equals("Search by Name")) {
                String type = EmployeeController.getEmployeeTypeByUsername(username);
                if (type.equals("1")) {
                    model.Admin admin = AdminController.getAdminByName(username);
                    txtFirstNameEditing.setText(admin.getFirstName());
                    txtLastNameEditing.setText(admin.getLastName());
                    txtFirstNameEditing.setText(admin.getFirstName());
                    txtUsernameEditing.setText(admin.getUsername());
                    txtStreetEditing.setText(admin.getStreet());
                    txtCityEditing.setText(admin.getCity());
                    txtCountryEditing.setText(admin.getCountry());
                    txtWorkCityEditing.setText("");
                    txtWorkDivisionEditing.setText("");
                    txtWorkProvinceEditing.setText("");
                    cmbEmployeeTypeEditing.setSelectedItem("Receptionist");
                    ArrayList<String> mobiles = admin.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListEditing.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("2")) {
                    model.Journalist journalist = JournalistController.getJournalistByName(username);
                    txtFirstNameEditing.setText(journalist.getFirstName());
                    txtLastNameEditing.setText(journalist.getLastName());
                    txtFirstNameEditing.setText(journalist.getFirstName());
                    txtUsernameEditing.setText(journalist.getUsername());
                    txtStreetEditing.setText(journalist.getStreet());
                    txtCityEditing.setText(journalist.getCity());
                    txtCountryEditing.setText(journalist.getCountry());
                    txtWorkCityEditing.setText(journalist.getWorkCity());
                    txtWorkDivisionEditing.setText(journalist.getWorkdivision());
                    txtWorkProvinceEditing.setText(journalist.getWorkProvince());
                    cmbEmployeeTypeEditing.setSelectedItem("Journalist");
                    ArrayList<String> mobiles = journalist.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListEditing.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("3")) {
                    model.Receptionist receptionist = ReceptionistController.getReceptionistByName(username);
                    txtFirstNameEditing.setText(receptionist.getFirstName());
                    txtLastNameEditing.setText(receptionist.getLastName());
                    txtFirstNameEditing.setText(receptionist.getFirstName());
                    txtUsernameEditing.setText(receptionist.getUsername());
                    txtStreetEditing.setText(receptionist.getStreet());
                    txtCityEditing.setText(receptionist.getCity());
                    txtCountryEditing.setText(receptionist.getCountry());
                    txtWorkCityEditing.setText("");
                    txtWorkDivisionEditing.setText("");
                    txtWorkProvinceEditing.setText("");
                    cmbEmployeeTypeEditing.setSelectedItem("Receptionist");
                    ArrayList<String> mobiles = receptionist.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListEditing.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("4")) {
                    model.Editor editor = EditorController.getEditorByName(username);
                    txtFirstNameEditing.setText(editor.getFirstName());
                    txtLastNameEditing.setText(editor.getLastName());
                    txtFirstNameEditing.setText(editor.getFirstName());
                    txtUsernameEditing.setText(editor.getUsername());
                    txtStreetEditing.setText(editor.getStreet());
                    txtCityEditing.setText(editor.getCity());
                    txtCountryEditing.setText(editor.getCountry());
                    txtWorkCityEditing.setText("");
                    txtWorkDivisionEditing.setText("");
                    txtWorkProvinceEditing.setText("");
                    cmbEmployeeTypeEditing.setSelectedItem("Editor");
                    ArrayList<String> mobiles = editor.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListEditing.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("5")) {
                    model.Approver approver = ApproverController.getApproverByName(username);
                    txtFirstNameEditing.setText(approver.getFirstName());
                    txtLastNameEditing.setText(approver.getLastName());
                    txtFirstNameEditing.setText(approver.getFirstName());
                    txtUsernameEditing.setText(approver.getUsername());
                    txtStreetEditing.setText(approver.getStreet());
                    txtCityEditing.setText(approver.getCity());
                    txtCountryEditing.setText(approver.getCountry());
                    txtWorkCityEditing.setText("");
                    txtWorkDivisionEditing.setText("");
                    txtWorkProvinceEditing.setText("");
                    cmbEmployeeTypeEditing.setSelectedItem("Approver");
                    ArrayList<String> mobiles = approver.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListEditing.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                }
            }
        }
        
    }//GEN-LAST:event_cmbEditEmployeeUsernameItemStateChanged

    private void cmbEditEmployeeIDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEditEmployeeIDItemStateChanged
        // TODO add your handling code here:
   
        if (cmbEditEmployeeID.getSelectedItem() != null) {
            cmbEditEmployeeUsername.setSelectedIndex(0);
            String ID = cmbEditEmployeeID.getSelectedItem().toString();
            if (!ID.equals("Search by ID")) {
                String type = EmployeeController.getEmployeeTypeByID(ID);
                if (type.equals("1")) {
                    model.Admin admin = AdminController.getAdminByID(ID);
                    txtFirstNameEditing.setText(admin.getFirstName());
                    txtLastNameEditing.setText(admin.getLastName());
                    txtFirstNameEditing.setText(admin.getFirstName());
                    txtUsernameEditing.setText(admin.getUsername());
                    txtStreetEditing.setText(admin.getStreet());
                    txtCityEditing.setText(admin.getCity());
                    txtCountryEditing.setText(admin.getCountry());
                    txtWorkCityEditing.setText("");
                    txtWorkDivisionEditing.setText("");
                    txtWorkProvinceEditing.setText("");
                    cmbEmployeeTypeEditing.setSelectedItem("Receptionist");
                    ArrayList<String> mobiles = admin.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListEditing.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("2")) {
                    model.Journalist journalist = JournalistController.getJournalistByID(ID);
                    txtFirstNameEditing.setText(journalist.getFirstName());
                    txtLastNameEditing.setText(journalist.getLastName());
                    txtFirstNameEditing.setText(journalist.getFirstName());
                    txtUsernameEditing.setText(journalist.getUsername());
                    txtStreetEditing.setText(journalist.getStreet());
                    txtCityEditing.setText(journalist.getCity());
                    txtCountryEditing.setText(journalist.getCountry());
                    txtWorkCityEditing.setText(journalist.getWorkCity());
                    txtWorkDivisionEditing.setText(journalist.getWorkdivision());
                    txtWorkProvinceEditing.setText(journalist.getWorkProvince());
                    cmbEmployeeTypeEditing.setSelectedItem("Journalist");
                    ArrayList<String> mobiles = journalist.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListEditing.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("3")) {
                    model.Receptionist receptionist = ReceptionistController.getReceptionistByID(ID);
                    txtFirstNameEditing.setText(receptionist.getFirstName());
                    txtLastNameEditing.setText(receptionist.getLastName());
                    txtFirstNameEditing.setText(receptionist.getFirstName());
                    txtUsernameEditing.setText(receptionist.getUsername());
                    txtStreetEditing.setText(receptionist.getStreet());
                    txtCityEditing.setText(receptionist.getCity());
                    txtCountryEditing.setText(receptionist.getCountry());
                    txtWorkCityEditing.setText("");
                    txtWorkDivisionEditing.setText("");
                    txtWorkProvinceEditing.setText("");
                    cmbEmployeeTypeEditing.setSelectedItem("Receptionist");
                    ArrayList<String> mobiles = receptionist.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListEditing.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("4")) {
                    model.Editor editor = EditorController.getEditorByID(ID);
                    txtFirstNameEditing.setText(editor.getFirstName());
                    txtLastNameEditing.setText(editor.getLastName());
                    txtFirstNameEditing.setText(editor.getFirstName());
                    txtUsernameEditing.setText(editor.getUsername());
                    txtStreetEditing.setText(editor.getStreet());
                    txtCityEditing.setText(editor.getCity());
                    txtCountryEditing.setText(editor.getCountry());
                    txtWorkCityEditing.setText("");
                    txtWorkDivisionEditing.setText("");
                    txtWorkProvinceEditing.setText("");
                    cmbEmployeeTypeEditing.setSelectedItem("Editor");
                    ArrayList<String> mobiles = editor.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListEditing.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                } else if (type.equals("5")) {
                    model.Approver approver = ApproverController.getApproverByID(ID);
                    txtFirstNameEditing.setText(approver.getFirstName());
                    txtLastNameEditing.setText(approver.getLastName());
                    txtFirstNameEditing.setText(approver.getFirstName());
                    txtUsernameEditing.setText(approver.getUsername());
                    txtStreetEditing.setText(approver.getStreet());
                    txtCityEditing.setText(approver.getCity());
                    txtCountryEditing.setText(approver.getCountry());
                    txtWorkCityEditing.setText("");
                    txtWorkDivisionEditing.setText("");
                    txtWorkProvinceEditing.setText("");
                    cmbEmployeeTypeEditing.setSelectedItem("Approver");
                    ArrayList<String> mobiles = approver.getMobileList();
                    DefaultListModel model = (DefaultListModel) mobileListEditing.getModel();
                    model.removeAllElements();
                    for (int i = 0; i < mobiles.size(); i++) {
                        model.addElement(mobiles.get(i));
                    }
                }

            }
        }
           
    }//GEN-LAST:event_cmbEditEmployeeIDItemStateChanged

    private void cmbSearchEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchEmployeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSearchEmployeeIDActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAdd;
    private javax.swing.JButton btnAddEditing;
    private javax.swing.JButton btnClearAdd;
    private javax.swing.JButton btnClearEditing;
    private javax.swing.JButton btnDeleteAdd;
    private javax.swing.JButton btnDeleteEditing;
    private javax.swing.JButton btnSaveAdd;
    private javax.swing.JButton btnSaveEditing;
    private javax.swing.JComboBox cmbEditEmployeeID;
    private javax.swing.JComboBox cmbEditEmployeeUsername;
    private javax.swing.JComboBox cmbEmployeeTypeAdd;
    private javax.swing.JComboBox cmbEmployeeTypeEditing;
    private javax.swing.JComboBox cmbSearchEmployeeID;
    private javax.swing.JComboBox cmbSearchEmployeeUsername;
    private javax.swing.JComboBox cmbSearchNews;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblFirstName10;
    private javax.swing.JLabel lblFirstName11;
    private javax.swing.JLabel lblFirstName12;
    private javax.swing.JLabel lblFirstName13;
    private javax.swing.JLabel lblFirstName14;
    private javax.swing.JLabel lblFirstName15;
    private javax.swing.JLabel lblFirstName16;
    private javax.swing.JLabel lblFirstName4;
    private javax.swing.JLabel lblFirstName5;
    private javax.swing.JLabel lblFirstName6;
    private javax.swing.JLabel lblFirstName7;
    private javax.swing.JLabel lblFirstName8;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblLastName4;
    private javax.swing.JLabel lblLastName5;
    private javax.swing.JLabel lblTitleNew10;
    private javax.swing.JLabel lblTitleNew11;
    private javax.swing.JLabel lblTitleNew12;
    private javax.swing.JLabel lblTitleNew2;
    private javax.swing.JLabel lblTitleNew3;
    private javax.swing.JLabel lblTitleNew4;
    private javax.swing.JLabel lblTitleNew43;
    private javax.swing.JLabel lblTitleNew44;
    private javax.swing.JLabel lblTitleNew45;
    private javax.swing.JLabel lblTitleNew46;
    private javax.swing.JLabel lblTitleNew48;
    private javax.swing.JLabel lblTitleNew49;
    private javax.swing.JLabel lblTitleNew5;
    private javax.swing.JLabel lblTitleNew50;
    private javax.swing.JLabel lblTitleNew51;
    private javax.swing.JLabel lblTitleNew52;
    private javax.swing.JLabel lblTitleNew53;
    private javax.swing.JLabel lblTitleNew54;
    private javax.swing.JLabel lblTitleNew55;
    private javax.swing.JLabel lblTitleNew56;
    private javax.swing.JLabel lblTitleNew58;
    private javax.swing.JLabel lblTitleNew59;
    private javax.swing.JLabel lblTitleNew6;
    private javax.swing.JLabel lblTitleNew60;
    private javax.swing.JLabel lblTitleNew61;
    private javax.swing.JLabel lblTitleNew62;
    private javax.swing.JLabel lblTitleNew63;
    private javax.swing.JLabel lblTitleNew7;
    private javax.swing.JLabel lblTitleNew8;
    private javax.swing.JLabel lblTitleNew9;
    private javax.swing.JList mobileListAdd;
    private javax.swing.JList mobileListEditing;
    private javax.swing.JList mobileListSearch;
    private javax.swing.JPanel pnlAddEmployee;
    private javax.swing.JPanel pnlContainerEdit;
    private javax.swing.JPanel pnlContainerNew;
    private javax.swing.JPanel pnlContainerNew1;
    private javax.swing.JPanel pnlEditEmployee;
    private javax.swing.JPanel pnlSearchEmployee;
    private javax.swing.JPanel pnlSearchNews;
    private javax.swing.JTabbedPane tbpMain;
    private javax.swing.JTextField txtApproverID;
    private javax.swing.JTextField txtCityAdd;
    private javax.swing.JTextField txtCityEditing;
    private javax.swing.JTextField txtCitySearch;
    private javax.swing.JTextField txtCountryAdd;
    private javax.swing.JTextField txtCountryEditing;
    private javax.swing.JTextField txtCountrySearch;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtEditorID;
    private javax.swing.JTextField txtEmployeeDesignationSearch;
    private javax.swing.JTextField txtFirstNameAdd;
    private javax.swing.JTextField txtFirstNameEditing;
    private javax.swing.JTextField txtFirstNameSearch;
    private javax.swing.JTextField txtJoinDate;
    private javax.swing.JTextField txtJournalistID;
    private javax.swing.JTextField txtLastNameAdd;
    private javax.swing.JTextField txtLastNameEditing;
    private javax.swing.JTextField txtLastNameSearch;
    private javax.swing.JTextField txtLeftDate;
    private javax.swing.JTextField txtMobileAdd;
    private javax.swing.JTextField txtMobileEditing;
    private javax.swing.JPasswordField txtPasswordAdd;
    private javax.swing.JTextField txtPublishedDate;
    private javax.swing.JTextField txtPublishedTime;
    private javax.swing.JTextField txtReceptionistID;
    private javax.swing.JTextField txtStreetAdd;
    private javax.swing.JTextField txtStreetEditing;
    private javax.swing.JTextField txtStreetSearch;
    private javax.swing.JTextField txtUsernameAdd;
    private javax.swing.JTextField txtUsernameEditing;
    private javax.swing.JTextField txtUsernameSearch;
    private javax.swing.JTextField txtWorkCityAdd;
    private javax.swing.JTextField txtWorkCityEditing;
    private javax.swing.JTextField txtWorkCitySearch;
    private javax.swing.JTextField txtWorkDivisionAdd;
    private javax.swing.JTextField txtWorkDivisionEditing;
    private javax.swing.JTextField txtWorkDivisionSearch;
    private javax.swing.JTextField txtWorkProvinceAdd;
    private javax.swing.JTextField txtWorkProvinceEditing;
    private javax.swing.JTextField txtWorkProvinceSearch;
    // End of variables declaration//GEN-END:variables
}
