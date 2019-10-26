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
@Table(name = "trains")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Train.findAll", query = "SELECT t FROM Train t"),
    @NamedQuery(name = "Train.findByTrainID", query = "SELECT t FROM Train t WHERE t.trainID = :trainID")})
public class Train implements DomainEntity,Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TrainID")
    private String trainID;
    @OneToMany(mappedBy = "trainid")
    private Collection<TrainDelayDetails> trainDelayDetailsCollection;
    @OneToMany(mappedBy = "trainid")
    private Collection<TrainDriverDetails> trainDriverDetailsCollection;

    public Train(String trainID) {
        this.trainID = trainID;
    }

    
}
