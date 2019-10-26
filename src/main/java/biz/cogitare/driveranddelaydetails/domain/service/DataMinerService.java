package biz.cogitare.driveranddelaydetails.domain.service;

/**
 * Executer class responsible to run a single or multiple Data Mining tasks. 
 * @author Sam
 */
public interface DataMinerService {

    /**
     * Executes one or more Miner tasks.
     */
    public void startMiningTasks();
}
