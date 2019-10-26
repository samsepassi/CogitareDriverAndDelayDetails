/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
