
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
public class PurchaseDuePayReport extends javax.swing.JFrame {

    DefaultTableModel dm;
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    /**
     * Creates new form PurchaseDuePayReport
     */
    public PurchaseDuePayReport() {
        super(" Purchase Payed Due Report");
        initComponents();
        conn = javaconnect.getConnection();
//        fetch();
    }

    //search engine
    private void filter(String query) {
        dm = (DefaultTableModel) table1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        table1.setRowSorter(tr);

        tr.setRowFilter(RowFilter.regexFilter(query));
    }

    public void fetch() {
        try {
            JTableHeader header = table1.getTableHeader();
            header.setForeground(Color.red);
            header.setFont(new Font("Tahome", Font.ITALIC, 12));
            table1.setRowSelectionInterval(0, 0);
            int iii = table1.getSelectedRow();
        
            dm = (DefaultTableModel) table1.getModel();
            String idd = dm.getValueAt(iii, 8).toString();
//        JOptionPane.showMessageDialog(null,idd);    
            String q = "select id as Id,item_no as 'Item No',item_type as 'Item Type',item_category as 'Item Category',item_quantity as 'Item Quantity',total_vat as 'Total',party as 'Party',due_paid as 'Paid',due_left as Due,payed_date as 'Payed Date' from purchase_item where party = '" + idd + "' and iscashdue = '" + 1 + "' order by id desc";
//            JOptionPane.showMessageDialog(null,q);
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            //increasing the width of columns
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Item not Fetched");
        }
    }

    //display sum

    public int getSum() {
        int rowsCount = table1.getRowCount();
        Double sum = 0.0;
        Double sum1 = 0.0;
        Double sum2 = 0.0;
        for (int i = 0; i < rowsCount; i++) {
            sum = sum + Double.parseDouble(table1.getValueAt(i, 7).toString());
            sum1 = sum1 + Double.parseDouble(table1.getValueAt(i, 8).toString());
        }
        jLabel5.setText(Double.toString(sum));
        jLabel6.setText(Double.toString(sum1));
        return 0;
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
        jLabel1 = new javax.swing.JLabel();
        search_text = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jLabel1.setText("Due Payed Report");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 160, -1));

        search_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_textKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_textKeyReleased(evt);
            }
        });
        jPanel1.add(search_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 140, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel12.setText("Search");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 70, -1));

        jButton4.setBackground(new java.awt.Color(153, 153, 255));
        jButton4.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jButton4.setText("Print");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, -1, 30));

        table1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        table1.setFont(new java.awt.Font("Sitka Subheading", 3, 12)); // NOI18N
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Item No", "Item Type", "Item Category", "Quantity", "Total", "Party Name", "Paid", "Due", "Payed Date"
            }
        ));
        jScrollPane1.setViewportView(table1);

        jLabel5.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addGap(43, 43, 43))
        );

        setSize(new java.awt.Dimension(1101, 571));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void search_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_textKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_textKeyPressed

    private void search_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_textKeyReleased
        // TODO add your handling code here:
        String query = search_text.getText();
        filter(query);
//        getSum();
    }//GEN-LAST:event_search_textKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("Report Print");
        MessageFormat footer = new MessageFormat("page{0,number,integer}");
        try {
            table1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (java.awt.print.PrinterException e) {
            System.err.format("cannot print", e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        fetch();
//        getSum();
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(PurchaseDuePayReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseDuePayReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseDuePayReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseDuePayReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PurchaseDuePayReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search_text;
    public javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
