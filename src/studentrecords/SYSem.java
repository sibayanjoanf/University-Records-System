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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Joan
 */
public class SYSem extends javax.swing.JPanel {
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
        
    /**
     * Creates new form Dashboard
     */
    public SYSem() {
        initComponents();
        
        showDate();
        showTime();
        populateTbl();
        tblSYear.getTableHeader().setDefaultRenderer(new TableHeader());
        tblSYear.getTableHeader().setPreferredSize(new Dimension(0,40));
        
        tblSem.getTableHeader().setDefaultRenderer(new TableHeader());
        tblSem.getTableHeader().setPreferredSize(new Dimension(0,40));
        
        txtYearTo.setEditable(false);
        
    }
    
    private class TableHeader extends DefaultTableCellRenderer {
        
        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
            Component com = super.getTableCellRendererComponent(jtable, o, bln1, bln1, i, i1);
            com.setBackground(new Color(157,12,12));
            com.setForeground(Color.white);
            com.setFont(com.getFont().deriveFont(Font.BOLD, 32));
            return com;
        }
    }
    
    public void clearFields() {
        txtYearFrom.setText("");
        txtYearTo.setText("");
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
            ps = con.prepareStatement("SELECT * FROM schoolyear ORDER BY syear");
            rs = ps.executeQuery();
            tblSYear.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT * FROM semester ORDER BY semester");
            rs = ps.executeQuery();
            tblSem.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
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
    
    public int[] date(String dateString) {
        int month = Integer.parseInt(dateString.substring(0,2));
        int day = Integer.parseInt(dateString.substring(3,5));
        int year = Integer.parseInt(dateString.substring(6,10));
        return new int[] {month, day, year};
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
        lblStudents1 = new javax.swing.JLabel();
        lblDate1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSYear = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSem = new javax.swing.JTable();
        pnlYearTo = new studentrecords.RoundedFrame();
        txtYearTo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pnlYearTo1 = new studentrecords.RoundedFrame();
        txtYearFrom = new javax.swing.JTextField();
        pnlAddRec = new studentrecords.RoundedFrame();
        btnAddRec = new javax.swing.JLabel();
        pnlDelRec = new studentrecords.RoundedFrame();
        btnDelRec = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pnlYearTo3 = new studentrecords.RoundedFrame();
        txtSem = new javax.swing.JTextField();
        pnlAddRec1 = new studentrecords.RoundedFrame();
        btnAddSem = new javax.swing.JLabel();
        pnlDelRec1 = new studentrecords.RoundedFrame();
        btnDelSem = new javax.swing.JLabel();

        pnlChangingScreen.setBackground(new java.awt.Color(240, 241, 243));
        pnlChangingScreen.setPreferredSize(new java.awt.Dimension(1327, 797));

        lblStudents.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblStudents.setText("School Year and Semester");

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

        lblStudents1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblStudents1.setForeground(new java.awt.Color(204, 204, 204));
        lblStudents1.setText("|   View Available Years and Semester");

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

        tblSYear.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tblSYear.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSYear.setRowHeight(100);
        tblSYear.setSelectionBackground(new java.awt.Color(239, 225, 225));
        tblSYear.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblSYear.setShowGrid(false);
        tblSYear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSYearMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSYear);

        tblSem.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tblSem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSem.setRowHeight(100);
        tblSem.setSelectionBackground(new java.awt.Color(239, 225, 225));
        tblSem.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblSem.setShowGrid(false);
        tblSem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSemMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSem);

        pnlYearTo.setBackground(new java.awt.Color(248, 248, 248));

        txtYearTo.setBackground(new java.awt.Color(248, 248, 248));
        txtYearTo.setColumns(3);
        txtYearTo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtYearTo.setForeground(new java.awt.Color(0, 0, 0));
        txtYearTo.setBorder(null);
        txtYearTo.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtYearTo.setSelectionColor(new java.awt.Color(221, 197, 197));

        javax.swing.GroupLayout pnlYearToLayout = new javax.swing.GroupLayout(pnlYearTo);
        pnlYearTo.setLayout(pnlYearToLayout);
        pnlYearToLayout.setHorizontalGroup(
            pnlYearToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlYearToLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtYearTo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnlYearToLayout.setVerticalGroup(
            pnlYearToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlYearToLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtYearTo, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("From (YYYY)");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("To (YYYY)");

        pnlYearTo1.setBackground(new java.awt.Color(248, 248, 248));

        txtYearFrom.setBackground(new java.awt.Color(248, 248, 248));
        txtYearFrom.setColumns(3);
        txtYearFrom.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtYearFrom.setForeground(new java.awt.Color(0, 0, 0));
        txtYearFrom.setBorder(null);
        txtYearFrom.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtYearFrom.setSelectionColor(new java.awt.Color(221, 197, 197));
        txtYearFrom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtYearFromFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtYearFromFocusLost(evt);
            }
        });
        txtYearFrom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtYearFromKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtYearFromKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlYearTo1Layout = new javax.swing.GroupLayout(pnlYearTo1);
        pnlYearTo1.setLayout(pnlYearTo1Layout);
        pnlYearTo1Layout.setHorizontalGroup(
            pnlYearTo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlYearTo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtYearFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlYearTo1Layout.setVerticalGroup(
            pnlYearTo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlYearTo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtYearFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlAddRec.setBackground(new java.awt.Color(157, 12, 12));

        btnAddRec.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAddRec.setForeground(new java.awt.Color(255, 255, 255));
        btnAddRec.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddRec.setText("+");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddRecLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddRec, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
        );
        pnlAddRecLayout.setVerticalGroup(
            pnlAddRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAddRec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlDelRec.setBackground(new java.awt.Color(157, 12, 12));

        btnDelRec.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnDelRec.setForeground(new java.awt.Color(255, 255, 255));
        btnDelRec.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDelRec.setText("Del");
        btnDelRec.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelRec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDelRecMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDelRecMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDelRecMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlDelRecLayout = new javax.swing.GroupLayout(pnlDelRec);
        pnlDelRec.setLayout(pnlDelRecLayout);
        pnlDelRecLayout.setHorizontalGroup(
            pnlDelRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDelRecLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelRec, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
        );
        pnlDelRecLayout.setVerticalGroup(
            pnlDelRecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDelRec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Add Semester");

        pnlYearTo3.setBackground(new java.awt.Color(248, 248, 248));

        txtSem.setBackground(new java.awt.Color(248, 248, 248));
        txtSem.setColumns(3);
        txtSem.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSem.setForeground(new java.awt.Color(0, 0, 0));
        txtSem.setBorder(null);
        txtSem.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtSem.setSelectionColor(new java.awt.Color(221, 197, 197));
        txtSem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSemFocusLost(evt);
            }
        });
        txtSem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlYearTo3Layout = new javax.swing.GroupLayout(pnlYearTo3);
        pnlYearTo3.setLayout(pnlYearTo3Layout);
        pnlYearTo3Layout.setHorizontalGroup(
            pnlYearTo3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlYearTo3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSem, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlYearTo3Layout.setVerticalGroup(
            pnlYearTo3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlYearTo3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSem, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlAddRec1.setBackground(new java.awt.Color(157, 12, 12));

        btnAddSem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAddSem.setForeground(new java.awt.Color(255, 255, 255));
        btnAddSem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddSem.setText("+");
        btnAddSem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddSem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddSemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddSemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddSemMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlAddRec1Layout = new javax.swing.GroupLayout(pnlAddRec1);
        pnlAddRec1.setLayout(pnlAddRec1Layout);
        pnlAddRec1Layout.setHorizontalGroup(
            pnlAddRec1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddRec1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddSem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
        );
        pnlAddRec1Layout.setVerticalGroup(
            pnlAddRec1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAddSem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlDelRec1.setBackground(new java.awt.Color(157, 12, 12));

        btnDelSem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnDelSem.setForeground(new java.awt.Color(255, 255, 255));
        btnDelSem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDelSem.setText("Del");
        btnDelSem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelSem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDelSemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDelSemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDelSemMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlDelRec1Layout = new javax.swing.GroupLayout(pnlDelRec1);
        pnlDelRec1.setLayout(pnlDelRec1Layout);
        pnlDelRec1Layout.setHorizontalGroup(
            pnlDelRec1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDelRec1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelSem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
        );
        pnlDelRec1Layout.setVerticalGroup(
            pnlDelRec1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDelSem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlChangingScreenLayout = new javax.swing.GroupLayout(pnlChangingScreen);
        pnlChangingScreen.setLayout(pnlChangingScreenLayout);
        pnlChangingScreenLayout.setHorizontalGroup(
            pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChangingScreenLayout.createSequentialGroup()
                .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                                .addComponent(lblStudents)
                                .addGap(18, 18, 18)
                                .addComponent(lblStudents1))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDate1)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlChangingScreenLayout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                                .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(pnlYearTo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(22, 22, 22)
                                .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                                        .addComponent(pnlYearTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(pnlAddRec, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pnlDelRec, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane1))
                        .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                                        .addComponent(pnlYearTo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(pnlAddRec1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pnlDelRec1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addGap(18, 18, 18)
                .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(64, 64, 64)
                .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                        .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlYearTo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(pnlAddRec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlYearTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pnlDelRec, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(197, 197, 197))
                    .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                        .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pnlDelRec1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlAddRec1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlYearTo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

    private void tblSYearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSYearMouseClicked
        int row = tblSYear.getSelectedRow();
        txtYearFrom.setText(tblSYear.getModel().getValueAt(row, 0).toString().substring(0, 4));
        txtYearTo.setText(tblSYear.getModel().getValueAt(row, 0).toString().substring(5, 9));
    }//GEN-LAST:event_tblSYearMouseClicked

    private void txtYearFromFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtYearFromFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtYearFromFocusGained

    private void txtYearFromFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtYearFromFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtYearFromFocusLost

    private void txtYearFromKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtYearFromKeyPressed
        limitInput(evt, txtYearFrom, 4);
    }//GEN-LAST:event_txtYearFromKeyPressed

    private void txtYearFromKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtYearFromKeyReleased
        
        if(txtYearFrom.getText().length() == 4)
            txtYearTo.setText((Integer.toString(Integer.parseInt(txtYearFrom.getText())+1)));        
        
    }//GEN-LAST:event_txtYearFromKeyReleased

    private void btnAddRecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddRecMouseExited
        // TODO add your handling code here:
        pnlAddRec.setBackground(new Color(157,12,12));
    }//GEN-LAST:event_btnAddRecMouseExited

    private void btnAddRecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddRecMouseEntered
        // TODO add your handling code here:
        pnlAddRec.setBackground(new Color(135,10,10));
    }//GEN-LAST:event_btnAddRecMouseEntered

    private void btnAddRecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddRecMouseClicked
        // TODO add your handling code here:
        if (txtYearFrom.getText().length() < 4)
            JOptionPane.showMessageDialog(null, "Invalid year.");
        else {
            try {
                con = ConnectDB.Connect();  
                String query = "INSERT INTO schoolyear VALUES (?)";
                ps = con.prepareStatement(query);
                ps.setString(1, txtYearFrom.getText().trim()+"-"+txtYearTo.getText().trim());
                ps.execute();
                populateTbl();
                clearFields();
                JOptionPane.showMessageDialog(null, "School year added successfully!");
                ps.close();
                con.close();
            } catch(Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "School year already exists.");
            }
        }
    }//GEN-LAST:event_btnAddRecMouseClicked

    private void btnDelRecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelRecMouseClicked
        int response = JOptionPane.showConfirmDialog(null, "Do you want to delete this record?", "Confirm", YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION) {
            
            if(txtYearFrom.getText().equals(""))
                JOptionPane.showMessageDialog(null, "There is no selected record!");
            else
                try {
                    con = ConnectDB.Connect();
                    ps = con.prepareStatement("DELETE FROM schoolyear WHERE syear = '"+txtYearFrom.getText().trim()+"-"+txtYearTo.getText().trim()+"'");
                    ps.execute();
                    ps.close();
                    con.close();
                    populateTbl();
                    clearFields();
                    JOptionPane.showMessageDialog(null, "A record was successfully deleted!");
                } catch(Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "This school year is in use. pakyu");
                }
        } else 
            JOptionPane.showMessageDialog(null, "Deleting was aborted!");
    }//GEN-LAST:event_btnDelRecMouseClicked

    private void btnDelRecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelRecMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelRecMouseEntered

    private void btnDelRecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelRecMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelRecMouseExited

    private void txtSemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSemFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSemFocusGained

    private void txtSemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSemFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSemFocusLost

    private void txtSemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSemKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSemKeyPressed

    private void txtSemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSemKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSemKeyReleased

    private void btnAddSemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddSemMouseClicked
        // TODO add your handling code here:
        if (txtYearFrom.getText().length() > 1)
            JOptionPane.showMessageDialog(null, "Invalid input.");
        else {
            try {
                con = ConnectDB.Connect();  
                String query = "INSERT INTO semester VALUES (?)";
                ps = con.prepareStatement(query);
                ps.setString(1, txtSem.getText().trim());
                ps.execute();
                populateTbl();
                txtSem.setText("");
                JOptionPane.showMessageDialog(null, "Semester added successfully!");
                ps.close();
                con.close();
            } catch(Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Semester already exists.");
            }
        }
    }//GEN-LAST:event_btnAddSemMouseClicked

    private void btnAddSemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddSemMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddSemMouseEntered

    private void btnAddSemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddSemMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddSemMouseExited

    private void btnDelSemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelSemMouseClicked
        int response = JOptionPane.showConfirmDialog(null, "Do you want to delete this record?", "Confirm", YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION) {
            
            if(txtSem.getText().equals(""))
                JOptionPane.showMessageDialog(null, "There is no selected record!");
            else
                try {
                    con = ConnectDB.Connect();
                    ps = con.prepareStatement("DELETE FROM semester WHERE semester = '"+txtSem.getText().trim()+"'");
                    ps.execute();
                    ps.close();
                    con.close();
                    populateTbl();
                    txtSem.setText("");
                    JOptionPane.showMessageDialog(null, "A record was successfully deleted!");
                } catch(Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "This semester is in use. pakyu");
                }
        } else 
            JOptionPane.showMessageDialog(null, "Deleting was aborted!");
    }//GEN-LAST:event_btnDelSemMouseClicked

    private void btnDelSemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelSemMouseEntered

    }//GEN-LAST:event_btnDelSemMouseEntered

    private void btnDelSemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelSemMouseExited
        // TODO add your handling btnAddSem
    }//GEN-LAST:event_btnDelSemMouseExited

    private void tblSemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSemMouseClicked
        int row = tblSem.getSelectedRow();
        txtSem.setText(tblSem.getModel().getValueAt(row, 0).toString());
    }//GEN-LAST:event_tblSemMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAddRec;
    private javax.swing.JLabel btnAddSem;
    private javax.swing.JLabel btnDelRec;
    private javax.swing.JLabel btnDelSem;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDate1;
    private javax.swing.JLabel lblStudents;
    private javax.swing.JLabel lblStudents1;
    private javax.swing.JLabel lblTime;
    private studentrecords.RoundedFrame pnlAddRec;
    private studentrecords.RoundedFrame pnlAddRec1;
    private javax.swing.JPanel pnlChangingScreen;
    private studentrecords.RoundedFrame pnlDelRec;
    private studentrecords.RoundedFrame pnlDelRec1;
    private studentrecords.RoundedFrame pnlYearTo;
    private studentrecords.RoundedFrame pnlYearTo1;
    private studentrecords.RoundedFrame pnlYearTo2;
    private studentrecords.RoundedFrame pnlYearTo3;
    private javax.swing.JTable tblSYear;
    private javax.swing.JTable tblSem;
    private javax.swing.JTextField txtSem;
    private javax.swing.JTextField txtYearFrom;
    private javax.swing.JTextField txtYearTo;
    private javax.swing.JTextField txtYearTo1;
    // End of variables declaration//GEN-END:variables
}
