package biz.cogitare.driveranddelaydetails.infrastructure.interfaces;

import biz.cogitare.driveranddelaydetails.domain.service.DataMinerService;
import biz.cogitare.driveranddelaydetails.infrastructure.domain.service.DataMinerServiceImplementation;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sam
 */
public class CLI {

    private static final Logger LOG = Logger.getLogger(CLI.class.getName());
    

    public static void main(String[] args) {
        LOG.log(Level.INFO, "Initiating all the scheduled data mining Tasks.");
        DataMinerService dataMinerService = new DataMinerServiceImplementation();
        dataMinerService.startMiningTasks();
        LOG.log(Level.INFO, "Tasks finished.");
    }
}
