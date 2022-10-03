package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Subscription;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SubscriptionRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Subscription save(Subscription param){return em.merge(param);}
}
