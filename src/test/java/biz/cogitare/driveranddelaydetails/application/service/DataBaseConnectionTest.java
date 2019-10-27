package biz.cogitare.driveranddelaydetails.application.service;

import biz.cogitare.driveranddelaydetails.infrastructure.peristence.DataBaseConnectionImplementation;
import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.Session;
import org.junit.Test;

/**
 *
 * @author Sam
 */
public class DataBaseConnectionTest {

    private final DataBaseConnection dataBaseConnection = new DataBaseConnectionImplementation();

    @Test
    public void testActive() {
        Session session = dataBaseConnection.getSession();
        assertThat(session.isOpen()).isTrue();
        assertThat(dataBaseConnection.isOpen()).isTrue();
        assertThat(dataBaseConnection.isSessionActive()).isTrue();

        session.close();
        assertThat(session.isOpen()).isFalse();
        assertThat(dataBaseConnection.isSessionActive()).isFalse();
    }

}
