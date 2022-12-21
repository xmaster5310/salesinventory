
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.value;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
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
public class Statement extends javax.swing.JFrame {

    DefaultTableModel dm;
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    /**
     * Creates new form Duereport
     */
    public Statement() {
        super("Statement");
        initComponents();
        conn = javaconnect.getConnection();
        fetch();
//        fetch1();
        getSum();
        getSum1();
        autoSave();
    }

    //display sum

    public int getSum() {
        int rowsCount = table1.getRowCount();
//        JOptionPane.showMessageDialog(null,rowsCount);
        Double sum = 0.0;
//        Double sum1 = 0.0;
        for (int i = 0; i < rowsCount; i++) {
            if (table1.getValueAt(i, 6) == null) {
                i = i + 1;
            } else {
                sum = sum + Double.parseDouble(table1.getValueAt(i, 6).toString());
//                    JOptionPane.showMessageDialog(null,sum);
            }
        }
        sum = Double.parseDouble(new DecimalFormat("##.##").format(sum));
        jLabel4.setText(Double.toString(sum));
//        jLabel5.setText(Double.toString(sum1));         
        return 0;
    }

    public int getSum1() {
        int rowsCount = table1.getRowCount();
//        JOptionPane.showMessageDialog(null,rowsCount);
//        Double sum = 0.0;
        Double sum1 = 0.0;
        for (int i = 0; i < rowsCount; i++) {
            if (table1.getValueAt(i, 12) == null) {
                i = i + 1;
            } else {
                sum1 = sum1 + Double.parseDouble(table1.getValueAt(i, 12).toString());
            }
        }
//        jLabel4.setText(Double.toString(sum));
        sum1 = Double.parseDouble(new DecimalFormat("##.##").format(sum1));
        jLabel5.setText(Double.toString(sum1));
        return 0;
    }

//    public void clock()
//    {
//        Calendar cal = new GregorianCalendar();
//                    int month = cal.get(Calendar.MONTH);
//                    int year = cal.get(Calendar.YEAR);
//                    int day = cal.get(Calendar.DAY_OF_MONTH);
////                    date.setText(year + "-" + (month+1) + "-" + day);
//    } 
//    
     public boolean validationDate() {
        if (date1.getText().equals(""))
        {
            return false;
        }     
        if(date2.getText().equals(""))
        {
            return false;
        }
        return true;
    }
    
    public void fetch() {
//         if(!validationDate())
//         {
//             JOptionPane.showMessageDialog(null,"Please Enter Date");
//             return;
//         }
        try {
            JTableHeader header = table1.getTableHeader();
            header.setForeground(Color.red);
            header.setFont(new Font("Tahome", Font.ITALIC, 14));//          
            String q = "SELECT purchase_item.receive_date as 'Buy Date',purchase_item.party as 'Party',purchase_item.item_type as 'Item Type',purchase_item.item_category as 'Category',purchase_item.item_quantity as 'Qty',purchase_item.item_buying_price as 'Buy Price',purchase_item.total_vat as 'Total',sale.item_sell_date as 'Sold Date',sale.customer_name as 'CustomerName',sale.item_name as 'Item Type',sale.item_model as 'Category',sale.item_quantity as 'Qty',sale.item_discount_total as 'Total' FROM purchase_item LEFT JOIN sale ON purchase_item.id = sale.id UNION SELECT purchase_item.receive_date as 'Buy Date',purchase_item.party as 'Party',purchase_item.item_type as 'Item Type',purchase_item.item_category as 'Category',purchase_item.item_quantity as 'Qty',purchase_item.item_buying_price as 'Buy Price',purchase_item.total_vat as 'Total',sale.item_sell_date as 'Sold Date',sale.customer_name as 'CustomerName',sale.item_name as 'Item Type',sale.item_model as 'Category',sale.item_quantity as 'Qty',sale.item_discount_total as 'Total' FROM purchase_item RIGHT JOIN sale ON purchase_item.id = sale.id ";
//            String q="SELECT purchase_item.receive_date as 'Buy Date',purchase_item.party as 'Party',purchase_item.item_type as 'Item Type',purchase_item.item_category as 'Category',purchase_item.item_quantity as 'Qty',purchase_item.item_buying_price as 'Buy Price',purchase_item.total_vat as 'Total',sale.item_sell_date as 'Sold Date',sale.customer_name as 'CustomerName',sale.item_name as 'Item Type',sale.item_model as 'Category',sale.item_quantity as 'Qty',sale.item_discount_total as 'Total' from purchase_item  left join  sale on purchase_item.item_no = sale.item_no";
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            //increasing the width of columns
            TableColumnModel columnModel = table1.getColumnModel();
//            columnModel.getColumn(0).setPreferredWidth(25);
//            columnModel.getColumn(1).setPreferredWidth(50);
//            columnModel.getColumn(2).setPreferredWidth(55);
            columnModel.getColumn(4).setPreferredWidth(40);
//            columnModel.getColumn(6).setPreferredWidth(40);
//            columnModel.getColumn(8).setPreferredWidth(80);
            columnModel.getColumn(11).setPreferredWidth(40);
//            columnModel.getColumn(12).setPreferredWidth(50);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Item not Fetched");
        }
    }

    public void Deleted()
    {
         String ggg = "delete from statement";
                try {
                    pst = conn.prepareStatement(ggg);                   
                    pst.execute();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
    }
    public void autoSave() {
        dm = (DefaultTableModel) table1.getModel();
//        JOptionPane.showMessageDialog(null, dm.getRowCount());
        try {
            String party, buy_date, item_type, category, qty, buy_price, total, sold_date, customer, itemtype, ccategory, quantity, ttotal;
            for (int i = 0; i < dm.getRowCount(); i++) {
                if (dm.getValueAt(i, 0) == null) {
                    buy_date = null;
                } else {
                    buy_date = dm.getValueAt(i, 0).toString();
                }
                if (dm.getValueAt(i, 1) == null) {
                    party = null;
                } else {
                    party = dm.getValueAt(i, 1).toString();
                }
                if (dm.getValueAt(i, 2) == null) {
                    item_type = null;
                } else {
                    item_type = dm.getValueAt(i, 2).toString();
                }
                if (dm.getValueAt(i, 3) == null) {
                    category = null;
                } else {
                    category = dm.getValueAt(i, 3).toString();
                }
                if (dm.getValueAt(i, 4) == null) {
                    qty = null;
                } else {
                    qty = dm.getValueAt(i, 4).toString();
                }
                if (dm.getValueAt(i, 5) == null) {
                    buy_price = null;
                } else {
                    buy_price = dm.getValueAt(i, 5).toString();
                }

                if (dm.getValueAt(i, 6) == null) {
                    total = null;
                } else {
                    total = dm.getValueAt(i, 6).toString();
                }

                if (dm.getValueAt(i, 7) == null) {
                    sold_date = null;
                } else {
                    sold_date = dm.getValueAt(i, 7).toString();
                }

                if (dm.getValueAt(i, 8) == null) {
                    customer = null;
                } else {
                    customer = dm.getValueAt(i, 8).toString();
                }

                if (dm.getValueAt(i, 9) == null) {
                    itemtype = null;
                } else {
                    itemtype = dm.getValueAt(i, 9).toString();
                }

                if (dm.getValueAt(i, 10) == null) {
                    ccategory = null;
                } else {
                    ccategory = dm.getValueAt(i, 10).toString();
                }

                if (dm.getValueAt(i, 11) == null) {
                    quantity = null;
                } else {
                    quantity = dm.getValueAt(i, 11).toString();
                }

                if (dm.getValueAt(i, 12) == null) {
                    ttotal = null;
                } else {
                    ttotal = dm.getValueAt(i, 12).toString();
                }
//                 JOptionPane.showMessageDialog(null,buy_date);

                String gg = "insert into statement(receive_date,party,item_type,item_category,item_quantity,item_buying_price,total_vat,item_sell_date,customer_name,item_name,item_model,item_qty,item_discount_total)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                try {
                    pst = conn.prepareStatement(gg);
                    pst.setString(1, buy_date);
                    pst.setString(2, party);
                    pst.setString(3, item_type);
                    pst.setString(4, category);
                    pst.setString(5, qty);
                    pst.setString(6, buy_price);
                    pst.setString(7, total);
                    pst.setString(8, sold_date);
                    pst.setString(9, customer);
                    pst.setString(10, itemtype);
                    pst.setString(11, ccategory);
                    pst.setString(12, quantity);
                    pst.setString(13, ttotal);
                    pst.execute();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "not saved");
        }
    }

    public void fetch1() {
        try {            
//            java.util.Date utilDate = date1.getDate();
//            java.sql.Date val1 = new java.sql.Date(utilDate.getTime());
//            Integer value=00000001;
//            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
//            java.sql.Date val3 =(java.sql.Date) originalFormat.parse(value.toString());
//            JOptionPane.showMessageDialog(null,val3);

//            java.util.Date utilDate1 = date2.getDate();
//            java.sql.Date val2 = new java.sql.Date(utilDate1.getTime());

            String val1=date1.getText();
            String val2=date2.getText();
            JTableHeader header = table1.getTableHeader();
            header.setForeground(Color.red);
            header.setFont(new Font("Tahome", Font.ITALIC, 14));//          
            String q = "select receive_date as 'Buy Date',party as Party,item_type as 'Item Type',item_category as 'Category',item_quantity as 'Qty',item_buying_price as 'Buy Price',total_vat as 'Total',item_sell_date as 'Sold Date',customer_name as 'Customer',item_name as 'Item Type',item_model as 'Category',item_qty as Qty,item_discount_total as 'Total' from statement where receive_date between '" + val1 + "' and '" + val2 + "' or item_sell_date between '" + val1 + "' and '" + val2 + "' ";

//            where receive_date between '"+val1+"' and '"+ val2+"' or item_sell_date between '"+val1+"' and '"+ val2+"'
//            JOptionPane.showMessageDialog(null, q);
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
//            JOptionPane.showMessageDialog(null, rs);

//            String qq="INSERT INTO statement (receive_date) SELECT receive_date FROM src_tbl";
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            //increasing the width of columns
            TableColumnModel columnModel = table1.getColumnModel();
//            columnModel.getColumn(0).setPreferredWidth(50);
//            columnModel.getColumn(1).setPreferredWidth(50);
              columnModel.getColumn(4).setPreferredWidth(40);
//            columnModel.getColumn(6).setPreferredWidth(40);
//            columnModel.getColumn(8).setPreferredWidth(80);
            columnModel.getColumn(11).setPreferredWidth(40);
//            columnModel.getColumn(2).setPreferredWidth(55);
//            columnModel.getColumn(4).setPreferredWidth(40);
//            columnModel.getColumn(7).setPreferredWidth(50);
//            columnModel.getColumn(8).setPreferredWidth(80);
//            columnModel.getColumn(11).setPreferredWidth(40);
//            columnModel.getColumn(12).setPreferredWidth(50);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Item not Fetched");
        }
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
        search_text = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        date1 = new javax.swing.JTextField();
        date2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jLabel1.setText("       Statement");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        search_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_textKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_textKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel12.setText("Search");

        jLabel2.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jLabel2.setText("From");

        jLabel3.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jLabel3.setText("To");

        jButton1.setFont(new java.awt.Font("Sitka Subheading", 3, 12)); // NOI18N
        jButton1.setText("ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(153, 153, 255));
        jButton6.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jButton6.setText("Print");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(26, 26, 26)
                .addComponent(search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(185, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(search_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(date1))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1))
                        .addGap(18, 18, 18))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 70));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table1 = new JTable()
        {
            public boolean isCellEditable(int row, int column)
            {
                for(int i=0;i<table1.getRowCount();i++)
                {
                    if(row ==i)
                    {
                        return false;
                    }
                }
                return true;
            }
        };
        table1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        table1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1270, 510));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 80, 1280, 510));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 1060, 0));

        jLabel4.setFont(new java.awt.Font("Palatino Linotype", 3, 12)); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 590, 130, 30));

        jLabel5.setFont(new java.awt.Font("Palatino Linotype", 3, 12)); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 590, 110, 30));

        setSize(new java.awt.Dimension(1279, 663));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void search_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_textKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_textKeyPressed

    private void search_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_textKeyReleased
        // TODO add your handling code here:
        String query = search_text.getText();
        filter(query);
    }//GEN-LAST:event_search_textKeyReleased

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked

//         if(evt.getClickCount() == 2)
//         {
//        TableModel model1 = table1.getModel();
//        int indexs[] = table1.getSelectedRows();
//        Object[] row = new Object[14];
//        PartyPayedDueReport pdr = new PartyPayedDueReport();
//        DefaultTableModel model2= (DefaultTableModel)pdr.table1.getModel();
//        for(int i=0;i<indexs.length;i++)
//        {
//            row[0] = model1.getValueAt(indexs[i],0);
//            row[1] = model1.getValueAt(indexs[i],1);
//            row[2] = model1.getValueAt(indexs[i],2);
//            row[3] = model1.getValueAt(indexs[i],3);
//            row[4] = model1.getValueAt(indexs[i],4);
//            row[5] = model1.getValueAt(indexs[i],5);
//            row[6] = model1.getValueAt(indexs[i],6);
//            row[7] = model1.getValueAt(indexs[i],7);
//            row[8] = model1.getValueAt(indexs[i],8);
//            row[9] = model1.getValueAt(indexs[i],9);
//            row[10] = model1.getValueAt(indexs[i],10);
////            row[11] = model1.getValueAt(indexs[i],11);       
//            model2.addRow(row);
//        }
//        pdr.setVisible(true);
//         }
//         else
//         {
//        int i = table1.getSelectedRow();
//        dm = (DefaultTableModel) table1.getModel();
//        jTextField1.setText(dm.getValueAt(i, 8).toString());
//        cell.setText(dm.getValueAt(i, 0).toString());
////        oldPaid.setText(dm.getValueAt(i, 11).toString());
//        jTextField2.setText(dm.getValueAt(i, 9).toString());
//        date.setText(dm.getValueAt(i,3).toString());
//         }
    }//GEN-LAST:event_table1MouseClicked

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
      public void clear()
      {
          date1.setText("");
          date2.setText("");
      }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:  
         if(!validationDate())
         {
             JOptionPane.showMessageDialog(null,"Please Enter Date");
             return;
         }
          String dob1=date1.getText();
        if(!valDOB(dob1))
        {
            JOptionPane.showMessageDialog(null,"Invalid date format");
            clear();
            return;
        }
         String dob2=date2.getText();
        if(!valDOB(dob2))
        {
            JOptionPane.showMessageDialog(null,"Invalid date format");
            clear();
            return;
        }
        fetch1();
        getSum();
        getSum1();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
//        JOptionPane.showMessageDialog(null,"hello closed");
        Deleted();
    }//GEN-LAST:event_formWindowClosing

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("Statement Print");
        //        MessageFormat footer = new MessageFormat("page{0,number,integer}");/
//        int a=Integer.parseInt(jLabel4.getText());
//        int b=Integer.parseInt(jLabel5.getText());
//        int s = b-a;
//        
        MessageFormat footer = new MessageFormat("Total purchase: "+jLabel4.getText()+" , Total sale: "+jLabel5.getText()+"");
//        MessageFormat footer = new MessageFormat("page{0,number,integer}");
        try {
            table1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (java.awt.print.PrinterException e) {
            System.err.format("cannot print", e.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowOpened
  //pay validation
//    public boolean validationPay()
//    {
//        if (jTextField1.getText().equals("")) {             
//            return false;
//        }
//        if (jTextField2.getText().equals("")) {             
//            return false;
//        }       
//        return true;
//    }
//    public boolean validationPayed()
//    {
//         if (jTextField3.getText().equals("")) {             
//            return false;
//        }
//         return true;
//    }

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
            java.util.logging.Logger.getLogger(DueSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DueSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DueSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DueSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DueSale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField date1;
    private javax.swing.JTextField date2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search_text;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
