package biz.cogitare.driveranddelaydetails.application.service;

import org.hibernate.Session;

/**
 * Class responsible to provide a physical connection to a Data Base.
 *
 * @author Sam
 */
public interface DataBaseConnection {

    /**
     * Opens and returns a Session object.
     *
     * @return
     */
    public Session getSession();

    /**
     * Destroys any open resources.
     */
    public void kill();

    /**
     * Checks whether current session is still open or not.
     *
     * @return
     */
    public boolean isSessionActive();

    /**
     * Checks whether SessionFactory is still open or not.
     *
     * @return
     */
    public boolean isOpen();

}
