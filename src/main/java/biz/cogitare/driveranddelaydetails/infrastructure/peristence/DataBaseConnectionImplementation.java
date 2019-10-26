/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    @Override
    public Session getSession() {
            return sessionFactory.openSession();
    }

    @Override
    public void kill() {
        sessionFactory.close();
    }

    @Override
    public SessionFactory getSessionFacotry() { //TODO get rid of this ?
        return sessionFactory;
    }

}
