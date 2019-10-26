/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.driveranddelaydetails.domain.service;

import java.util.List;

/**
 * Marshalling a list of raw data to a list of domain objects.
 * @author Sam
 */
public interface DomainMarshallerService {

    /**
     * Transforming a list of string to a map containing domain object.
     * @param rawData - a list of strings to be marshalled.
     * @return
     */
    public List<TrainDetailsDto> serializeToDomainDTO(final List<String[]> rawData);
}
