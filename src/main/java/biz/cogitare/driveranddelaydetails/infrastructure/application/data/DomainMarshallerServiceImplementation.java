/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.driveranddelaydetails.infrastructure.application.data;

import biz.cogitare.driveranddelaydetails.application.service.DateUtils;
import biz.cogitare.driveranddelaydetails.domain.service.DomainMarshallerService;
import biz.cogitare.driveranddelaydetails.domain.service.TrainDetailsDto;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Sam
 */
public class DomainMarshallerServiceImplementation implements DomainMarshallerService {

    @Override
    public List<TrainDetailsDto> serializeToDomainDTO(final List<String[]> rawData) {
        List<TrainDetailsDto> refinedDataList = rawData.stream()
                .map(eachRawData -> {
                    return new TrainDetailsDto(
                            eachRawData[0], eachRawData[1], DateUtils.stringToDate(eachRawData[2]), eachRawData[3], eachRawData[4]);
                }).collect(Collectors.toList());
        return refinedDataList;
    }

}
