package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Offer;
import repository.OfferRepository;
import service.OfferService;

public class OfferServiceImpl extends BaseEntityServiceImpl<Offer,Integer, OfferRepository> implements OfferService {
    public OfferServiceImpl(OfferRepository repository) {
        super(repository);
    }
}
