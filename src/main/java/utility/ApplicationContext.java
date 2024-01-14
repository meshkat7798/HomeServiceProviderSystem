package utility;

import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

import javax.persistence.EntityManager;

@SuppressWarnings("unused")
public class ApplicationContext {

    private static final EntityManager entityManager;
    private static final CustomerRepository CUSTOMER_REPOSITORY;
    private static final OrderRepository ORDER_REPOSITORY;
    private static final ServiceRepository SERVICE_REPOSITORY;
    private static final SpecialistRepository SPECIALIST_REPOSITORY;
    private static final SubServiceRepository SUB_SERVICE_REPOSITORY;
    private static final CommentRepository COMMENT_REPOSITORY;
    private static final OfferRepository OFFER_REPOSITORY;

    private static final CustomerService CUSTOMER_SERVICE;
    private static final OrderService ORDER_SERVICE;
    private static final ServiceService SERVICE_SERVICE;
    private static final SpecialistService SPECIALIST_SERVICE;
    private static final SubServiceService SUB_SERVICE_SERVICE;
    private static final CommentService COMMENT_SERVICE;
    private static final OfferService OFFER_SERVICE;


    static {
        entityManager = EntityMangerProvider.getEntityManager();
        CUSTOMER_REPOSITORY = new CustomerRepositoryImpl(entityManager);
        ORDER_REPOSITORY = new OrderRepositoryImpl(entityManager);
        SERVICE_REPOSITORY = new ServiceRepositoryImpl(entityManager);
        SPECIALIST_REPOSITORY = new SpecialistRepositoryImpl(entityManager);
        SUB_SERVICE_REPOSITORY = new SubServiceRepositoryImpl(entityManager);
        COMMENT_REPOSITORY = new CommentRepositoryImpl(entityManager);
        OFFER_REPOSITORY = new OfferRepositoryImpl(entityManager);

        CUSTOMER_SERVICE = new CustomerServiceImpl(CUSTOMER_REPOSITORY);
        ORDER_SERVICE = new OrderServiceImpl(ORDER_REPOSITORY);
        SERVICE_SERVICE = new ServiceServiceImpl(SERVICE_REPOSITORY);
        SPECIALIST_SERVICE = new SpecialistServiceImpl(SPECIALIST_REPOSITORY);
        SUB_SERVICE_SERVICE = new SubServiceServiceImpl(SUB_SERVICE_REPOSITORY);
        COMMENT_SERVICE = new CommentServiceImpl(COMMENT_REPOSITORY);
        OFFER_SERVICE = new OfferServiceImpl(OFFER_REPOSITORY);

    }

    public static CustomerService getCustomerService() {
        return CUSTOMER_SERVICE;
    }

    public static OrderService getOrderService() {
        return ORDER_SERVICE;
    }

    public static ServiceService getServiceService() {
        return SERVICE_SERVICE;
    }

    public static SpecialistService getSpecialistService() {
        return SPECIALIST_SERVICE;
    }

    public static SubServiceService getSubServiceService() {
        return SUB_SERVICE_SERVICE;
    }

    public static CommentService getCommentService(){
        return COMMENT_SERVICE;
    }

    public static OfferService getOfferService(){
        return OFFER_SERVICE;
    }
}


