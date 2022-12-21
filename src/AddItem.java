
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AddItem extends javax.swing.JFrame {

    DefaultTableModel dm;
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    /**
     * Creates new form AddItem
     */
    public AddItem() {
        super("Items");
        initComponents();
        conn = javaconnect.getConnection();
        fetch();
//        clock();
        fillPartyCombo();
        itemNNo();
    }
//    adding quantity sum for graphs

    public void quantitysum() {
        String sqlll = "select sum(item_quantity) as item_quantity from purchase_item where receive_date ='" + jTextField4.getText() + "'";
        try {
            pst = conn.prepareStatement(sqlll);
            rs = pst.executeQuery(sqlll);
            rs.next();
            String a = rs.getString("item_quantity");
            String newssql = "update purchase_item set sum_quantity='" + a + "'"
                    + "where receive_date ='" + jTextField4.getText() + "'";
            try {
                pst = conn.prepareStatement(newssql);
                pst.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void itemNNo() {
        Random random = new Random();
        int number;
        for (int counter = 1; counter <= 1; counter++) {
            number = 1 + random.nextInt(1000000);
            String a = (String.valueOf(number));
            jTextField2.setText(a);
        }
    }

    public void fillPartyCombo() {
        try {
            String sql = "select DISTINCT party_name from party";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                jComboBox1.addItem(rs.getString("party_name"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void fetch() {
        try {
            JTableHeader header = table1.getTableHeader();
            header.setForeground(Color.red);
            header.setFont(new Font("Tahome", Font.ITALIC, 12));
            String q = "select id as Id,receiving_party as 'Receiving Party',bill_no as 'Bill No',item_no as 'Item No',item_category as 'Type',item_model as 'Category',item_quantity as 'Quantity',item_buying_price as 'Buying Price',item_total_price as 'Total Price' from item ORDER BY id DESC";
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            //increasing the width of columns
            TableColumnModel columnModel = table1.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(30);
            columnModel.getColumn(1).setPreferredWidth(80);
            columnModel.getColumn(2).setPreferredWidth(80);
            columnModel.getColumn(3).setPreferredWidth(30);
            columnModel.getColumn(4).setPreferredWidth(100);
            columnModel.getColumn(6).setPreferredWidth(50);
        } catch (Exception e) {

        }
    }

    public void clock() {
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        jTextField4.setText(year + "-" + (month + 1) + "-" + day);
    }

    public void clear() {
        jTextField1.setText("");
        jTextField3.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField8.setText("");
        jTextField10.setText("");
        totalvat.setText("");
        jTextField5.setText("");
        bill_no.setText("");
        jComboBox1.setSelectedItem("Select Party");
        paytype.setSelectedItem("Select Pay Type");
        paid.setVisible(false);
        jLabel11.setVisible(false);
        itemNNo();
    }

    //search engine
    private void filter(String query) {
        dm = (DefaultTableModel) table1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        table1.setRowSorter(tr);

        tr.setRowFilter(RowFilter.regexFilter(query));
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
        jLabel12 = new javax.swing.JLabel();
        search_text = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        totalvat = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        paytype = new javax.swing.JComboBox();
        paid = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        bill_no = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(99, 114, 139));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setText("Item Details");

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel12.setText("Search");

        search_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_textKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_textKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(516, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel12)
                    .addComponent(search_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(236, 224, 228));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel3.setText("Receiving Date");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 100, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel4.setText("Item No");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 60, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel5.setText("Bill Number");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 80, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel8.setText("Quantity");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 70, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel9.setText("Buying Price");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 90, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel11.setText("Due Paid");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 100, 20));

        jTextField2.setEditable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 160, -1));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 160, -1));

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 160, -1));

        jTextField8.setEditable(false);
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 160, -1));

        jPanel4.setBackground(new java.awt.Color(231, 202, 231));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel2.setText("Add Items");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 150, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 360, 70));

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel13.setText("Item Category");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 90, -1));
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 160, -1));

        totalvat.setEditable(false);
        totalvat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalvatActionPerformed(evt);
            }
        });
        totalvat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalvatKeyTyped(evt);
            }
        });
        jPanel3.add(totalvat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 160, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel14.setText("Total with VAT");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 110, 20));

        jLabel15.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel15.setText("Total Price");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 100, 40));

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel17.setText("Vat Amount");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 80, 30));

        jTextField10.setEditable(false);
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField10KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 160, -1));

        jComboBox1.setFont(new java.awt.Font("Sitka Heading", 3, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Party" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 160, -1));

        paytype.setFont(new java.awt.Font("Sitka Heading", 3, 12)); // NOI18N
        paytype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Pay Type", "Due" }));
        paytype.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                paytypePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        paytype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paytypeActionPerformed(evt);
            }
        });
        jPanel3.add(paytype, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 160, -1));

        paid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidActionPerformed(evt);
            }
        });
        paid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                paidKeyTyped(evt);
            }
        });
        jPanel3.add(paid, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 160, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel16.setText("Receiving party");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 110, 40));

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 160, -1));

        bill_no.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bill_noKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bill_noKeyTyped(evt);
            }
        });
        jPanel3.add(bill_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 160, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel6.setText("Item Type");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 80, 20));

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 160, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 610));

        table1.setBackground(new java.awt.Color(255, 204, 204));
        table1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        table1.setFont(new java.awt.Font("Sitka Subheading", 1, 12)); // NOI18N
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 870, 540));

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setBackground(new java.awt.Color(153, 153, 255));
        jButton4.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jButton4.setText("Print");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 80, 40));

        jButton5.setBackground(new java.awt.Color(153, 153, 255));
        jButton5.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jButton5.setText("Clear");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 10, 90, 40));

        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 100, 40));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 550, 860, 80));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1250, 610));

        setSize(new java.awt.Dimension(1267, 695));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
  //item no validation
//
//    public boolean validationItemNo() {
//        if (jTextField2.getText().equals("")) {
//            return false;
//        }
//        return true;
//    }

    //itemcategory no validation
    public boolean validationItemCategory() {
        if (jTextField3.getText().equals("")) {
            return false;
        }
        return true;
    }

    //item Model validation
    public boolean validationItemModel() {
        if (jTextField1.getText().equals("")) {
            return false;
        }
        return true;
    }

    //item quantity no validation
    public boolean validationItemQuantity() {
        if (jTextField5.getText().equals("")) {
            return false;
        }
        return true;
    }

    // item buying validation
    public boolean validationItemBuyingPrice() {
        if (jTextField6.getText().equals("")) {
            return false;
        }
        return true;
    }

    // party validation
    public boolean validationParty() {
        if (jComboBox1.getSelectedItem() == "Select Party") {
            return false;
        }
        return true;
    }
    // Bill validation

    public boolean validationBill() {
        if (bill_no.getText().equals("")) {
            return false;
        }
        return true;
    }

    public static boolean isLeapYear(int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        } else {
            return year % 4 == 0;
        }
    }
    public static boolean valDOB(String DOB)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date BOD = null;
        df.setLenient(false);
        try{
            
            BOD=df.parse(DOB);
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    public boolean validationDate()
    {
       if (jTextField4.getText().equals("")) {
            return false;
        }
        return true; 
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here: 
        if (!validationDate()) {
            JOptionPane.showMessageDialog(null, "Please enter date");
            return;
        }
        String dob=jTextField4.getText();
        if(!valDOB(dob))
        {
            JOptionPane.showMessageDialog(null,"Invalid date format");
            return;
        }
        
     
//                converting nepali date to english
//        String Date = jTextField7.getText();
//        int pos1 = Date.indexOf("-");
//        int pos2 = Date.indexOf("-", pos1 + 1);
//        String year = Date.substring(0, pos1);
//        String month = Date.substring(pos1 + 1, pos2);
//        String day = Date.substring(pos2 + 1);
//        int nepYear = Integer.parseInt(year);
//        int nepMonth = Integer.parseInt(month);
//        int nepDay = Integer.parseInt(day);
//        JOptionPane.showMessageDialog(null,year);
//        JOptionPane.showMessageDialog(null,month);
//        JOptionPane.showMessageDialog(null,day);
//        initComponents();
//        Map<Integer, int[]> nepaliMap = new HashMap<Integer, int[]>();
//
//        nepaliMap.put(2000, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
//        nepaliMap.put(2001, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2002, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2003, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2004, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
//        nepaliMap.put(2005, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2006, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2007, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2008, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
//        nepaliMap.put(2009, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2010, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2011, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2012, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
//        nepaliMap.put(2013, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2014, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2015, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2016, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
//        nepaliMap.put(2017, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2018, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2019, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
//        nepaliMap.put(2020, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2021, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2022, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
//        nepaliMap.put(2023, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
//        nepaliMap.put(2024, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2025, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2026, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2027, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
//        nepaliMap.put(2028, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2029, new int[]{0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2030, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2031, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
//        nepaliMap.put(2032, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2033, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2034, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2035, new int[]{0, 30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
//        nepaliMap.put(2036, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2037, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2038, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2039, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
//        nepaliMap.put(2040, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2041, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2042, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2043, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
//        nepaliMap.put(2044, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2045, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2046, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2047, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2048, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2049, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
//        nepaliMap.put(2050, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
//        nepaliMap.put(2051, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2052, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2053, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
//        nepaliMap.put(2054, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
//        nepaliMap.put(2055, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2056, new int[]{0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2057, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2058, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
//        nepaliMap.put(2059, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2060, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2061, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2062, new int[]{0, 30, 32, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31});
//        nepaliMap.put(2063, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2064, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2065, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2066, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
//        nepaliMap.put(2067, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2068, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2069, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2070, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
//        nepaliMap.put(2071, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2072, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2073, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
//        nepaliMap.put(2074, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2075, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2076, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
//        nepaliMap.put(2077, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
//        nepaliMap.put(2078, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2079, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
//        nepaliMap.put(2080, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
//        nepaliMap.put(2081, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30});
//        nepaliMap.put(2082, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
//        nepaliMap.put(2083, new int[]{0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
//        nepaliMap.put(2084, new int[]{0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
//        nepaliMap.put(2085, new int[]{0, 31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30});
//        nepaliMap.put(2086, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
//        nepaliMap.put(2087, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30});
//        nepaliMap.put(2088, new int[]{0, 30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30});
//        nepaliMap.put(2089, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
//        nepaliMap.put(2090, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
//
//        int startingNepYear = 2000;
//        int startingNepMonth = 1;
//        int startingNepDay = 1;
//        int dayOfWeek = Calendar.WEDNESDAY; // 2000/1/1 is Wednesday
//        int startingEngYear = 1943;
//        int startingEngMonth = 4;
//        int startingEngDay = 14;
//
//        long totalNepDaysCount = 0;
//
//// count total days in-terms of year
//        for (int i = startingNepYear; i < nepYear; i++) {
//            for (int j = 1; j <= 12; j++) {
//                totalNepDaysCount += nepaliMap.get(i)[j];
//            }
//        }
////        JOptionPane.showMessageDialog(null,totalNepDaysCount);
//// count total days in-terms of month
//        for (int j = startingNepMonth; j < nepMonth; j++) {
//            totalNepDaysCount += nepaliMap.get(nepYear)[j];
//        }
//
//// count total days in-terms of date
//        totalNepDaysCount += nepDay - startingNepDay;
//
//        int[] daysInMonth = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//        int[] daysInMonthOfLeapYear = new int[]{0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//        // calculation of equivalent english date...
//        int engYear = startingEngYear;
//        int engMonth = startingEngMonth;
//        int engDay = startingEngDay;
//
//        int endDayOfMonth = 0;
//
//        while (totalNepDaysCount != 0) {
//            if (isLeapYear(engYear)) {
//                endDayOfMonth = daysInMonthOfLeapYear[engMonth];
//            } else {
//                endDayOfMonth = daysInMonth[engMonth];
//            }
//            engDay++;
//            dayOfWeek++;
//            if (engDay > endDayOfMonth) {
//                engMonth++;
//                engDay = 1;
//                if (engMonth > 12) {
//                    engYear++;
//                    engMonth = 1;
//                }
//            }
//            if (dayOfWeek > 7) {
//                dayOfWeek = 1;
//            }
//            totalNepDaysCount--;
//        }
//        String newDate = engYear + "-" + engMonth + "-" + engDay;
//        jTextField4.setText(newDate);
//      
//        if (!validationItemNo()) {
//            JOptionPane.showMessageDialog(null, "Please enter Item No");
//            return;
//        }
        if (!validationBill()) {
            JOptionPane.showMessageDialog(null, "Please enter Bill Number ");
            return;
        }
        if (!validationItemModel()) {
            JOptionPane.showMessageDialog(null, "Please enter Item Type ");
            return;
        }
        if (!validationItemCategory()) {
            JOptionPane.showMessageDialog(null, "Please enter Item Category");
            return;
        }
//        if (!validationLength()) {
//            JOptionPane.showMessageDialog(null, "Please enter Length");
//            return;
//        }
        if (!validationItemQuantity()) {
            JOptionPane.showMessageDialog(null, "Please enter Item Quantity");
            return;
        }
        if (!validationItemBuyingPrice()) {
            JOptionPane.showMessageDialog(null, "Please enter Item Buying Price");
            return;
        }
        if (!validationParty()) {
            JOptionPane.showMessageDialog(null, "Please select Party");
            return;
        }

//          JOptionPane.showMessageDialog(null,newDate);

        if (paytype.getSelectedItem() == "Due") {
            Double z;
            String item_category = jTextField3.getText();
            String item_model = jTextField1.getText();
//            String lenn = quantity.getText();
            String sqlll = "select * from item where item_category ='" + item_category + "' and item_model ='" + item_model + "'";
            try {
                pst = conn.prepareStatement(sqlll);
                rs = pst.executeQuery(sqlll);
//                JOptionPane.showMessageDialog(null,"success");
                //new party
                if (rs.next() == false) {
                    //checking if party exist in due_purchase and updating his/her due value
                    String item_cat = jTextField3.getText();
                    String pName = (String) jComboBox1.getSelectedItem();
                    String item_mod = jTextField1.getText();
                    String s = "select * from due_purchase where customer_name ='" + pName + "'";
                    try {
                        pst = conn.prepareStatement(s);
                        rs = pst.executeQuery(s);
                        if (rs.next() == true) {
                            //updating paid value from due purchase
                            Double tvat = Double.parseDouble(totalvat.getText());
                            Double olddue = rs.getDouble("due");
                            Double nowdue;
                            if (paid.getText().equals("")) {
                                nowdue = tvat;
                            } else {
                                Double nowpaid = Double.parseDouble(paid.getText());
                                Double m = tvat - nowpaid;
                                nowdue = m;
                            }
                            Double zz = olddue + nowdue;
                            String newssql = "update due_purchase set item_no='" + jTextField2.getText() + "', item_price='" + jTextField6.getText() + "', item_sell_date='" + jTextField4.getText() + "', item_name='" + jTextField3.getText() + "', item_model='" + jTextField1.getText() + "', item_quantity='" + jTextField5.getText() + "', item_total='" + totalvat.getText() + "', "
                                    + "due= '" + zz + "'"
                                    + "where customer_name ='" + pName + "'";
                            try {
                                pst = conn.prepareStatement(newssql);
                                pst.execute();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                        } else {
                            String sql12345 = "insert into due_purchase(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,customer_name,paid,due,bill_no) values(?,?,?,?,?,?,?,?,?,?,?)";
                            try {
                                pst = conn.prepareStatement(sql12345);
                                pst.setString(1, jTextField2.getText());
                                pst.setString(2, jTextField6.getText());
                                pst.setString(3, jTextField4.getText());
                                pst.setString(4, jTextField3.getText());
                                pst.setString(5, jTextField1.getText());
//                                pst.setString(6, jTextField5.getText());
                                pst.setString(6, jTextField5.getText());
                                pst.setString(7, totalvat.getText());
                                pst.setString(8, (String) jComboBox1.getSelectedItem());
                                if (paid.getText().equals("")) {
                                    pst.setString(9, "0");
                                } else {
                                    pst.setString(9, paid.getText());
                                }
                                if (paid.getText().equals("")) {
                                    pst.setString(10, totalvat.getText());
                                } else {
                                    Double aa = Double.parseDouble(totalvat.getText());
                                    Double bb = Double.parseDouble(paid.getText());
                                    Double cc = aa - bb;
                                    pst.setDouble(10, cc);
                                }
                                pst.setString(11, bill_no.getText());
                                pst.execute();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                    //saving into purchase item
                    String sql123 = "insert into purchase_item(receive_date,item_no,item_type,item_category,item_quantity,item_buying_price,total_price,vat_amount,total_vat,party,cash_due,due_paid,due_left,payed_date,bill_no,iscashdue) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(sql123);
                        pst.setString(1, jTextField4.getText());
                        pst.setString(2, jTextField2.getText());
                        pst.setString(3, jTextField3.getText());
                        pst.setString(4, jTextField1.getText());
//                        pst.setString(5, jTextField5.getText());
                        pst.setString(5, jTextField5.getText());
                        pst.setString(6, jTextField6.getText());
                        pst.setString(7, jTextField8.getText());
                        pst.setString(8, jTextField10.getText());
                        pst.setString(9, totalvat.getText());
                        pst.setString(10, (String) jComboBox1.getSelectedItem());
                        if (paytype.getSelectedItem() == "Due") {
                            pst.setString(11, "0");
                        } else {
                            pst.setString(11, "1");
                        }
                        if (paid.getText().equals("")) {
                            pst.setString(12, "0");
                        } else {
                            pst.setString(12, paid.getText());
                        }
                        if (paytype.getSelectedItem() == "Due") {
                            if (paid.getText().equals("")) {
                                pst.setString(13, totalvat.getText());
                            } else {
                                Double a = Double.parseDouble(totalvat.getText());
                                Double b = Double.parseDouble(paid.getText());
                                Double c = a - b;
                                z = c;
                                pst.setDouble(13, c);
                            }
                        } else {
                            pst.setString(13, "0");
                        }
                        pst.setString(14, jTextField4.getText());
                        pst.setString(15, bill_no.getText());
                        pst.setString(16, "1");
                        pst.execute();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    //saving into item database
                    String sql13 = "insert into item(receive_date,receiving_party,item_no,item_category,item_model,item_quantity,item_buying_price,item_total_price,vat_amount,total_vat,cash_due,due_paid,due_left,bill_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(sql13);
                        pst.setString(1, jTextField4.getText());
                        pst.setString(2, (String) jComboBox1.getSelectedItem());
                        pst.setString(3, jTextField2.getText());
                        pst.setString(4, jTextField3.getText());
                        pst.setString(5, jTextField1.getText());
//                        pst.setString(6, jTextField5.getText());
                        pst.setString(6, jTextField5.getText());
                        pst.setString(7, jTextField6.getText());
                        pst.setString(8, jTextField8.getText());
                        pst.setString(9, jTextField10.getText());
                        pst.setString(10, totalvat.getText());
                        if (paytype.getSelectedItem() == "Due") {
                            pst.setString(11, "0");
                        } else {
                            pst.setString(11, "1");
                        }
                        if (paid.getText().equals("")) {
                            pst.setString(12, "0");
                        } else {
                            pst.setString(12, paid.getText());
                        }
                        if (paytype.getSelectedItem() == "Due") {
                            if (paid.getText().equals("")) {
                                pst.setString(13, totalvat.getText());
                            } else {
                                Double a = Double.parseDouble(totalvat.getText());
                                Double b = Double.parseDouble(paid.getText());
                                Double c = a - b;
                                pst.setDouble(13, c);
                            }
                        } else {
                            pst.setString(13, "0");
                        }
                        pst.setString(14, bill_no.getText());
                        pst.execute();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                } // baki Old party huda
                // purano stock ajai x vane
                //rs.next true huda ko case
                else {
                    //checking if party exist in due_purchase and updating his/her due value
                    String item_cat = jTextField3.getText();
                    String pName = (String) jComboBox1.getSelectedItem();
                    String item_mod = jTextField1.getText();
                    String s = "select * from due_purchase where customer_name ='" + pName + "'";
                    try {
                        pst = conn.prepareStatement(s);
                        rs = pst.executeQuery(s);
                        if (rs.next() == true) {
                            //updating paid value from due purchase
                            Double tvat = Double.parseDouble(totalvat.getText());
                            Double olddue = rs.getDouble("due");
                            Double nowdue;
                            if (paid.getText().equals("")) {
                                nowdue = tvat;
                            } else {
                                Double nowpaid = Double.parseDouble(paid.getText());
                                Double m = tvat - nowpaid;
                                nowdue = m;
                            }
                            Double zz = olddue + nowdue;
                            String newssql = "update due_purchase set item_no='" + jTextField2.getText() + "', item_price='" + jTextField6.getText() + "', item_sell_date='" + jTextField4.getText() + "', item_name='" + jTextField3.getText() + "', item_model='" + jTextField1.getText() + "', item_quantity='" + jTextField5.getText() + "', item_total='" + totalvat.getText() + "', "
                                    + "due= '" + zz + "'"
                                    + "where customer_name ='" + pName + "'";
                            try {
                                pst = conn.prepareStatement(newssql);
                                pst.execute();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                        } else {
                            String sql12345 = "insert into due_purchase(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,customer_name,paid,due,bill_no) values(?,?,?,?,?,?,?,?,?,?,?)";
                            try {
                                pst = conn.prepareStatement(sql12345);
                                pst.setString(1, jTextField2.getText());
                                pst.setString(2, jTextField6.getText());
                                pst.setString(3, jTextField4.getText());
                                pst.setString(4, jTextField3.getText());
                                pst.setString(5, jTextField1.getText());
//                                pst.setString(6, jTextField5.getText());
                                pst.setString(6, jTextField5.getText());
                                pst.setString(7, totalvat.getText());
                                pst.setString(8, (String) jComboBox1.getSelectedItem());
                                if (paid.getText().equals("")) {
                                    pst.setString(9, "0");
                                } else {
                                    pst.setString(9, paid.getText());
                                }
                                if (paid.getText().equals("")) {
                                    pst.setString(10, totalvat.getText());
                                } else {
                                    Double aa = Double.parseDouble(totalvat.getText());
                                    Double bb = Double.parseDouble(paid.getText());
                                    Double cc = aa - bb;
                                    pst.setDouble(10, cc);
                                }
                                pst.setString(11, bill_no.getText());
                                pst.execute();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                    //updating item quantity
                    String item_categoryyy = jTextField3.getText();
                    String item_modellll = jTextField1.getText();
//                    String lengthh = jTextField5.getText();
                    String sqlitem = "select * from item where item_category ='" + item_categoryyy + "' and item_model ='" + item_modellll + "' ";
                    try {
                        pst = conn.prepareStatement(sqlitem);
                        rs = pst.executeQuery(sqlitem);
                        //updating item quantity 
                        while (rs.next()) {
                            Double q = rs.getDouble("item_quantity");
                            Double r = Double.parseDouble(jTextField5.getText());
                            Double ss = q + r;
                            String newsql = "update item set "
                                    + "item_quantity= '" + ss + "' "
                                    + "where item_category ='" + item_category + "' and item_model ='" + item_model + "' ";
                            try {
                                pst = conn.prepareStatement(newsql);
                                pst.execute();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                    //saving into purchase item
                    String sql123 = "insert into purchase_item(receive_date,item_no,item_type,item_category,item_quantity,item_buying_price,total_price,vat_amount,total_vat,party,cash_due,due_paid,due_left,payed_date,bill_no,iscashdue) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(sql123);
                        Double q = Double.parseDouble(jTextField5.getText());
                        pst.setString(1, jTextField4.getText());
                        pst.setString(2, jTextField2.getText());
                        pst.setString(3, jTextField3.getText());
                        pst.setString(4, jTextField1.getText());
//                        pst.setString(5, jTextField5.getText());
                        pst.setString(5, jTextField5.getText());
                        pst.setString(6, jTextField6.getText());
                        pst.setString(7, jTextField8.getText());
                        pst.setString(8, jTextField10.getText());
                        pst.setString(9, totalvat.getText());
                        pst.setString(10, (String) jComboBox1.getSelectedItem());
                        if (paytype.getSelectedItem() == "Due") {
                            pst.setString(11, "0");
                        } else {
                            pst.setString(11, "1");
                        }
                        if (paid.getText().equals("")) {
                            pst.setString(12, "0");
                        } else {
                            pst.setString(12, paid.getText());
                        }
                        if (paytype.getSelectedItem() == "Due") {
                            if (paid.getText().equals("")) {
                                pst.setString(13, totalvat.getText());
                            } else {
                                Double a = Double.parseDouble(totalvat.getText());
                                Double b = Double.parseDouble(paid.getText());
                                Double c = a - b;
                                z = c;
                                pst.setDouble(13, c);
                            }
                        } else {
                            pst.setString(13, "0");
                        }
                        pst.setString(14, jTextField4.getText());
                        pst.setString(15, bill_no.getText());
                        pst.setString(16, "1");
                        pst.execute();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
                //rs.next false ko part yai samma ho 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } // selected item due hoina vane 
        //baki nahuda ko case ma 
        else {
            //updating item quantity if paid cash
            String item_category = jTextField3.getText();
            String item_model = jTextField1.getText();
//            String len = jTextField5.getText();
            String sqll3 = "select * from item where item_category ='" + item_category + "' and item_model ='" + item_model + "'";
            try {
                pst = conn.prepareStatement(sqll3);
                rs = pst.executeQuery(sqll3);
                //updating item quantity 
                if (rs.next() == true) {
                    Double q = rs.getDouble("item_quantity");
                    Double r = Double.parseDouble(jTextField5.getText());
                    Double s = q + r;
                    String newsql = "update item set "
                            + "item_quantity= '" + s + "'"
                            + "where item_category ='" + item_category + "' and item_model ='" + item_model + "' ";
                    try {
                        pst = conn.prepareStatement(newsql);
                        pst.execute();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    //saving to purchase item
                    //saving into purchaseitem if payed cash
                    String sql123 = "insert into purchase_item(receive_date,item_no,item_type,item_category,item_quantity,item_buying_price,total_price,vat_amount,total_vat,party,cash_due,due_paid,due_left,payed_date,bill_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(sql123);
                        pst.setString(1, jTextField4.getText());
                        pst.setString(2, jTextField2.getText());
                        pst.setString(3, jTextField3.getText());
                        pst.setString(4, jTextField1.getText());
//                        pst.setString(5, jTextField5.getText());
                        pst.setString(5, jTextField5.getText());
                        pst.setString(6, jTextField6.getText());
                        pst.setString(7, jTextField8.getText());
                        pst.setString(8, jTextField10.getText());
                        pst.setString(9, totalvat.getText());
                        pst.setString(10, (String) jComboBox1.getSelectedItem());
                        pst.setString(11, "1");
                        if (paid.getText().equals("")) {
                            pst.setString(12, "0");
                        } else {
                            pst.setString(12, paid.getText());
                        }
                        if (paytype.getSelectedItem() == "Due") {
                            if (paid.getText().equals("")) {
                                pst.setString(13, totalvat.getText());
                            } else {
                                Double a = Double.parseDouble(totalvat.getText());
                                Double b = Double.parseDouble(paid.getText());
                                Double c = a - b;
                                pst.setDouble(13, c);
                            }
                        } else {
                            pst.setString(13, "0");
                        }
                        pst.setString(14, jTextField4.getText());
                        pst.setString(15, bill_no.getText());
                        pst.execute();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                } else {

                    String pName = (String) jComboBox1.getSelectedItem();
                    String s = "select * from due_purchase where customer_name ='" + pName + "'";
                    try {
                        pst = conn.prepareStatement(s);
                        rs = pst.executeQuery(s);
                        if (rs.next() == false) {
                            //saving into due_purchase table
                            String sql1234 = "insert into due_purchase(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,customer_name,paid,due,bill_no) values(?,?,?,?,?,?,?,?,?,?,?)";
                            try {
                                pst = conn.prepareStatement(sql1234);
                                pst.setString(1, "0");
                                pst.setString(2, "0");
                                pst.setString(3, "2003-01-01");
                                pst.setString(4, jTextField3.getText());
                                pst.setString(5, jTextField1.getText());
//                                pst.setString(6, "0");
                                pst.setString(6, "0");
                                pst.setString(7, "0");
                                pst.setString(8, (String) jComboBox1.getSelectedItem());
                                pst.setString(9, "0");
                                pst.setString(10, "0");
                                pst.setString(11, bill_no.getText());
                                pst.execute();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }

                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                    //saving into purchaseitem if payed cash
                    String sql123 = "insert into purchase_item(receive_date,item_no,item_type,item_category,item_quantity,item_buying_price,total_price,vat_amount,total_vat,party,cash_due,due_paid,due_left,payed_date,bill_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(sql123);
                        pst.setString(1, jTextField4.getText());
                        pst.setString(2, jTextField2.getText());
                        pst.setString(3, jTextField3.getText());
                        pst.setString(4, jTextField1.getText());
//                        pst.setString(5, jTextField5.getText());
                        pst.setString(5, jTextField5.getText());
                        pst.setString(6, jTextField6.getText());
                        pst.setString(7, jTextField8.getText());
                        pst.setString(8, jTextField10.getText());
                        pst.setString(9, totalvat.getText());
                        pst.setString(10, (String) jComboBox1.getSelectedItem());
                        pst.setString(11, "1");
                        if (paid.getText().equals("")) {
                            pst.setString(12, "0");
                        } else {
                            pst.setString(12, paid.getText());
                        }
                        if (paytype.getSelectedItem() == "Due") {
                            if (paid.getText().equals("")) {
                                pst.setString(13, totalvat.getText());
                            } else {
                                Double a = Double.parseDouble(totalvat.getText());
                                Double b = Double.parseDouble(paid.getText());
                                Double c = a - b;
                                pst.setDouble(13, c);
                            }
                        } else {
                            pst.setString(13, "0");
                        }
                        pst.setString(14, jTextField4.getText());
                        pst.setString(15, bill_no.getText());
                        pst.execute();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                    //saving into item database
                    String sql13 = "insert into item(receive_date,receiving_party,item_no,item_category,item_model,item_quantity,item_buying_price,item_total_price,vat_amount,total_vat,cash_due,due_paid,due_left,bill_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(sql13);
                        pst.setString(1, jTextField4.getText());
                        pst.setString(2, (String) jComboBox1.getSelectedItem());
                        pst.setString(3, jTextField2.getText());
                        pst.setString(4, jTextField3.getText());
                        pst.setString(5, jTextField1.getText());
//                        pst.setString(6, jTextField5.getText());
                        pst.setString(6, jTextField5.getText());
                        pst.setString(7, jTextField6.getText());
                        pst.setString(8, jTextField8.getText());
                        pst.setString(9, jTextField10.getText());
                        pst.setString(10, totalvat.getText());
                        if (paytype.getSelectedItem() == "Due") {
                            pst.setString(11, "0");
                        } else {
                            pst.setString(11, "1");
                        }
                        if (paid.getText().equals("")) {
                            pst.setString(12, "0");
                        } else {
                            pst.setString(12, paid.getText());
                        }
                        if (paytype.getSelectedItem() == "Due") {
                            if (paid.getText().equals("")) {
                                pst.setString(13, totalvat.getText());
                            } else {
                                Double a = Double.parseDouble(totalvat.getText());
                                Double b = Double.parseDouble(paid.getText());
                                Double c = a - b;
                                pst.setDouble(13, c);
                            }
                        } else {
                            pst.setString(13, "0");
                        }
                        pst.setString(14, bill_no.getText());
                        pst.execute();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        String itemNo = jTextField2.getText();
        String sqllle = "select * from purchase_item where item_no = '" + itemNo + "' ";
        try {
            pst = conn.prepareStatement(sqllle);
            rs = pst.executeQuery(sqllle);
            if (rs.next() == false) {
                //saving into daybook table
                String daybook = "insert into daybook(item_no,item_price,item_buy_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due,purchase_cash,cash,non_cash,bill_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                try {
                    pst = conn.prepareStatement(daybook);
                    pst.setString(1, rs.getString("item_no"));
                    pst.setString(2, rs.getString("item_buying_price"));
                    pst.setString(3, rs.getString("payed_date"));
                    pst.setString(4, rs.getString("item_type"));
                    pst.setString(5, rs.getString("item_category"));
//                    pst.setString(6, rs.getString("length"));
                    pst.setString(6, rs.getString("item_quantity"));
                    pst.setString(7, rs.getString("total_price"));
                    pst.setString(8, "13");
                    pst.setString(9, rs.getString("total_vat"));
                    pst.setString(10, rs.getString("party"));
                    pst.setString(11, rs.getString("due_paid"));
                    pst.setString(12, rs.getString("due_left"));
                    if (paytype.getSelectedItem() == "Due") {
                        if (paid.getText().equals("")) {
                            pst.setString(13, "0");
                        } else {
                            pst.setString(13, paid.getText());
                        }
                    } else {
                        pst.setString(13, rs.getString("total_vat"));
                    }
                    pst.setString(14, "0");
                    pst.setString(15, "0");
                    pst.setString(16, bill_no.getText());
                    pst.execute();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                do {
                    //saving into daybook table
                    String daybook = "insert into daybook(item_no,item_price,item_buy_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due,purchase_cash,cash,non_cash,bill_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(daybook);
                        pst.setString(1, rs.getString("item_no"));
                        pst.setString(2, rs.getString("item_buying_price"));
                        pst.setString(3, rs.getString("payed_date"));
                        pst.setString(4, rs.getString("item_type"));
                        pst.setString(5, rs.getString("item_category"));
//                        pst.setString(6, rs.getString("length"));
                        pst.setString(6, rs.getString("item_quantity"));
                        pst.setString(7, rs.getString("total_price"));
                        pst.setString(8, "13");
                        pst.setString(9, rs.getString("total_vat"));
                        pst.setString(10, rs.getString("party"));
                        pst.setString(11, rs.getString("due_paid"));
                        pst.setString(12, rs.getString("due_left"));
                        if (paytype.getSelectedItem() == "Due") {
                            if (paid.getText().equals("")) {
                                pst.setString(13, "0");
                            } else {
                                pst.setString(13, paid.getText());
                            }
                        } else {
                            pst.setString(13, rs.getString("total_vat"));
                        }
                        pst.setString(14, "0");
                        pst.setString(15, "0");
                        pst.setString(16, bill_no.getText());
                        pst.execute();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                } while (rs.next());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        JOptionPane.showMessageDialog(null, "Item Added Succesfully");
        clear();
        fetch();
//        quantitysum();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        // TODO add your handling code here:'

    }//GEN-LAST:event_jTextField6KeyPressed

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        // TODO add your handling code here:
        Double qty, price, total;
        qty = Double.parseDouble(jTextField5.getText());
        price = Double.parseDouble(jTextField6.getText());

        total = qty * price;
        total = Double.parseDouble(new DecimalFormat("##.##").format(total));
        jTextField8.setText("" + total);

        Double d = Double.parseDouble(jTextField8.getText());
        Double e = 13 * d;
        Double f = e / 100;
        f = Double.parseDouble(new DecimalFormat("##.##").format(f));
        jTextField10.setText("" + f);
        Double g = d + f;
        g = Double.parseDouble(new DecimalFormat("##.##").format(g));
        totalvat.setText("" + g);
    }//GEN-LAST:event_jTextField6KeyReleased

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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        clear();
//        clock();
        jButton1.setText("Save");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table1MouseClicked

    private void search_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_textKeyPressed
        // TODO add your handling code here:            
    }//GEN-LAST:event_search_textKeyPressed

    private void search_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_textKeyReleased
        // TODO add your handling code here:
        String query = search_text.getText();
        filter(query);
    }//GEN-LAST:event_search_textKeyReleased

    private void totalvatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalvatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalvatActionPerformed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
//        char c = evt.getKeyChar();
//        if (!((Character.isAlphabetic(c) || c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
//            evt.consume();
//        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void totalvatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalvatKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!((Character.isAlphabetic(c) || c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_totalvatKeyTyped

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10KeyTyped

    private void paidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paidActionPerformed

    private void paidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_paidKeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        paid.setVisible(false);
        jLabel11.setVisible(false);
//        jTextField4.setVisible(false);
    }//GEN-LAST:event_formWindowOpened

    private void paytypePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_paytypePopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (paytype.getSelectedItem() == "Due") {
            paid.setVisible(true);
            jLabel11.setVisible(true);
        } else {
            paid.setVisible(false);
            jLabel11.setVisible(false);
        }
    }//GEN-LAST:event_paytypePopupMenuWillBecomeInvisible

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:         
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField5KeyTyped

    private void paytypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paytypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paytypeActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:       
//        quantitysum();
    }//GEN-LAST:event_formWindowActivated

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void bill_noKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bill_noKeyReleased
        // TODO add your handling code here:      
    }//GEN-LAST:event_bill_noKeyReleased

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowIconified

    private void bill_noKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bill_noKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_bill_noKeyTyped

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
            java.util.logging.Logger.getLogger(AddItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddItem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bill_no;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField paid;
    private javax.swing.JComboBox paytype;
    private javax.swing.JTextField search_text;
    private javax.swing.JTable table1;
    private javax.swing.JTextField totalvat;
    // End of variables declaration//GEN-END:variables
}
