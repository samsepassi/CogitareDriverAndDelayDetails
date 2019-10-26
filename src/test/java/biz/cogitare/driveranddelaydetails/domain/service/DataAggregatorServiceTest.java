package biz.cogitare.driveranddelaydetails.domain.service;

import biz.cogitare.driveranddelaydetails.infrastructure.application.data.DataAggregatorServiceImplementation;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sam
 */
public class DataAggregatorServiceTest {

    private final DataAggregatorService aggregatorService = new DataAggregatorServiceImplementation();
    private final List<TrainDetailsDto> dtoList = new ArrayList<>();

    private final TrainDetailsDto dto1 = new TrainDetailsDto("2F29", "Wimbledon", new Date(), "John Scott", "40");
    private final TrainDetailsDto dto2 = new TrainDetailsDto("2F29", "Surbiton", new Date(), "John Scott", "20");
    private final TrainDetailsDto dto3 = new TrainDetailsDto("2F29", "Esher", new Date(), "Shane Watson", "180");
    private final TrainDetailsDto dto4 = new TrainDetailsDto("2C33", "Hersham", new Date(), "Barry Williams", "10");
    private final TrainDetailsDto dto5 = new TrainDetailsDto("2C33", "Staines", new Date(), "Barry Williams", "0");

    @Before
    public void setup() {
        dtoList.add(dto1);
        dtoList.add(dto2);
        dtoList.add(dto3);
        dtoList.add(dto4);
        dtoList.add(dto5);
    }
    
    @Test
    public void testGroupByTrainDriver(){
        Map<String, Map<String, List<TrainDetailsDto>>> aggregateResults = aggregatorService.groupByTrainDriver(dtoList);
        assertThat(aggregateResults.containsKey("2F29")).isTrue();
        assertThat(aggregateResults.containsKey("2C33")).isTrue();
        
        Map<String, List<TrainDetailsDto>> map1 = aggregateResults.get("2F29");
        assertThat(map1.size()).isEqualTo(2);
        
        assertThat(map1.containsKey("John Scott")).isTrue();
        assertThat(map1.containsKey("Shane Watson")).isTrue();
        
        List<TrainDetailsDto> list1 = map1.get("John Scott");
        assertThat(list1.size()).isEqualTo(2);
        List<TrainDetailsDto> list2 = map1.get("Shane Watson");
        assertThat(list2.size()).isEqualTo(1);
        
        Map<String, List<TrainDetailsDto>> map2= aggregateResults.get("2C33");
        assertThat(map2.size()).isEqualTo(1);
        
        assertThat(map2.containsKey("Barry Williams")).isTrue();
        
        List<TrainDetailsDto> list3 = map2.get("Barry Williams");
        assertThat(list3.size()).isEqualTo(2);   
    }

}
