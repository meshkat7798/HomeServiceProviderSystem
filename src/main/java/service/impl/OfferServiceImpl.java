package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Offer;
import entity.MyOrder;
import entity.user.Specialist;
import repository.OfferRepository;
import service.OfferService;
import utility.InputHandling;

import java.time.LocalDate;

public class OfferServiceImpl extends BaseEntityServiceImpl<Offer,Integer, OfferRepository> implements OfferService {
    public OfferServiceImpl(OfferRepository repository) {
        super(repository);
    }
    @Override
    public Offer setOfferInfo(MyOrder myOrder, Specialist specialist) {
        System.out.println("*** Offer ***");

        System.out.println("Enter Your Date Of Offer:");
        LocalDate offeredStartingDate = InputHandling.stringToDate(InputHandling.dateInput());
        while (offeredStartingDate.isBefore(myOrder.getDateOfNeed())){
            System.out.println("Date Of Need is: " + myOrder.getDateOfNeed()+
                    "!! Your Offering Date Can not Be Before Date Of Need:");
            offeredStartingDate = InputHandling.stringToDate(InputHandling.dateInput());
        }

        System.out.println("The Base Price Of This SubService is:  " + myOrder.getSubService().getBasePrice());
        System.out.println("The Price Offered By Customer For This MyOrder is: " + myOrder.getOfferedPrice());
        System.out.println("What Is Your Offering Price :");
        double offeredPrice = InputHandling.doubleInput();
        while (offeredPrice < myOrder.getSubService().getBasePrice()) {
            System.out.println("Your Offer Can Not Be Less Than The Base Price:");
            offeredPrice = InputHandling.doubleInput();
        }
        System.out.println("How Many Hours Do You Think This MyOrder Would Take?");
        double durationHoursOfOrder = InputHandling.doubleInput();

        return new Offer(specialist,offeredPrice,offeredStartingDate,durationHoursOfOrder);
    }
}
