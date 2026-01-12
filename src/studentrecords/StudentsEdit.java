/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package studentrecords;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Joan
 */
public class StudentsEdit extends javax.swing.JPanel {
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    String path2 = null;
    
    StudentsView sv = new StudentsView();
    
    /**
     * Creates new form Dashboard
     */
    public StudentsEdit() {
        initComponents();
    }
    
    public void populateTbl() {
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT * FROM student");
            rs = ps.executeQuery();
            sv.tblStudents.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void autoEmail() {
        if(txtLastName.getText().equals("") && txtGivenName.getText().equals(""))
            txtEmail.setText("@plm.edu.ph");
        else {
            txtEmail.setText(txtGivenName.getText().replaceAll(" ", "").toLowerCase().substring(0,1) 
                    + txtLastName.getText().replaceAll(" ", "").toLowerCase() 
                    + txtDateStarted.getText().substring(txtDateStarted.getText().length()-4)
                    + "@plm.edu.ph");
            txtEmail1.setText(txtGivenName.getText().replaceAll(" ", "").toLowerCase().substring(0,1) 
                    + txtLastName.getText().replaceAll(" ", "").toLowerCase() 
                    + txtDateStarted.getText().substring(txtDateStarted.getText().length()-4)
                    + "@plm.edu.ph");
        }
    }
    
    public String genderAbbv() {
        String gender;
        if(cmbGender.getSelectedItem().equals("Male"))
            gender = "M";
        else 
            gender = "F";
        return gender;
    }
    
    public String statusAbbv() {
        String status;
        if(cmbStatus.getSelectedItem().equals("Active"))
            status = "A";
        else if(cmbStatus.getSelectedItem().equals("Inactive"))
            status = "I";
        else
            status = "G";
        return status;
    }
    
    public void limitInput(KeyEvent evt, JTextField fieldName, int maxLength) {
        int fieldLength = fieldName.getText().length();
        char c = evt.getKeyChar();
        if(c >= '0' && c <= '9' && fieldLength < maxLength) 
            fieldName.setEditable(true);
        else if(c == KeyEvent.VK_BACK_SPACE)
            fieldName.setEditable(true);
        else 
            fieldName.setEditable(false);
    }
    
    public void limitStrInput(KeyEvent evt, JTextField fieldName, int maxLength) {
        int fieldLength = fieldName.getText().length();
        char c = evt.getKeyChar();
        if(((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) && fieldLength < maxLength) 
            fieldName.setEditable(true);
        else if(c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_SPACE || c == '.')
            fieldName.setEditable(true);
        else 
            fieldName.setEditable(false);
    }
    
    public static String capitalize(String name) {
        char[] nameArray = name.toCharArray();
        boolean foundSpace = true;
        
        for(int letter = 0; letter < nameArray.length; letter++) {
            if(Character.isLetter(nameArray[letter])) {
                nameArray[letter] = Character.toLowerCase(nameArray[letter]);
                if(foundSpace) {
                    nameArray[letter] = Character.toUpperCase(nameArray[letter]);
                    foundSpace = false;
                }
            }
            else
                foundSpace = true;
        }
        return String.valueOf(nameArray);
    }
    
    public void courseCodeDisplay() {
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT course_code FROM course WHERE description = '"+cmbCourseCode.getSelectedItem()+"'");
            rs = ps.executeQuery();
            while(rs.next())
                txtCourseCode.setText(rs.getString("course_code"));
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
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

        jLabel5 = new javax.swing.JLabel();
        pnlStudentNum4 = new studentrecords.RoundedFrame();
        jTextField5 = new javax.swing.JTextField();
        dateChooser = new com.raven.datechooser.DateChooser();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        pnlStudentNum = new studentrecords.RoundedFrame();
        txtStudentNum = new javax.swing.JTextField();
        lblStudentNumYear = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlLastName = new studentrecords.RoundedFrame();
        txtLastName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pnlGivenName = new studentrecords.RoundedFrame();
        txtGivenName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pnlBday = new studentrecords.RoundedFrame();
        txtBday = new javax.swing.JTextField();
        btnDate = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pnlEmail = new studentrecords.RoundedFrame();
        txtEmail = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbGender = new javax.swing.JComboBox<>();
        pnlMobileNumber = new studentrecords.RoundedFrame();
        txtMobileNumber = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pnlDefaultPhoto = new studentrecords.RoundedFrame();
        btnDeafultPhoto = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pnlDateStarted = new studentrecords.RoundedFrame();
        txtDateStarted = new javax.swing.JTextField();
        btnDate1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnlDateGrad = new studentrecords.RoundedFrame();
        txtDateGrad = new javax.swing.JTextField();
        btnDate2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbCourseCode = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        pnlAddRec = new studentrecords.RoundedFrame();
        btnEditRec = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        pnlAddress = new studentrecords.RoundedFrame();
        txtAddress1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        displayStudent = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        imgStudent = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtStudentNum1 = new javax.swing.JLabel();
        txtLastName1 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JLabel();
        txtCourseCode = new javax.swing.JLabel();
        txtEmail1 = new javax.swing.JLabel();
        txtGender = new javax.swing.JLabel();
        txtMobileNum = new javax.swing.JLabel();
        txtAddress = new javax.swing.JLabel();
        txtBday1 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        txtDateStarted1 = new javax.swing.JLabel();
        txtDateGrad1 = new javax.swing.JLabel();

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Birthdate");

        pnlStudentNum4.setBackground(new java.awt.Color(244, 244, 244));

        jTextField5.setBackground(new java.awt.Color(244, 244, 244));
        jTextField5.setColumns(3);
        jTextField5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(0, 0, 0));
        jTextField5.setBorder(null);

        javax.swing.GroupLayout pnlStudentNum4Layout = new javax.swing.GroupLayout(pnlStudentNum4);
        pnlStudentNum4.setLayout(pnlStudentNum4Layout);
        pnlStudentNum4Layout.setHorizontalGroup(
            pnlStudentNum4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudentNum4Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        pnlStudentNum4Layout.setVerticalGroup(
            pnlStudentNum4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudentNum4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        dateChooser.setForeground(new java.awt.Color(157, 12, 12));
        dateChooser.setDateFormat("MM/dd/yyyy");
        dateChooser.setTextRefernce(txtBday);

        dateChooser1.setForeground(new java.awt.Color(157, 12, 12));
        dateChooser1.setDateFormat("MM/dd/yyyy");
        dateChooser1.setTextRefernce(txtDateStarted);

        dateChooser2.setForeground(new java.awt.Color(157, 12, 12));
        dateChooser2.setDateFormat("MM/dd/yyyy");
        dateChooser2.setTextRefernce(txtDateGrad);

        setBackground(new java.awt.Color(240, 241, 243));
        setPreferredSize(new java.awt.Dimension(1247, 606));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        pnlStudentNum.setBackground(new java.awt.Color(248, 248, 248));

        txtStudentNum.setBackground(new java.awt.Color(248, 248, 248));
        txtStudentNum.setColumns(3);
        txtStudentNum.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtStudentNum.setForeground(new java.awt.Color(153, 153, 153));
        txtStudentNum.setBorder(null);
        txtStudentNum.setSelectedTextColor(new java.awt.Color(153, 153, 153));
        txtStudentNum.setSelectionColor(new java.awt.Color(248, 248, 248));

        lblStudentNumYear.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        lblStudentNumYear.setForeground(new java.awt.Color(153, 153, 153));
        lblStudentNumYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStudentNumYear.setText("2024");

        javax.swing.GroupLayout pnlStudentNumLayout = new javax.swing.GroupLayout(pnlStudentNum);
        pnlStudentNum.setLayout(pnlStudentNumLayout);
        pnlStudentNumLayout.setHorizontalGroup(
            pnlStudentNumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudentNumLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStudentNumYear, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStudentNum)
                .addGap(14, 14, 14))
        );
        pnlStudentNumLayout.setVerticalGroup(
            pnlStudentNumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStudentNumLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlStudentNumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtStudentNum, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(lblStudentNumYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Student Number");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Last Name");

        pnlLastName.setBackground(new java.awt.Color(248, 248, 248));

        txtLastName.setBackground(new java.awt.Color(248, 248, 248));
        txtLastName.setColumns(3);
        txtLastName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtLastName.setForeground(new java.awt.Color(0, 0, 0));
        txtLastName.setBorder(null);
        txtLastName.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtLastName.setSelectionColor(new java.awt.Color(221, 197, 197));
        txtLastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLastNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLastNameFocusLost(evt);
            }
        });
        txtLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLastNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLastNameKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlLastNameLayout = new javax.swing.GroupLayout(pnlLastName);
        pnlLastName.setLayout(pnlLastNameLayout);
        pnlLastNameLayout.setHorizontalGroup(
            pnlLastNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLastNameLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        pnlLastNameLayout.setVerticalGroup(
            pnlLastNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLastNameLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("First Name");

        pnlGivenName.setBackground(new java.awt.Color(248, 248, 248));

        txtGivenName.setBackground(new java.awt.Color(248, 248, 248));
        txtGivenName.setColumns(3);
        txtGivenName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtGivenName.setForeground(new java.awt.Color(0, 0, 0));
        txtGivenName.setBorder(null);
        txtGivenName.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtGivenName.setSelectionColor(new java.awt.Color(221, 197, 197));
        txtGivenName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGivenNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGivenNameFocusLost(evt);
            }
        });
        txtGivenName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGivenNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGivenNameKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlGivenNameLayout = new javax.swing.GroupLayout(pnlGivenName);
        pnlGivenName.setLayout(pnlGivenNameLayout);
        pnlGivenNameLayout.setHorizontalGroup(
            pnlGivenNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGivenNameLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(txtGivenName, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        pnlGivenNameLayout.setVerticalGroup(
            pnlGivenNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGivenNameLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtGivenName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Birthdate");

        pnlBday.setBackground(new java.awt.Color(248, 248, 248));

        txtBday.setBackground(new java.awt.Color(248, 248, 248));
        txtBday.setColumns(3);
        txtBday.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtBday.setForeground(new java.awt.Color(0, 0, 0));
        txtBday.setBorder(null);
        txtBday.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtBday.setSelectionColor(new java.awt.Color(221, 197, 197));
        txtBday.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBdayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBdayFocusLost(evt);
            }
        });

        btnDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/studentrecords/Images/icons8-calendar-13.png"))); // NOI18N
        btnDate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDateMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlBdayLayout = new javax.swing.GroupLayout(pnlBday);
        pnlBday.setLayout(pnlBdayLayout);
        pnlBdayLayout.setHorizontalGroup(
            pnlBdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBdayLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(txtBday, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBdayLayout.setVerticalGroup(
            pnlBdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBdayLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtBday, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlBdayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Mobile Number");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Email Address");

        pnlEmail.setBackground(new java.awt.Color(248, 248, 248));

        txtEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEmail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtEmail.setText("@plm.edu.ph");

        javax.swing.GroupLayout pnlEmailLayout = new javax.swing.GroupLayout(pnlEmail);
        pnlEmail.setLayout(pnlEmailLayout);
        pnlEmailLayout.setHorizontalGroup(
            pnlEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
            .addGroup(pnlEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEmailLayout.createSequentialGroup()
                    .addContainerGap(14, Short.MAX_VALUE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        pnlEmailLayout.setVerticalGroup(
            pnlEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
            .addGroup(pnlEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Gender");

        cmbGender.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        cmbGender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbGender.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbGenderFocusLost(evt);
            }
        });

        pnlMobileNumber.setBackground(new java.awt.Color(248, 248, 248));

        txtMobileNumber.setBackground(new java.awt.Color(248, 248, 248));
        txtMobileNumber.setColumns(3);
        txtMobileNumber.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtMobileNumber.setForeground(new java.awt.Color(0, 0, 0));
        txtMobileNumber.setBorder(null);
        txtMobileNumber.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtMobileNumber.setSelectionColor(new java.awt.Color(221, 197, 197));
        txtMobileNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMobileNumberFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMobileNumberFocusLost(evt);
            }
        });
        txtMobileNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMobileNumberKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMobileNumberKeyReleased(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("+63");

        javax.swing.GroupLayout pnlMobileNumberLayout = new javax.swing.GroupLayout(pnlMobileNumber);
        pnlMobileNumber.setLayout(pnlMobileNumberLayout);
        pnlMobileNumberLayout.setHorizontalGroup(
            pnlMobileNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMobileNumberLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMobileNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMobileNumberLayout.setVerticalGroup(
            pnlMobileNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMobileNumberLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlMobileNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMobileNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel2.setBackground(new java.awt.Color(157, 12, 12));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Complete the fields below to modify the record");

        jLabel10.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Do not leave an empty field as much as possible. Click the image on the right to update.");

        pnlDefaultPhoto.setBackground(new java.awt.Color(157, 12, 12));
        pnlDefaultPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDefaultPhotoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlDefaultPhotoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlDefaultPhotoMouseExited(evt);
            }
        });

        btnDeafultPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDeafultPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/studentrecords/Images/icons8-no-image-30.png"))); // NOI18N
        btnDeafultPhoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlDefaultPhotoLayout = new javax.swing.GroupLayout(pnlDefaultPhoto);
        pnlDefaultPhoto.setLayout(pnlDefaultPhotoLayout);
        pnlDefaultPhotoLayout.setHorizontalGroup(
            pnlDefaultPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDefaultPhotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDeafultPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDefaultPhotoLayout.setVerticalGroup(
            pnlDefaultPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDefaultPhotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDeafultPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlDefaultPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDefaultPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Date Started");

        pnlDateStarted.setBackground(new java.awt.Color(248, 248, 248));

        txtDateStarted.setBackground(new java.awt.Color(248, 248, 248));
        txtDateStarted.setColumns(3);
        txtDateStarted.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDateStarted.setForeground(new java.awt.Color(0, 0, 0));
        txtDateStarted.setBorder(null);
        txtDateStarted.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDateStartedFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDateStartedFocusLost(evt);
            }
        });

        btnDate1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/studentrecords/Images/icons8-calendar-13.png"))); // NOI18N
        btnDate1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDate1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDate1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlDateStartedLayout = new javax.swing.GroupLayout(pnlDateStarted);
        pnlDateStarted.setLayout(pnlDateStartedLayout);
        pnlDateStartedLayout.setHorizontalGroup(
            pnlDateStartedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDateStartedLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(txtDateStarted, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlDateStartedLayout.setVerticalGroup(
            pnlDateStartedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDateStartedLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtDateStarted, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDateStartedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Date Graduated");

        pnlDateGrad.setBackground(new java.awt.Color(248, 248, 248));

        txtDateGrad.setBackground(new java.awt.Color(248, 248, 248));
        txtDateGrad.setColumns(3);
        txtDateGrad.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDateGrad.setForeground(new java.awt.Color(0, 0, 0));
        txtDateGrad.setBorder(null);
        txtDateGrad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDateGradFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDateGradFocusLost(evt);
            }
        });

        btnDate2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDate2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/studentrecords/Images/icons8-calendar-13.png"))); // NOI18N
        btnDate2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDate2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDate2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlDateGradLayout = new javax.swing.GroupLayout(pnlDateGrad);
        pnlDateGrad.setLayout(pnlDateGradLayout);
        pnlDateGradLayout.setHorizontalGroup(
            pnlDateGradLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDateGradLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(txtDateGrad, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlDateGradLayout.setVerticalGroup(
            pnlDateGradLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDateGradLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtDateGrad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlDateGradLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDate2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Course");

        cmbCourseCode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbCourseCode.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbCourseCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbCourseCodeFocusLost(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("Status");

        cmbStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive", "Graduated" }));
        cmbStatus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbStatus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbStatusFocusLost(evt);
            }
        });

        pnlAddRec.setBackground(new java.awt.Color(157, 12, 12));

        btnEditRec.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnEditRec.setForeground(new java.awt.Color(255, 255, 255));
        btnEditRec.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEditRec.setText("Update Record");
        btnEditRec.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditRec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditRecMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditRecMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditRecMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlAddRecLayout = new javax.swing.GroupLayout(pnlAddRec);
        pnlAddRec.setLayout(pnlAddRecLayout);
        pnlAddRecLayout.setHorizontalGroup(
            pnlAddRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEditRec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
        );
        pnlAddRecLayout.setVerticalGroup(
            pnlAddRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEditRec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel28.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel28.setText("House Address");

        pnlAddress.setBackground(new java.awt.Color(248, 248, 248));

        txtAddress1.setBackground(new java.awt.Color(248, 248, 248));
        txtAddress1.setColumns(3);
        txtAddress1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtAddress1.setForeground(new java.awt.Color(0, 0, 0));
        txtAddress1.setBorder(null);
        txtAddress1.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtAddress1.setSelectionColor(new java.awt.Color(221, 197, 197));
        txtAddress1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAddress1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAddress1FocusLost(evt);
            }
        });
        txtAddress1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAddress1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAddress1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlAddressLayout = new javax.swing.GroupLayout(pnlAddress);
        pnlAddress.setLayout(pnlAddressLayout);
        pnlAddressLayout.setHorizontalGroup(
            pnlAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddressLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(txtAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        pnlAddressLayout.setVerticalGroup(
            pnlAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtAddress1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(157, 12, 12));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jLabel30.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel30.setText("(if not yet graduated, leave it blank)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(pnlBday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(pnlStudentNum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(pnlLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(pnlMobileNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pnlAddRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(pnlGivenName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(pnlEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(40, 40, 40))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel13)
                            .addComponent(cmbCourseCode, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(371, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(pnlDateStarted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlDateGrad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30)))
                        .addGap(37, 37, 37))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pnlGivenName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlStudentNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlMobileNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDateStarted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDateGrad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCourseCode, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(pnlAddRec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        displayStudent.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("Student No.");

        jPanel4.setBackground(new java.awt.Color(229, 229, 229));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        imgStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/studentrecords/Images/imgStudentSample.png"))); // NOI18N
        imgStudent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        imgStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgStudentMouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("Last Name");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("First Name");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("Email");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setText("Gender");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setText("Course Code");

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setText("Mobile No.");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setText("Address");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setText("Birthday");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel25.setText("Status");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setText("Date Started");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setText("Date Graduated");

        txtStudentNum1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtStudentNum1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        txtLastName1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtLastName1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtFirstName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtFirstName.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtCourseCode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCourseCode.setToolTipText("");
        txtCourseCode.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtEmail1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtGender.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtMobileNum.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtAddress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtBday1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtDateStarted1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtDateGrad1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout displayStudentLayout = new javax.swing.GroupLayout(displayStudent);
        displayStudent.setLayout(displayStudentLayout);
        displayStudentLayout.setHorizontalGroup(
            displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayStudentLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayStudentLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStudentNum1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(displayStudentLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(displayStudentLayout.createSequentialGroup()
                                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayStudentLayout.createSequentialGroup()
                                        .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel22))
                                        .addGap(35, 35, 35))
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayStudentLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(displayStudentLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txtMobileNum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(txtAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(txtBday1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(txtStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtDateStarted1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtDateGrad1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(displayStudentLayout.createSequentialGroup()
                                .addComponent(imgStudent)
                                .addGap(16, 16, 16)
                                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCourseCode, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLastName1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel21)
                                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(25, 25, 25))
        );
        displayStudentLayout.setVerticalGroup(
            displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayStudentLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtStudentNum1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imgStudent)
                    .addGroup(displayStudentLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLastName1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCourseCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(31, 31, 31)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtEmail1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtMobileNum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtBday1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateStarted1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateGrad1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(displayStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(displayStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtLastNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLastNameFocusGained
        // TODO add your handling code here:
        pnlLastName.setBackground(new Color(240,240,240));
        txtLastName.setBackground(new Color(240,240,240));
    }//GEN-LAST:event_txtLastNameFocusGained

    private void txtLastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLastNameFocusLost
        // TODO add your handling code here:
        pnlLastName.setBackground(new Color(248,248,248));
        txtLastName.setBackground(new Color(248,248,248));
    }//GEN-LAST:event_txtLastNameFocusLost

    private void txtGivenNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGivenNameFocusGained
        // TODO add your handling code here:
        pnlGivenName.setBackground(new Color(240,240,240));
        txtGivenName.setBackground(new Color(240,240,240));
    }//GEN-LAST:event_txtGivenNameFocusGained

    private void txtGivenNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGivenNameFocusLost
        // TODO add your handling code here:
        pnlGivenName.setBackground(new Color(248,248,248));
        txtGivenName.setBackground(new Color(248,248,248));
    }//GEN-LAST:event_txtGivenNameFocusLost

    private void txtBdayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBdayFocusGained
        // TODO add your handling code here:
        pnlBday.setBackground(new Color(240,240,240));
        txtBday.setBackground(new Color(240,240,240));
    }//GEN-LAST:event_txtBdayFocusGained

    private void txtBdayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBdayFocusLost
        // TODO add your handling code here:
        txtBday1.setText(txtBday.getText());
        pnlBday.setBackground(new Color(248,248,248));
        txtBday.setBackground(new Color(248,248,248));
    }//GEN-LAST:event_txtBdayFocusLost

    private void txtMobileNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMobileNumberFocusGained
        // TODO add your handling code here:
        pnlMobileNumber.setBackground(new Color(240,240,240));
        txtMobileNumber.setBackground(new Color(240,240,240));
    }//GEN-LAST:event_txtMobileNumberFocusGained

    private void txtMobileNumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMobileNumberFocusLost
        // TODO add your handling code here:
        pnlMobileNumber.setBackground(new Color(248,248,248));
        txtMobileNumber.setBackground(new Color(248,248,248));
    }//GEN-LAST:event_txtMobileNumberFocusLost

    private void txtDateStartedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateStartedFocusGained
        // TODO add your handling code here:
        pnlDateStarted.setBackground(new Color(240,240,240));
        txtDateStarted.setBackground(new Color(240,240,240));
    }//GEN-LAST:event_txtDateStartedFocusGained

    private void txtDateStartedFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateStartedFocusLost
        // TODO add your handling code here:
        autoEmail();
        txtDateStarted1.setText(txtDateStarted.getText());
        pnlDateStarted.setBackground(new Color(248,248,248));
        txtDateStarted.setBackground(new Color(248,248,248));
    }//GEN-LAST:event_txtDateStartedFocusLost

    private void txtDateGradFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateGradFocusGained
        // TODO add your handling code here:
        pnlDateGrad.setBackground(new Color(240,240,240));
        txtDateGrad.setBackground(new Color(240,240,240));
    }//GEN-LAST:event_txtDateGradFocusGained

    private void txtDateGradFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateGradFocusLost
        // TODO add your handling code here:
        txtDateGrad1.setText(txtDateGrad.getText());
        pnlDateGrad.setBackground(new Color(248,248,248));
        txtDateGrad.setBackground(new Color(248,248,248));
    }//GEN-LAST:event_txtDateGradFocusLost

    private void btnEditRecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditRecMouseClicked
        // TODO add your handling code here:
        if(!(cmbStatus.getSelectedItem().equals("Graduated"))) {
            txtDateGrad.setText(null);
        }
        try {
            ImageIcon icon = (ImageIcon) imgStudent.getIcon();
            Image image = icon.getImage();
            BufferedImage bi = new BufferedImage(
                image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bi.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos); 
            byte[] img = baos.toByteArray();
            
            con = ConnectDB.Connect();  
            String query = "UPDATE student SET lastname=?, firstname=?, email=?, gender=?, course_code = (SELECT course_code FROM course WHERE description=?)"
                    + ", cp_num=?, address=?, birthday=?, status=?, date_started=?, date_graduated=?, student_img=? "
                    + "WHERE student_no = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, capitalize(txtLastName.getText().trim()));
            ps.setString(2, capitalize(txtGivenName.getText().trim()));
            ps.setString(3, txtEmail.getText().trim());
            ps.setString(4, genderAbbv());
            ps.setString(5, cmbCourseCode.getSelectedItem().toString());
            ps.setString(6, txtMobileNumber.getText().trim());
            ps.setString(7, capitalize(txtAddress1.getText().trim()));
            ps.setString(8, txtBday.getText().trim());
            ps.setString(9, statusAbbv());
            ps.setString(10, txtDateStarted.getText().trim());
            ps.setString(11, txtDateGrad.getText().trim());
            ps.setBlob(12, new ByteArrayInputStream(img));
            ps.setString(13, lblStudentNumYear.getText().trim() + txtStudentNum.getText().trim());
            ps.execute();
            
//            ps = con.prepareStatement("UPDATE GRADES (student_no) VALUES (?) WHERE student_no=?");
//            ps.setString(1, lblStudentNumYear.getText().trim() + txtStudentNum.getText().trim());
//            ps.execute();
            
            
            populateTbl();
            JOptionPane.showMessageDialog(null, "Student record has been updated sucessfully!");
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "There are invalid input fields!");
        }
    }//GEN-LAST:event_btnEditRecMouseClicked

    private void btnEditRecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditRecMouseEntered
        // TODO add your handling code here:
        pnlAddRec.setBackground(new Color(135,10,10));
    }//GEN-LAST:event_btnEditRecMouseEntered

    private void btnEditRecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditRecMouseExited
        // TODO add your handling code here:
        pnlAddRec.setBackground(new Color(157,12,12));
    }//GEN-LAST:event_btnEditRecMouseExited

    private void btnDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDateMouseClicked
        // TODO add your handling code here:
      dateChooser.showPopup();
    }//GEN-LAST:event_btnDateMouseClicked

    private void btnDate1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDate1MouseClicked
        // TODO add your handling code here:
        dateChooser1.showPopup();
    }//GEN-LAST:event_btnDate1MouseClicked

    private void txtLastNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastNameKeyReleased
        // TODO add your handling code here:
        txtLastName1.setText(capitalize(txtLastName.getText()));
        autoEmail();
    }//GEN-LAST:event_txtLastNameKeyReleased

    private void txtGivenNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGivenNameKeyReleased
        // TODO add your handling code here:
        txtFirstName.setText(capitalize(txtGivenName.getText()));
        autoEmail();
    }//GEN-LAST:event_txtGivenNameKeyReleased

    private void txtMobileNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMobileNumberKeyReleased
        // TODO add your handling code here:
        txtMobileNum.setText("0" + txtMobileNumber.getText());
    }//GEN-LAST:event_txtMobileNumberKeyReleased

    private void cmbGenderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbGenderFocusLost
        // TODO add your handling code here:
        txtGender.setText(cmbGender.getSelectedItem().toString());
    }//GEN-LAST:event_cmbGenderFocusLost

    private void cmbCourseCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbCourseCodeFocusLost
        // TODO add your handling code here:\
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT course_code FROM course WHERE description = '"+cmbCourseCode.getSelectedItem()+"'");
            rs = ps.executeQuery();
            while(rs.next())
                txtCourseCode.setText(rs.getString("course_code"));
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cmbCourseCodeFocusLost

    private void cmbStatusFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbStatusFocusLost
        // TODO add your handling code here:
        txtStatus.setText(cmbStatus.getSelectedItem().toString());
    }//GEN-LAST:event_cmbStatusFocusLost

    private void btnDate2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDate2MouseClicked
        // TODO add your handling code here:
        dateChooser2.showPopup();
    }//GEN-LAST:event_btnDate2MouseClicked

    private void txtAddress1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAddress1FocusGained
        // TODO add your handling code here:
        pnlAddress.setBackground(new Color(240,240,240));
        txtAddress1.setBackground(new Color(240,240,240));
    }//GEN-LAST:event_txtAddress1FocusGained

    private void txtAddress1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAddress1FocusLost
        // TODO add your handling code here:
        pnlAddress.setBackground(new Color(248,248,248));
        txtAddress1.setBackground(new Color(248,248,248));
    }//GEN-LAST:event_txtAddress1FocusLost

    private void txtAddress1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddress1KeyReleased
        // TODO add your handling code here:
        txtAddress.setText(capitalize(txtAddress1.getText()));
    }//GEN-LAST:event_txtAddress1KeyReleased

    private void txtMobileNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMobileNumberKeyPressed
        // TODO add your handling code here:
        limitInput(evt, txtMobileNumber, 10);
    }//GEN-LAST:event_txtMobileNumberKeyPressed

    private void txtLastNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastNameKeyPressed
        // TODO add your handling code here:
        limitStrInput(evt, txtLastName, 20);
    }//GEN-LAST:event_txtLastNameKeyPressed

    private void txtGivenNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGivenNameKeyPressed
        // TODO add your handling code here:
        limitStrInput(evt, txtGivenName, 20);
    }//GEN-LAST:event_txtGivenNameKeyPressed

    private void txtAddress1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddress1KeyPressed
        // TODO add your handling code here:
        if((txtAddress1.getText().length() < 150) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)
            txtAddress1.setEditable(true);
        else
            txtAddress1.setEditable(false);
    }//GEN-LAST:event_txtAddress1KeyPressed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        txtGender.setText(cmbGender.getSelectedItem().toString());
        txtBday1.setText(txtBday.getText());
        txtDateStarted1.setText(txtDateStarted.getText());
        lblStudentNumYear.setText(txtDateStarted.getText().substring(txtDateStarted.getText().length()-4));
        txtDateGrad1.setText(txtDateGrad.getText());
        courseCodeDisplay();
        txtStatus.setText(cmbStatus.getSelectedItem().toString());
    }//GEN-LAST:event_formMouseClicked

    private void imgStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgStudentMouseClicked
        // TODO add your handling code here:
        JFileChooser chooseFile = new JFileChooser();
        chooseFile.showOpenDialog(null);
        File f = chooseFile.getSelectedFile();
        String path = f.getAbsolutePath();
        try {
            BufferedImage bi = ImageIO.read(new File(path));
            Image img = bi.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img);
            imgStudent.setIcon(icon);
            path2 = path;
        } catch(Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_imgStudentMouseClicked

    private void pnlDefaultPhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDefaultPhotoMouseClicked
        // TODO add your handling code here:
        int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete photo?", "Delete Photo", YES_NO_OPTION);
        if(res == JOptionPane.YES_OPTION){
            try {
                BufferedImage bi = ImageIO.read(new File("C:\\Users\\Joan\\Documents\\NetBeansProjects\\studentrecords\\build\\classes\\studentrecords\\images\\imgStudentSample.png"));
                Image img2 = bi.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
                ImageIcon icon2 = new ImageIcon(img2);
                imgStudent.setIcon(icon2);
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_pnlDefaultPhotoMouseClicked

    private void pnlDefaultPhotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDefaultPhotoMouseEntered
        // TODO add your handling code here:
        pnlDefaultPhoto.setBackground(new Color(135,10,10));
    }//GEN-LAST:event_pnlDefaultPhotoMouseEntered

    private void pnlDefaultPhotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDefaultPhotoMouseExited
        // TODO add your handling code here:
        pnlDefaultPhoto.setBackground(new Color(157,12,12));
    }//GEN-LAST:event_pnlDefaultPhotoMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnDate;
    private javax.swing.JLabel btnDate1;
    private javax.swing.JLabel btnDate2;
    private javax.swing.JLabel btnDeafultPhoto;
    private javax.swing.JLabel btnEditRec;
    public javax.swing.JComboBox<String> cmbCourseCode;
    public javax.swing.JComboBox<String> cmbGender;
    public javax.swing.JComboBox<String> cmbStatus;
    public com.raven.datechooser.DateChooser dateChooser;
    public com.raven.datechooser.DateChooser dateChooser1;
    public com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JPanel displayStudent;
    public javax.swing.JLabel imgStudent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField5;
    public javax.swing.JLabel lblStudentNumYear;
    private studentrecords.RoundedFrame pnlAddRec;
    private studentrecords.RoundedFrame pnlAddress;
    private studentrecords.RoundedFrame pnlBday;
    private studentrecords.RoundedFrame pnlDateGrad;
    private studentrecords.RoundedFrame pnlDateStarted;
    private studentrecords.RoundedFrame pnlDefaultPhoto;
    private studentrecords.RoundedFrame pnlEmail;
    private studentrecords.RoundedFrame pnlGivenName;
    private studentrecords.RoundedFrame pnlLastName;
    private studentrecords.RoundedFrame pnlMobileNumber;
    private studentrecords.RoundedFrame pnlStudentNum;
    private studentrecords.RoundedFrame pnlStudentNum4;
    public javax.swing.JLabel txtAddress;
    public javax.swing.JTextField txtAddress1;
    public javax.swing.JTextField txtBday;
    public javax.swing.JLabel txtBday1;
    public javax.swing.JLabel txtCourseCode;
    public javax.swing.JTextField txtDateGrad;
    public javax.swing.JLabel txtDateGrad1;
    public javax.swing.JTextField txtDateStarted;
    public javax.swing.JLabel txtDateStarted1;
    public javax.swing.JLabel txtEmail;
    public javax.swing.JLabel txtEmail1;
    public javax.swing.JLabel txtFirstName;
    public javax.swing.JLabel txtGender;
    public javax.swing.JTextField txtGivenName;
    public javax.swing.JTextField txtLastName;
    public javax.swing.JLabel txtLastName1;
    public javax.swing.JLabel txtMobileNum;
    public javax.swing.JTextField txtMobileNumber;
    public javax.swing.JLabel txtStatus;
    public javax.swing.JTextField txtStudentNum;
    public javax.swing.JLabel txtStudentNum1;
    // End of variables declaration//GEN-END:variables

}
