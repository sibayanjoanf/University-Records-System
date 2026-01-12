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
public class SubjectsView extends javax.swing.JPanel {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    /**
     * Creates new form Dashboard
     */
    public SubjectsView() {
        initComponents();
        
        tblSubjects.getTableHeader().setDefaultRenderer(new TableHeader());
        tblSubjects.getTableHeader().setPreferredSize(new Dimension(0,40));
        
        populateTbl();
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
            ps = con.prepareStatement("SELECT * FROM vw_subject ORDER BY \"Subject Code\"");
            rs = ps.executeQuery();
            tblSubjects.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void populateCmbCollege() {
        cmbCollegeCode.removeAllItems();
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT description FROM college WHERE status = 'A' ORDER BY description");
            rs = ps.executeQuery();
            while(rs.next()) {
                cmbCollegeCode.addItem(rs.getString("description"));
            }
            ps.close();
            con.close();
            rs.close();
        } catch(Exception e) {
           System.out.println(e);
        }
    }
    
    public void clearFields() {
        txtSubjectCode.setText("");
        txtSubjectDesc.setText("");
        dateChooser.toDay();
        txtDateClosed.setText("");
        cmbStatus.setSelectedIndex(0);
        cmbCollegeCode.setSelectedIndex(0);
        cmbUnits.setSelectedIndex(0);
        cmbCurriculum.setSelectedIndex(0);
    }
    
    public void status() {
        int row = tblSubjects.getSelectedRow();
        switch(tblSubjects.getModel().getValueAt(row, 5).toString()) {
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
        int row = tblSubjects.getSelectedRow();
        Object value = tblSubjects.getModel().getValueAt(row, column);
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
        tblSubjects = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pnlDeleteRec = new studentrecords.RoundedFrame();
        btnDeleteRec = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtSubjectDesc = new javax.swing.JTextField();
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
        txtSubjectCode = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        cmbCollegeCode = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cmbUnits = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        cmbCurriculum = new javax.swing.JComboBox<>();

        dateChooser.setForeground(new java.awt.Color(157, 12, 12));
        dateChooser.setDateFormat("MM/dd/yyyy");
        dateChooser.setTextRefernce(txtDateOpened);

        dateChooser1.setForeground(new java.awt.Color(157, 12, 12));
        dateChooser1.setDateFormat("MM/dd/yyyy");
        dateChooser1.setTextRefernce(txtDateClosed);

        jPanel1.setBackground(new java.awt.Color(240, 241, 243));

        tblSubjects.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblSubjects.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSubjects.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblSubjects.setFocusable(false);
        tblSubjects.setRowHeight(50);
        tblSubjects.setSelectionBackground(new java.awt.Color(239, 225, 225));
        tblSubjects.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblSubjects.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSubjectsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSubjects);
        if (tblSubjects.getColumnModel().getColumnCount() > 0) {
            tblSubjects.getColumnModel().getColumn(0).setResizable(false);
            tblSubjects.getColumnModel().getColumn(1).setResizable(false);
            tblSubjects.getColumnModel().getColumn(2).setResizable(false);
            tblSubjects.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("Subject Code");

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

        txtSubjectDesc.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSubjectDesc.setBorder(null);
        txtSubjectDesc.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtSubjectDesc.setSelectionColor(new java.awt.Color(221, 197, 197));
        txtSubjectDesc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSubjectDescFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSubjectDescFocusLost(evt);
            }
        });
        txtSubjectDesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSubjectDescKeyPressed(evt);
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
            .addGap(0, 166, Short.MAX_VALUE)
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
            .addGap(0, 166, Short.MAX_VALUE)
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
            .addComponent(btnAddRec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );
        pnlAddRecLayout.setVerticalGroup(
            pnlAddRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddRecLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAddRec, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtSubjectCode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSubjectCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSubjectCode.setBorder(null);
        txtSubjectCode.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtSubjectCode.setSelectionColor(new java.awt.Color(221, 197, 197));
        txtSubjectCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSubjectCodeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSubjectCodeFocusLost(evt);
            }
        });
        txtSubjectCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSubjectCodeKeyPressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setText("College Code");

        cmbCollegeCode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbCollegeCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbCollegeCodeFocusGained(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setText("Units");

        cmbUnits.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbUnits.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setText("Curriculum");

        cmbCurriculum.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbCurriculum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2000", "2010", "2020", "2021", "2022", "2023", "2024" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(30, 30, 30)
                                .addComponent(txtSubjectCode, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                                .addComponent(pnlDeleteRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSubjectDesc)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(cmbCollegeCode, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlAddRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addComponent(txtDateOpened, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDateOpened, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtDateClosed, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDateClosed, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(59, 59, 59)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(cmbUnits, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbCurriculum, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)))
                .addGap(26, 26, 26))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDeleteRec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSubjectCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cmbUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cmbCurriculum, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtSubjectDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54)
                        .addComponent(pnlAddRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cmbCollegeCode, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
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
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
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

    private void tblSubjectsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSubjectsMouseClicked
        // TODO add your handling code here:
        int row = tblSubjects.getSelectedRow();
        txtSubjectCode.setText(tblSubjects.getModel().getValueAt(row, 0).toString());
        txtSubjectDesc.setText(tblSubjects.getModel().getValueAt(row, 1).toString());
        cmbUnits.setSelectedItem(tblSubjects.getModel().getValueAt(row, 2).toString());
        cmbCurriculum.setSelectedItem(tblSubjects.getModel().getValueAt(row, 3).toString());
        txtDateOpened.setText(modifyDate(6));
        txtDateClosed.setText(modifyDate(7));
        status();
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT description FROM college WHERE college_code = '"+tblSubjects.getModel().getValueAt(row, 4).toString()+"'");
            rs = ps.executeQuery();
            while(rs.next())
                cmbCollegeCode.setSelectedItem(rs.getString("description"));
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        btnAddRec.setText("Update Record");
        txtSubjectCode.setEditable(false);
        txtSubjectCode.setFocusable(false);
        
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
    }//GEN-LAST:event_tblSubjectsMouseClicked

    private void btnDeleteRecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteRecMouseClicked
        // TODO add your handling code here:
        if(btnAddRec.getText().equals("Update Record")) {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to delete this record?", "Confirm", YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION) {
                if(txtSubjectCode.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "There is no selected record!");
                else
                    try {
                        con = ConnectDB.Connect();
                        ps = con.prepareStatement("DELETE FROM subject WHERE subject_code = '"+txtSubjectCode.getText().trim()+"'");
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

    private void txtSubjectDescFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubjectDescFocusGained
        // TODO add your handling code here:
        jPanel2.setBackground(new Color(157,12,12));
    }//GEN-LAST:event_txtSubjectDescFocusGained

    private void txtSubjectDescFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubjectDescFocusLost
        // TODO add your handling code here:
        jPanel2.setBackground(new Color(229,229,229));
    }//GEN-LAST:event_txtSubjectDescFocusLost

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
                ps = con.prepareStatement("INSERT INTO subject VALUES('"+txtSubjectCode.getText().trim().toUpperCase()+"'"
                    + ", '"+txtSubjectDesc.getText().trim()+"'"
                    + ", '"+cmbUnits.getSelectedItem()+"'"
                    + ", '"+cmbCurriculum.getSelectedItem()+"'"
                    + ", (SELECT college_code FROM college WHERE description = '"+cmbCollegeCode.getSelectedItem()+"')"
                    + ", '"+statusAbbv()+"'"
                    + ", '"+txtDateOpened.getText().trim()+"'"
                    + ", '"+txtDateClosed.getText().trim()+"')");
                ps.execute();
                populateTbl();
                clearFields();
                JOptionPane.showMessageDialog(null, "New subject record has been added sucessfully!");
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
                ps = con.prepareStatement("UPDATE subject SET description = '"+txtSubjectDesc.getText().trim()+"'"
                    + ", units = '"+cmbUnits.getSelectedItem()+"'"
                    + ", curriculum = '"+cmbCurriculum.getSelectedItem()+"'"
                    + ", college_code = (SELECT college_code FROM college WHERE description = '"+cmbCollegeCode.getSelectedItem()+"')"
                    + ", status = '"+statusAbbv()+"'"
                    + ", date_opened = '"+txtDateOpened.getText().trim()+"'"
                    + ", date_closed = '"+txtDateClosed.getText().trim()+"'"
                    + "WHERE subject_code = '"+txtSubjectCode.getText()+"'");
                ps.execute();
                populateTbl();
                JOptionPane.showMessageDialog(null, "Subject record has been updated sucessfully!");
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

    private void txtSubjectCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubjectCodeFocusGained
        // TODO add your handling code here:
        jPanel4.setBackground(new Color(157,12,12));
    }//GEN-LAST:event_txtSubjectCodeFocusGained

    private void txtSubjectCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubjectCodeFocusLost
        // TODO add your handling code here:
        jPanel4.setBackground(new Color(229,229,229));
    }//GEN-LAST:event_txtSubjectCodeFocusLost

    private void txtSubjectCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubjectCodeKeyPressed
        // TODO add your handling code here:
        limitStrInput(evt, txtSubjectCode, 10);
    }//GEN-LAST:event_txtSubjectCodeKeyPressed

    private void txtSubjectDescKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubjectDescKeyPressed
        // TODO add your handling code here:
        limitStrInput(evt, txtSubjectDesc, 100);
    }//GEN-LAST:event_txtSubjectDescKeyPressed

    private void cmbCollegeCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbCollegeCodeFocusGained
        // TODO add your handling code here:
        populateCmbCollege();
    }//GEN-LAST:event_cmbCollegeCodeFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel btnAddRec;
    private javax.swing.JLabel btnDateClosed;
    private javax.swing.JLabel btnDateOpened;
    public javax.swing.JLabel btnDeleteRec;
    public javax.swing.JComboBox<String> cmbCollegeCode;
    public javax.swing.JComboBox<String> cmbCurriculum;
    public javax.swing.JComboBox<String> cmbStatus;
    public javax.swing.JComboBox<String> cmbUnits;
    public com.raven.datechooser.DateChooser dateChooser;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private studentrecords.RoundedFrame pnlAddRec;
    public studentrecords.RoundedFrame pnlDeleteRec;
    public javax.swing.JTable tblSubjects;
    public javax.swing.JTextField txtDateClosed;
    public javax.swing.JTextField txtDateOpened;
    public javax.swing.JTextField txtSubjectCode;
    public javax.swing.JTextField txtSubjectDesc;
    // End of variables declaration//GEN-END:variables
}
