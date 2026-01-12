/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package studentrecords;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Joan
 */
public class StudentGradesView extends javax.swing.JPanel {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    /**
     * Creates new form Dashboard
     */
    public StudentGradesView() {
        initComponents();
        
        tblGrades.getTableHeader().setDefaultRenderer(new TableHeader());
        tblGrades.getTableHeader().setPreferredSize(new Dimension(0,40));
        
        populateTbl();
        populateCmbSY();
        populateCmbSem();
        populateCmbCollege();
    }
    
    private class TableHeader extends DefaultTableCellRenderer {
        
        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
            Component com = super.getTableCellRendererComponent(jtable, o, bln1, bln1, i, i1);
            com.setBackground(new Color(157,12,12));
            com.setForeground(Color.white);
            com.setFont(com.getFont().deriveFont(Font.BOLD, 10));
            return com;
        }
    }
    
    public void populateTbl() {
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT * FROM vw_student_grades ORDER BY \"School Year\"");
            rs = ps.executeQuery();
            tblGrades.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void defaultPhoto() {
        try {
            BufferedImage bi = ImageIO.read(new File("C:\\Users\\Joan\\Documents\\NetBeansProjects\\studentrecords\\build\\classes\\studentrecords\\images\\imgStudentSample.png"));
            Image img2 = bi.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            ImageIcon icon2 = new ImageIcon(img2);
            imgStudent.setIcon(icon2);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void populateCmbSY() {
        cmbSchoolYear.removeAllItems();
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT syear FROM schoolyear ORDER BY syear");
            rs = ps.executeQuery();
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
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT semester FROM semester ORDER BY semester");
            rs = ps.executeQuery();
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
    
    public void populateCmbCollege() {
        cmbCollege.removeAllItems();
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT description FROM college ORDER BY description");
            rs = ps.executeQuery();
            while(rs.next()) {
                cmbCollege.addItem(rs.getString("description"));
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
        int row = tblGrades.getSelectedRow();
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT description FROM subject WHERE college_code = (SELECT college_code FROM college where description = '"+cmbCollege.getSelectedItem()+"') ORDER BY description");
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
    
    public void populateStudentDeets() {
        int row = tblGrades.getSelectedRow();
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT lastname, firstname, course_code FROM student WHERE student_no = '"+tblGrades.getModel().getValueAt(row, 2).toString()+"'");
            rs = ps.executeQuery();
            while(rs.next()) {
                txtLastName.setText(rs.getString("lastname"));
                txtFirstName.setText(rs.getString("firstname"));
                txtCourseCode.setText(rs.getString("course_code"));
            }
            ps.close();
            con.close();
            rs.close();
        } catch(Exception e) {
           System.out.println(e);
        }
    }
    
    public void studentImgAppear() {  
        int row = tblGrades.getSelectedRow();
        try {
            con = ConnectDB.Connect();
            String query = "SELECT student_img FROM student WHERE student_no = '"+tblGrades.getModel().getValueAt(row, 2).toString()+"'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if(rs.next()) {
                Blob img = rs.getBlob(1);
                if(img != null) {
                    byte[] bytea = img.getBytes(1, (int)img.length());
                    ImageIcon icon = new ImageIcon(bytea);
                    Image imgTemp = icon.getImage();
                    Image imgResized = imgTemp.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
                    ImageIcon iconResized = new ImageIcon(imgResized);
                    imgStudent.setIcon(iconResized);
                } else {
                    defaultPhoto();
                }
            }
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrades = new javax.swing.JTable();
        displayStudent = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        imgStudent = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtStudentNum = new javax.swing.JLabel();
        txtLastName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JLabel();
        txtCourseCode = new javax.swing.JLabel();
        cmbSchoolYear = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        cmbSemester = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        cmbBlockNum = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        cmbGrade = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        cmbSubject = new javax.swing.JComboBox<>();
        pnlAddRec = new studentrecords.RoundedFrame();
        btnAddRec = new javax.swing.JLabel();
        pnlEditRec = new studentrecords.RoundedFrame();
        btnEditRec = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        cmbCollege = new javax.swing.JComboBox<>();
        pnlDeleteRec = new studentrecords.RoundedFrame();
        btnDeleteRec = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(240, 241, 243));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        tblGrades.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblGrades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGrades.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblGrades.setFocusable(false);
        tblGrades.setRowHeight(50);
        tblGrades.setSelectionBackground(new java.awt.Color(239, 225, 225));
        tblGrades.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblGrades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGradesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGrades);
        if (tblGrades.getColumnModel().getColumnCount() > 0) {
            tblGrades.getColumnModel().getColumn(0).setResizable(false);
            tblGrades.getColumnModel().getColumn(1).setResizable(false);
            tblGrades.getColumnModel().getColumn(2).setResizable(false);
            tblGrades.getColumnModel().getColumn(3).setResizable(false);
        }

        displayStudent.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("Student No.");

        jPanel4.setBackground(new java.awt.Color(229, 229, 229));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        imgStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/studentrecords/Images/imgStudentSample.png"))); // NOI18N
        imgStudent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("Last Name");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("First Name");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("School Year");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setText("Course Code");

        txtStudentNum.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtStudentNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtLastName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtLastName.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtFirstName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtFirstName.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtCourseCode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCourseCode.setToolTipText("");
        txtCourseCode.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        cmbSchoolYear.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setText("Semester");

        cmbSemester.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel30.setText("Block No.");

        cmbBlockNum.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbBlockNum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BLK 1", "BLK 2", "BLK 3", "BLK 4", "BLK 5" }));

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setText("Grade");

        cmbGrade.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbGrade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "1.25", "1.5", "1.75", "2", "2.25", "2.5", "2.75", "3", "4", "5" }));

        jLabel32.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel32.setText("Subject");

        cmbSubject.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        pnlAddRec.setBackground(new java.awt.Color(157, 12, 12));

        btnAddRec.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAddRec.setForeground(new java.awt.Color(255, 255, 255));
        btnAddRec.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddRec.setText("+ Add");
        btnAddRec.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddRec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddRecMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddRecMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddRecMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlAddRecLayout = new javax.swing.GroupLayout(pnlAddRec);
        pnlAddRec.setLayout(pnlAddRecLayout);
        pnlAddRecLayout.setHorizontalGroup(
            pnlAddRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAddRec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
        );
        pnlAddRecLayout.setVerticalGroup(
            pnlAddRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddRecLayout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addComponent(btnAddRec, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlEditRec.setBackground(new java.awt.Color(0, 102, 153));

        btnEditRec.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnEditRec.setForeground(new java.awt.Color(255, 255, 255));
        btnEditRec.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEditRec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/studentrecords/Images/icons8-edit-25.png"))); // NOI18N
        btnEditRec.setText("Edit");
        btnEditRec.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditRec.setIconTextGap(10);
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

        javax.swing.GroupLayout pnlEditRecLayout = new javax.swing.GroupLayout(pnlEditRec);
        pnlEditRec.setLayout(pnlEditRecLayout);
        pnlEditRecLayout.setHorizontalGroup(
            pnlEditRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEditRec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
        );
        pnlEditRecLayout.setVerticalGroup(
            pnlEditRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEditRec, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        jLabel33.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel33.setText("College");

        cmbCollege.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbCollege.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbCollegeFocusLost(evt);
            }
        });
        cmbCollege.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCollegeActionPerformed(evt);
            }
        });

        pnlDeleteRec.setBackground(new java.awt.Color(255, 255, 255));
        pnlDeleteRec.setToolTipText("Delete Record");

        btnDeleteRec.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDeleteRec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/studentrecords/Images/icons8-delete-15 (1).png"))); // NOI18N
        btnDeleteRec.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteRec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteRecMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteRecMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteRecMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlDeleteRecLayout = new javax.swing.GroupLayout(pnlDeleteRec);
        pnlDeleteRec.setLayout(pnlDeleteRecLayout);
        pnlDeleteRecLayout.setHorizontalGroup(
            pnlDeleteRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDeleteRec, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );
        pnlDeleteRecLayout.setVerticalGroup(
            pnlDeleteRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDeleteRecLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDeleteRec, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout displayStudentLayout = new javax.swing.GroupLayout(displayStudent);
        displayStudent.setLayout(displayStudentLayout);
        displayStudentLayout.setHorizontalGroup(
            displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayStudentLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, displayStudentLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(displayStudentLayout.createSequentialGroup()
                                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(imgStudent, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(15, 15, 15)
                                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCourseCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(displayStudentLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel21)))))
                            .addGroup(displayStudentLayout.createSequentialGroup()
                                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmbSchoolYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(displayStudentLayout.createSequentialGroup()
                                        .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30)
                                            .addComponent(cmbBlockNum, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel31)
                                            .addComponent(cmbGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(30, 30, 30)
                                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayStudentLayout.createSequentialGroup()
                                        .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cmbSubject, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(displayStudentLayout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(pnlEditRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(pnlAddRec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGap(9, 9, 9))
                                    .addComponent(cmbSemester, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(displayStudentLayout.createSequentialGroup()
                                        .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel32)
                                            .addComponent(jLabel28))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(displayStudentLayout.createSequentialGroup()
                                .addComponent(cmbCollege, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, displayStudentLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(37, 37, 37)
                        .addComponent(txtStudentNum, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlDeleteRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        displayStudentLayout.setVerticalGroup(
            displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayStudentLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDeleteRec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtStudentNum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imgStudent)
                    .addGroup(displayStudentLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCourseCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(31, 31, 31)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(displayStudentLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cmbSchoolYear, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(displayStudentLayout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayStudentLayout.createSequentialGroup()
                        .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(displayStudentLayout.createSequentialGroup()
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbCollege, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(displayStudentLayout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbBlockNum, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlAddRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlEditRec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayStudentLayout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cmbGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(displayStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(displayStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblGradesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGradesMouseClicked
        // TODO add your handling code here:
        int row = tblGrades.getSelectedRow();
        populateStudentDeets();
        cmbSchoolYear.setSelectedItem(tblGrades.getModel().getValueAt(row, 0).toString());
        cmbSemester.setSelectedItem(tblGrades.getModel().getValueAt(row, 1).toString());
        txtStudentNum.setText(tblGrades.getModel().getValueAt(row, 2).toString());
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT description FROM college WHERE college_code = '"+tblGrades.getModel().getValueAt(row, 5).toString()+"'");
            rs = ps.executeQuery();
            while(rs.next()) {
                cmbCollege.setSelectedItem(rs.getString("description"));
            }
            ps.close();
            con.close();
            rs.close();
        } catch(Exception e) {
           System.out.println(e);
        }
        cmbSubject.setSelectedItem(tblGrades.getModel().getValueAt(row, 4).toString());
        cmbBlockNum.setSelectedItem(tblGrades.getModel().getValueAt(row, 6).toString());
        cmbGrade.setSelectedItem(tblGrades.getModel().getValueAt(row, 7).toString());
        studentImgAppear();
    }//GEN-LAST:event_tblGradesMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
//        tblGrades.clearSelection();
//        try {
//            clearFields();
//        } catch (IOException ex) {
//            Logger.getLogger(StudentGradesView.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jPanel1MouseClicked

    private void btnAddRecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddRecMouseClicked
        // TODO add your handling code here:
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("INSERT INTO grades VALUES('"+cmbSchoolYear.getSelectedItem().toString()+"'"
            + ", '"+cmbSemester.getSelectedItem().toString()+"'"
            + ", '"+txtStudentNum.getText().trim()+"'"
            + ", (SELECT subject_code FROM subject WHERE description = '"+cmbSubject.getSelectedItem().toString()+"')"
            + ", (SELECT college_code FROM subject WHERE description = '"+cmbSubject.getSelectedItem().toString()+"')"
            + ", '"+cmbBlockNum.getSelectedItem().toString()+"'"
            + ", '"+cmbGrade.getSelectedItem()+"')");
            ps.execute();
            populateTbl();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "The record you are trying to add already exists!");
        }
    }//GEN-LAST:event_btnAddRecMouseClicked

    private void btnAddRecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddRecMouseEntered
        // TODO add your handling code here:
        pnlAddRec.setBackground(new Color(135,10,10));
    }//GEN-LAST:event_btnAddRecMouseEntered

    private void btnAddRecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddRecMouseExited
        // TODO add your handling code here:
        pnlAddRec.setBackground(new Color(157,12,12));
    }//GEN-LAST:event_btnAddRecMouseExited

    private void btnEditRecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditRecMouseExited
        // TODO add your handling code here:
        pnlEditRec.setBackground(new Color(0,102,153));
    }//GEN-LAST:event_btnEditRecMouseExited

    private void btnEditRecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditRecMouseEntered
        // TODO add your handling code here:
        pnlEditRec.setBackground(new Color(0,79,119));
    }//GEN-LAST:event_btnEditRecMouseEntered

    private void btnEditRecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditRecMouseClicked
        // TODO add your handling code here:
        cmbSchoolYear.setEnabled(false);
        cmbSemester.setEnabled(false);
        cmbSubject.setEnabled(false);
        cmbBlockNum.setEnabled(false);
        
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("UPDATE grades SET grade=? WHERE syear=? AND semester=? AND subject_code=(SELECT subject_code FROM subject WHERE description=?) AND college_code=(SELECT college_code FROM college WHERE description = ?) AND block_no=? AND student_no=?");
            ps.setString(2, cmbSchoolYear.getSelectedItem().toString());
            ps.setString(3, cmbSemester.getSelectedItem().toString());
            ps.setString(4, cmbSubject.getSelectedItem().toString());
            ps.setString(5, cmbCollege.getSelectedItem().toString());
            ps.setString(6, cmbBlockNum.getSelectedItem().toString());
            ps.setString(1, cmbGrade.getSelectedItem().toString());
            ps.setString(7, txtStudentNum.getText());
            ps.execute();
            populateTbl();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Invalid!");
        }
        
        cmbSchoolYear.setEnabled(true);
        cmbSemester.setEnabled(true);
        cmbSubject.setEnabled(true);
        cmbBlockNum.setEnabled(true);
    }//GEN-LAST:event_btnEditRecMouseClicked

    private void cmbCollegeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCollegeActionPerformed
        // TODO add your handling code here:
        populateCmbSubject();
    }//GEN-LAST:event_cmbCollegeActionPerformed

    private void btnDeleteRecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteRecMouseClicked
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(null, "Do you want to delete this record?", "Confirm", YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION) {

            if(txtStudentNum.getText().equals(""))
            JOptionPane.showMessageDialog(null, "There is no selected record!");
            else
            try {
                con = ConnectDB.Connect();
                ps = con.prepareStatement("DELETE FROM grades WHERE syear=? AND semester=? AND subject_code=(SELECT subject_code FROM subject WHERE description=?) AND college_code=(SELECT college_code FROM college WHERE description = ?) AND block_no=? AND student_no=? AND grade=?");
                ps.setString(1, cmbSchoolYear.getSelectedItem().toString());
                ps.setString(2, cmbSemester.getSelectedItem().toString());
                ps.setString(3, cmbSubject.getSelectedItem().toString());
                ps.setString(4, cmbCollege.getSelectedItem().toString());
                ps.setString(5, cmbBlockNum.getSelectedItem().toString());
                ps.setString(6, txtStudentNum.getText());
                ps.setString(7, cmbGrade.getSelectedItem().toString());
                ps.execute();
                ps.close();
                con.close();
                populateTbl();
                JOptionPane.showMessageDialog(null, "A record was successfully deleted!");
            } catch(Exception e) {
                System.out.println(e);
            }
        } else
        JOptionPane.showMessageDialog(null, "Deleting was aborted!");
    }//GEN-LAST:event_btnDeleteRecMouseClicked

    private void btnDeleteRecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteRecMouseEntered
        // TODO add your handling code here:
        pnlDeleteRec.setBackground(new Color(239,225,225));
    }//GEN-LAST:event_btnDeleteRecMouseEntered

    private void btnDeleteRecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteRecMouseExited
        // TODO add your handling code here:
        pnlDeleteRec.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnDeleteRecMouseExited

    private void cmbCollegeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbCollegeFocusLost
        // TODO add your handling code here:
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT description FROM subject "
                    + "WHERE college_code = (SELECT college_code FROM college "
                    + "WHERE description = '"+cmbCollege.getSelectedItem().toString()+"')");
            rs = ps.executeQuery();
            while(rs.next()) {
                cmbSubject.setSelectedItem(rs.getString("description"));
            }
            ps.close();
            con.close();
            rs.close();
        } catch(Exception e) {
           System.out.println(e);
        }
    }//GEN-LAST:event_cmbCollegeFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAddRec;
    private javax.swing.JLabel btnDeleteRec;
    private javax.swing.JLabel btnEditRec;
    private javax.swing.JComboBox<String> cmbBlockNum;
    private javax.swing.JComboBox<String> cmbCollege;
    private javax.swing.JComboBox<String> cmbGrade;
    private javax.swing.JComboBox<String> cmbSchoolYear;
    private javax.swing.JComboBox<String> cmbSemester;
    private javax.swing.JComboBox<String> cmbSubject;
    private javax.swing.JPanel displayStudent;
    public javax.swing.JLabel imgStudent;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private studentrecords.RoundedFrame pnlAddRec;
    private studentrecords.RoundedFrame pnlDeleteRec;
    private studentrecords.RoundedFrame pnlEditRec;
    public javax.swing.JTable tblGrades;
    public javax.swing.JLabel txtCourseCode;
    public javax.swing.JLabel txtFirstName;
    public javax.swing.JLabel txtLastName;
    public javax.swing.JLabel txtStudentNum;
    // End of variables declaration//GEN-END:variables
}
