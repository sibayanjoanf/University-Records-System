/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package studentrecords;

import com.raven.datechooser.SelectedDate;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static javax.management.Query.value;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Joan
 */
public class CollegesView extends javax.swing.JPanel {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    /**
     * Creates new form Dashboard
     */
    public CollegesView() {
        initComponents();
        
        tblColleges.getTableHeader().setDefaultRenderer(new TableHeader());
        tblColleges.getTableHeader().setPreferredSize(new Dimension(0,40));
//        tblColleges.setDefaultRenderer(Object.class, new BoldSelectedRow());
        
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
    
//    public class BoldSelectedRow extends DefaultTableCellRenderer {
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,int column) {
//            Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//            if(isSelected)
//                com.setFont(com.getFont().deriveFont(Font.BOLD));
//            else
//                com.setFont(com.getFont().deriveFont(Font.PLAIN));
//            return com;
//        }
//    }
    
    public void populateTbl() {
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT * FROM vw_college ORDER BY \"College Code\"");
            rs = ps.executeQuery();
            tblColleges.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void clearFields() {
        txtCollegeCode.setText("");
        txtCollegeDesc.setText("");
        dateChooser.toDay();
        txtDateClosed.setText("");
        cmbStatus.setSelectedIndex(0);
    }
    
    public void status() {
        int row = tblColleges.getSelectedRow();
        switch(tblColleges.getModel().getValueAt(row, 4).toString()) {
            case "A" -> cmbStatus.setSelectedItem("Active");
            default -> cmbStatus.setSelectedItem("Inactive");
        }
    }
    
    public String statusAbbv() {
        String status;
        if(cmbStatus.getSelectedItem().equals("Active"))
            status = "A";
        else
            status = "I";
        return status;
    }
    
    public String modifyDate(int column) {
        int row = tblColleges.getSelectedRow();
        Object value = tblColleges.getModel().getValueAt(row, column);
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
    
    public int[] date(String dateString) {
        int month = Integer.parseInt(dateString.substring(0,2));
        int day = Integer.parseInt(dateString.substring(3,5));
        int year = Integer.parseInt(dateString.substring(6,10));
        return new int[] {month, day, year};
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser = new com.raven.datechooser.DateChooser();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblColleges = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pnlDeleteRec = new studentrecords.RoundedFrame();
        btnDeleteRec = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtCollegeDesc = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtDateOpened = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnDateOpened = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtDateClosed = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnDateClosed = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        pnlAddRec = new studentrecords.RoundedFrame();
        btnAddRec = new javax.swing.JLabel();
        txtCollegeCode = new javax.swing.JTextField();

        dateChooser.setForeground(new java.awt.Color(157, 12, 12));
        dateChooser.setDateFormat("MM/dd/yyyy");
        dateChooser.setTextRefernce(txtDateOpened);

        dateChooser1.setForeground(new java.awt.Color(157, 12, 12));
        dateChooser1.setDateFormat("MM/dd/yyyy");
        dateChooser1.setTextRefernce(txtDateClosed);

        jPanel1.setBackground(new java.awt.Color(240, 241, 243));

        tblColleges.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblColleges.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "COLLEGE_CODE", "DESCRIPTION", "DATE_OPENED", "DATE_CLOSED", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblColleges.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblColleges.setFocusable(false);
        tblColleges.setRowHeight(50);
        tblColleges.setSelectionBackground(new java.awt.Color(239, 225, 225));
        tblColleges.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblColleges.setShowGrid(false);
        tblColleges.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCollegesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblColleges);
        if (tblColleges.getColumnModel().getColumnCount() > 0) {
            tblColleges.getColumnModel().getColumn(0).setResizable(false);
            tblColleges.getColumnModel().getColumn(1).setResizable(false);
            tblColleges.getColumnModel().getColumn(2).setResizable(false);
            tblColleges.getColumnModel().getColumn(3).setResizable(false);
            tblColleges.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("College Code");

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
            .addComponent(btnDeleteRec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );
        pnlDeleteRecLayout.setVerticalGroup(
            pnlDeleteRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDeleteRecLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDeleteRec, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/studentrecords/Images/plmlogo_180px.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("Description");

        txtCollegeDesc.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCollegeDesc.setBorder(null);
        txtCollegeDesc.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtCollegeDesc.setSelectionColor(new java.awt.Color(221, 197, 197));
        txtCollegeDesc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCollegeDescFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCollegeDescFocusLost(evt);
            }
        });
        txtCollegeDesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCollegeDescKeyPressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(229, 229, 229));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(229, 229, 229));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        txtDateOpened.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDateOpened.setBorder(null);
        txtDateOpened.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDateOpenedFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDateOpenedFocusLost(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("Date Opened");

        btnDateOpened.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDateOpened.setIcon(new javax.swing.ImageIcon(getClass().getResource("/studentrecords/Images/icons8-calendar-13.png"))); // NOI18N
        btnDateOpened.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDateOpened.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDateOpenedMouseClicked(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(229, 229, 229));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        txtDateClosed.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDateClosed.setBorder(null);
        txtDateClosed.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDateClosedFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDateClosedFocusLost(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("Date Closed");

        btnDateClosed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDateClosed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/studentrecords/Images/icons8-calendar-13.png"))); // NOI18N
        btnDateClosed.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDateClosed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDateClosedMouseClicked(evt);
            }
        });

        cmbStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("Status");

        pnlAddRec.setBackground(new java.awt.Color(157, 12, 12));

        btnAddRec.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAddRec.setForeground(new java.awt.Color(255, 255, 255));
        btnAddRec.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddRec.setText("Add Record");
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
            .addComponent(btnAddRec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlAddRecLayout.setVerticalGroup(
            pnlAddRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddRecLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAddRec, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtCollegeCode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCollegeCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCollegeCode.setBorder(null);
        txtCollegeCode.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtCollegeCode.setSelectionColor(new java.awt.Color(221, 197, 197));
        txtCollegeCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCollegeCodeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCollegeCodeFocusLost(evt);
            }
        });
        txtCollegeCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCollegeCodeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(30, 30, 30)
                        .addComponent(txtCollegeCode, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(pnlDeleteRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCollegeDesc)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtDateOpened, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDateOpened, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19)
                            .addComponent(cmbStatus, 0, 224, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtDateClosed, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDateClosed, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlAddRec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(26, 26, 26))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDeleteRec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCollegeCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtCollegeDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDateOpened, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDateOpened, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDateClosed, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDateClosed, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlAddRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void tblCollegesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCollegesMouseClicked
        // TODO add your handling code here:
        int row = tblColleges.getSelectedRow();
        txtCollegeCode.setText(tblColleges.getModel().getValueAt(row, 0).toString());
        txtCollegeDesc.setText(tblColleges.getModel().getValueAt(row, 1).toString());
        txtDateOpened.setText(modifyDate(2));
        txtDateClosed.setText(modifyDate(3));
        status();
        btnAddRec.setText("Update Record");
        txtCollegeCode.setEditable(false);
        txtCollegeCode.setFocusable(false);
        
        int[] dateStarted = date(txtDateOpened.getText());
        dateChooser.setSelectedDate(new SelectedDate(dateStarted[1], dateStarted[0], dateStarted[2]));
            
        Object val = txtDateClosed.getText();
        if (val != null && !val.toString().isBlank()) {
            String dateClose = val.toString().trim();
            int[] dateClosed = date(dateClose);
            dateChooser1.setSelectedDate(new SelectedDate(dateClosed[1], dateClosed[0], dateClosed[2]));
        } else {
            dateChooser1.toDay();
            txtDateClosed.setText(null);
        }
    }//GEN-LAST:event_tblCollegesMouseClicked

    private void btnDeleteRecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteRecMouseClicked
        // TODO add your handling code here:
        if(btnAddRec.getText().equals("Update Record")) {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to delete this record?", "Confirm", YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION) {
                if(txtCollegeCode.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "There is no selected record!");
                else
                    try {
                        con = ConnectDB.Connect();
                        ps = con.prepareStatement("DELETE FROM college WHERE college_code = '"+txtCollegeCode.getText().trim()+"'");
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
        }
    }//GEN-LAST:event_btnDeleteRecMouseClicked

    private void btnDeleteRecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteRecMouseEntered
        // TODO add your handling code here:
        pnlDeleteRec.setBackground(new Color(239,225,225));
    }//GEN-LAST:event_btnDeleteRecMouseEntered

    private void btnDeleteRecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteRecMouseExited
        // TODO add your handling code here:
        pnlDeleteRec.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnDeleteRecMouseExited

    private void txtCollegeDescFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCollegeDescFocusGained
        // TODO add your handling code here:
        jPanel2.setBackground(new Color(157,12,12));
    }//GEN-LAST:event_txtCollegeDescFocusGained

    private void txtCollegeDescFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCollegeDescFocusLost
        // TODO add your handling code here:
        jPanel2.setBackground(new Color(229,229,229));
    }//GEN-LAST:event_txtCollegeDescFocusLost

    private void txtDateOpenedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateOpenedFocusGained
        // TODO add your handling code here:
        jPanel5.setBackground(new Color(157,12,12));
    }//GEN-LAST:event_txtDateOpenedFocusGained

    private void txtDateOpenedFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateOpenedFocusLost
        // TODO add your handling code here:
        jPanel5.setBackground(new Color(229,229,229));
    }//GEN-LAST:event_txtDateOpenedFocusLost

    private void btnDateOpenedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDateOpenedMouseClicked
        // TODO add your handling code here:
        dateChooser.showPopup();
    }//GEN-LAST:event_btnDateOpenedMouseClicked

    private void txtDateClosedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateClosedFocusGained
        // TODO add your handling code here:
        jPanel6.setBackground(new Color(157,12,12));
    }//GEN-LAST:event_txtDateClosedFocusGained

    private void txtDateClosedFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateClosedFocusLost
        // TODO add your handling code here:
        jPanel6.setBackground(new Color(229,229,229));
    }//GEN-LAST:event_txtDateClosedFocusLost

    private void btnDateClosedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDateClosedMouseClicked
        // TODO add your handling code here:
        dateChooser1.showPopup();
    }//GEN-LAST:event_btnDateClosedMouseClicked

    private void btnAddRecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddRecMouseClicked
        // TODO add your handling code here:
        if(btnAddRec.getText().equals("Add Record")) {
            if(cmbStatus.getSelectedItem().equals("Active")) {
                txtDateClosed.setText(null);
            }
            try {
                con = ConnectDB.Connect();
                ps = con.prepareStatement("INSERT INTO college VALUES('"+txtCollegeCode.getText().trim().toUpperCase()+"'"
                    + ", '"+txtCollegeDesc.getText().trim()+"'"
                    + ", '"+txtDateOpened.getText().trim()+"'"
                    + ", '"+txtDateClosed.getText().trim()+"'"
                    + ", '"+statusAbbv()+"')");
                ps.execute();
                populateTbl();
                clearFields();
                JOptionPane.showMessageDialog(null, "New college record has been added sucessfully!");
                ps.close();
                con.close();
            } catch(Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "There are invalid input fields!");
            }
        } else {
            if(cmbStatus.getSelectedItem().equals("Active")) {
                txtDateClosed.setText(null);
            }
            try {
                con = ConnectDB.Connect();
                ps = con.prepareStatement("UPDATE college SET description = '"+txtCollegeDesc.getText().trim()+"'"
                    + ", date_opened = '"+txtDateOpened.getText().trim()+"'"
                    + ", date_closed = '"+txtDateClosed.getText().trim()+"'"
                    + ", status = '"+statusAbbv()+"'"
                    + "WHERE college_code = '"+txtCollegeCode.getText()+"'");
                ps.execute();
                populateTbl();
                JOptionPane.showMessageDialog(null, "College record has been updated sucessfully!");
                ps.close();
                con.close();
            } catch(Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "There are invalid input fields!");
            }
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

    private void txtCollegeCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCollegeCodeFocusGained
        // TODO add your handling code here:
        jPanel4.setBackground(new Color(157,12,12));
    }//GEN-LAST:event_txtCollegeCodeFocusGained

    private void txtCollegeCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCollegeCodeFocusLost
        // TODO add your handling code here:
        jPanel4.setBackground(new Color(229,229,229));
    }//GEN-LAST:event_txtCollegeCodeFocusLost

    private void txtCollegeCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCollegeCodeKeyPressed
        // TODO add your handling code here:
        limitStrInput(evt, txtCollegeCode, 10);
    }//GEN-LAST:event_txtCollegeCodeKeyPressed

    private void txtCollegeDescKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCollegeDescKeyPressed
        // TODO add your handling code here:
        limitStrInput(evt, txtCollegeDesc, 100);
    }//GEN-LAST:event_txtCollegeDescKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel btnAddRec;
    private javax.swing.JLabel btnDateClosed;
    private javax.swing.JLabel btnDateOpened;
    public javax.swing.JLabel btnDeleteRec;
    public javax.swing.JComboBox<String> cmbStatus;
    public com.raven.datechooser.DateChooser dateChooser;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private studentrecords.RoundedFrame pnlAddRec;
    public studentrecords.RoundedFrame pnlDeleteRec;
    public javax.swing.JTable tblColleges;
    public javax.swing.JTextField txtCollegeCode;
    public javax.swing.JTextField txtCollegeDesc;
    public javax.swing.JTextField txtDateClosed;
    public javax.swing.JTextField txtDateOpened;
    // End of variables declaration//GEN-END:variables
}
