
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
//import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
//import javax.swing.table.TableColumnModel;
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
public class Daybook extends javax.swing.JFrame {

    DefaultTableModel dm;
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    /**
     * Creates new form Dreport
     */
    public Daybook() {
        super("DayBook");
        initComponents();
        conn = javaconnect.getConnection();
        fetch();
        clock();
        getSum();
    }

    //search engine
    private void filter(String query) {
        dm = (DefaultTableModel) table1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        table1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
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
//        JOptionPane.showMessageDialog(null, Date);
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
//        JOptionPane.showMessageDialog(null, currentEngDate);
        Calendar baseEngDate = new GregorianCalendar();
//        JOptionPane.showMessageDialog(null, baseEngDate);
        baseEngDate.set(startingEngYear, startingEngMonth, startingEngDay);
        long totalEngDaysCount = daysBetween(baseEngDate, currentEngDate);
//        long totalengday = totalEngDaysCount + 3;
//        JOptionPane.showMessageDialog(null, totalEngDaysCount);

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

        date_txt.setText(newDate);
    }

    public void date() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
    }

    public void fetch() {
        try {
            String date = date_txt.getText();
            JTableHeader header = table1.getTableHeader();
            header.setForeground(Color.red);
            header.setFont(new Font("Tahome", Font.ITALIC, 12));
            String q = "select item_name as Item,item_model as 'Item Model',item_price as Price,item_quantity as Qty,item_total as 'Total',item_discount_percent as 'Vat%',item_discount_total as 'Total Price',item_buy_date as 'Buy Date',item_sell_date as 'Sell Date',customer_name as 'CName',paid as 'Paid',due as 'Due',purchase_cash as 'Purchase Cash',cash as 'Cash',non_cash as 'Non Cash',bill_no as 'BillNo' from daybook where item_sell_date = '" + date + "' or item_buy_date ='" + date + "' ";
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
//            //increasing the width of columns
            TableColumnModel columnModel = table1.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(50);
            columnModel.getColumn(1).setPreferredWidth(60);
//            columnModel.getColumn(1).setPreferredWidth(50);
            columnModel.getColumn(3).setPreferredWidth(50);
            columnModel.getColumn(4).setPreferredWidth(60);
            columnModel.getColumn(5).setPreferredWidth(25);
//
            columnModel.getColumn(6).setPreferredWidth(70);
//
            columnModel.getColumn(9).setPreferredWidth(90);
            columnModel.getColumn(7).setPreferredWidth(80);
//
            columnModel.getColumn(8).setPreferredWidth(80);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Item not Fetched");
        }
    }

//    public void bank() {
//        String sqlbank = "select MAX(deposit) as deposit from bank_transaction where transaction_type ='Deposit' and date='" + date_txt.getText() + "' ";
//        try {
//            pst = conn.prepareStatement(sqlbank);
//            rs = pst.executeQuery(sqlbank);
//            rs.next();
//            Double oo = rs.getDouble("deposit");
//            oo = Double.parseDouble(new DecimalFormat("##.##").format(oo));
//            deposit.setText("" + oo);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//
//        String sqlbank1 = "select MAX(withdraw) as withdraw from bank_transaction where transaction_type ='Withdraw' and date='" + date_txt.getText() + "' ";
//        try {
//            pst = conn.prepareStatement(sqlbank1);
//            rs = pst.executeQuery(sqlbank1);
//            rs.next();
//            Double oo = rs.getDouble("withdraw");
//            oo = Double.parseDouble(new DecimalFormat("##.##").format(oo));
//            withdraw.setText("" + oo);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
//    public void remittance() {
//        String sqlbank1 = "select MAX(totalsent) as tsent from remittance where date='" + date_txt.getText() + "' ";
//        try {
//            pst = conn.prepareStatement(sqlbank1);
//            rs = pst.executeQuery(sqlbank1);
//            rs.next();
//            Double oo = rs.getDouble("tsent");
//            oo = Double.parseDouble(new DecimalFormat("##.##").format(oo));
//            remittancesend.setText("" + oo);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//
//        String sqlbank2 = "select MAX(totalreceive) as treceive from remittance where date='" + date_txt.getText() + "' ";
//        try {
//            pst = conn.prepareStatement(sqlbank2);
//            rs = pst.executeQuery(sqlbank2);
//            rs.next();
//            Double oo = rs.getDouble("treceive");
//            oo = Double.parseDouble(new DecimalFormat("##.##").format(oo));
//            remittancereceive.setText("" + oo);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
    //display sum
    public int getSum() {
        //showing into opening stock field
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
//        yestarday date
        cal.add(Calendar.DAY_OF_MONTH, -1);
        //day befor yesterday
        Calendar call = Calendar.getInstance();
        call.add(Calendar.DAY_OF_MONTH, -2);
        String dby = sdf.format(call.getTime());
//        JOptionPane.showMessageDialog(null,dby);
        //Date after adding one day to the current date
        String newD = sdf.format(cal.getTime());
        String sqlDate = "select cash from daybook where item_sell_date ='" + newD + "' or item_buy_date='" + newD + "'";
        try {
            pst = conn.prepareStatement(sqlDate);
            rs = pst.executeQuery(sqlDate);
            //hijoko din ma cash xoina bhane
            if (rs.next() == false) {
//                JOptionPane.showMessageDialog(null,"inside hijoko din");
                String sqlDate1 = "select cash from daybook where item_sell_date ='" + dby + "' or item_buy_date='" + dby + "'";
                try {
                    pst = conn.prepareStatement(sqlDate1);
                    rs = pst.executeQuery(sqlDate1);
//                    JOptionPane.showMessageDialog(null,sqlDate1);
                    //astiko din ko cash xoina bhane
                    if (rs.next() == false) {
//                        JOptionPane.showMessageDialog(null,"inside astiko din data nahuda");
                        //if no opening stock then
                        ostock.setText("0");
                        //updating today opening stock
                        int rowsCount = table1.getRowCount();
                        Double sum = 0.0;
                        Double sum1 = 0.0;
                        Double sum2 = 0.0;
                        for (int i = 0; i < rowsCount; i++) {

                            sum = sum + Double.parseDouble(table1.getValueAt(i, 12).toString());
                            sum1 = sum1 + Double.parseDouble(table1.getValueAt(i, 13).toString());
                            sum2 = sum2 + Double.parseDouble(table1.getValueAt(i, 14).toString());
                        }
                        Double openings = Double.parseDouble(ostock.getText());
//                sum = openings + sum;         
                        Double purchase = sum;
                        Double sale = sum1;
                        Double TodayStock = sale - purchase;
                        cash.setText(Double.toString(TodayStock));
                        noncash.setText(Double.toString(sum2));
//                purchasecash.setText(Double.toString(sum2));
                        Double newstock = Double.parseDouble(ostock.getText()) + TodayStock;
                        String daybook = "update daybook set o_stock = '" + newstock + "' where item_sell_date = '" + date_txt.getText() + "' or item_buy_date='" + date_txt.getText() + "'";
                        try {
                            pst = conn.prepareStatement(daybook);
                            pst.execute();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    } else {
                        do {
//                            JOptionPane.showMessageDialog(null,"inside astiko din data huda ");
                            //showing into opening stock field
                            SimpleDateFormat sdff = new SimpleDateFormat("yyyy/MM/dd");
                            Calendar call12 = Calendar.getInstance();
//        Subtracting 1 Day to the current date
                            call12.add(Calendar.DAY_OF_MONTH, -2);

                            //Date after adding one day to the current date
                            String newDatee = sdff.format(call12.getTime());
                            String sqlDatee = "select MAX(o_stock) as o_stock from daybook where item_sell_date ='" + newDatee + "' or item_buy_date='" + newDatee + "'";
                            try {
//                                JOptionPane.showMessageDialog(null,sqlDatee);
                                pst = conn.prepareStatement(sqlDatee);
                                rs = pst.executeQuery(sqlDatee);
                                rs.next();
                                Double oo = rs.getDouble("o_stock");
                                oo = Double.parseDouble(new DecimalFormat("##.##").format(oo));
                                ostock.setText("" + oo);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }

                            //getting today cash sum
                            int rowsCount = table1.getRowCount();
                            Double sum = 0.0;
                            Double sum1 = 0.0;
                            Double sum2 = 0.0;
                            for (int i = 0; i < rowsCount; i++) {

                                sum = sum + Double.parseDouble(table1.getValueAt(i, 12).toString());
                                sum1 = sum1 + Double.parseDouble(table1.getValueAt(i, 13).toString());
                                sum2 = sum2 + Double.parseDouble(table1.getValueAt(i, 14).toString());
                            }
                            Double openings = Double.parseDouble(ostock.getText());
//                sum = openings + sum;         
                            Double purchase = sum;
                            Double sale = sum1;
                            Double TodayStock = sale - purchase;
                            cash.setText(Double.toString(TodayStock));
                            noncash.setText(Double.toString(sum2));
//                purchasecash.setText(Double.toString(sum2));
                            Double newstock = Double.parseDouble(ostock.getText()) + TodayStock;
                            String daybook = "update daybook set o_stock = '" + newstock + "' where item_sell_date = '" + date_txt.getText() + "' or item_buy_date='" + date_txt.getText() + "'";
                            try {
                                pst = conn.prepareStatement(daybook);
                                pst.execute();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                        } while (rs.next());

                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
//
//                //if no opening stock then
//                ostock.setText("0");
//                //updating today opening stock
//                int rowsCount = table1.getRowCount();
//                Double sum = 0.0;
//                Double sum1 = 0.0;
//                Double sum2 = 0.0;
//                for (int i = 0; i < rowsCount; i++) {
//
//                    sum = sum + Double.parseDouble(table1.getValueAt(i, 12).toString());
//                    sum1 = sum1 + Double.parseDouble(table1.getValueAt(i, 13).toString());
//                    sum2 = sum2 + Double.parseDouble(table1.getValueAt(i, 14).toString());
//                }
//                Double openings = Double.parseDouble(ostock.getText());
////                sum = openings + sum;         
//                Double purchase = sum;
//                Double sale = sum1;
//                Double TodayStock = sale - purchase;
//                cash.setText(Double.toString(TodayStock));
//                noncash.setText(Double.toString(sum2));
////                purchasecash.setText(Double.toString(sum2));
//                Double newstock = Double.parseDouble(ostock.getText()) + TodayStock;
//                String daybook = "update daybook set o_stock = '" + newstock + "' where item_sell_date = '" + date_txt.getText() + "' or item_buy_date='" + date_txt.getText() + "'";
//                try {
//                    pst = conn.prepareStatement(daybook);
//                    pst.execute();
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(null, e);
//                }
            } else {
                //if opening stock found
                do {
                    //showing into opening stock field
                    SimpleDateFormat sdff = new SimpleDateFormat("yyyy/MM/dd");
                    Calendar call1 = Calendar.getInstance();
//        Subtracting 1 Day to the current date
                    call1.add(Calendar.DAY_OF_MONTH, -1);

                    //Date after adding one day to the current date
                    String newDatee = sdff.format(call1.getTime());
                    String sqlDatee = "select MAX(o_stock) as o_stock from daybook where item_sell_date ='" + newDatee + "' or item_buy_date='" + newDatee + "'";
                    try {
                        pst = conn.prepareStatement(sqlDatee);
                        rs = pst.executeQuery(sqlDatee);
                        rs.next();
                        Double oo = rs.getDouble("o_stock");
                        oo = Double.parseDouble(new DecimalFormat("##.##").format(oo));
                        ostock.setText("" + oo);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                    //getting today cash sum
                    int rowsCount = table1.getRowCount();
                    Double sum = 0.0;
                    Double sum1 = 0.0;
                    Double sum2 = 0.0;
                    for (int i = 0; i < rowsCount; i++) {

                        sum = sum + Double.parseDouble(table1.getValueAt(i, 12).toString());
                        sum1 = sum1 + Double.parseDouble(table1.getValueAt(i, 13).toString());
                        sum2 = sum2 + Double.parseDouble(table1.getValueAt(i, 14).toString());
                    }
                    Double openings = Double.parseDouble(ostock.getText());
//                sum = openings + sum;         
                    Double purchase = sum;
                    Double sale = sum1;
                    Double TodayStock = sale - purchase;
                    cash.setText(Double.toString(TodayStock));
                    noncash.setText(Double.toString(sum2));
//                purchasecash.setText(Double.toString(sum2));
                    Double newstock = Double.parseDouble(ostock.getText()) + TodayStock;
                    String daybook = "update daybook set o_stock = '" + newstock + "' where item_sell_date = '" + date_txt.getText() + "' or item_buy_date='" + date_txt.getText() + "'";
                    try {
                        pst = conn.prepareStatement(daybook);
                        pst.execute();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                } while (rs.next());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Item not Fetched");
        }
        return 0;
    }

    public void updateOStock() {
        Double openings = Double.parseDouble(ostock.getText());
        Double nostock = Double.parseDouble(ostock.getText()) + Double.parseDouble(cash.getText());
//        Double a = Double.parseDouble(purchasecash.getText());
//        Double b = Double.parseDouble(deposit.getText());
//        Double c = Double.parseDouble(withdraw.getText());
//        Double d = Double.parseDouble(remittancesend.getText());
//        Double ee = Double.parseDouble(remittancereceive.getText());
//        nostock = nostock - a - b + c - d + ee;
        label.setText(Double.toString(nostock));
//                    JOptionPane.showMessageDialog(null,nostock);
        String daybook = "update daybook set o_stock = '" + nostock + "' where item_sell_date = '" + date_txt.getText() + "'";
        try {
            pst = conn.prepareStatement(daybook);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
        date_txt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        search_text = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ostock = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        noncash = new javax.swing.JLabel();
        cash = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        date_txt.setEditable(false);
        date_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                date_txtKeyReleased(evt);
            }
        });
        jPanel1.add(date_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 119, -1));

        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel1.setText("Opening stock");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 100, 30));

        jLabel2.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel2.setText("Date");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 47, -1));

        search_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_textKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_textKeyReleased(evt);
            }
        });
        jPanel1.add(search_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 140, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel12.setText("Search");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 70, -1));

        ostock.setEditable(false);
        ostock.setFont(new java.awt.Font("Sitka Heading", 3, 12)); // NOI18N
        ostock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ostockKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ostockKeyReleased(evt);
            }
        });
        jPanel1.add(ostock, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 140, -1));

        jLabel11.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jLabel11.setText("DayBook");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 130, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        table1.setFont(new java.awt.Font("Lucida Bright", 3, 12)); // NOI18N
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 440));

        jPanel3.setBackground(new java.awt.Color(240, 207, 249));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jLabel3.setText("Sale Cash");

        jLabel4.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N

        noncash.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N

        cash.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jLabel6.setText("Sale Non Cash");

        label.setFont(new java.awt.Font("Sitka Heading", 3, 36)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(cash, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(396, 396, 396)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(noncash, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cash, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(noncash, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1368, 623));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void date_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_date_txtKeyReleased
        // TODO add your handling code here:        
    }//GEN-LAST:event_date_txtKeyReleased

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        fetch();
        getSum();
//        bank();
//        remittance();
        updateOStock();
        jLabel3.setVisible(false);
        cash.setVisible(false);
    }//GEN-LAST:event_formWindowActivated

    private void search_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_textKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_textKeyPressed

    private void search_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_textKeyReleased
        // TODO add your handling code here:
        String query = search_text.getText();
        filter(query);
        getSum();
    }//GEN-LAST:event_search_textKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void ostockKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ostockKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ostockKeyPressed

    private void ostockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ostockKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ostockKeyReleased

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
            java.util.logging.Logger.getLogger(Daybook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Daybook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Daybook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Daybook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Daybook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cash;
    private javax.swing.JTextField date_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel noncash;
    private javax.swing.JTextField ostock;
    private javax.swing.JTextField search_text;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
