package biz.cogitare.driveranddelaydetails.domain.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Sam
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "stations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Station.findAll", query = "SELECT s FROM Station s"),
    @NamedQuery(name = "Station.findByStationID", query = "SELECT s FROM Station s WHERE s.stationID = :stationID")})
public class Station implements DomainEntity,Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "StationID")
    private String stationID;
    @OneToMany(mappedBy = "station")
    private Collection<TrainDelayDetails> trainDelayDetailsCollection;
    @OneToMany(mappedBy = "startStation")
    private Collection<TrainDriverDetails> trainDriverDetailsCollection;
    @OneToMany(mappedBy = "endStation")
    private Collection<TrainDriverDetails> trainDriverDetailsCollection1;

    public Station(String stationID) {
        this.stationID = stationID;
    }
    
    
}
