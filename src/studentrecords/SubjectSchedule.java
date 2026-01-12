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
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.Timer;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Joan
 */
public class SubjectSchedule extends javax.swing.JPanel {
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    SubjectScheduleView ssv = new SubjectScheduleView();
    SubjectScheduleDayView ssdv = new SubjectScheduleDayView();
    SubjectScheduleAdd ssa = new SubjectScheduleAdd();
    
    /**
     * Creates new form Dashboard
     */
    public SubjectSchedule() {
        initComponents();
        
        showDate();
        showTime();
        schoolYearDisplay();
        semDisplay();
        
        ScheduleCard.add(ssv);
        ScheduleCard.add(ssdv);
        ScheduleCard.add(ssa);
        
        ssv.setVisible(false);
        ssdv.setVisible(false);
        ssa.setVisible(false);
        
        cmbSYear.setVisible(false);
        cmbSem.setVisible(false);
        pnlConfirm.setVisible(false);
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
            ps = con.prepareStatement("SELECT * FROM vw_subject_schedule_selected ORDER BY \"Day\"");
            rs = ps.executeQuery();
            ssv.tblScheduleSelected.setModel(DbUtils.resultSetToTableModel(rs));
            
            float[] floatRelativeWidths = {0.08f, 0.05f, 0.08f, 0.10f, 0.08f, 0.10f, 0.08f, 0.08f, 0.06f, 0.06f, 0.04f, 0.04f, 0.07f};
            int intTotalWidth = ssv.tblScheduleSelected.getWidth();
            for (int intCounter = 0; intCounter < ssv.tblScheduleSelected.getColumnCount(); intCounter++) {
                int intColumnWidth = (int) (intTotalWidth * floatRelativeWidths[intCounter]); // Calculate width as a fraction of the total width
                ssv.tblScheduleSelected.getColumnModel().getColumn(intCounter).setPreferredWidth(intColumnWidth);
            }
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void clearFields() {
//        ssv.txtSubjectCode.setText("");
//        ssv.txtSubjectDesc.setText("");
//        ssv.dateChooser.toDay();
//        ssv.txtDateClosed.setText("");
//        ssv.cmbStatus.setSelectedIndex(0);
//        ssv.cmbCollegeCode.setSelectedIndex(0);
//        ssv.cmbUnits.setSelectedIndex(0);
//        ssv.cmbCurriculum.setSelectedIndex(0);
    }
    
    public void schoolYearDisplay() {
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT syear FROM schoolyear");
            rs = ps.executeQuery();
            cmbSYear.removeAllItems();
            cmbSYear.addItem("Select a school year.");
            while(rs.next())
                cmbSYear.addItem(rs.getString("syear"));
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void semDisplay() {
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT semester FROM semester");
            rs = ps.executeQuery();
            cmbSem.removeAllItems();
            while(rs.next())
                cmbSem.addItem(rs.getString("semester"));
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
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
        lblSchedule = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        pnlSearchAdd = new javax.swing.JPanel();
        pnlView = new studentrecords.RoundedFrame();
        btnView = new javax.swing.JLabel();
        pnlSelect = new studentrecords.RoundedFrame();
        btnSelect = new javax.swing.JLabel();
        cmbSYear = new javax.swing.JComboBox<>();
        cmbSem = new javax.swing.JComboBox<>();
        pnlConfirm = new studentrecords.RoundedFrame();
        btnConfirm = new javax.swing.JLabel();
        lblSchedule1 = new javax.swing.JLabel();
        ScheduleCard = new javax.swing.JLayeredPane();
        lblDate1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        pnlChangingScreen.setBackground(new java.awt.Color(240, 241, 243));
        pnlChangingScreen.setPreferredSize(new java.awt.Dimension(1327, 797));

        lblSchedule.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblSchedule.setText("Schedule");

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
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDate.setText("MMM. dd, yyyy");
        lblDate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblTime.setBackground(new java.awt.Color(153, 153, 153));
        lblTime.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTime.setText("hh:mm:ss");
        lblTime.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pnlSearchAdd.setBackground(new java.awt.Color(255, 255, 255));

        pnlView.setBackground(new java.awt.Color(157, 12, 12));

        btnView.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnView.setForeground(new java.awt.Color(255, 255, 255));
        btnView.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnView.setText("View Per Day");
        btnView.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlViewLayout = new javax.swing.GroupLayout(pnlView);
        pnlView.setLayout(pnlViewLayout);
        pnlViewLayout.setHorizontalGroup(
            pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlViewLayout.setVerticalGroup(
            pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        pnlSelect.setBackground(new java.awt.Color(157, 12, 12));

        btnSelect.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSelect.setForeground(new java.awt.Color(255, 255, 255));
        btnSelect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSelect.setText("Select Schedule");
        btnSelect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSelectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSelectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSelectMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlSelectLayout = new javax.swing.GroupLayout(pnlSelect);
        pnlSelect.setLayout(pnlSelectLayout);
        pnlSelectLayout.setHorizontalGroup(
            pnlSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSelectLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlSelectLayout.setVerticalGroup(
            pnlSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSelect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        cmbSYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSYear.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbSYearFocusLost(evt);
            }
        });
        cmbSYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSYearActionPerformed(evt);
            }
        });

        cmbSem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbSemFocusLost(evt);
            }
        });
        cmbSem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSemActionPerformed(evt);
            }
        });

        pnlConfirm.setBackground(new java.awt.Color(157, 12, 12));

        btnConfirm.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnConfirm.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnConfirm.setText("Confirm Schedule");
        btnConfirm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConfirmMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfirmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfirmMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlConfirmLayout = new javax.swing.GroupLayout(pnlConfirm);
        pnlConfirm.setLayout(pnlConfirmLayout);
        pnlConfirmLayout.setHorizontalGroup(
            pnlConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConfirmLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlConfirmLayout.setVerticalGroup(
            pnlConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnConfirm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlSearchAddLayout = new javax.swing.GroupLayout(pnlSearchAdd);
        pnlSearchAdd.setLayout(pnlSearchAddLayout);
        pnlSearchAddLayout.setHorizontalGroup(
            pnlSearchAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbSYear, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbSem, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        pnlSearchAddLayout.setVerticalGroup(
            pnlSearchAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchAddLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlSearchAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchAddLayout.createSequentialGroup()
                .addGroup(pnlSearchAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlSearchAddLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSearchAddLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(pnlView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlSearchAddLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        lblSchedule1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSchedule1.setForeground(new java.awt.Color(204, 204, 204));
        lblSchedule1.setText("|   View Current Schedule");

        ScheduleCard.setLayout(new java.awt.CardLayout());

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
                                .addComponent(lblSchedule)
                                .addGap(18, 18, 18)
                                .addComponent(lblSchedule1))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 710, Short.MAX_VALUE)
                        .addComponent(lblDate1)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(ScheduleCard, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(40, 40, 40))
        );
        pnlChangingScreenLayout.setVerticalGroup(
            pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlChangingScreenLayout.createSequentialGroup()
                        .addGroup(pnlChangingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSchedule)
                            .addComponent(lblSchedule1))
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
                .addComponent(ScheduleCard, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
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

    private void btnSelectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectMouseExited
        pnlSelect.setBackground(new Color(157,12,12));
    }//GEN-LAST:event_btnSelectMouseExited

    private void btnSelectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectMouseEntered
        pnlSelect.setBackground(new Color(135,10,10));
    }//GEN-LAST:event_btnSelectMouseEntered

    private void btnSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectMouseClicked
        if (btnSelect.getText().equals("Select Schedule")){
            cmbSYear.setVisible(true);
            cmbSem.setVisible(true);
            pnlConfirm.setVisible(true);
            
            ssa.setVisible(true);
            ssv.setVisible(false);
            ssdv.setVisible(false);
            
            btnSelect.setText("View Entire Schedule");
            btnView.setText("View Per Day");
        } else if (btnSelect.getText().equals("View Entire Schedule")){
            ssdv.setVisible(false);
            ssv.setVisible(true);
            ssa.setVisible(false);
            
            cmbSYear.setVisible(false);
            cmbSem.setVisible(false);
            pnlConfirm.setVisible(false);

            btnSelect.setText("Select Schedule");
            btnView.setText("View Per Day");
        }
    }//GEN-LAST:event_btnSelectMouseClicked

    private void btnViewMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewMouseExited
        // TODO add your handling code here:
        pnlView.setBackground(new Color(157,12,12));
    }//GEN-LAST:event_btnViewMouseExited

    private void btnViewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewMouseEntered
        // TODO add your handling code here:
        pnlView.setBackground(new Color(135,10,10));
    }//GEN-LAST:event_btnViewMouseEntered

    private void btnViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewMouseClicked
        // TODO add your handling code here:
        if(btnView.getText().equals("View Per Day")) {
            ssdv.setVisible(true);
            ssv.setVisible(false);
            ssa.setVisible(false);
            
            cmbSYear.setVisible(false);
            cmbSem.setVisible(false);
            pnlConfirm.setVisible(false);

            btnSelect.setText("Select Schedule");
            btnView.setText("View Entire Schedule");
        } else if (btnView.getText().equals("View Entire Schedule")) {
            ssdv.setVisible(false);
            ssv.setVisible(true);
            ssa.setVisible(false);
            
            cmbSYear.setVisible(false);
            cmbSem.setVisible(false);
            pnlConfirm.setVisible(false);
            
            btnSelect.setText("Select Schedule");
            btnView.setText("View Per Day");
        }
    }//GEN-LAST:event_btnViewMouseClicked

    private void cmbSYearFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSYearFocusLost
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT * FROM vw_subject_schedule WHERE \"School Year\" = '"+ cmbSYear.getSelectedItem().toString()+"' AND \"Semester\" = '"+ cmbSem.getSelectedItem().toString()+"' ORDER BY \"Day\"");
            rs = ps.executeQuery();
            ssa.tblScheduleOptions.setModel(DbUtils.resultSetToTableModel(rs));

            float[] floatRelativeWidths = {0.08f, 0.05f, 0.08f, 0.10f, 0.08f, 0.10f, 0.08f, 0.08f, 0.06f, 0.06f, 0.04f, 0.04f, 0.07f};
            int intTotalWidth = ssa.tblScheduleOptions.getWidth();
            for (int intCounter = 0; intCounter < ssa.tblScheduleOptions.getColumnCount(); intCounter++) {
                int intColumnWidth = (int) (intTotalWidth * floatRelativeWidths[intCounter]); // Calculate width as a fraction of the total width
                ssa.tblScheduleOptions.getColumnModel().getColumn(intCounter).setPreferredWidth(intColumnWidth);
            }
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cmbSYearFocusLost

    private void cmbSYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSYearActionPerformed
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT * FROM vw_subject_schedule WHERE \"School Year\" = '"+ cmbSYear.getSelectedItem().toString()+"' AND \"Semester\" = '"+ cmbSem.getSelectedItem().toString()+"' ORDER BY \"Day\"");
            ResultSet res = ps.executeQuery();
            ssa.tblScheduleOptions.setModel(DbUtils.resultSetToTableModel(res));

            float[] floatRelativeWidths = {0.08f, 0.05f, 0.08f, 0.10f, 0.08f, 0.10f, 0.08f, 0.08f, 0.06f, 0.06f, 0.04f, 0.04f, 0.07f};
            int intTotalWidth = ssa.tblScheduleOptions.getWidth();
            for (int intCounter = 0; intCounter < ssa.tblScheduleOptions.getColumnCount(); intCounter++) {
                int intColumnWidth = (int) (intTotalWidth * floatRelativeWidths[intCounter]); // Calculate width as a fraction of the total width
                ssa.tblScheduleOptions.getColumnModel().getColumn(intCounter).setPreferredWidth(intColumnWidth);
            }
            res.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cmbSYearActionPerformed

    private void cmbSemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSemFocusLost
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT * FROM vw_subject_schedule WHERE \"School Year\" = '"+ cmbSYear.getSelectedItem().toString()+"' AND \"Semester\" = '"+ cmbSem.getSelectedItem().toString()+"' ORDER BY \"Day\"");
            ResultSet rs = ps.executeQuery();
            ssa.tblScheduleOptions.setModel(DbUtils.resultSetToTableModel(rs));

            float[] floatRelativeWidths = {0.08f, 0.05f, 0.08f, 0.10f, 0.08f, 0.10f, 0.08f, 0.08f, 0.06f, 0.06f, 0.04f, 0.04f, 0.07f};
            int intTotalWidth = ssa.tblScheduleOptions.getWidth();
            for (int intCounter = 0; intCounter < ssa.tblScheduleOptions.getColumnCount(); intCounter++) {
                int intColumnWidth = (int) (intTotalWidth * floatRelativeWidths[intCounter]); // Calculate width as a fraction of the total width
                ssa.tblScheduleOptions.getColumnModel().getColumn(intCounter).setPreferredWidth(intColumnWidth);
            }
            rs.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cmbSemFocusLost

    private void cmbSemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSemActionPerformed
        // TODO add your handling code here:
        try {
            con = ConnectDB.Connect();
            ps = con.prepareStatement("SELECT * FROM vw_subject_schedule WHERE \"School Year\" = '"+ cmbSYear.getSelectedItem().toString()+"' AND \"Semester\" = '"+ cmbSem.getSelectedItem().toString()+"' ORDER BY \"Day\"");
            ResultSet res = ps.executeQuery();
            ssa.tblScheduleOptions.setModel(DbUtils.resultSetToTableModel(res));

            float[] floatRelativeWidths = {0.08f, 0.05f, 0.08f, 0.10f, 0.08f, 0.10f, 0.08f, 0.08f, 0.06f, 0.06f, 0.04f, 0.04f, 0.07f};
            int intTotalWidth = ssa.tblScheduleOptions.getWidth();
            for (int intCounter = 0; intCounter < ssa.tblScheduleOptions.getColumnCount(); intCounter++) {
                int intColumnWidth = (int) (intTotalWidth * floatRelativeWidths[intCounter]); // Calculate width as a fraction of the total width
                ssa.tblScheduleOptions.getColumnModel().getColumn(intCounter).setPreferredWidth(intColumnWidth);
            }
            res.close();
            ps.close();
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cmbSemActionPerformed

    private void btnConfirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmMouseClicked
        if(ssa.tblScheduleOptions.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "No schedule selected");
        } else {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to confirm this schedule?", "Confirm", YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION) {
                try {
                    con = ConnectDB.Connect();
                    ps = con.prepareStatement("TRUNCATE TABLE SUBJECT_SCHEDULE_SELECTED");
                    ps.executeUpdate();
                
                
                    ps = con.prepareStatement("SELECT * FROM subject_schedule WHERE syear = '"+ cmbSYear.getSelectedItem().toString()+"' AND semester = '"+ cmbSem.getSelectedItem().toString()+"' ORDER BY day");
                    rs = ps.executeQuery();
                
                    while (rs.next()){
                        ps = con.prepareStatement("INSERT INTO SUBJECT_SCHEDULE_SELECTED (syear, semester, college_code, block_no, subject_code, day, time, room, type, sequence_no, employee_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                        ps.setString(1, rs.getString("syear"));
                        ps.setString(2, rs.getString("semester"));
                        ps.setString(3, rs.getString("college_code"));
                        ps.setString(4, rs.getString("block_no"));
                        ps.setString(5, rs.getString("subject_code"));
                        ps.setString(6, rs.getString("day"));
                        ps.setString(7, rs.getString("time"));
                        ps.setString(8, rs.getString("room"));
                        ps.setString(9, rs.getString("type"));
                        ps.setInt(10, rs.getInt("sequence_no"));
                        ps.setString(11, rs.getString("employee_id"));
                        ps.executeUpdate();
                        
                    } 
                    
                    populateTbl();
                    ssdv.populateTbl();
                    JOptionPane.showMessageDialog(null, "Schedule confirmed.");
                    rs.close();
                    ps.close();
                    con.close();
                    
                    ssdv.setVisible(false);
                    ssv.setVisible(true);
                    ssa.setVisible(false);

                    cmbSYear.setVisible(false);
                    cmbSem.setVisible(false);
                    pnlConfirm.setVisible(false);

                    btnSelect.setText("Select Schedule");
                    btnView.setText("View Per Day");
                } catch(Exception e) {
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_btnConfirmMouseClicked

    private void btnConfirmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmMouseEntered
        pnlConfirm.setBackground(new Color(135,10,10));
    }//GEN-LAST:event_btnConfirmMouseEntered

    private void btnConfirmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmMouseExited
        pnlConfirm.setBackground(new Color(157,12,12));
    }//GEN-LAST:event_btnConfirmMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane ScheduleCard;
    private javax.swing.JLabel btnConfirm;
    private javax.swing.JLabel btnSelect;
    private javax.swing.JLabel btnSelect1;
    private javax.swing.JLabel btnView;
    private javax.swing.JComboBox<String> cmbSYear;
    private javax.swing.JComboBox<String> cmbSem;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDate1;
    private javax.swing.JLabel lblSchedule;
    private javax.swing.JLabel lblSchedule1;
    private javax.swing.JLabel lblTime;
    private javax.swing.JPanel pnlChangingScreen;
    private studentrecords.RoundedFrame pnlConfirm;
    private javax.swing.JPanel pnlSearchAdd;
    private studentrecords.RoundedFrame pnlSelect;
    private studentrecords.RoundedFrame pnlSelect1;
    private studentrecords.RoundedFrame pnlView;
    // End of variables declaration//GEN-END:variables
}
