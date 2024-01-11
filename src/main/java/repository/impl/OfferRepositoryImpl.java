package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Offer;
import repository.OfferRepository;

import javax.persistence.EntityManager;

public class OfferRepositoryImpl extends BaseEntityRepositoryImpl<Offer, Integer> implements OfferRepository {
    public OfferRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Offer> getEntityClass() {
        return Offer.class;
    }
}
