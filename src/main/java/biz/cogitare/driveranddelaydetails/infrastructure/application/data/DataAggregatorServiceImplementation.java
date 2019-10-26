/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.driveranddelaydetails.infrastructure.application.data;

import biz.cogitare.driveranddelaydetails.domain.service.DataAggregatorService;
import biz.cogitare.driveranddelaydetails.domain.service.TrainDetailsDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Sam
 */
public class DataAggregatorServiceImplementation implements DataAggregatorService {

    @Override
    public Map<String, Map<String, List<TrainDetailsDto>>> groupByTrainDriver(final List<TrainDetailsDto> listOfDto) {
        Map<String, Map<String, List<TrainDetailsDto>>> aggregatedDto
                = listOfDto.stream().collect(Collectors.groupingBy(TrainDetailsDto::getTrainID,
                        Collectors.groupingBy(TrainDetailsDto::getDriverName, LinkedHashMap::new, Collectors.toList())));
        return aggregatedDto;
    }

}
