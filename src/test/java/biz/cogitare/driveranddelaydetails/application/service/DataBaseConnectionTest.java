//package biz.cogitare.driveranddelaydetails.application.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import static org.mockito.Mockito.when;
//import org.mockito.runners.MockitoJUnitRunner;
//
///**
// *
// * @author Sam
// */
//@RunWith(MockitoJUnitRunner.class)
//public class DataBaseConnectionTest {
//
//    @Mock
//    private DataBaseConnection dataBaseConnection;
//
//    @Mock
//    private SessionFactory sessionFactory;
//
//    @Mock
//    private Session session;
//
//    @Before
//    public void setup() {
//        when(sessionFactory.openSession()).thenReturn(session);
//    }
//
//    @Test
//    public void testCheckingActiveDatabaseConnection() {
//        //When connection is active both session and session factory is open
//        when(session.isOpen()).thenReturn(true);
//        when(sessionFactory.isOpen()).thenReturn(true);
//        
//        //Create a new session and checking the returned values
//        dataBaseConnection.getSession();
//        assertThat(dataBaseConnection.isOpen()).isTrue();
//        assertThat(dataBaseConnection.isSessionActive()).isTrue();
//    }
//
//    //@Test
//    public void testKill() {
//        dataBaseConnection.kill();
//        //Killing the database connection should close the session and session factory.
//        when(sessionFactory.isOpen()).thenReturn(false);
//        when(session.isOpen()).thenReturn(false);
//
//        
//        assertThat(dataBaseConnection.isOpen()).isFalse();
//        assertThat(dataBaseConnection.isSessionActive()).isFalse();
//    }
//
//}
