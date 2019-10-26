/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.driveranddelaydetails.application.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 *
 * @author Sam
 */
public class DateUtilsTest {

    private final String TimeStamp = "2018-09-04T14:40:00";

    @Test
    public void testValidStringToDate() {
        Date date = DateUtils.stringToDate(TimeStamp);

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        assertThat(year).isEqualTo(2018);
        assertThat(month).isEqualTo(9);
        assertThat(day).isEqualTo(4);

        assertThat(hours).isEqualTo(14);
        assertThat(minute).isEqualTo(40);
        assertThat(second).isEqualTo(0);

    }

    @Test
    public void testInvalidStringToDate() {
        Date date = DateUtils.stringToDate("NA");
        assertThat(date).isNull();
    }
}
