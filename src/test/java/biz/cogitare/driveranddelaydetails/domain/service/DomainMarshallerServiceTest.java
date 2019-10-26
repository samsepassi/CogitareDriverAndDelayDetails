package biz.cogitare.driveranddelaydetails.domain.service;

import biz.cogitare.driveranddelaydetails.infrastructure.application.data.DomainMarshallerServiceImplementation;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sam
 */
public class DomainMarshallerServiceTest {

    private final DomainMarshallerService marshallerService = new DomainMarshallerServiceImplementation();
    List<String[]> rawData = new ArrayList<>();

    @Before
    public void setup() {
        String[] record1 = new String[]{"2C33", "Twickenham", "2018-09-04T15:09:00", "Barry Williams", "20"};
        String[] record2 = new String[]{"5Y41", "Clapham Yard", "2018-09-04T14:50:00", "Andrew Lees", "40"};
        rawData.add(record1);
        rawData.add(record2);
    }

    @Test
    public void testSerializeToDomainDTO() {
        List<TrainDetailsDto> detailsDtos = marshallerService.serializeToDomainDTO(rawData);

        assertThat(detailsDtos.size()).isEqualTo(2);

        TrainDetailsDto dto = detailsDtos.get(0);
        assertThat(dto).isNotNull();
        assertThat(dto.getTrainID()).isEqualTo("2C33");
        assertThat(dto.getStation()).isEqualTo("Twickenham");
        assertThat(dto.getDriverName()).isEqualTo("Barry Williams");
        assertThat(dto.getDepartureLateness()).isEqualTo("20");
        
        Date date = dto.getDepartureTime();
        
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

        assertThat(hours).isEqualTo(15);
        assertThat(minute).isEqualTo(9);
        assertThat(second).isEqualTo(0);
       
    }
}
