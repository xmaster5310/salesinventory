
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
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
public class MonthlyReport extends javax.swing.JFrame {

    DefaultTableModel dm;
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    /**
     * Creates new form Mreport
     */
    // interval date validation    
    public MonthlyReport() {
        super("Report");
        initComponents();
        conn = javaconnect.getConnection();
        clock();
        date1.setVisible(false);
        date2.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jButton1.setVisible(false);
        date_txt.setVisible(false);
        newdate.setVisible(false);
    }

    public void customclear() {
        date1.setVisible(false);
        date2.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jButton1.setVisible(false);
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

    public boolean validationDate() {
        if (date1.getText().equals("")) {
            return false;
        }
        if (date2.getText().equals("")) {
            return false;
        }
        return true;
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
        date_txt.setText(newDate);
        newdate.setText(newDate);
    }

    public void dateconversion() {
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

//              Calendar cal = new GregorianCalendar();
//        int month1 = cal.get(Calendar.MONTH);
//        int year1 = cal.get(Calendar.YEAR);
//        int day1 = cal.get(Calendar.DAY_OF_MONTH);
//        String d=year1 + "-" + (month1 + 1) + "-" + day1;
        String Date = newdate.getText();
//        JOptionPane.showMessageDialog(null, Date);
        int pos1 = Date.indexOf("-");
        int pos2 = Date.indexOf("-", pos1 + 1);
        String year = Date.substring(0, pos1);
        String month = Date.substring(pos1 + 1, pos2);
        String day = Date.substring(pos2 + 1);
        int engYear = Integer.parseInt(year);
        int engMonth = Integer.parseInt(month);
        int engDay = Integer.parseInt(day);
//            JOptionPane.showMessageDialog(null,engYear);

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
//            date_txt.setText(newDate);
//            JOptionPane.showMessageDialog(null,newDate);
        newdate.setText(newDate);
    }

    public void fetch() {

        try {
            String val1 = date1.getText();
            String val2 = date2.getText();
            JTableHeader header = table.getTableHeader();
            header.setForeground(Color.red);
            header.setFont(new Font("Tahome", Font.ITALIC, 12));

            String q = "select item_name as Item,item_model as 'Item Model',item_price as Price,item_quantity as Quantity,item_total as 'Total',item_discount_percent as 'Vat%',item_discount_total as 'Total Price',item_sell_date as 'Sell Date',customer_name as 'CName',paid as 'Paid',due as 'Due',bill_no as 'BillNo' from sale where item_sell_date between '" + val1 + "' and '" + val2 + "' ";
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Item not Fetched");
        }

    }

    //search engine
    private void filter(String query) {
        dm = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }

    //display sum
    public int getSum() {
        int rowsCount = table.getRowCount();
//        Double sum = 0.0;
        Double sum1 = 0.0;
//        Double sum2 = 0.0;
        for (int i = 0; i < rowsCount; i++) {
//                sum = sum + Double.parseDouble(table.getValueAt(i, 3).toString());  
            sum1 = sum1 + Double.parseDouble(table.getValueAt(i, 6).toString());
//                sum2 = sum2 + Double.parseDouble(table.getValueAt(i,6).toString());
        }
//        sum = Double.parseDouble(new DecimalFormat("##.##").format(sum));
//        jLabel4.setText(Double.toString(sum));
        jLabel4.setVisible(true);
        jLabel5.setText(Double.toString(sum1));
//        jLabel6.setText(Double.toString(sum2));
//        JOptionPane.showMessageDialog(null,table1.getValueAt(1,1).toString());
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        date1 = new javax.swing.JTextField();
        date2 = new javax.swing.JTextField();
        combo = new javax.swing.JComboBox();
        date_txt = new javax.swing.JLabel();
        newdate = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        search_text = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setText("Monthly/Yearly Report");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 220, -1));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel2.setText("From");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 47, 20));

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel3.setText("To");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 33, -1));

        jButton1.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 70, -1));

        jButton6.setBackground(new java.awt.Color(153, 153, 255));
        jButton6.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jButton6.setText("Print");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 80, 30));
        jPanel1.add(date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 100, -1));
        jPanel1.add(date2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 90, -1));

        combo.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Report Type", "Today", "Last Week", "Last Month", "Last Year", "Custom Date" }));
        combo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });
        jPanel1.add(combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 150, -1));
        jPanel1.add(date_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 70, 10));
        jPanel1.add(newdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 70, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 60));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table = new JTable()
        {
            public boolean isCellEditable(int row, int column)
            {
                for(int i=0;i<table.getRowCount();i++)
                {
                    if(row ==i)
                    {
                        return false;
                    }
                }
                return true;
            }
        };
        table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        table.setFont(new java.awt.Font("Sitka Subheading", 3, 12)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 940, 480));

        search_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_textKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_textKeyReleased(evt);
            }
        });
        jPanel2.add(search_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 140, -1));

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel12.setText("Search");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 60, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 940, 500));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, -1, -1));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        jLabel5.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jPanel4.add(jLabel5);

        jLabel6.setFont(new java.awt.Font("Sitka Subheading", 3, 14)); // NOI18N
        jPanel4.add(jLabel6);

        jLabel4.setFont(new java.awt.Font("Sitka Heading", 3, 14)); // NOI18N
        jLabel4.setText("Total sale");
        jPanel4.add(jLabel4);

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 940, 60));

        setSize(new java.awt.Dimension(949, 652));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
//        jLabel4.setVisible(false);
    }//GEN-LAST:event_formWindowActivated
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

    public void clear() {
        date1.setText("");
        date2.setText("");
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (!validationDate()) {
            JOptionPane.showMessageDialog(null, "Please Enter Date");
            return;
        }
        String dob = date1.getText();
        if (!valDOB(dob)) {
            JOptionPane.showMessageDialog(null, "Invalid date format");
            clear();
            return;
        }
        String dob1 = date2.getText();
        if (!valDOB(dob1)) {
            JOptionPane.showMessageDialog(null, "Invalid date format");
            clear();
            return;
        }
        fetch();
        getSum();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void search_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_textKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_textKeyPressed

    private void search_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_textKeyReleased
        // TODO add your handling code here:
        if (!validationDate()) {
            JOptionPane.showMessageDialog(null, "Please Enter Date");
            search_text.setText("");
            return;
        }

        String query = search_text.getText();
        filter(query);
        getSum();
    }//GEN-LAST:event_search_textKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("Report Print");
//        MessageFormat footer = new MessageFormat("page{0,number,integer}");/
        MessageFormat footer = new MessageFormat(" Total:" + jLabel5.getText() + "");
        try {
            table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (java.awt.print.PrinterException e) {
            System.err.format("cannot print", e.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    public void Today() {
        try {
//             JOptionPane.showMessageDialog(null,"here came");
            String val1 = date_txt.getText();
//            String val2=date2.getText();
            JTableHeader header = table.getTableHeader();
            header.setForeground(Color.red);
            header.setFont(new Font("Tahome", Font.ITALIC, 12));

            String q = "select item_name as Item,item_model as 'Item Model',item_price as Price,item_quantity as Quantity,item_total as 'Total',item_discount_percent as 'Vat%',item_discount_total as 'Total Price',item_sell_date as 'Sell Date',customer_name as 'CName',paid as 'Paid',due as 'Due',bill_no as 'BillNo' from sale where item_sell_date= '" + val1 + "' ";
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Item not Fetched");
        }
    }

    public void LastWeek() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
//        yestarday date
            cal.add(Calendar.DAY_OF_MONTH, -7);
            //day befor yesterday
//        Calendar call = Calendar.getInstance();
//        call.add(Calendar.DAY_OF_MONTH, -2);
            String lastWeek = sdf.format(cal.getTime());
            newdate.setText(lastWeek);
            dateconversion();

            String val1 = date_txt.getText();
//                         JOptionPane.showMessageDialog(null,val1);
            String val2 = newdate.getText();
//                         JOptionPane.showMessageDialog(null,val2);
            JTableHeader header = table.getTableHeader();
            header.setForeground(Color.red);
            header.setFont(new Font("Tahome", Font.ITALIC, 12));
            String q = "select item_name as Item,item_model as 'Item Model',item_price as Price,item_quantity as Quantity,item_total as 'Total',item_discount_percent as 'Vat%',item_discount_total as 'Total Price',item_sell_date as 'Sell Date',customer_name as 'CName',paid as 'Paid',due as 'Due',bill_no as 'BillNo' from sale where item_sell_date between '" + val2 + "' and '" + val1 + "' ";
//            JOptionPane.showMessageDialog(null,q);
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Item not Fetched");
        }
    }

    public void LastMonth() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
//        yestarday date
            cal.add(Calendar.DAY_OF_MONTH, -30);
            //day befor yesterday
//        Calendar call = Calendar.getInstance();
//        call.add(Calendar.DAY_OF_MONTH, -2);
            String lastMonth = sdf.format(cal.getTime());
            newdate.setText(lastMonth);
            dateconversion();

            String val1 = date_txt.getText();
//                         JOptionPane.showMessageDialog(null,val1);
            String val2 = newdate.getText();
//                         JOptionPane.showMessageDialog(null,val2);
            JTableHeader header = table.getTableHeader();
            header.setForeground(Color.red);
            header.setFont(new Font("Tahome", Font.ITALIC, 12));
            String q = "select item_name as Item,item_model as 'Item Model',item_price as Price,item_quantity as Quantity,item_total as 'Total',item_discount_percent as 'Vat%',item_discount_total as 'Total Price',item_sell_date as 'Sell Date',customer_name as 'CName',paid as 'Paid',due as 'Due',bill_no as 'BillNo' from sale where item_sell_date between '" + val2 + "' and '" + val1 + "' ";
//            JOptionPane.showMessageDialog(null,q);
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Item not Fetched");
        }
    }

    public void LastYear() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
//        yestarday date
            cal.add(Calendar.DAY_OF_MONTH, -365);
            //day befor yesterday
//        Calendar call = Calendar.getInstance();
//        call.add(Calendar.DAY_OF_MONTH, -2);
            String lastWeek = sdf.format(cal.getTime());
            newdate.setText(lastWeek);
            dateconversion();

            String val1 = date_txt.getText();
//                         JOptionPane.showMessageDialog(null,val1);
            String val2 = newdate.getText();
//                         JOptionPane.showMessageDialog(null,val2);
            JTableHeader header = table.getTableHeader();
            header.setForeground(Color.red);
            header.setFont(new Font("Tahome", Font.ITALIC, 12));
            String q = "select item_name as Item,item_model as 'Item Model',item_price as Price,item_quantity as Quantity,item_total as 'Total',item_discount_percent as 'Vat%',item_discount_total as 'Total Price',item_sell_date as 'Sell Date',customer_name as 'CName',paid as 'Paid',due as 'Due',bill_no as 'BillNo' from sale where item_sell_date between '" + val2 + "' and '" + val1 + "' ";
//            JOptionPane.showMessageDialog(null,q);
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Item not Fetched");
        }
    }
    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        // TODO add your handling code here:
        if (combo.getSelectedItem() == "Today") {
            customclear();
            Today();
        } else if (combo.getSelectedItem() == "Select Report Type") {
            customclear();
        } else if (combo.getSelectedItem() == "Last Week") {
            customclear();
            LastWeek();
        } else if (combo.getSelectedItem() == "Last Month") {
            customclear();
            LastMonth();
        } else if (combo.getSelectedItem() == "Last Year") {
            customclear();
            LastYear();
        } else {
            dm = (DefaultTableModel) table.getModel();
            dm.setRowCount(0);
            date1.setVisible(true);
            date2.setVisible(true);
            jLabel2.setVisible(true);
            jLabel3.setVisible(true);
            jButton1.setVisible(true);
        }
    }//GEN-LAST:event_comboActionPerformed

    private void comboPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
//        date1.setVisible(false);
//            date2.setVisible(false);
//            jLabel2.setVisible(false);
//            jLabel3.setVisible(false);
    }//GEN-LAST:event_comboPopupMenuWillBecomeInvisible

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
//        int ii = table.getSelectedRow();
//        JOptionPane.showMessageDialog(null, ii);
        if (evt.getClickCount() == 2) {
//            JOptionPane.showMessageDialog(null, "mouse  clicked");
//            TableModel model1 = table.getModel();
            int i = table.getSelectedRow();
            dm = (DefaultTableModel) table.getModel();
            String billno = dm.getValueAt(i, 11).toString();

            //update sale bill no value
            String sql2 = "update bill set "
                    + "show_bill ='" + 0 + "'"
                    + "where bill_no = " + billno;
            try {
                pst = conn.prepareStatement(sql2);
                pst.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            HashMap m = new HashMap();
            try {
                JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Damak sales\\src\\showbill.jrxml");
                JasperReport ireport = JasperCompileManager.compileReport(jdesign);
                JasperPrint jprint = JasperFillManager.fillReport(ireport, m, conn);
                JasperViewer.viewReport(jprint, false);
                try {
                    dm = (DefaultTableModel) table.getModel();
                    JRTableModelDataSource datasource = new JRTableModelDataSource(dm);
                    String reportSource = "./example.jrxml";
                    JasperReport jr = JasperCompileManager.compileReport(reportSource);
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("title1", "title 1");
                    JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
                    JasperViewer.viewReport(jp, false);
                    //update sale bill no value
                    String sql22 = "update bill set "
                            + "show_bill ='" + 1 + "'"
                            + "where bill_no = " + billno;
                    try {
                        pst = conn.prepareStatement(sql22);
                        pst.execute();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (JRException ex) {
                Logger.getLogger(MonthlyReport.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
//            JOptionPane.showMessageDialog(null, "mouse not clicked");
        }
    }//GEN-LAST:event_tableMouseClicked

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
            java.util.logging.Logger.getLogger(MonthlyReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MonthlyReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MonthlyReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MonthlyReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MonthlyReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox combo;
    private javax.swing.JTextField date1;
    private javax.swing.JTextField date2;
    private javax.swing.JLabel date_txt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel newdate;
    private javax.swing.JTextField search_text;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
