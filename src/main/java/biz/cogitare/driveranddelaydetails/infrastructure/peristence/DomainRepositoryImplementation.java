package biz.cogitare.driveranddelaydetails.infrastructure.peristence;

import biz.cogitare.driveranddelaydetails.domain.model.DomainRepository;
import org.hibernate.Session;

/**
 *
 * @author Sam
 */
public class DomainRepositoryImplementation<DomainEntity> extends DomainRepository<DomainEntity> {

    @Override
    public DomainEntity save(DomainEntity entity) {
        Session session = super.getSession();
        return (DomainEntity) session.merge(entity);
    }

}
