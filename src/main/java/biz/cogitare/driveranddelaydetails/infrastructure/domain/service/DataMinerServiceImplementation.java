/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.driveranddelaydetails.infrastructure.domain.service;

import biz.cogitare.driveranddelaydetails.application.exceptions.FileReaderException;
import biz.cogitare.driveranddelaydetails.application.service.FileReaderService;
import biz.cogitare.driveranddelaydetails.domain.service.DataMinerService;
import biz.cogitare.driveranddelaydetails.domain.service.DomainMarshallerService;
import biz.cogitare.driveranddelaydetails.domain.service.Miner;
import biz.cogitare.driveranddelaydetails.domain.service.TrainDetailsDto;
import biz.cogitare.driveranddelaydetails.infrastructure.application.FileReaderServiceImplementation;
import biz.cogitare.driveranddelaydetails.infrastructure.application.data.DomainMarshallerServiceImplementation;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sam
 */
public class DataMinerServiceImplementation implements DataMinerService {

    final private String header = "TrainId|Station|ActualDepartureTime|DriverName|DepartureLatenessInSeconds";
    private static final Logger LOG = Logger.getLogger(DataMinerServiceImplementation.class.getName());

    final private FileReaderService fileReaderService = new FileReaderServiceImplementation();
    final private DomainMarshallerService marshallerService = new DomainMarshallerServiceImplementation();

    Miner<TrainDetailsDto> trainDriverDetailsMiner = new TrainDriverDetailsMinerImplementation();
    Miner<TrainDetailsDto> trainDelayDetailsMiner = new TrainDelayDetailsMinerImplementation();

    @Override
    public void startMiningTasks() {
        try {
            final List<String[]> rawData = fileReaderService.readFile("DriverAndDelayDetails.txt", header);
            final List<TrainDetailsDto> refinedDomainData = marshallerService.serializeToDomainDTO(rawData);
            try {

                trainDriverDetailsMiner.feed(refinedDomainData);
                trainDriverDetailsMiner.startMining();

                trainDelayDetailsMiner.feed(refinedDomainData);
                trainDelayDetailsMiner.startMining();
            } finally {
                try {
                    trainDriverDetailsMiner.close();
                    trainDelayDetailsMiner.close();
                } catch (IOException ex) {
                    Logger.getLogger(DataMinerServiceImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (FileReaderException ex) {
            LOG.log(Level.SEVERE, "DataMinerService failed due to an exception : {0}", ex.getMessage());
        }
    } 
    //TODO : exceptions

}
