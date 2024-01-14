package service;

import base.service.BaseEntityService;
import entity.Offer;
import entity.MyOrder;
import entity.user.Specialist;

public interface OfferService extends BaseEntityService<Offer,Integer> {
    Offer setOfferInfo(MyOrder myOrder, Specialist specialist);
}
