package biz.cogitare.driveranddelaydetails.infrastructure.domain.service;

import biz.cogitare.driveranddelaydetails.application.service.DataBaseConnection;
import biz.cogitare.driveranddelaydetails.domain.model.DomainRepository;
import biz.cogitare.driveranddelaydetails.domain.model.Driver;
import biz.cogitare.driveranddelaydetails.domain.model.JournyStatus;
import biz.cogitare.driveranddelaydetails.domain.model.Station;
import biz.cogitare.driveranddelaydetails.domain.model.Train;
import biz.cogitare.driveranddelaydetails.domain.model.TrainDriverDetails;
import biz.cogitare.driveranddelaydetails.domain.service.DataAggregatorService;
import biz.cogitare.driveranddelaydetails.domain.service.Miner;
import biz.cogitare.driveranddelaydetails.domain.service.TrainDetailsDto;
import biz.cogitare.driveranddelaydetails.infrastructure.application.data.DataAggregatorServiceImplementation;
import biz.cogitare.driveranddelaydetails.infrastructure.peristence.DataBaseConnectionImplementation;
import biz.cogitare.driveranddelaydetails.infrastructure.peristence.DomainRepositoryImplementation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Sam
 */
public class TrainDriverDetailsMinerImplementation extends Miner<TrainDetailsDto> {

    private List<TrainDetailsDto> trainDetailsList;
    final private DataAggregatorService aggregatorService = new DataAggregatorServiceImplementation();
    final private DataBaseConnection dataBaseConnection = new DataBaseConnectionImplementation();
    final private Session session = dataBaseConnection.getSession();

    final private DomainRepository<TrainDriverDetails> trainDriverDetailsRepository = new DomainRepositoryImplementation<>();
    final private DomainRepository<Driver> driverRepository = new DomainRepositoryImplementation<>();
    final private DomainRepository<Station> stationRepository = new DomainRepositoryImplementation<>();
    final private DomainRepository<Train> trainRepository = new DomainRepositoryImplementation<>();

    @Override
    public void feed(Collection<TrainDetailsDto> input) {
        trainDetailsList = new ArrayList<>(input);
    }

    @Override
    public void startMining() {

        Map<String, Map<String, List<TrainDetailsDto>>> aggregatedDto = aggregatorService.groupByTrainDriver(trainDetailsList);

        driverRepository.setSession(session);
        stationRepository.setSession(session);
        trainRepository.setSession(session);
        trainDriverDetailsRepository.setSession(session);

        Transaction transaction = session.beginTransaction();
        try {
            aggregatedDto.forEach((trainID, DetailsMap) -> {
                Train train = trainRepository.save(new Train(trainID));
                DetailsMap.forEach((driverName, Records) -> {

                    Driver driver = driverRepository.save(new Driver(driverName));

                    Station startStation = stationRepository.save(new Station(Records.get(0).getStation()));

                    Station endStation = stationRepository.save(new Station(Records.get(Records.size() - 1).getStation()));

                    TrainDriverDetails trainDriverDetails = new TrainDriverDetails();
                    trainDriverDetails.setDriverName(driver);
                    trainDriverDetails.setTrainid(train);
                    trainDriverDetails.setStartStation(startStation);
                    trainDriverDetails.setEndStation(endStation);
                    trainDriverDetails.setJourneyStatus((Records.get(Records.size() - 1).getDepartureLateness().equalsIgnoreCase("NA") ? JournyStatus.INPROGRESS : JournyStatus.COMPLETED));
                    trainDriverDetailsRepository.save(trainDriverDetails);

                });
            });
            transaction.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void close() throws IOException {
        dataBaseConnection.kill();;
    }

}
