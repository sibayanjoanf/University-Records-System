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
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class EmployeesView extends javax.swing.JPanel {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    /**
     * Creates new form Dashboard
     */
    public EmployeesView() {
        initComponents();
        
        tblEmployees.getTableHeader().setDefaultRenderer(new TableHeader());
        tblEmployees.getTableHeader().setPreferredSize(new Dimension(0,40));
        
        populateTbl();
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
            ps = con.prepareStatement("SELECT * FROM vw_employee ORDER BY \"Last Name\"");
            rs = ps.executeQuery();
            tblEmployees.setModel(DbUtils.resultSetToTableModel(rs));
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
            imgEmployee.setIcon(icon2);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void clearFields() {
        txtEmployeeID.setText("");
        txtLastName.setText("");
        txtFirstName.setText("");
        txtMobileNum.setText("");
        txtEmail.setText("");
        txtGender.setText("");
        txtStatus.setText("");
        txtBday.setText("");
        txtDateStarted.setText("");
        txtDateResigned.setText("");
        txtAddress.setText("");
        defaultPhoto();
    }
    
    public void gender() {
        int row = tblEmployees.getSelectedRow();
        switch(tblEmployees.getModel().getValueAt(row, 4).toString()) {
            case "F" -> txtGender.setText("Female");
            case "M" -> txtGender.setText("Male");
            default -> txtGender.setText("Non-binary");
        }      
    }
    
    public void status() {
        int row = tblEmployees.getSelectedRow();
        switch(tblEmployees.getModel().getValueAt(row, 8).toString()) {
            case "A" -> txtStatus.setText("Active");
            case "I" -> txtStatus.setText("Inactive");
            default -> txtStatus.setText("Resigned");
        }
    }
    
    public String modifyDate(int column) {
        int row = tblEmployees.getSelectedRow();
        Object value = tblEmployees.getModel().getValueAt(row, column);
        if(value != null) {
            String date = value.toString();
            if(!date.isBlank()) {
                String year = date.substring(0,4);
                String month = date.substring(5,7);
                String day = date.substring(8,10);
                return month + "/" + day + "/" + year;
            }
        }
        return null;
    }
    
    public void studentImgAppear() {        
        int row = tblEmployees.getSelectedRow();
        try {
            con = ConnectDB.Connect();
            String query = "SELECT employee_img FROM employee WHERE employee_id = '"+tblEmployees.getModel().getValueAt(row, 0).toString()+"'";
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
                    imgEmployee.setIcon(iconResized);
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
        tblEmployees = new javax.swing.JTable();
        displayStudent = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        imgEmployee = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtEmployeeID = new javax.swing.JLabel();
        pnlDeleteRec = new studentrecords.RoundedFrame();
        btnDeleteRec = new javax.swing.JLabel();
        txtLastName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JLabel();
        txtEmail = new javax.swing.JLabel();
        txtGender = new javax.swing.JLabel();
        txtMobileNum = new javax.swing.JLabel();
        txtAddress = new javax.swing.JLabel();
        txtBday = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        txtDateStarted = new javax.swing.JLabel();
        txtDateResigned = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(240, 241, 243));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        tblEmployees.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblEmployees.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEmployees.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblEmployees.setFocusable(false);
        tblEmployees.setRowHeight(50);
        tblEmployees.setSelectionBackground(new java.awt.Color(239, 225, 225));
        tblEmployees.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblEmployees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmployeesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmployees);
        if (tblEmployees.getColumnModel().getColumnCount() > 0) {
            tblEmployees.getColumnModel().getColumn(0).setResizable(false);
            tblEmployees.getColumnModel().getColumn(1).setResizable(false);
            tblEmployees.getColumnModel().getColumn(2).setResizable(false);
            tblEmployees.getColumnModel().getColumn(3).setResizable(false);
        }

        displayStudent.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("Employee ID");

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

        imgEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/studentrecords/Images/imgStudentSample.png"))); // NOI18N
        imgEmployee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        imgEmployee.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("Last Name");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("First Name");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("Email");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setText("Gender");

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
        jLabel27.setText("Date Resigned");

        txtEmployeeID.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEmployeeID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

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
            .addComponent(btnDeleteRec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtLastName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtLastName.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtFirstName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtFirstName.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtGender.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtMobileNum.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtAddress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtBday.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtDateStarted.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtDateResigned.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout displayStudentLayout = new javax.swing.GroupLayout(displayStudent);
        displayStudent.setLayout(displayStudentLayout);
        displayStudentLayout.setHorizontalGroup(
            displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayStudentLayout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(displayStudentLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDeleteRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtBday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtDateStarted, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtDateResigned, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtGender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                        .addComponent(txtMobileNum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(displayStudentLayout.createSequentialGroup()
                                .addComponent(imgEmployee)
                                .addGap(18, 18, 18)
                                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayStudentLayout.createSequentialGroup()
                                        .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel18))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(25, 25, 25))
        );
        displayStudentLayout.setVerticalGroup(
            displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayStudentLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmployeeID, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDeleteRec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgEmployee)
                    .addGroup(displayStudentLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(txtBday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateStarted, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateResigned, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(displayStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnDeleteRecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteRecMouseEntered
        // TODO add your handling code here:
        pnlDeleteRec.setBackground(new Color(239,225,225));
    }//GEN-LAST:event_btnDeleteRecMouseEntered

    private void btnDeleteRecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteRecMouseExited
        // TODO add your handling code here:
        pnlDeleteRec.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnDeleteRecMouseExited

    private void tblEmployeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeesMouseClicked
        // TODO add your handling code here:
        int row = tblEmployees.getSelectedRow();
        txtEmployeeID.setText(tblEmployees.getModel().getValueAt(row, 0).toString());
        txtLastName.setText(tblEmployees.getModel().getValueAt(row, 1).toString());
        txtFirstName.setText(tblEmployees.getModel().getValueAt(row, 2).toString());
        txtEmail.setText(tblEmployees.getModel().getValueAt(row, 3).toString());
        gender();
        txtMobileNum.setText("0" + tblEmployees.getModel().getValueAt(row, 5).toString());
        txtAddress.setText(tblEmployees.getModel().getValueAt(row, 6).toString());
        txtBday.setText(modifyDate(7));
        status();
        txtDateStarted.setText(modifyDate(9));
        txtDateResigned.setText(modifyDate(10));
        studentImgAppear();
    }//GEN-LAST:event_tblEmployeesMouseClicked

    private void btnDeleteRecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteRecMouseClicked
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(null, "Do you want to delete this record?", "Confirm", YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION) {
            
            if(txtEmployeeID.getText().equals(""))
                JOptionPane.showMessageDialog(null, "There is no selected record!");
            else
                try {
                    con = ConnectDB.Connect();
                    ps = con.prepareStatement("DELETE FROM employee WHERE employee_id = '"+txtEmployeeID.getText().trim()+"'");
                    ps.execute();
                    ps.close();
                    con.close();
                    populateTbl();
                    clearFields();
                    JOptionPane.showMessageDialog(null, "A record was successfully deleted!");
                } catch(Exception e) {
                    System.out.println(e);
                }
        } else 
            JOptionPane.showMessageDialog(null, "Deleting was aborted!");
    }//GEN-LAST:event_btnDeleteRecMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        tblEmployees.clearSelection();
        clearFields();
    }//GEN-LAST:event_jPanel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnDeleteRec;
    private javax.swing.JPanel displayStudent;
    public javax.swing.JLabel imgEmployee;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private studentrecords.RoundedFrame pnlDeleteRec;
    public javax.swing.JTable tblEmployees;
    public javax.swing.JLabel txtAddress;
    public javax.swing.JLabel txtBday;
    public javax.swing.JLabel txtDateResigned;
    public javax.swing.JLabel txtDateStarted;
    public javax.swing.JLabel txtEmail;
    public javax.swing.JLabel txtEmployeeID;
    public javax.swing.JLabel txtFirstName;
    public javax.swing.JLabel txtGender;
    public javax.swing.JLabel txtLastName;
    public javax.swing.JLabel txtMobileNum;
    public javax.swing.JLabel txtStatus;
    // End of variables declaration//GEN-END:variables
}
