package biz.cogitare.driveranddelaydetails.domain.service;

import java.util.List;
import java.util.Map;

/**
 * Performs aggregation on a collection of domain objects.
 * @author Sam
 */
public interface DataAggregatorService {

    /**
     *
     * @param listOfDto - A list of TrainDetailsDto objects.
     * @return - A map grouped by trainID, Train Driver with corresponding detailed records.
     */
    public Map<String, Map<String, List<TrainDetailsDto>>> groupByTrainDriver(final List<TrainDetailsDto> listOfDto);
}
