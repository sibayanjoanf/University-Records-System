/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package studentrecords;

import com.raven.datechooser.SelectedDate;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Joan
 */
public class StudentGrades extends javax.swing.JPanel {
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    StudentGradesView sgv = new StudentGradesView();
    
    /**
     * Creates new form Dashboard
     */
    public StudentGrades() {
        initComponents();
        
        showDate();
        showTime();
        
        CoursesCard.add(sgv);
        
        sgv.setVerifyInputWhenFocusTarget(false);
        
        sgv.tblGrades.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me) {
                lblStudents1.setText("|   View Grades Records");
            }
        });
        
        populateCmbSY();
        populateCmbSem();
        populateCmbSubject();
    }
    
    public void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("MMM. dd, yyyy");
        String date = s.format(d);
        lblDate.setText(date);
    }
    
    public void showTime() {
        new Timer (0,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss");
                String time = s.format(d);
                lblTime.setText(time);
            }
        }).start();
    }
    
    public void populateTbl() {
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT * FROM vw_student_grades ORDER BY syear");
            rs = ps.executeQuery();
            sgv.tblGrades.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
//    public void clearFields() {
//        csv.txtCourseCode.setText("");
//        csv.txtCourseDesc.setText("");
//        csv.dateChooser.toDay();
//        csv.txtDateClosed.setText("");
//        csv.cmbStatus.setSelectedIndex(0);
//        csv.cmbCollegeCode.setSelectedIndex(0);
//    }
    
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
    
    public void populateCmbSY() {
        cmbSchoolYear.removeAllItems();
        cmbSchoolYear.addItem("");
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT syear FROM schoolyear ORDER BY syear");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                cmbSchoolYear.addItem(rs.getString("syear"));
            }
            ps.close();
            con.close();
            rs.close();
        } catch(Exception e) {
           System.out.println(e);
        }
    }
    
    public void populateCmbSem() {
        cmbSemester.removeAllItems();
        cmbSemester.addItem("");
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT semester FROM semester ORDER BY semester");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                cmbSemester.addItem(rs.getString("semester"));
            }
            ps.close();
            con.close();
            rs.close();
        } catch(Exception e) {
           System.out.println(e);
        }
    }
    
    public void populateCmbSubject() {
        cmbSubject.removeAllItems();
        cmbSubject.addItem("");
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT description FROM subject ORDER BY description");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                cmbSubject.addItem(rs.getString("description"));
            }
            ps.close();
            con.close();
            rs.close();
        } catch(Exception e) {
           System.out.println(e);
        }
    }
    
    public void search() {
            try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT * FROM vw_student_grades WHERE \"School Year\" LIKE '%"+cmbSchoolYear.getSelectedItem().toString()+"%'"
                    + "OR \"Semester\" LIKE '%"+cmbSemester.getSelectedItem().toString()+"%'"
                    + "AND \"Subject Code\" LIKE (SELECT subject_code FROM subject WHERE description = '"+cmbSubject.getSelectedItem().toString()+"')"
                    + "AND \"Block No\" LIKE '%"+cmbBlockNum.getSelectedItem().toString()+"%'");
            rs = ps.executeQuery();
            sgv.tblGrades.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();
            ps.close();
            rs.close();
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

        pnlChangingScreen = new javax.swing.JPanel();
        lblStudents = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        pnlSearchAdd = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmbSchoolYear = new javax.swing.JComboBox<>();
        cmbSemester = new javax.swing.JComboBox<>();
        cmbBlockNum = new javax.swing.JComboBox<>();
        cmbSubject = new javax.swing.JComboBox<>();
        lblStudents1 = new javax.swing.JLabel();
        CoursesCard = new javax.swing.JLayeredPane();
        lblDate1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        pnlChangingScreen.setBackground(new java.awt.Color(240, 241, 243));
        pnlChangingScreen.setPreferredSize(new java.awt.Dimension(1327, 797));

        lblStudents.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblStudents.setText("Student Grades");

        jPanel2.setBackground(new java.awt.Color(157, 12, 12));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        lblDate.setBackground(new java.awt.Color(153, 153, 153));
        lblDate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblDate.setForeground(new java.awt.Color(0, 0, 0));
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDate.setText("MMM. dd, yyyy");
        lblDate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblTime.setBackground(new java.awt.Color(153, 153, 153));
        lblTime.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTime.setForeground(new java.awt.Color(0, 0, 0));
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTime.setText("hh:mm:ss");
        lblTime.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pnlSearchAdd.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/studentrecords/Images/icons8-search-30 (1).png"))); // NOI18N

        txtSearch.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(204, 204, 204));
        txtSearch.setText("Search Student Number");
        txtSearch.setBorder(null);
        txtSearch.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtSearch.setSelectionColor(new java.awt.Color(239, 225, 225));
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        cmbSchoolYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSchoolYear.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbSchoolYearFocusLost(evt);
            }
        });
        cmbSchoolYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSchoolYearActionPerformed(evt);
            }
        });

        cmbSemester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSemester.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbSemesterFocusLost(evt);
            }
        });
        cmbSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSemesterActionPerformed(evt);
            }
        });

        cmbBlockNum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "BLK 1", "BLK 2", "BLK 3", "BLK 4", "BLK 5" }));
        cmbBlockNum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbBlockNumFocusLost(evt);
            }
        });
        cmbBlockNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBlockNumActionPerformed(evt);
            }
        });

        cmbSubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSubject.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbSubjectFocusLost(evt);
            }
        });
        cmbSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSubjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSearchAddLayout = new javax.swing.GroupLayout(pnlSearchAdd);
        pnlSearchAdd.setLayout(pnlSearchAddLayout);
        pnlSearchAddLayout.setHorizontalGroup(
            pnlSearchAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112)
                .addComponent(cmbSchoolYear, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbBlockNum, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSearchAddLayout.setVerticalGroup(
            pnlSearchAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSearchAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchAddLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSearchAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSchoolYear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBlockNum, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        lblStudents1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblStudents1.setForeground(new java.awt.Color(204, 204, 204));
        lblStudents1.setText("|   View Grades");

        CoursesCard.setLayout(new java.awt.CardLayout());

        lblDate1.setBackground(new java.awt.Color(153, 153, 153));
        lblDate1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblDate1.setForeground(new java.awt.Color(204, 204, 204));
        lblDate1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDate1.setText("Date & Time");
        lblDate1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlChangingScreenLayout = new javax.swing.GroupLayout(pnlChangingScreen);
        pnlChangingScreen.setLayout(pnlChangingScreenLayout);
        pnlChangingScreenLayout.setHorizontalGroup(
            pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChangingScreenLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlSearchAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                        .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                                .addComponent(lblStudents)
                                .addGap(18, 18, 18)
                                .addComponent(lblStudents1))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 719, Short.MAX_VALUE)
                        .addComponent(lblDate1)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(CoursesCard, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(40, 40, 40))
        );
        pnlChangingScreenLayout.setVerticalGroup(
            pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                        .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStudents)
                            .addComponent(lblStudents1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                        .addComponent(lblDate)
                        .addGap(3, 3, 3)
                        .addComponent(lblTime))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDate1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(pnlSearchAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CoursesCard, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlChangingScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlChangingScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
        // TODO add your handling code here:
        if(txtSearch.getText().equals("Search Student Number")) {
            txtSearch.setText("");
            txtSearch.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        // TODO add your handling code here:
        if(txtSearch.getText().equals("")) {
            txtSearch.setText("Search Student Number");
            txtSearch.setForeground(new Color(204,204,204));
        }
    }//GEN-LAST:event_txtSearchFocusLost

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT * FROM vw_student_grades WHERE \"School Year\" LIKE '%"+txtSearch.getText()+"%'"
                    + "OR \"Semester\" LIKE '%"+txtSearch.getText()+"%'"
                    + "OR \"Student No\" LIKE '%"+txtSearch.getText()+"%'"
                    + "OR \"Subject Code\" LIKE '%"+txtSearch.getText().toUpperCase()+"%'"
                    + "OR \"Subject\" LIKE '%"+txtSearch.getText().toUpperCase()+"%'"
                    + "OR \"Grade\" LIKE '%"+txtSearch.getText()+"%'"
                    + "OR remark LIKE '%"+capitalize(txtSearch.getText())+"%'"
                    + "OR \"Block No\" LIKE '%"+txtSearch.getText().toUpperCase()+"%'");
            rs = ps.executeQuery();
            sgv.tblGrades.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();
            ps.close();
            rs.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        search();

//    try (Connection con = ConnectDB.Connect();
//         PreparedStatement ps = con.prepareStatement(
//                 "SELECT * FROM vw_student_grades " +
//                 "WHERE syear LIKE ? " +
//                 "OR semester LIKE ? " +
//                 "OR subject_code LIKE (SELECT subject_code FROM subject WHERE description = ?) " +
//                 "OR block_no LIKE ?")) {
//
//        // Set query parameters
//        ps.setString(1, "%" + cmbSchoolYear.getSelectedItem().toString().trim() + "%");
//        ps.setString(2, "%" + cmbSemester.getSelectedItem().toString().trim() + "%");
//        ps.setString(3, cmbSubject.getSelectedItem().toString().trim());
//        ps.setString(4, "%" + cmbBlockNum.getSelectedItem().toString().trim() + "%");
//
//        // Execute query
//        try (ResultSet rs = ps.executeQuery()) {
//            sgv.tblGrades.setModel(DbUtils.resultSetToTableModel(rs));
//        }
//
//    } catch (Exception e) {
//        e.printStackTrace(); // Log the error details
//        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Invalid!", JOptionPane.ERROR_MESSAGE);
//    }
    }//GEN-LAST:event_formMouseClicked

    private void cmbSchoolYearFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSchoolYearFocusLost
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_cmbSchoolYearFocusLost

    private void cmbSemesterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSemesterFocusLost
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_cmbSemesterFocusLost

    private void cmbBlockNumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbBlockNumFocusLost
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_cmbBlockNumFocusLost

    private void cmbSubjectFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSubjectFocusLost
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_cmbSubjectFocusLost

    private void cmbSchoolYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSchoolYearActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_cmbSchoolYearActionPerformed

    private void cmbSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSemesterActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_cmbSemesterActionPerformed

    private void cmbBlockNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBlockNumActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_cmbBlockNumActionPerformed

    private void cmbSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSubjectActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_cmbSubjectActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane CoursesCard;
    private javax.swing.JComboBox<String> cmbBlockNum;
    private javax.swing.JComboBox<String> cmbSchoolYear;
    private javax.swing.JComboBox<String> cmbSemester;
    private javax.swing.JComboBox<String> cmbSubject;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDate1;
    private javax.swing.JLabel lblStudents;
    private javax.swing.JLabel lblStudents1;
    private javax.swing.JLabel lblTime;
    private javax.swing.JPanel pnlChangingScreen;
    private javax.swing.JPanel pnlSearchAdd;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
