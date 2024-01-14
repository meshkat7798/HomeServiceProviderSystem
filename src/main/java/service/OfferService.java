package service;

import base.service.BaseEntityService;
import entity.Offer;
import entity.Order;
import entity.user.Specialist;

public interface OfferService extends BaseEntityService<Offer,Integer> {
    Offer setOfferInfo(Order order, Specialist specialist);
}
