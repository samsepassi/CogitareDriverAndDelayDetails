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
        LOG.log(Level.INFO, "Starting Mining tasks ...");
        try {
            final List<String[]> rawData = fileReaderService.readFile("DriverAndDelayDetails.txt", header);
            final List<TrainDetailsDto> refinedDomainData = marshallerService.serializeToDomainDTO(rawData);
            try {
                LOG.log(Level.INFO, "Starting TrainDriverDetailsMiner task.");
                trainDriverDetailsMiner.feed(refinedDomainData);
                trainDriverDetailsMiner.startMining();

                LOG.log(Level.INFO, "Starting TrainDelayDetailsMiner task.");
                trainDelayDetailsMiner.feed(refinedDomainData);
                trainDelayDetailsMiner.startMining();

                LOG.log(Level.INFO, "Mining tasks finished.");
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
}
