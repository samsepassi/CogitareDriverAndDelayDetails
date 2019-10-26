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
@Table(name = "drivers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Driver.findAll", query = "SELECT d FROM Driver d"),
    @NamedQuery(name = "Driver.findByDriverName", query = "SELECT d FROM Driver d WHERE d.driverName = :driverName")})
public class Driver implements DomainEntity,Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Driver_Name")
    private String driverName;
    @OneToMany(mappedBy = "driverName")
    private Collection<TrainDriverDetails> trainDriverDetailsCollection;

    public Driver(String driverName) {
        this.driverName = driverName;
    }
    
    
}
