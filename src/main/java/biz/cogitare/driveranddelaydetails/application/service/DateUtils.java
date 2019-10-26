package biz.cogitare.driveranddelaydetails.application.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Date utility class. 
 * @author Sam
 */
public class DateUtils {

    private static final Logger LOG = Logger.getLogger(DateUtils.class.getName());

    /**
     * Convert a String timestamp to Java Date.
     * @param timeStamp - In form of String
     * @return - A Java Date class.
     */
    public static Date stringToDate(String timeStamp) {
        try {
            if(!timeStamp.equalsIgnoreCase("NA")){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return formatter.parse(timeStamp);
            }else{
                return null;
            }
        } catch (ParseException ex) {
            LOG.log(Level.SEVERE, null, ex);
            //ex.printStackTrace();
            return null;
        }
    }
}
