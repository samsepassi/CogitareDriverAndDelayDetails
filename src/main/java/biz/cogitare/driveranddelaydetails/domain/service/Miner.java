/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
