package biz.cogitare.driveranddelaydetails.infrastructure.peristence;

import biz.cogitare.driveranddelaydetails.application.service.DataBaseConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Sam
 */
public class DataBaseConnectionImplementation implements DataBaseConnection {

    private static final SessionFactory sessionFactory = new Configuration().configure()
            .buildSessionFactory();
    private Session session;

    @Override
    public Session getSession() {
        
        session = sessionFactory.openSession();
        return session;
    }

    @Override
    public void kill() {
        sessionFactory.close();
    }

    @Override
    public boolean isSessionActive() {
        return session.isOpen();
    }

    @Override
    public boolean isOpen() {
        return sessionFactory.isOpen();
    }

}
