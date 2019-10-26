package biz.cogitare.driveranddelaydetails.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Sam
 */
@RunWith(MockitoJUnitRunner.class)
public class DataBaseConnectionTest {

    @Mock
    private DataBaseConnection dataBaseConnection;

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Before
    public void setup() {
        when(dataBaseConnection.getSession()).thenReturn(session);
        when(dataBaseConnection.getSessionFacotry()).thenReturn(sessionFactory);
        when(session.isOpen()).thenReturn(true);

    }

    @Test
    public void testGetSesssion() {
        Session session = dataBaseConnection.getSession();
        assertThat(session.isOpen()).isTrue();
    }

//    @Test
//    public void testKill() {
//        SessionFactory sessionFactory = dataBaseConnection.getSessionFacotry();
//        assertThat(sessionFactory.isOpen()).isTrue();
//
//        dataBaseConnection.kill();
//        assertThat(sessionFactory.isOpen()).isFalse();
//    }

}
