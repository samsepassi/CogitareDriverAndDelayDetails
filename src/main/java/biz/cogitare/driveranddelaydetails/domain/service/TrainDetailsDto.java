/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.driveranddelaydetails.domain.service;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object used as mean to move data between Tasks and Services through the application.
 * @author Sam
 */
@Data
@AllArgsConstructor
public class TrainDetailsDto {

    private String TrainID;
    private String Station;
    private Date DepartureTime;
    private String DriverName;
    private String DepartureLateness;
}
