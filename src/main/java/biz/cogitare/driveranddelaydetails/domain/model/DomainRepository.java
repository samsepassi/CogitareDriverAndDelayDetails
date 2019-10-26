/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.driveranddelaydetails.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;

/**
 * Performs CRUD operations for domain entity objects.
 * @author Sam
 * @param <DomainEntity>
 */
@Getter
@Setter
public abstract class DomainRepository<DomainEntity> {

    private Session session;

    /**
     * Saves entity object to data store.
     * @param entity
     * @return - The entity.
     */
    public abstract DomainEntity save(DomainEntity entity);
}
