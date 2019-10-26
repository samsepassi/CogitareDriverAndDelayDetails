package biz.cogitare.driveranddelaydetails.domain.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "train_delay_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrainDelayDetails.findAll", query = "SELECT t FROM TrainDelayDetails t"),
    @NamedQuery(name = "TrainDelayDetails.findByJourneyid", query = "SELECT t FROM TrainDelayDetails t WHERE t.journeyid = :journeyid"),
    @NamedQuery(name = "TrainDelayDetails.findByDeparturetime", query = "SELECT t FROM TrainDelayDetails t WHERE t.departuretime = :departuretime"),
    @NamedQuery(name = "TrainDelayDetails.findByDeparturelateness", query = "SELECT t FROM TrainDelayDetails t WHERE t.departurelateness = :departurelateness")})
public class TrainDelayDetails implements DomainEntity,Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "Journey_id")
    private Integer journeyid;
    @Column(name = "Departure_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departuretime;
    @Column(name = "Departure_lateness")
    private Integer departurelateness;
    @JoinColumn(name = "Station", referencedColumnName = "StationID")
    @ManyToOne
    private Station station;
    @JoinColumn(name = "Train_id", referencedColumnName = "TrainID")
    @ManyToOne
    private Train trainid;
    
}
