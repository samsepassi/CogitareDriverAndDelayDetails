package biz.cogitare.driveranddelaydetails.domain.service;

import java.io.Closeable;
import java.util.Collection;

/**
 * Represents a single data mining Task.
 * @author Sam
 * @param <TrainDetailsDto>
 */
public abstract class Miner<TrainDetailsDto> implements Closeable {

    /**
     * Receives a collection of TrainDetailsDto objects
     * @param input  - A collection
     */
    public abstract void feed(Collection<TrainDetailsDto> input);

    /**
     * Processes and perform the required task.
     */
    public abstract void startMining();

}
