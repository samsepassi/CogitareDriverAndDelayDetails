/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.driveranddelaydetails.application.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Class responsible to provide a physical connection to a Data Base.
 * @author Sam
 */
public interface DataBaseConnection {

    /**
     * Opens and returns a Session object.
     * @return
     */
    public Session getSession();

    /**
     * Destroys any open resources.
     */
    public void kill();
    
    /**
     * Returns a SessionFactory instance.
     * @return
     */
    public SessionFactory getSessionFacotry();

}
