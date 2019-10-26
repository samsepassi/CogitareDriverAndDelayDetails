/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.driveranddelaydetails.domain.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Sam
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "train_driver_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrainDriverDetails.findAll", query = "SELECT t FROM TrainDriverDetails t"),
    @NamedQuery(name = "TrainDriverDetails.findByJourneyid", query = "SELECT t FROM TrainDriverDetails t WHERE t.journeyid = :journeyid"),
    @NamedQuery(name = "TrainDriverDetails.findByJourneyStatus", query = "SELECT t FROM TrainDriverDetails t WHERE t.journeyStatus = :journeyStatus")})
public class TrainDriverDetails implements DomainEntity,Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "Journey_id")
    private Integer journeyid;
    @JoinColumn(name = "Train_id", referencedColumnName = "TrainID")
    @ManyToOne
    private Train trainid;
    @JoinColumn(name = "Start_Station", referencedColumnName = "StationID")
    @ManyToOne
    private Station startStation;
    @JoinColumn(name = "End_Station", referencedColumnName = "StationID")
    @ManyToOne
    private Station endStation;
    @JoinColumn(name = "Driver_Name", referencedColumnName = "Driver_Name")
    @ManyToOne
    private Driver driverName;
    @Enumerated(EnumType.STRING)
    @Column(name = "Journey_Status")
    private JournyStatus journeyStatus;

}
