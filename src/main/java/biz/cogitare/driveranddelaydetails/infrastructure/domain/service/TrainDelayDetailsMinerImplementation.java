package biz.cogitare.driveranddelaydetails.infrastructure.domain.service;

import biz.cogitare.driveranddelaydetails.application.service.DataBaseConnection;
import biz.cogitare.driveranddelaydetails.domain.model.DomainRepository;
import biz.cogitare.driveranddelaydetails.domain.model.Station;
import biz.cogitare.driveranddelaydetails.domain.model.Train;
import biz.cogitare.driveranddelaydetails.domain.model.TrainDelayDetails;
import biz.cogitare.driveranddelaydetails.domain.service.Miner;
import biz.cogitare.driveranddelaydetails.domain.service.TrainDetailsDto;
import biz.cogitare.driveranddelaydetails.infrastructure.peristence.DataBaseConnectionImplementation;
import biz.cogitare.driveranddelaydetails.infrastructure.peristence.DomainRepositoryImplementation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Sam
 */
public class TrainDelayDetailsMinerImplementation extends Miner<TrainDetailsDto> {

    private List<TrainDetailsDto> trainDetailsList;
    final private DataBaseConnection dataBaseConnection = new DataBaseConnectionImplementation();
    final private Session session = dataBaseConnection.getSession();

    final private DomainRepository<Station> stationRepository = new DomainRepositoryImplementation<>();
    final private DomainRepository<Train> trainRepository = new DomainRepositoryImplementation<>();
    final private DomainRepository<TrainDelayDetails> trainDelayRepository = new DomainRepositoryImplementation<>();
    
    @Override
    public void feed(final Collection<TrainDetailsDto> input) {
        trainDetailsList = new ArrayList<>(input);
    }

    @Override
    public void startMining() {

        stationRepository.setSession(session);
        trainRepository.setSession(session);
        trainDelayRepository.setSession(session);

        Transaction transaction = session.beginTransaction();
        try {
            trainDetailsList.forEach(detailsDto -> {
                if (!detailsDto.getDepartureLateness().equalsIgnoreCase("NA") && null != detailsDto.getDepartureTime()) {
                    Train train = trainRepository.save(new Train(detailsDto.getTrainID()));
                    Station station = stationRepository.save(new Station(detailsDto.getStation()));
                    TrainDelayDetails delayDetails = new TrainDelayDetails();
                    delayDetails.setTrainid(train);
                    delayDetails.setStation(station);
                    delayDetails.setDeparturetime(detailsDto.getDepartureTime());
                    delayDetails.setDeparturelateness(Integer.parseInt(detailsDto.getDepartureLateness()));
                    trainDelayRepository.save(delayDetails);
                }
            });
            transaction.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void close() throws IOException {
        dataBaseConnection.kill();
    }

}
