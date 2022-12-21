
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DueSale extends javax.swing.JFrame {

    DefaultTableModel dm;
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    /**
     * Creates new form Duereport
     */
    public DueSale() {
        super("Due Report");
        initComponents();
        conn = javaconnect.getConnection();
        fetch();
        clock();
    }

    public void clear() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        cell.setText("");
        oldPaid.setText("");
    }

    public long daysBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    public void clock() {
        Map<Integer, int[]> nepaliMap = new HashMap<Integer, int[]>();
        nepaliMap.put(2000, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2001, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2002, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2003, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2004, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2005, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2006, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2007, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2008, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        nepaliMap.put(2009, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2010, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2011, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2012, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        nepaliMap.put(2013, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2014, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2015, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2016, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        nepaliMap.put(2017, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2018, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2019, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2020, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2021, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2022, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        nepaliMap.put(2023, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2024, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2025, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2026, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2027, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2028, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2029, new int[]{0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2030, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2031, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2032, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2033, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2034, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2035, new int[]{0, 30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        nepaliMap.put(2036, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2037, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2038, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2039, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        nepaliMap.put(2040, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2041, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2042, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2043, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        nepaliMap.put(2044, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2045, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2046, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2047, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2048, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2049, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        nepaliMap.put(2050, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2051, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2052, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2053, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        nepaliMap.put(2054, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2055, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2056, new int[]{0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2057, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2058, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2059, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2060, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2061, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2062, new int[]{0, 30, 32, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31});
        nepaliMap.put(2063, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2064, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2065, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2066, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        nepaliMap.put(2067, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2068, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2069, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2070, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        nepaliMap.put(2071, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2072, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2073, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        nepaliMap.put(2074, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2075, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2076, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        nepaliMap.put(2077, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        nepaliMap.put(2078, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2079, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        nepaliMap.put(2080, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        nepaliMap.put(2081, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2082, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2083, new int[]{0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2084, new int[]{0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2085, new int[]{0, 31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2086, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2087, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2088, new int[]{0, 30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2089, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        nepaliMap.put(2090, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});

        Calendar cal = new GregorianCalendar();
        int month1 = cal.get(Calendar.MONTH);
        int year1 = cal.get(Calendar.YEAR);
        int day1 = cal.get(Calendar.DAY_OF_MONTH);
        String d = year1 + "-" + (month1 + 1) + "-" + day1;
        String Date = d;
//            JOptionPane.showMessageDialog(null,Date);
        int pos1 = Date.indexOf("-");
        int pos2 = Date.indexOf("-", pos1 + 1);
        String year = Date.substring(0, pos1);
        String month = Date.substring(pos1 + 1, pos2);
        String day = Date.substring(pos2 + 1);
        int engYear = Integer.parseInt(year);
        int engMonth = Integer.parseInt(month);
        int engDay = Integer.parseInt(day);

        int startingEngYear = 1944;
        int startingEngMonth = 1;
        int startingEngDay = 1;
        int dayOfWeek = Calendar.SATURDAY; // 1944/1/1 is Saturday
        int startingNepYear = 2000;
        int startingNepMonth = 9;
        int startingNepDay = 17;

        Calendar currentEngDate = new GregorianCalendar();
        currentEngDate.set(engYear, engMonth, engDay);
        Calendar baseEngDate = new GregorianCalendar();
        baseEngDate.set(startingEngYear, startingEngMonth, startingEngDay);
        long totalEngDaysCount = daysBetween(baseEngDate, currentEngDate);

        // initialize required Nepali date variables with starting Nepali date
        int nepYear = startingNepYear;
        int nepMonth = startingNepMonth;
        int nepDay = startingNepDay;

// decrement totalEngDaysCount until its value becomes 0
        while (totalEngDaysCount != 0) {

            // getting the total number of days in month nepMonth in year nepYear    
            int daysInIthMonth = nepaliMap.get(nepYear)[nepMonth];

            nepDay++; // incrementing nepali day

            if (nepDay > daysInIthMonth) {
                nepMonth++;
                nepDay = 1;
            }
            if (nepMonth > 12) {
                nepYear++;
                nepMonth = 1;
            }

            dayOfWeek++; // count the days in terms of 7 days
            if (dayOfWeek > 7) {
                dayOfWeek = 1;
            }
            totalEngDaysCount--;
        }
        String newDate = nepYear + "-" + nepMonth + "-" + nepDay;
        date.setText(newDate);
    }

    public void fetch() {
        try {
            JTableHeader header = table1.getTableHeader();
            header.setForeground(Color.red);
            header.setFont(new Font("Tahome", Font.ITALIC, 12));
            String q = "select id as Id, item_no as 'Item No',item_price as 'Item Price',item_sell_date as 'Sell Date',item_name as 'ItemName',item_model as 'ItemModel',item_quantity as 'Quantity',item_total as 'Total',item_discount_percent as 'Dis%',item_discount_total as 'Total Amount',customer_name as 'Customer Name',due as Due from due ORDER BY id DESC";
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            //increasing the width of columns
            TableColumnModel columnModel = table1.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(25);
            columnModel.getColumn(1).setPreferredWidth(50);
            columnModel.getColumn(2).setPreferredWidth(55);
            columnModel.getColumn(3).setPreferredWidth(55);
            columnModel.getColumn(6).setPreferredWidth(40);
            columnModel.getColumn(8).setPreferredWidth(25);
            columnModel.getColumn(11).setPreferredWidth(50);
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cell = new javax.swing.JLabel();
        oldPaid = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jLabel1.setText("  Due Report");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 575, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(26, 26, 26)
                .addComponent(search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(search_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 70));

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
        table1.setFont(new java.awt.Font("Sitka Subheading", 3, 12)); // NOI18N
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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 300));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1060, 300));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel3.setText("Date");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 50, -1));

        jTextField1.setEditable(false);
        jPanel4.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 160, -1));

        jLabel4.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel4.setText("Remaining due");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        jTextField2.setEditable(false);
        jPanel4.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 150, -1));

        jLabel5.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel5.setText("Due");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 50, -1));

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        jPanel4.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 160, -1));

        jTextField4.setEditable(false);
        jPanel4.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 260, 40));

        jLabel6.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel6.setText("Customer Name");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        cell.setBackground(new java.awt.Color(0, 102, 102));
        cell.setForeground(new java.awt.Color(0, 102, 102));
        jPanel4.add(cell, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 40, 20));

        oldPaid.setBackground(new java.awt.Color(0, 102, 102));
        oldPaid.setForeground(new java.awt.Color(0, 102, 102));
        jPanel4.add(oldPaid, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 50, 10));

        jLabel7.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel7.setText("Payed");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 50, -1));

        date.setEditable(false);
        date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dateKeyTyped(evt);
            }
        });
        jPanel4.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 160, -1));

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Sitka Subheading", 3, 48)); // NOI18N
        jButton1.setText("Pay In");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 140));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 1060, 170));

        setSize(new java.awt.Dimension(1072, 579));
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

        if (evt.getClickCount() == 2) {
            TableModel model1 = table1.getModel();
            int indexs[] = table1.getSelectedRows();
            Object[] row = new Object[14];
            PayedDueReport pdr = new PayedDueReport();
            DefaultTableModel model2 = (DefaultTableModel) pdr.table1.getModel();
            for (int i = 0; i < indexs.length; i++) {
                row[0] = model1.getValueAt(indexs[i], 0);
                row[1] = model1.getValueAt(indexs[i], 1);
                row[2] = model1.getValueAt(indexs[i], 2);
                row[3] = model1.getValueAt(indexs[i], 3);
                row[4] = model1.getValueAt(indexs[i], 4);
                row[5] = model1.getValueAt(indexs[i], 5);
                row[6] = model1.getValueAt(indexs[i], 6);
                row[7] = model1.getValueAt(indexs[i], 7);
                row[8] = model1.getValueAt(indexs[i], 8);
                row[9] = model1.getValueAt(indexs[i], 9);
                row[10] = model1.getValueAt(indexs[i], 10);
//            row[11] = model1.getValueAt(indexs[i],11);       
                model2.addRow(row);
            }
            pdr.setVisible(true);
        } else {
            int i = table1.getSelectedRow();
            dm = (DefaultTableModel) table1.getModel();
            jTextField1.setText(dm.getValueAt(i, 10).toString());
            cell.setText(dm.getValueAt(i, 0).toString());
//        oldPaid.setText(dm.getValueAt(i, 11).toString());
            jTextField2.setText(dm.getValueAt(i, 11).toString());
            date.setText(dm.getValueAt(i, 3).toString());
        }
    }//GEN-LAST:event_table1MouseClicked

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
        Double a = Double.parseDouble(jTextField2.getText());
        Double b = Double.parseDouble(jTextField3.getText());
        Double remaining = a - b;
        remaining = Double.parseDouble(new DecimalFormat("##.##").format(remaining));
        if (remaining < 0) {
            JOptionPane.showMessageDialog(null, "Cannot pay");
            fetch();
            clear();
        } else {
            jTextField4.setText("" + remaining);
        }

    }//GEN-LAST:event_jTextField3KeyReleased
    //pay validation

    public boolean validationPay() {
        if (jTextField1.getText().equals("")) {
            return false;
        }
        if (jTextField2.getText().equals("")) {
            return false;
        }
        return true;
    }

    public boolean validationPayed() {
        if (jTextField3.getText().equals("")) {
            return false;
        }
        return true;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:  
        if (!validationPay()) {
            JOptionPane.showMessageDialog(null, "Please select a list from table");
            return;
        }
        if (!validationPayed()) {
            JOptionPane.showMessageDialog(null, "Please enter payed amount");
            return;
        }
        Double v = Double.parseDouble(jTextField2.getText());
//        JOptionPane.showMessageDialog(null,v);
        if (v <= 0) {
            JOptionPane.showMessageDialog(null, "No due left now");
            fetch();
            clear();

        } else {

            int i = table1.getSelectedRow();
//                JOptionPane.showMessageDialog(null,i);
            dm = (DefaultTableModel) table1.getModel();
            String dueId = dm.getValueAt(i, 0).toString();
            String itemNo = dm.getValueAt(i, 1).toString();
            String itemPrice = dm.getValueAt(i, 2).toString();
            String sellDate = dm.getValueAt(i, 3).toString();
            String itemName = dm.getValueAt(i, 4).toString();
            String itemModel = dm.getValueAt(i, 5).toString();
            String quantity = dm.getValueAt(i, 6).toString();
            String total = dm.getValueAt(i, 7).toString();
            String discount = dm.getValueAt(i, 8).toString();
            String totalAmount = dm.getValueAt(i, 9).toString();
            String customerName = dm.getValueAt(i, 10).toString();
//                String length = dm.getValueAt(i,12).toString();

            String sql1 = "insert into daybook(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due,cash,non_cash,purchase_cash) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                pst = conn.prepareStatement(sql1);
                pst.setString(1, itemNo);
                pst.setString(2, itemPrice);
                pst.setString(3, date.getText());
                pst.setString(4, itemName);
                pst.setString(5, itemModel);
                pst.setString(6, quantity);
                pst.setString(7, total);
                pst.setString(8, discount);
                pst.setString(9, total);
                pst.setString(10, customerName);
                pst.setString(11, jTextField3.getText());
                pst.setString(12, jTextField4.getText());
                pst.setString(13, jTextField3.getText());
                pst.setString(14, "0");
                pst.setString(15, "0");
                pst.execute();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            String sql12 = "insert into due_paid(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due,item_payed_date) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                pst = conn.prepareStatement(sql12);
                pst.setString(1, itemNo);
                pst.setString(2, itemPrice);
                pst.setString(3, sellDate);
                pst.setString(4, itemName);
                pst.setString(5, itemModel);
                pst.setString(6, quantity);
                pst.setString(7, total);
                pst.setString(8, discount);
                pst.setString(9, total);
                pst.setString(10, customerName);
                pst.setString(11, jTextField3.getText());
                pst.setString(12, jTextField4.getText());
                pst.setString(13, date.getText());

//                java.util.Date utilDate = date.getDate();
//               java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());               
                pst.execute();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            Double cel = Double.parseDouble(cell.getText());
//           Double oldPayed = Double.parseDouble(oldPaid.getText());
//           Double newPayed = Double.parseDouble(jTextField3.getText());
//           Double newPaid = oldPayed + newPayed;
//           newPaid = Double.parseDouble(new DecimalFormat("##.##").format(newPaid));

            String sql = "update due set "
                    //                    + "paid= '" + newPaid + "' ,"
                    + "due ='" + jTextField4.getText() + "' "
                    + "where id = " + cel;
            try {
                pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Payed Succesfully");
                fetch();
                clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyTyped

    private void dateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_dateKeyReleased

    private void dateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dateKeyTyped

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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DueSale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cell;
    private javax.swing.JTextField date;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel oldPaid;
    private javax.swing.JTextField search_text;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
