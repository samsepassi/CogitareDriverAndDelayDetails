package biz.cogitare.driveranddelaydetails.domain.model;

import biz.cogitare.driveranddelaydetails.application.service.DataBaseConnection;
import biz.cogitare.driveranddelaydetails.infrastructure.peristence.DomainRepositoryImplementation;
import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Sam
 */
@RunWith(MockitoJUnitRunner.class)
public class DomainRepositoryTest {

    @Mock
    DataBaseConnection dataBaseConnection;

    @Mock
    Session session;

    private Train train;
    private Driver driver;
    private Station station;
    private TrainDelayDetails trainDelayDetails;
    private TrainDriverDetails trainDriverDetails;

    @Before
    public void setup() {
        when(dataBaseConnection.getSession()).thenReturn(session);
        when(session.merge(any((DomainEntity.class))))
                .then(entity -> entity.getArguments()[0]);

        train = new Train("Test_Train");
        driver = new Driver("Test_Driver");
        station = new Station("Test_Station");
        trainDelayDetails = new TrainDelayDetails();
        trainDelayDetails.setJourneyid(123);
        trainDriverDetails = new TrainDriverDetails();
        trainDriverDetails.setJourneyid(12345);

    }

    @Test()
    public void testTrainRepositoy() {
        DomainRepository<Train> trainRepositoy = new DomainRepositoryImplementation<>();
        trainRepositoy.setSession(session);
        Train savedTrain = trainRepositoy.save(train);
        assertThat(savedTrain.getTrainID()).isEqualTo(train.getTrainID());
    }

    @Test()
    public void testDriverRepositoy() {
        DomainRepository<Driver> driverRepositoy = new DomainRepositoryImplementation<>();
        driverRepositoy.setSession(session);
        Driver savedDriver = driverRepositoy.save(driver);
        assertThat(savedDriver.getDriverName()).isEqualTo(driver.getDriverName());
    }

    @Test()
    public void testStationRepositoy() {
        DomainRepository<Station> stationRepositoy = new DomainRepositoryImplementation<>();
        stationRepositoy.setSession(session);
        Station sstationTrain = stationRepositoy.save(station);
        assertThat(sstationTrain.getStationID()).isEqualTo(station.getStationID());
    }

    @Test()
    public void testTrainDelayDetailsRepositoy() {
        DomainRepository<TrainDelayDetails> delayDetailsRepositoy = new DomainRepositoryImplementation<>();
        delayDetailsRepositoy.setSession(session);
        TrainDelayDetails delayDetailsEntity = delayDetailsRepositoy.save(trainDelayDetails);
        assertThat(delayDetailsEntity.getJourneyid()).isEqualTo(trainDelayDetails.getJourneyid());
    }

    @Test()
    public void testTrainDriverDetailsRepositoy() {
        DomainRepository<TrainDriverDetails> trainDriverRepositoy = new DomainRepositoryImplementation<>();
        trainDriverRepositoy.setSession(session);
        TrainDriverDetails trainDriverDetailsEntity = trainDriverRepositoy.save(trainDriverDetails);
        assertThat(trainDriverDetailsEntity.getJourneyid()).isEqualTo(trainDriverDetails.getJourneyid());
    }
}
