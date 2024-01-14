package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Order;
import entity.Service;
import entity.SubService;
import entity.enumeration.OrderStatus;
import entity.user.Customer;
import entity.user.Specialist;
import repository.OrderRepository;
import service.*;
import utility.ApplicationContext;
import utility.InputHandling;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
public class OrderServiceImpl extends BaseEntityServiceImpl<Order, Integer, OrderRepository> implements OrderService {
    public OrderServiceImpl(OrderRepository repository) {
        super(repository);
    }

    ServiceService serviceService = ApplicationContext.getServiceService();
    SubServiceService subServiceService = ApplicationContext.getSubServiceService();
    CustomerService customerService = ApplicationContext.getCustomerService();
    SpecialistService specialistService = ApplicationContext.getSpecialistService();

    @Override
    public Order setOrderInfo(Customer customer) {
        System.out.println("*** Order ***");

        System.out.println("Please Choose a Service By ID From Below:");
        System.out.println();
        serviceService.showServices();
        int serviceId = InputHandling.integerInput();
        while (!serviceService.existsById(serviceId)) {
            System.out.println("Please Choose An Existing ID:");
            serviceId = InputHandling.integerInput();
        }
        Service service = serviceService.findById(serviceId);
        System.out.println("Please Choose a SubService By ID From Below:");
        System.out.println();
        subServiceService.subServicesOfOneService(service);
        int subServiceId = InputHandling.integerInput();
        while (!serviceService.existsById(subServiceId)) {
            System.out.println("Please Choose An Existing ID:");
            subServiceId = InputHandling.integerInput();
        }
        SubService subService = subServiceService.findById(subServiceId);

        System.out.println("Please Enter Your Address Info:");
        String address = InputHandling.stringInput();
        System.out.println("Enter Your Date Of Need:");
        LocalDate dateOfNeed = InputHandling.stringToDate(InputHandling.dateInput());
        while (dateOfNeed.isBefore(LocalDate.now())){
            System.out.println("Date Of need Can not Be before Today:");
            dateOfNeed = InputHandling.stringToDate(InputHandling.dateInput());
        }

        System.out.println("The Base Price Of This SubService is:  " + subService.getBasePrice());
        System.out.println("What Is Your Offering Price :");
        double offeredPrice = InputHandling.doubleInput();
        while (offeredPrice < subService.getBasePrice()) {
            System.out.println("Your Offer Can Not Be Less Than The Base Price:");
            offeredPrice = InputHandling.doubleInput();
        }
        System.out.println("Any Other Details You Would Like To Share?(like what needs to be done specifically)");
        String details = InputHandling.sentenceInput();

        return new Order(customer, subService, address, offeredPrice, dateOfNeed, details);
    }

    @Override
    public List<Order> loadByOrderStatus(OrderStatus orderStatus) {
        return repository.loadByOrderStatus(orderStatus);
    }

    @Override
    public boolean existByOrderStatus(OrderStatus orderStatus) {
        return repository.existByOrderStatus(orderStatus);
    }

    void creditExchange(Order order, int finalPrice) {
        Customer customer = order.getCustomer();
        customer.setCredit(order.getCustomer().getCredit() - finalPrice);
        customer.setId(order.getCustomer().getId());
        customerService.creatOrUpdate(customer);

        Specialist specialist = order.getSpecialist();
        specialist.setCredit(order.getSpecialist().getCredit() + finalPrice);
        specialist.setId(order.getSpecialist().getId());
        specialistService.creatOrUpdate(specialist);

    }
}
