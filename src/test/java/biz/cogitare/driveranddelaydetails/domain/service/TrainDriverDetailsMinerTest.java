package biz.cogitare.driveranddelaydetails.domain.service;

import biz.cogitare.driveranddelaydetails.application.exceptions.FileReaderException;
import biz.cogitare.driveranddelaydetails.application.service.DataBaseConnection;
import biz.cogitare.driveranddelaydetails.application.service.FileReaderService;
import biz.cogitare.driveranddelaydetails.domain.model.Driver;
import biz.cogitare.driveranddelaydetails.domain.model.Station;
import biz.cogitare.driveranddelaydetails.domain.model.Train;
import biz.cogitare.driveranddelaydetails.domain.model.TrainDriverDetails;
import biz.cogitare.driveranddelaydetails.infrastructure.application.FileReaderServiceImplementation;
import biz.cogitare.driveranddelaydetails.infrastructure.application.data.DomainMarshallerServiceImplementation;
import biz.cogitare.driveranddelaydetails.infrastructure.domain.service.TrainDriverDetailsMinerImplementation;
import biz.cogitare.driveranddelaydetails.infrastructure.peristence.DataBaseConnectionImplementation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sam
 */
public class TrainDriverDetailsMinerTest {

    private final String resourceURI = "MinerTestData.txt";
    private final String header = "TrainId|Station|ActualDepartureTime|DriverName|DepartureLatenessInSeconds";
    List<TrainDetailsDto> minerDtoFeed = new ArrayList<>();

    private final Miner<TrainDetailsDto> trainDriverDetailsMiner = new TrainDriverDetailsMinerImplementation();
    private final FileReaderService fileReaderService = new FileReaderServiceImplementation();
    private final DomainMarshallerService marshallerService = new DomainMarshallerServiceImplementation();
    private final DataBaseConnection dataBaseConnection = new DataBaseConnectionImplementation();

    @Before
    public void setup() throws FileReaderException {
        List<String[]> rawContents = fileReaderService.readFile(resourceURI, header);
        minerDtoFeed = marshallerService.serializeToDomainDTO(rawContents);
        trainDriverDetailsMiner.feed(minerDtoFeed);
    }

    @Test
    public void testMine() throws IOException {

        trainDriverDetailsMiner.startMining();
        Session session = dataBaseConnection.getSession();
        try {
            Query query = session.createQuery("from Train");
            List<Train> trainList = query.list();
            assertThat(trainList.size()).isEqualTo(4);
            List<String> trainIds = trainList.stream().map(Train::getTrainID).collect(Collectors.toList());
            minerDtoFeed.forEach(eachDto -> {
                assertThat(trainIds.contains(eachDto.getTrainID())).isTrue();
            });

            query = session.createQuery("from Driver");
            List<Driver> driverList = query.list();
            assertThat(driverList.size()).isEqualTo(4);
            List<String> driverNames = driverList.stream().map(Driver::getDriverName).collect(Collectors.toList());
            minerDtoFeed.forEach(eachDto -> {
                assertThat(driverNames.contains(eachDto.getDriverName())).isTrue();
            });

            query = session.createQuery("from Station");
            List<Station> StationList = query.list();
            assertThat(StationList.size()).isEqualTo(3);
            List<String> stationIds = StationList.stream().map(Station::getStationID).collect(Collectors.toList());
            minerDtoFeed.forEach(eachDto -> {
                assertThat(stationIds.contains(eachDto.getStation())).isTrue();
            });

            query = session.createQuery("from TrainDriverDetails");
            List<TrainDriverDetails> trainDriverDetailsList = query.list();
            assertThat(trainDriverDetailsList.size()).isEqualTo(4);
        } finally {
            session.close();
        }
    }

}
