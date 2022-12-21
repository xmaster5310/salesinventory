
import java.awt.List;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author laptop
 */
public class Login extends javax.swing.JFrame {

    Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    /**
     * Creates new form Login
     */
    public Login() {
        super("Login");
        initComponents();
        conn = javaconnect.getConnection();
        clock();
    }

    public void clear() {
        jPasswordField1.setText("");
    }

//    public void call() {
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        Calendar cal = Calendar.getInstance();
////        today date
//        cal.add(Calendar.DAY_OF_MONTH, 0);
//        String trail = sdf.format(cal.getTime());
////        JOptionPane.showMessageDialog(null,trail);
//        String sqlDate = "select date,date2,istrailfinished from trail";
//        try {
//            pst = conn.prepareStatement(sqlDate);
//            rs = pst.executeQuery(sqlDate);
//            if (rs.next()) {
//                String date = rs.getString("date");
//                String date2 = rs.getString("date2");
////                JOptionPane.showMessageDialog(null,date);
////                String trailfinished = rs.getString("istrailfinished");
//                if (trail.equals(date) || trail.equals(date2)) {
////                    JOptionPane.showMessageDialog(null,"here came");
//                    String d = "update trail set istrailfinished = '" + 1 + "'";
//                    try {
//                        pst = conn.prepareStatement(d);
//                        pst.execute();
//                        JOptionPane.showMessageDialog(null, "Trail finished !! Contact to Developer!! Thank you !!!");
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(null, e);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }

    public void clock() {
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        date.setText(year + "." + (month + 1) + "." + day);

        String date1 = date.getText();
        if (date1.equals("2020.10.8")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.9")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.10")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.11")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.12")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.13")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.14")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.15")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.16")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.17")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.18")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.19")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.20")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.21")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.22")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.23")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.24")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.25")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.26")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.27")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.28")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
        } else if (date1.equals("2020.10.29")) {
            JOptionPane.showMessageDialog(null, "Licenced expired");
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel1.setText("Password");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 60, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jButton1.setText("Log in");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        jPanel2.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 180, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel4.setText("Login here !!");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 120, -1));
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 180, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel3.setText("Email");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 40, -1));

        date.setBackground(new java.awt.Color(255, 255, 255));
        date.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login-image.jpg"))); // NOI18N
        jPanel2.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel5.setText("Email");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 40, -1));

        jLabel6.setText("jLabel6");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        setSize(new java.awt.Dimension(406, 329));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String email = jTextField1.getText();
        String password = jPasswordField1.getText();

        if (email.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill the form");
        } else {
            try {

                String query = "SELECT * FROM login WHERE email =? and password=?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    setVisible(false);
                    Dashboard d = new Dashboard();
                    d.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Username or Password did not matched");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String email = jTextField1.getText();
            String password = jPasswordField1.getText();

            if (email.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill the form");
            } else {
                try {
                    pst = conn.prepareStatement("select * from login where email=? and password=?");
                    pst.setString(1, email);
                    pst.setString(2, password);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        setVisible(false);
                        Dashboard d = new Dashboard();
                        d.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Username or Password did not matched");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
//        call();
//        String sqlDate = "select istrailfinished from trail";
//        try {
//            pst = conn.prepareStatement(sqlDate);
//            rs = pst.executeQuery(sqlDate);
//            if (rs.next()) {
//                String trailfinished = rs.getString("istrailfinished");
////                JOptionPane.showMessageDialog(null,trailfinished);
//                if (trailfinished.equals("1")) {
//                    
//                    SimpleDateFormat sdff = new SimpleDateFormat("yyyyMMdd");
//                    Calendar call = Calendar.getInstance();
////        today date
//                    call.add(Calendar.DAY_OF_MONTH, 0);
//                    String traill = sdff.format(call.getTime());
//                    String d = "update trail set date = '" + traill + "'";
//                    try {
//                        pst = conn.prepareStatement(d);
//                        pst.execute();
//                        JOptionPane.showMessageDialog(null, "Trail finished !! Contact to Developer!! Thank you !!!");
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(null, e);
//                    }
//                    call();
//                }
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
    }//GEN-LAST:event_formWindowActivated

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
