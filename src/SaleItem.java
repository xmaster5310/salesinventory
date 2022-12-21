
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.Connection;
//import java.sql.Date;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
public class SaleItem extends javax.swing.JFrame {

    DefaultTableModel dm;
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    /**
     * Creates new form SaleItem
     */
    public SaleItem() {
        super("SaleItem");
        initComponents();
        conn = javaconnect.getConnection();
//        fetch();
        clock();
        fillItemCombo();
        fillcostumer();
        vat.setVisible(false);
        alltotal.setVisible(false);
        billno.setVisible(true);
    }

//        // selling date validation    
//    public boolean validationSold() {
//        if (jDateChooser1.getDate()== null) {
//            return false;
//        }       
//        return true;
//    }
    public long daysBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    //    adding quantity sum for graphs
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
        newdate.setText(newDate);
    }

    public void quantitysum() {
        String sqlll = "select sum(item_quantity) as item_quantity from sale where item_sell_date ='" + newdate.getText() + "'";
        try {
            pst = conn.prepareStatement(sqlll);
            rs = pst.executeQuery(sqlll);
            rs.next();
            JOptionPane.showMessageDialog(null,rs);
            String a = rs.getString("item_quantity");
            String newssql = "update sale set sum_quantity='" + a + "'"
                    + "where item_sell_date ='" + newdate.getText() + "'";
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

    public void fillitemdetail() {
        String itemCategory = (String) item.getSelectedItem();
        String itemmodel = (String) model.getSelectedItem();
        String sql = "select * from item where item_category ='" + itemCategory + "' and item_model='" + itemmodel + "'";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String itemNumber = rs.getString("item_no");
                itemNo.setText(itemNumber);
//                String itemPrice = rs.getString("item_buying_price");
//                price.setText(itemPrice);  
                String itemStock = rs.getString("item_quantity");
                totalstock.setText(itemStock);
                String id = rs.getString("id");
            }
            if (totalstock.getText().equals(0)) {
                JOptionPane.showMessageDialog(null, "finished");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void fillcostumer() {
//        String itemCategory = (String) item.getSelectedItem();
//        String itemmodel = (String) model.getSelectedItem();
        String sql = "select * from customer";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String costumername = rs.getString("customer_name");
                jComboBox3.addItem(costumername);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//    public void clock() {
//        Calendar cal = new GregorianCalendar();
//        int month = cal.get(Calendar.MONTH);
//        int year = cal.get(Calendar.YEAR);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        selldate.setText(year + "-" + (month + 1) + "-" + day);
//    }
    //quantity validation
    public boolean validationQuantity() {
        if (quantity.getText().equals("")) {
            return false;
        }
        return true;
    }

    public boolean validationItemList() {
        if (item.getSelectedItem() == "Select an item") {
            return false;
        }
        return true;
    }

    public void fetch() {
        try {
            JTableHeader header = jTable1.getTableHeader();
            header.setForeground(Color.red);
            header.setFont(new Font("Tahome", Font.ITALIC, 12));
            String q = "select customer_name as CName,item_price as 'Item Price',item_sell_date as 'Sell Date',item_name as 'ItemName',item_model as 'ItemModel',item_quantity as 'Quantity',item_total as 'Total',item_discount_percent as 'Dis %',item_discount_total as 'Total Amt',paid as Paid,due as Due from sale ORDER BY id DESC";
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            //increasing the width of columns
            TableColumnModel columnModel = jTable1.getColumnModel();
//            columnModel.getColumn(0).setPreferredWidth(35);
            columnModel.getColumn(1).setPreferredWidth(65);
            columnModel.getColumn(2).setPreferredWidth(75);
            columnModel.getColumn(5).setPreferredWidth(55);
            columnModel.getColumn(6).setPreferredWidth(60);
            columnModel.getColumn(7).setPreferredWidth(40);

            columnModel.getColumn(8).setPreferredWidth(60);
            columnModel.getColumn(10).setPreferredWidth(60);
            columnModel.getColumn(11).setPreferredWidth(60);
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Item not Fetched");
        }
    }

    public void fillItemCombo() {
        try {
            String sql = "select DISTINCT item_category from item where item_quantity > 0";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                item.addItem(rs.getString("item_category"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//    public void fillCustomerCombo() {
//        try {
//            String sql = "select DISTINCT customer_name from customer";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                jComboBox2.addItem(rs.getString("customer_name"));
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
    public void fillItemModelCombo() {
        try {
            String itemCategory = (String) item.getSelectedItem();
            String sql = "select DISTINCT item_model from item where item_category ='" + itemCategory + "'";
//            JOptionPane.showMessageDialog(null,sql);
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                model.addItem(rs.getString("item_model"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//    public void fillItemlengthCombo() {
//        try {
//            String itemCategory = (String) item.getSelectedItem();
//            String itemModel = (String) model.getSelectedItem();
//            String sql = "select length from item where item_category ='" + itemCategory + "' and item_model='" + itemModel + "' ";
////            JOptionPane.showMessageDialog(null,sql);
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                model1.addItem(rs.getString("length"));
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
    public void clear() {
//        itemNo.setText("");
        price.setText("");
        paid.setText("");
        due.setText("");
//        itemNo.setText("");
//        totalstock.setText("");
        jTextField8.setText("");
        total.setText("");
        percent.setText("");
        discountamount.setText("");
        quantity.setText("");
        dueamount.setText("");
//        jComboBox2.setText("");
//          item.removeAllItems(); 
//        jDateChooser1.setDate(null);
//        item.setSelectedItem("Select an item");
//        model.setSelectedItem("Select model");
//        model1.setSelectedItem("Select length");
//        jComboBox1.setSelectedItem("Select Type to Pay");

    }

    public void clear1() {
//        itemNo.setText("");
        price.setText("");
        paid.setText("");
        due.setText("");
        itemNo.setText("");
        totalstock.setText("");
        jTextField8.setText("");
        total.setText("");
        percent.setText("");
        discountamount.setText("");
        quantity.setText("");
        dueamount.setText("");
        jComboBox2.setText("");
        jComboBox2.setEditable(true);
        selldate.setEditable(true);
        jComboBox3.setEditable(true);
        jComboBox3.setEnabled(true);
//        jButton2.setEnabled(false);

//          item.removeAllItems(); 
//        jDateChooser1.setDate(null);
//        item.setSelectedItem("Select an item");
//        model.setSelectedItem("Select model");
//        model1.setSelectedItem("Select length");
//        jComboBox1.setSelectedItem("Select Type to Pay");
    }

    public void clearcombo() {
        item.setSelectedItem("Select an item");
        model.setSelectedItem("Select model");
//        model1.setSelectedItem("Select length");
        jComboBox1.setSelectedItem("Select Type to Pay");
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
        item = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        quantity = new javax.swing.JTextField();
        model = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        dueamount = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        paid = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        due = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        itemNo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        totalstock = new javax.swing.JTextField();
        total = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        discountamount = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        percent = new javax.swing.JTextField();
        selldate = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        vat = new javax.swing.JLabel();
        alltotal = new javax.swing.JLabel();
        newdate = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        length = new javax.swing.JLabel();
        billno = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        item.setFont(new java.awt.Font("Tw Cen MT", 3, 12)); // NOI18N
        item.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select an item" }));
        item.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                itemPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                itemPopupMenuWillBecomeVisible(evt);
            }
        });
        item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                itemMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                itemMouseReleased(evt);
            }
        });
        item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemActionPerformed(evt);
            }
        });
        jPanel2.add(item, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 160, 20));

        jLabel6.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel6.setText("Quantity");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        quantity.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });
        quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quantityKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantityKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quantityKeyTyped(evt);
            }
        });
        jPanel2.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 140, -1));

        model.setFont(new java.awt.Font("Sitka Subheading", 3, 12)); // NOI18N
        model.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Category" }));
        model.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                modelPopupMenuCanceled(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                modelPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                modelPopupMenuWillBecomeVisible(evt);
            }
        });
        model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modelActionPerformed(evt);
            }
        });
        jPanel2.add(model, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 160, 20));

        jButton2.setBackground(new java.awt.Color(204, 204, 255));
        jButton2.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jButton2.setText("Add");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 100, 60));

        dueamount.setEditable(false);
        dueamount.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jPanel2.add(dueamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, 160, 50));

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel14.setText("Total Amount");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 110, -1));

        jLabel15.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel15.setText("Customer Name");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        paid.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        paid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paidKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                paidKeyTyped(evt);
            }
        });
        jPanel2.add(paid, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 150, -1));

        jLabel16.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel16.setText("Paid");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        due.setEditable(false);
        due.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jPanel2.add(due, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 160, 50));

        jLabel17.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jLabel17.setText("Amount Due");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, -1, -1));

        jComboBox2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox2KeyReleased(evt);
            }
        });
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 160, -1));

        jComboBox3.setFont(new java.awt.Font("Sitka Heading", 3, 12)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Customer" }));
        jComboBox3.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox3PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox3PopupMenuWillBecomeVisible(evt);
            }
        });
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 160, -1));

        jComboBox1.setFont(new java.awt.Font("Sitka Heading", 3, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Type to Pay", "Due Amount" }));
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeVisible(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 160, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, 590, 220));

        jLabel2.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jLabel2.setText("Item Detail");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 120, -1));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel3.setText("Item Number");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        itemNo.setEditable(false);
        itemNo.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        itemNo.setEnabled(false);
        jPanel3.add(itemNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 42, 120, -1));

        jLabel4.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel4.setText("Price");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 22, -1, -1));

        jLabel5.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel5.setText("Sold Date");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(386, 22, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Total");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 50, -1));

        jLabel8.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel8.setText("Total Stock");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 100, -1));

        totalstock.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        totalstock.setEnabled(false);
        jPanel3.add(totalstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 130, -1));

        total.setEditable(false);
        total.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jPanel3.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 232, 95));

        jLabel9.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel9.setText("%");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, -1, -1));

        price.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });
        price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                priceKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                priceKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                priceKeyTyped(evt);
            }
        });
        jPanel3.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 130, -1));

        discountamount.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        discountamount.setEnabled(false);
        discountamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountamountActionPerformed(evt);
            }
        });
        jPanel3.add(discountamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 80, -1));

        jLabel10.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel10.setText("Amount");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, -1, -1));

        jLabel11.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel11.setText("Vat");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 50, -1));

        jLabel13.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jLabel13.setText("Availaible Stock");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 120, -1));

        jTextField8.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jTextField8.setEnabled(false);
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 130, -1));

        percent.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        percent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                percentActionPerformed(evt);
            }
        });
        percent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                percentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                percentKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                percentKeyTyped(evt);
            }
        });
        jPanel3.add(percent, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 80, -1));

        selldate.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        selldate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selldateActionPerformed(evt);
            }
        });
        selldate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                selldateKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                selldateKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                selldateKeyTyped(evt);
            }
        });
        jPanel3.add(selldate, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 130, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 570, 220));

        jLabel7.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jLabel7.setText("Item Manager");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 300, 120, -1));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jLabel12.setText("Item Order Menu");

        newdate.setEditable(false);
        newdate.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newdate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel12)
                .addGap(27, 27, 27)
                .addComponent(vat, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(alltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(545, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(alltotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(newdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(vat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 40));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setFont(new java.awt.Font("Sitka Subheading", 3, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Type", "Category", "Quantity", "Price", "Total", "Vat amount", "Customer Name", "Total", "Sell Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1180, 230));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1180, 250));

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jButton1.setText("Generate Receipt");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 290, -1, 40));

        jButton4.setBackground(new java.awt.Color(204, 204, 255));
        jButton4.setFont(new java.awt.Font("Sitka Subheading", 3, 18)); // NOI18N
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 290, 80, 40));

        length.setForeground(new java.awt.Color(204, 204, 204));
        getContentPane().add(length, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 304, 60, 20));
        getContentPane().add(billno, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 120, 20));

        setSize(new java.awt.Dimension(1193, 595));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_itemMouseClicked

    private void itemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemMousePressed

    private void modelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modelActionPerformed
        // TODO add your handling code here: 
//        model1.removeAllItems();

    }//GEN-LAST:event_modelActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusGained

    private void itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemActionPerformed
        // TODO add your handling code here: 
        model.removeAllItems();
//        model1.removeAllItems();
        fillItemModelCombo();
    }//GEN-LAST:event_itemActionPerformed

    private void modelPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_modelPopupMenuWillBecomeVisible
        // TODO add your handling code here:     
    }//GEN-LAST:event_modelPopupMenuWillBecomeVisible

    private void modelPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_modelPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
//        model1.removeAllItems();
//        fillItemlengthCombo();
        fillitemdetail();
    }//GEN-LAST:event_modelPopupMenuWillBecomeInvisible
//validation  for quantity without selecting item at first

    public boolean validation1() {
        if (item.getSelectedItem() == "Select an item") {
            return false;
        }
        return true;
    }

    //price validation
    public boolean validationPrice() {
        if (price.getText().equals("")) {
            return false;
        }
        return true;
    }

    private void quantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyReleased
        // TODO add your handling code here:                
        if (!validation1()) {
            JOptionPane.showMessageDialog(null, "Please select an item first");
            quantity.setText("");
            return;
        }
        if (!validationPrice()) {
            JOptionPane.showMessageDialog(null, "Please enter price ");
            quantity.setText("");
            return;
        }
        String itemCategory = (String) item.getSelectedItem();
        String itemmodel = (String) model.getSelectedItem();
//        String length = (String) model1.getSelectedItem();
        String sql = "select * from item where item_category ='" + itemCategory + "' and item_model='" + itemmodel + "'";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                Double itemStock = rs.getDouble("item_quantity");
//                JOptionPane.showMessageDialog(null,itemStock);
                Double Qty = Double.parseDouble(quantity.getText());
                Double availaibleQty = itemStock - Qty;
                String itemnoo = rs.getString("item_no");
                itemNo.setText(itemnoo);
//                JOptionPane.showMessageDialog(null,availaibleQty);
                if (availaibleQty < 0) {
                    JOptionPane.showMessageDialog(null, "No Stock Left");
                    quantity.setText("");
                    jTextField8.setText("");
                    total.setText("");
                    dueamount.setText("");
                    price.setText("");
                    totalstock.setText("");
                } else {
//                availaibleQty = Integer.parseInt(new DecimalFormat("##.##").format(availaibleQty));
                    jTextField8.setText("" + availaibleQty);

                    Double qty, buyingprice, totalprice;
                    qty = Double.parseDouble(quantity.getText());
                    buyingprice = Double.parseDouble(price.getText());
                    totalprice = qty * buyingprice;
                    totalprice = Double.parseDouble(new DecimalFormat("##.##").format(totalprice));
                    total.setText("" + totalprice);
                    dueamount.setText("" + totalprice);
                    discountamount.setText("");
                    paid.setText("");
                    totalstock.setText("" + availaibleQty);
//                price.setText("");
                    due.setText("");
                }
            }
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"null value");
        }
    }//GEN-LAST:event_quantityKeyReleased

    private void itemPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_itemPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_itemPopupMenuWillBecomeInvisible

    //price validation
    public boolean validationqtyy() {
        if (item.getSelectedItem() == "Select an item") {
            return false;
        }
        return true;
    }
    private void priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceKeyReleased
        // TODO add your handling code here:
        if (!validationqtyy()) {
            JOptionPane.showMessageDialog(null, "Please select an item first");
            price.setText("");
            return;
        }
    }//GEN-LAST:event_priceKeyReleased

    public void billNo() {
        String bbb = "select max(bill_no) as 'id' from bill";
        try {
            pst = conn.prepareStatement(bbb);
            rs = pst.executeQuery(bbb);
            if (rs.next()) {
//                String ccc = "select max(bill_no) as 'bid' from bill";
//                try {
//                    pst = conn.prepareStatement(ccc);
//                    rs = pst.executeQuery(ccc);
                int x = rs.getInt("id");
                x = x + 1;
                String xx = String.valueOf(x);
                billno.setText(xx);
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(null, e);
//                }               
            } else {
                billno.setText("1");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (jTable1.getRowCount() <= 0) {
                JOptionPane.showMessageDialog(null, "No Data Added");
            } else {
                jButton2.setEnabled(false);
                billNo();
                int price, itemquantity;
                String selldate, itemname, itemmodel, customername;
                Double itemtotal, vatamount, total;
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    price = Integer.parseInt(jTable1.getValueAt(i, 3).toString());
                    selldate = jTable1.getValueAt(i, 8).toString();
                    itemname = jTable1.getValueAt(i, 0).toString();
                    itemmodel = jTable1.getValueAt(i, 1).toString();
                    itemquantity = Integer.parseInt(jTable1.getValueAt(i, 2).toString());
                    itemtotal = Double.parseDouble(jTable1.getValueAt(i, 4).toString());
                    vatamount = Double.parseDouble(jTable1.getValueAt(i, 5).toString());
                    customername = jTable1.getValueAt(i, 6).toString();
                    total = Double.parseDouble(jTable1.getValueAt(i, 7).toString());
                    String sql11 = "select customer_address,customer_pan_no from customer where customer_name='" + customername + "' ";
                    try {
                        pst = conn.prepareStatement(sql11);
                        rs = pst.executeQuery();
                        if (rs.next() == true) {
                            String customeraddress = rs.getString("customer_address");
                            String customerpan = rs.getString("customer_pan_no");
                            String sql1 = "insert into bill(item_price,item_sell_date,item_name,item_model,item_quantity,item_total,vat_amount,total,customer_name,bill_no,vat_total,amount_total,customer_address,customer_pan_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                            try {
                                pst = conn.prepareStatement(sql1);
                                pst.setInt(1, price);
                                pst.setString(2, selldate);
                                pst.setString(3, itemname);
                                pst.setString(4, itemmodel);
                                pst.setInt(5, itemquantity);
                                pst.setDouble(6, itemtotal);
                                pst.setDouble(7, vatamount);
                                pst.setDouble(8, total);
                                pst.setString(9, customername);
                                pst.setString(10, billno.getText());
                                pst.setString(11, (String) vat.getText());
                                pst.setString(12, (String) alltotal.getText());
                                pst.setString(13, customeraddress);
                                pst.setString(14, customerpan);
                                pst.execute();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                        } else {

                            String sql1 = "insert into bill(item_price,item_sell_date,item_name,item_model,item_quantity,item_total,vat_amount,total,customer_name,bill_no,vat_total,amount_total,customer_address,customer_pan_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                            try {
                                pst = conn.prepareStatement(sql1);
                                pst.setInt(1, price);
                                pst.setString(2, selldate);
                                pst.setString(3, itemname);
                                pst.setString(4, itemmodel);
                                pst.setInt(5, itemquantity);
                                pst.setDouble(6, itemtotal);
                                pst.setDouble(7, vatamount);
                                pst.setDouble(8, total);
                                pst.setString(9, customername);
                                pst.setString(10, billno.getText());
                                pst.setString(11, (String) vat.getText());
                                pst.setString(12, (String) alltotal.getText());
                                pst.setString(13, "");
                                pst.setString(14, "");
                                pst.execute();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                }

                // TODO add your handling code here:
                HashMap m = new HashMap();
                JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Damak sales\\src\\showbill.jrxml");
                JasperReport ireport = JasperCompileManager.compileReport(jdesign);
                JasperPrint jprint = JasperFillManager.fillReport(ireport, m, conn);
                JasperViewer.viewReport(jprint, false);

                try {
                    dm = (DefaultTableModel) jTable1.getModel();
                    JRTableModelDataSource datasource = new JRTableModelDataSource(dm);
                    String reportSource = "./showbill.jrxml";
                    JasperReport jr = JasperCompileManager.compileReport(reportSource);
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("title1", "title 1");
                    JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
                    JasperViewer.viewReport(jp, false);
                    clear1();
                    clear();
                    billno.setVisible(false);
                    jButton2.setEnabled(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JRException ex) {
            Logger.getLogger(SaleItem.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        
         //update sale bill no value
            String sql2 = "update sale set "
                    + "bill_no ='" + billno.getText() + "'"
                    + "where bill_no = " + 0;
            try {
                pst = conn.prepareStatement(sql2);
                pst.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    //paid validation
    public boolean validationPaidd() {
        if (paid.getText().equals("")) {
            return false;
        }
        return true;
    }

    public boolean validationVat() {
        if (percent.getText().equals("")) {
            return false;
        }
        return true;
    }

    public boolean validationDate() {
        if (selldate.getText().equals("")) {
            return false;
        }
        return true;
    }

    //price validation
    public boolean validationCustomer() {
        if (jComboBox2.getText().equals("") && jComboBox3.getSelectedItem() == "Select Customer") {
            return false;
        }
        return true;
    }

    public static boolean valDOB(String DOB) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date BOD = null;
        df.setLenient(false);
        try {

            BOD = df.parse(DOB);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:  
        if (!validationItemList()) {
            JOptionPane.showMessageDialog(null, "Please select an item");
            return;
        }
        if (!validationDate()) {
            JOptionPane.showMessageDialog(null, "Please enter date");
            return;
        }
        String dob = selldate.getText();
        if (!valDOB(dob)) {
            JOptionPane.showMessageDialog(null, "Invalid date format");
            return;
        }
        if (!validationPrice()) {
            JOptionPane.showMessageDialog(null, "Please enter price");
            return;
        }
        if (!validationQuantity()) {
            JOptionPane.showMessageDialog(null, "Please enter quantity");
            return;
        }
        if (!validationCustomer()) {
            JOptionPane.showMessageDialog(null, "Please enter customer name");
            return;
        }
        if (!validationVat()) {
            JOptionPane.showMessageDialog(null, "Please enter Vat Percent");
            return;
        }
        //making these items non editable after added item
        jComboBox2.setEditable(false);
        selldate.setEditable(false);
        jComboBox3.setEditable(false);
        jComboBox3.setEnabled(false);

        if (jComboBox3.getSelectedItem() == "Select Customer") {
            dm = (DefaultTableModel) jTable1.getModel();
            dm.addRow(new Object[]{
                item.getSelectedItem(),
                model.getSelectedItem(),
                quantity.getText(),
                price.getText(),
                total.getText(),
                discountamount.getText(),
                jComboBox2.getText(),
                dueamount.getText(),
                selldate.getText(), //                itemNo.getText(), //            due.getText(),
            });
        } else {
            dm = (DefaultTableModel) jTable1.getModel();
            dm.addRow(new Object[]{
                item.getSelectedItem(),
                model.getSelectedItem(),
                quantity.getText(),
                price.getText(),
                total.getText(),
                discountamount.getText(),
                jComboBox3.getSelectedItem(),
                dueamount.getText(),
                selldate.getText(), //                itemNo.getText(), //            due.getText(),
            });
        }

        Double VatAmount = 0.0;
        Double Total = 0.0;
        for (int i = 0;
                i < jTable1.getRowCount();
                i++) {
            VatAmount = VatAmount + Double.parseDouble(jTable1.getValueAt(i, 5).toString());
            Total = Total + Double.parseDouble(jTable1.getValueAt(i, 7).toString());
        }
        VatAmount = Double.parseDouble(new DecimalFormat("##.##").format(VatAmount));

        vat.setText(Double.toString(VatAmount));
        Total = Double.parseDouble(new DecimalFormat("##.##").format(Total));

        alltotal.setText(Double.toString(Total));
//        clear();
        if (jComboBox1.getSelectedItem()
                == "Select Type to Pay") {

            //update item table . subtract item quantity after its being sold
            String sql2 = "update item set "
                    + "item_quantity ='" + jTextField8.getText() + "'"
                    + "where item_no = " + itemNo.getText();
            try {
                pst = conn.prepareStatement(sql2);
                pst.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            //saving into daybook database
            String sql = "insert into daybook(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due,cash,non_cash,purchase_cash,bill_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, itemNo.getText());
                pst.setString(2, price.getText());
//               java.util.Date utilDate = jDateChooser1.getDate();
//               java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                pst.setString(3, selldate.getText());
                pst.setString(4, (String) item.getSelectedItem());
                pst.setString(5, (String) model.getSelectedItem());
//                pst.setString(6, (String) model1.getSelectedItem());
                pst.setString(6, quantity.getText());
                pst.setString(7, total.getText());

                if (percent.getText().equals("")) {
                    pst.setString(8, "0");
                } else {
                    pst.setString(8, percent.getText());
                }
                pst.setString(9, dueamount.getText());
                if (jComboBox3.getSelectedItem() == "Select Customer") {
                    pst.setString(10, jComboBox2.getText());
                } else {
                    pst.setString(10, (String) jComboBox3.getSelectedItem());
                }

                pst.setString(11, "0");
                pst.setString(12, "0");
                pst.setString(13, dueamount.getText());
                pst.setString(14, "0");
                pst.setString(15, "0.0");
                pst.setString(16, itemNo.getText());
                pst.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            //saving into sale database
            String sql1 = "insert into sale(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due,bill_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                pst = conn.prepareStatement(sql1);
                pst.setString(1, itemNo.getText());
                pst.setString(2, price.getText());
//               java.util.Date utilDate = jDateChooser1.getDate();
//               java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                pst.setString(3, selldate.getText());
                pst.setString(4, (String) item.getSelectedItem());
                pst.setString(5, (String) model.getSelectedItem());
//                pst.setString(6, (String) model1.getSelectedItem());
                pst.setString(6, quantity.getText());
                pst.setString(7, total.getText());

                if (percent.getText().equals("")) {
                    pst.setString(8, "0");
                } else {
                    pst.setString(8, percent.getText());
                }
                pst.setString(9, dueamount.getText());
//                pst.setString(10, jComboBox2.getText());
                if (jComboBox3.getSelectedItem() == "Select Customer") {
                    pst.setString(10, jComboBox2.getText());
                } else {
                    pst.setString(10, (String) jComboBox3.getSelectedItem());
                }
                pst.setString(11, "0");
                pst.setString(12, "0");
                pst.setString(13, "0");
                pst.execute();
                JOptionPane.showMessageDialog(null, "Item Processed");
                clear();
//                fetch();
//                clock();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else {
            //update item table . subtract item quantity after its being sold
            String sql121 = "update item set "
                    + "item_quantity ='" + jTextField8.getText() + "'"
                    + "where item_no = " + itemNo.getText();
            try {
                pst = conn.prepareStatement(sql121);
                pst.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            String sql = "insert into daybook(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due,cash,non_cash,purchase_cash,bill_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, itemNo.getText());
                pst.setString(2, price.getText());
                pst.setString(3, selldate.getText());
                pst.setString(4, (String) item.getSelectedItem());
                pst.setString(5, (String) model.getSelectedItem());
//                pst.setString(6, (String) model1.getSelectedItem());
                pst.setString(6, quantity.getText());
                pst.setString(7, total.getText());

                if (percent.getText().equals("")) {
                    pst.setString(8, "0");
                } else {
                    pst.setString(8, percent.getText());
                }
                pst.setString(9, dueamount.getText());
//                pst.setString(10, jComboBox2.getText());
                if (jComboBox3.getSelectedItem() == "Select Customer") {
                    pst.setString(10, jComboBox2.getText());
                } else {
                    pst.setString(10, (String) jComboBox3.getSelectedItem());
                }
                if (paid.getText().equals("")) {
                    pst.setString(11, "0");
                } else {
                    pst.setString(11, paid.getText());
                }
                if (due.getText().equals("")) {
                    pst.setString(12, dueamount.getText());
                } else {
                    pst.setString(12, due.getText());
                }

                if (paid.getText().equals("")) {
                    pst.setString(13, "0");
                } else {
                    pst.setString(13, paid.getText());
                }

                if (paid.getText().equals("")) {
                    pst.setString(14, dueamount.getText());
                } else {
                    pst.setString(14, due.getText());
                }
                pst.setString(15, "0.0");
                pst.setString(16, itemNo.getText());
                pst.execute();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

//            //saving into due_paid_report
            String sql12 = "insert into due_paid(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due,item_payed_date) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                pst = conn.prepareStatement(sql12);
                pst.setString(1, itemNo.getText());
                pst.setString(2, price.getText());
                pst.setString(3, selldate.getText());
                pst.setString(4, (String) item.getSelectedItem());
                pst.setString(5, (String) model.getSelectedItem());
//                pst.setString(6, (String) model1.getSelectedItem());
                pst.setString(6, quantity.getText());
                pst.setString(7, total.getText());

                if (percent.getText().equals("")) {
                    pst.setString(8, "0");
                } else {
                    pst.setString(8, percent.getText());
                }
                pst.setString(9, dueamount.getText());
//                pst.setString(10, jComboBox2.getText());
                if (jComboBox3.getSelectedItem() == "Select Customer") {
                    pst.setString(10, jComboBox2.getText());
                } else {
                    pst.setString(10, (String) jComboBox3.getSelectedItem());
                }
                if (paid.getText().equals("")) {
                    pst.setString(11, "0");
                } else {
                    pst.setString(11, paid.getText());
                }
                if (due.getText().equals("")) {
                    pst.setString(12, dueamount.getText());
                } else {
                    pst.setString(12, due.getText());
                }
                pst.setString(13, selldate.getText());
                pst.execute();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            //saving into sale database
            String sql111 = "insert into sale(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due,bill_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                pst = conn.prepareStatement(sql111);
                pst.setString(1, itemNo.getText());
                pst.setString(2, price.getText());
//               java.util.Date utilDate = jDateChooser1.getDate();
//               java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                pst.setString(3, selldate.getText());
                pst.setString(4, (String) item.getSelectedItem());
                pst.setString(5, (String) model.getSelectedItem());
                pst.setString(6, quantity.getText());
                pst.setString(7, total.getText());
                if (percent.getText().equals("")) {
                    pst.setString(8, "0");
                } else {
                    pst.setString(8, percent.getText());
                }
//               pst.setString(8, percent.getText());
                pst.setString(9, dueamount.getText());
                if (jComboBox3.getSelectedItem() == "Select Customer") {
                    pst.setString(10, jComboBox2.getText());
                } else {
                    pst.setString(10, (String) jComboBox3.getSelectedItem());
                }
                if (paid.getText().equals("")) {
                    pst.setString(11, "0");
                } else {
                    pst.setString(11, paid.getText());
                }
                if (due.getText().equals("")) {
                    pst.setString(12, dueamount.getText());
                } else {
                    pst.setString(12, due.getText());
                }
                pst.setString(13,"0");
                pst.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            //saving into due database
            String cName = jComboBox2.getText();
            String sqlll = "select * from due where customer_name ='" + cName + "'";
            try {
                pst = conn.prepareStatement(sqlll);
                rs = pst.executeQuery(sqlll);
                if (rs.next() == false) {
                    //saving into due_paid_report
                    String sql1222 = "insert into due(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due) values(?,?,?,?,?,?,?,?,?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(sql1222);
                        pst.setString(1, itemNo.getText());
                        pst.setString(2, price.getText());
//               java.util.Date utilDate = jDateChooser1.getDate();
//               java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                        pst.setString(3, selldate.getText());
                        pst.setString(4, (String) item.getSelectedItem());
                        pst.setString(5, (String) model.getSelectedItem());
//                        pst.setString(6, (String) model1.getSelectedItem());
                        pst.setString(6, quantity.getText());
                        pst.setString(7, total.getText());

                        if (percent.getText().equals("")) {
                            pst.setString(8, "0");
                        } else {
                            pst.setString(8, percent.getText());
                        }
                        pst.setString(9, dueamount.getText());
//                        pst.setString(10, jComboBox2.getText());
                        if (jComboBox3.getSelectedItem() == "Select Customer") {
                            pst.setString(10, jComboBox2.getText());
                        } else {
                            pst.setString(10, (String) jComboBox3.getSelectedItem());
                        }
                        if (paid.getText().equals("")) {
                            pst.setString(11, "0");
                        } else {
                            pst.setString(11, paid.getText());
                        }
                        if (due.getText().equals("")) {
                            pst.setString(12, dueamount.getText());
                        } else {
                            pst.setString(12, due.getText());
                        }
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Item processed for due");
                        clear();
//                        fetch();
//                        clock();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                } else {
                    do {

//                if(olddue<0)
//                {
//                    olddue = 0;
//                     int newdue = Integer.parseInt(due.getText());
//                  int newwdue = olddue + newdue;
//                  String sqlllll = "update due set "+ " due= '" + newwdue + "'"
//                    + "where customer_name = '" +  cName + "'";
//                   try {               
//                pst = conn.prepareStatement(sqlllll);
//                pst.execute(); 
//                JOptionPane.showMessageDialog(null,"Item processesd for due");
//                fetch();
//                clear();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);             
//            }
//                }
//                else
//                {
                        Double olddue = rs.getDouble("due");
                        Double newdue = Double.parseDouble(due.getText());
                        Double newwdue = olddue + newdue;
                        //adding and subtraccting paid and due amount
                        String sqlllll = "update due set " + " due= '" + newwdue + "'"
                                + "where customer_name = '" + cName + "'";
//                 
                        try {
                            pst = conn.prepareStatement(sqlllll);
                            pst.execute();
                            JOptionPane.showMessageDialog(null, "Item processesd for due");
//                            fetch();
                            clear();
//                            clock();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }

                    } while (rs.next());
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        //        saving into bill database
//            String sql1 = "insert into bill(bill_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,vat_amount,total,customer_name,vat_total,amount_total) values(?,?,?,?,?,?,?,?,?,?,?,?)";
//            try {
//                pst = conn.prepareStatement(sql1);
//                pst.setString(1, itemNo.getText());
//                pst.setString(2, price.getText());
////               java.util.Date utilDate = jDateChooser1.getDate();
////               java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//                pst.setString(3, selldate.getText());
//                pst.setString(4, (String) item.getSelectedItem());
//                pst.setString(5, (String) model.getSelectedItem());
////                pst.setString(6, (String) model1.getSelectedItem());
//                pst.setString(6, quantity.getText());
//                pst.setString(7, total.getText());
//
//                
//                    pst.setString(8, discountamount.getText());
////                }
//                pst.setString(9, dueamount.getText());
//                pst.setString(10, jComboBox2.getText());
//                pst.setString(11, vat.getText());
//                pst.setString(12, alltotal.getText());
//                pst.execute();
//                JOptionPane.showMessageDialog(null, "Item Processed");
//                clear();
////                fetch();
//                clock();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }

//        if (jComboBox1.getSelectedItem() == "Select Type to Pay") {
//
//            //update item table . subtract item quantity after its being sold
//            String sql2 = "update item set "
//                    + "item_quantity ='" + jTextField8.getText() + "'"
//                    + "where item_no = " + itemNo.getText();
//            try {
//                pst = conn.prepareStatement(sql2);
//                pst.execute();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//            //saving into daybook database
//            String sql = "insert into daybook(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due,cash,non_cash,purchase_cash) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            try {
//                pst = conn.prepareStatement(sql);
//                pst.setString(1, itemNo.getText());
//                pst.setString(2, price.getText());
////               java.util.Date utilDate = jDateChooser1.getDate();
////               java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//                pst.setString(3, selldate.getText());
//                pst.setString(4, (String) item.getSelectedItem());
//                pst.setString(5, (String) model.getSelectedItem());
////                pst.setString(6, (String) model1.getSelectedItem());
//                pst.setString(6, quantity.getText());
//                pst.setString(7, total.getText());
//
//                if (percent.getText().equals("")) {
//                    pst.setString(8, "0");
//                } else {
//                    pst.setString(8, percent.getText());
//                }
//                pst.setString(9, dueamount.getText());
//                pst.setString(10, jComboBox2.getText());
//                pst.setString(11, "0");
//                pst.setString(12, "0");
//                pst.setString(13, dueamount.getText());
//                pst.setString(14, "0");
//                pst.setString(15, "0.0");
//                pst.execute();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//            //saving into sale database
//            String sql1 = "insert into sale(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due) values(?,?,?,?,?,?,?,?,?,?,?,?)";
//            try {
//                pst = conn.prepareStatement(sql1);
//                pst.setString(1, itemNo.getText());
//                pst.setString(2, price.getText());
////               java.util.Date utilDate = jDateChooser1.getDate();
////               java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//                pst.setString(3, selldate.getText());
//                pst.setString(4, (String) item.getSelectedItem());
//                pst.setString(5, (String) model.getSelectedItem());
////                pst.setString(6, (String) model1.getSelectedItem());
//                pst.setString(6, quantity.getText());
//                pst.setString(7, total.getText());
//
//                if (percent.getText().equals("")) {
//                    pst.setString(8, "0");
//                } else {
//                    pst.setString(8, percent.getText());
//                }
//                pst.setString(9, dueamount.getText());
//                pst.setString(10, jComboBox2.getText());
//                pst.setString(11, "0");
//                pst.setString(12, "0");
//                pst.execute();
//                JOptionPane.showMessageDialog(null, "Item Processed");
//                clear();
//                fetch();
//                clock();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//        } else {
//            //update item table . subtract item quantity after its being sold
//            String sql121 = "update item set "
//                    + "item_quantity ='" + jTextField8.getText() + "'"
//                    + "where item_no = " + itemNo.getText();
//            try {
//                pst = conn.prepareStatement(sql121);
//                pst.execute();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//
//            String sql = "insert into daybook(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due,cash,non_cash,purchase_cash) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            try {
//                pst = conn.prepareStatement(sql);
//                pst.setString(1, itemNo.getText());
//                pst.setString(2, price.getText());
//                pst.setString(3, selldate.getText());
//                pst.setString(4, (String) item.getSelectedItem());
//                pst.setString(5, (String) model.getSelectedItem());
////                pst.setString(6, (String) model1.getSelectedItem());
//                pst.setString(6, quantity.getText());
//                pst.setString(7, total.getText());
//
//                if (percent.getText().equals("")) {
//                    pst.setString(8, "0");
//                } else {
//                    pst.setString(8, percent.getText());
//                }
//                pst.setString(9, dueamount.getText());
//                pst.setString(10, jComboBox2.getText());
//                if (paid.getText().equals("")) {
//                    pst.setString(11, "0");
//                } else {
//                    pst.setString(11, paid.getText());
//                }
//                if (due.getText().equals("")) {
//                    pst.setString(12, dueamount.getText());
//                } else {
//                    pst.setString(12, due.getText());
//                }
//
//                if (paid.getText().equals("")) {
//                    pst.setString(13, "0");
//                } else {
//                    pst.setString(13, paid.getText());
//                }
//
//                if (paid.getText().equals("")) {
//                    pst.setString(14, dueamount.getText());
//                } else {
//                    pst.setString(14, due.getText());
//                }
//                pst.setString(15, "0.0");
//                pst.execute();
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//
////            //saving into due_paid_report
//            String sql12 = "insert into due_paid(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due,item_payed_date) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            try {
//                pst = conn.prepareStatement(sql12);
//                pst.setString(1, itemNo.getText());
//                pst.setString(2, price.getText());
//                pst.setString(3, selldate.getText());
//                pst.setString(4, (String) item.getSelectedItem());
//                pst.setString(5, (String) model.getSelectedItem());
////                pst.setString(6, (String) model1.getSelectedItem());
//                pst.setString(6, quantity.getText());
//                pst.setString(7, total.getText());
//
//                if (percent.getText().equals("")) {
//                    pst.setString(8, "0");
//                } else {
//                    pst.setString(8, percent.getText());
//                }
//                pst.setString(9, dueamount.getText());
//                pst.setString(10, jComboBox2.getText());
//                if (paid.getText().equals("")) {
//                    pst.setString(11, "0");
//                } else {
//                    pst.setString(11, paid.getText());
//                }
//                if (due.getText().equals("")) {
//                    pst.setString(12, dueamount.getText());
//                } else {
//                    pst.setString(12, due.getText());
//                }
//                pst.setString(13, selldate.getText());
//                pst.execute();
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//
////         //saving into sale database
////                String sql111 = "insert into sale(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due) values(?,?,?,?,?,?,?,?,?,?,?,?)";
////        try {
////               pst = conn.prepareStatement(sql111);           
////               pst.setString(1, itemNo.getText());
////               pst.setString(2, price.getText());              
//////               java.util.Date utilDate = jDateChooser1.getDate();
//////               java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
////               pst.setString(3, selldate.getText());
////               pst.setString(4, (String) item.getSelectedItem());
////               pst.setString(5, (String) model.getSelectedItem());
////               pst.setString(6, quantity.getText());
////               pst.setString(7, total.getText());
////               if (percent.getText().equals(""))
////                {
////                    pst.setString(8, "0");
////                } else 
////                {
////                   pst.setString(8, percent.getText());
////                }
//////               pst.setString(8, percent.getText());
////               pst.setString(9, dueamount.getText()); 
////               pst.setString(10,(String)jComboBox2.getSelectedItem());               
////              if (paid.getText().equals(""))
////                {
////                    pst.setString(11, "0");
////                } else 
////                {
////                   pst.setString(11, paid.getText());
////                } 
////                  if (due.getText().equals(""))
////                {
////                    pst.setString(12, dueamount.getText());
////                } else 
////                {
////                   pst.setString(12, due.getText());
////                } 
////               pst.execute(); 
////                       } catch (Exception e) {
////            JOptionPane.showMessageDialog(null, e);
////        }
//            //saving into due database
//            String cName = jComboBox2.getText();
//            String sqlll = "select * from due where customer_name ='" + cName + "'";
//            try {
//                pst = conn.prepareStatement(sqlll);
//                rs = pst.executeQuery(sqlll);
//                if (rs.next() == false) {
//                    //saving into due_paid_report
//                    String sql1222 = "insert into due(item_no,item_price,item_sell_date,item_name,item_model,item_quantity,item_total,item_discount_percent,item_discount_total,customer_name,paid,due) values(?,?,?,?,?,?,?,?,?,?,?,?)";
//                    try {
//                        pst = conn.prepareStatement(sql1222);
//                        pst.setString(1, itemNo.getText());
//                        pst.setString(2, price.getText());
////               java.util.Date utilDate = jDateChooser1.getDate();
////               java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//                        pst.setString(3, selldate.getText());
//                        pst.setString(4, (String) item.getSelectedItem());
//                        pst.setString(5, (String) model.getSelectedItem());
////                        pst.setString(6, (String) model1.getSelectedItem());
//                        pst.setString(6, quantity.getText());
//                        pst.setString(7, total.getText());
//
//                        if (percent.getText().equals("")) {
//                            pst.setString(8, "0");
//                        } else {
//                            pst.setString(8, percent.getText());
//                        }
//                        pst.setString(9, dueamount.getText());
//                        pst.setString(10, jComboBox2.getText());
//                        if (paid.getText().equals("")) {
//                            pst.setString(11, "0");
//                        } else {
//                            pst.setString(11, paid.getText());
//                        }
//                        if (due.getText().equals("")) {
//                            pst.setString(12, dueamount.getText());
//                        } else {
//                            pst.setString(12, due.getText());
//                        }
//                        pst.execute();
//                        JOptionPane.showMessageDialog(null, "Item processed for due");
//                        clear();
//                        fetch();
//                        clock();
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(null, e);
//                    }
//                } else {
//                    do {
//
////                if(olddue<0)
////                {
////                    olddue = 0;
////                     int newdue = Integer.parseInt(due.getText());
////                  int newwdue = olddue + newdue;
////                  String sqlllll = "update due set "+ " due= '" + newwdue + "'"
////                    + "where customer_name = '" +  cName + "'";
////                   try {               
////                pst = conn.prepareStatement(sqlllll);
////                pst.execute(); 
////                JOptionPane.showMessageDialog(null,"Item processesd for due");
////                fetch();
////                clear();
////            } catch (Exception e) {
////                JOptionPane.showMessageDialog(null, e);             
////            }
////                }
////                else
////                {
//                        Double olddue = rs.getDouble("due");
//                        Double newdue = Double.parseDouble(due.getText());
//                        Double newwdue = olddue + newdue;
//                        //adding and subtraccting paid and due amount
//                        String sqlllll = "update due set " + " due= '" + newwdue + "'"
//                                + "where customer_name = '" + cName + "'";
////                 
//                        try {
//                            pst = conn.prepareStatement(sqlllll);
//                            pst.execute();
//                            JOptionPane.showMessageDialog(null, "Item processesd for due");
//                            fetch();
//                            clear();
//                            clock();
//                        } catch (Exception e) {
//                            JOptionPane.showMessageDialog(null, e);
//                        }
//
//                    } while (rs.next());
//                }
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceActionPerformed

    private void discountamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountamountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountamountActionPerformed

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
        // TODO add your handling code here                
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        //validating quantity
        if (!validationPrice()) {
            JOptionPane.showMessageDialog(null, "Please enter price");
//                  jComboBox1.setSelectedItem("Select type to pay");
            return;
        }
        if (jComboBox1.getSelectedItem() == "Due Amount") {

            due.setText(dueamount.getText());
//            jLabel15.setVisible(true);
            jLabel16.setVisible(true);
            jLabel17.setVisible(true);
//            customerName.setVisible(true);
            paid.setVisible(true);
            due.setVisible(true);
        } else {
//            jLabel15.setVisible(false);
//            customerName.setVisible(false);
            due.setText("0");
            paid.setVisible(false);
            due.setVisible(false);
            jLabel16.setVisible(false);
            jLabel17.setVisible(false);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:         
//            jLabel15.setVisible(false);
//            customerName.setVisible(false);  
        jLabel16.setVisible(false);
        jLabel17.setVisible(false);
        paid.setVisible(false);
        due.setVisible(false);
    }//GEN-LAST:event_formWindowOpened

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed

    private void quantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_quantityKeyTyped

    private void priceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceKeyTyped
        // TODO add your handling code here:
        if (price.getText().equals("")) {
            discountamount.setText("0");
            String newTotal;
            newTotal = total.getText();
            dueamount.setText(newTotal);
        }
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_priceKeyTyped

    private void quantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityKeyPressed
//quantity validation

    public boolean validationPaid() {
        if (quantity.getText().equals("")) {
            paid.setText("");
            return false;
        }
        return true;
    }

    private void paidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidKeyReleased
        // TODO add your handling code here:
        if (!validationPaid()) {
            JOptionPane.showMessageDialog(null, "Please Enter Quantity");
            return;
        }
        Double totalprice = Double.parseDouble(dueamount.getText());
        Double ppaid = Double.parseDouble(paid.getText());
        Double ddue = totalprice - ppaid;
        ddue = Double.parseDouble(new DecimalFormat("##.##").format(ddue));
        due.setText("" + ddue);

    }//GEN-LAST:event_paidKeyReleased

    private void paidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidKeyTyped
        // TODO add your handling code here:                 
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_paidKeyTyped

    private void priceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceKeyPressed
        // TODO add your handling code here:        
    }//GEN-LAST:event_priceKeyPressed

    private void percentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_percentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_percentActionPerformed

    private void percentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_percentKeyPressed
        // TODO add your handling code here:        

    }//GEN-LAST:event_percentKeyPressed

    //quantity validation
    public boolean validationPercent() {
        if (quantity.getText().equals("")) {
            price.setText("");
            return false;
        }
        return true;
    }
    private void percentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_percentKeyReleased
        // TODO add your handling code here:
        if (!validationPercent()) {
            JOptionPane.showMessageDialog(null, "Please Enter Quantity");
            percent.setText("");
            return;
        }
        char c = evt.getKeyChar();
        if ((c == KeyEvent.VK_BACK_SPACE)) {
            discountamount.setText("0");
            due.setText(total.getText());
            dueamount.setText(total.getText());
        }
        Double discountpercent = Double.parseDouble(percent.getText());
        Double totalprice = Double.parseDouble(total.getText());
        Double priceafterdiscount = discountpercent * totalprice;
        Double newpriceafterdiscount = priceafterdiscount / 100;
        newpriceafterdiscount = Double.parseDouble(new DecimalFormat("##.##").format(newpriceafterdiscount));
        discountamount.setText("" + newpriceafterdiscount);

        Double amountdiscount = Double.parseDouble(discountamount.getText());
        Double dueaamount = totalprice + amountdiscount;
        dueaamount = Double.parseDouble(new DecimalFormat("##.##").format(dueaamount));
        dueamount.setText("" + dueaamount);
//                paid.setText("");
        due.setText("" + dueaamount);
        paid.setText("");
    }//GEN-LAST:event_percentKeyReleased

    private void percentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_percentKeyTyped
        // TODO add your handling code here:        

    }//GEN-LAST:event_percentKeyTyped

    public void clearAll() {
        price.setText("");
        paid.setText("");
        due.setText("");
        itemNo.setText("");
        totalstock.setText("");
        jTextField8.setText("");
        total.setText("");
        percent.setText("");
        discountamount.setText("");
        quantity.setText("");
        dueamount.setText("");
        jComboBox2.setText("");
        jComboBox2.setEditable(true);
        selldate.setEditable(true);
//        jComboBox3.setEditable(true);
        jComboBox3.setEnabled(true);
        jButton2.setEnabled(true);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rows = model.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        clearAll();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
//        try
//        {
//            int i = jTable1.getSelectedRow();
////            JOptionPane.showMessageDialog(null,i);
//            dm = (DefaultTableModel) jTable1.getModel();
////            //date ko lagi            
////            itemNo.setText(dm.getValueAt(i, 1).toString());
////            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) dm.getValueAt(i, 1).toString());
////            jDateChooser1.setDate(date);
////            price.setText(dm.getValueAt(i, 2).toString());
//            item.setSelectedItem(dm.getValueAt(i, 3).toString());
//            model.setSelectedItem(dm.getValueAt(i, 4).toString()); 
//            
////            length.setText(dm.getValueAt(i, 5).toString());
//            
//            quantity.setText(dm.getValueAt(i, 5).toString());
////            total.setText(dm.getValueAt(i, 7).toString());
////            percent.setText(dm.getValueAt(i, 8).toString());
//            dueamount.setText(dm.getValueAt(i, 6).toString());
////            paid.setText(dm.getValueAt(i, 10).toString());
////            due.setText(dm.getValueAt(i, 11).toString());
//        //date ko lagi
////            java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse((String) dm.getValueAt(i, 5).toString());
////            jDateChooser2.setDate(date1);
////             jTextField5.setText(dm.getValueAt(i, 5).toString());
////            jTextField6.setText(dm.getValueAt(i, 6).toString());
////            jTextField7.setText(dm.getValueAt(i, 8).toString());           
////            jTextField8.setText(dm.getValueAt(i, 7).toString());
//        } catch (Exception ex) {
//            Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:         
    }//GEN-LAST:event_jButton2MouseClicked

    private void jComboBox1PopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeVisible
        // TODO add your handling code here:        
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeVisible

    private void selldateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selldateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selldateActionPerformed

    private void selldateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_selldateKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_selldateKeyPressed

    private void selldateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_selldateKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_selldateKeyReleased

    private void selldateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_selldateKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_selldateKeyTyped

    private void itemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_itemMouseReleased

    private void itemPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_itemPopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_itemPopupMenuWillBecomeVisible

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void modelPopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_modelPopupMenuCanceled
        // TODO add your handling code here:
    }//GEN-LAST:event_modelPopupMenuCanceled

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
//        quantitysum();
    }//GEN-LAST:event_formWindowActivated

    private void jComboBox3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox3PopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3PopupMenuWillBecomeInvisible

    private void jComboBox3PopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox3PopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3PopupMenuWillBecomeVisible

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        if (!validationqtyy()) {
            JOptionPane.showMessageDialog(null, "Please select an item first");
            price.setText("");
            return;
        }
        if (!validationPrice()) {
            JOptionPane.showMessageDialog(null, "Please enter price");
//                  jComboBox1.setSelectedItem("Select type to pay");
            return;
        }
        if (jComboBox3.getSelectedItem() == "Select Customer") {
            jComboBox2.setVisible(true);
            jLabel15.setVisible(true);
        } else {
            jComboBox2.setVisible(false);
            jLabel15.setVisible(false);
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyReleased
        // TODO add your handling code here:        
    }//GEN-LAST:event_jComboBox2KeyReleased

    /**
     * @param args the command line arguments
     *
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
            java.util.logging.Logger.getLogger(SaleItem.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SaleItem.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SaleItem.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaleItem.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SaleItem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alltotal;
    private javax.swing.JLabel billno;
    private javax.swing.JTextField discountamount;
    private javax.swing.JTextField due;
    private javax.swing.JTextField dueamount;
    private javax.swing.JComboBox item;
    private javax.swing.JTextField itemNo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JTextField jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel length;
    private javax.swing.JComboBox model;
    private javax.swing.JTextField newdate;
    private javax.swing.JTextField paid;
    private javax.swing.JTextField percent;
    private javax.swing.JTextField price;
    private javax.swing.JTextField quantity;
    private javax.swing.JTextField selldate;
    private javax.swing.JTextField total;
    private javax.swing.JTextField totalstock;
    private javax.swing.JLabel vat;
    // End of variables declaration//GEN-END:variables
}
