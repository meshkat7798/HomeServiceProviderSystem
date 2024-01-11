package utility;
import entity.*;
import entity.user.User;
import lombok.Getter;

@SuppressWarnings("unused")
public class SecurityContext {


    private SecurityContext() {
    }
    @Getter
    private static User currentUser;
    @Getter
    private static Order currentOrder;
    @Getter
    private static Service currentService;
    @Getter
    private static SubService currentSubService;
    @Getter
    private static Comment currentComment;
    @Getter
    private static Offer currentOffer;

    public static void logout() {
        currentUser = null;
    }
    public static void fillUserContext(User user){currentUser = user;}
    public static void fillOrderContext(Order order){currentOrder = order;}
    public static void fillServiceContext(Service service){currentService = service;}
    public static void fillSubServiceContext(SubService subService){currentSubService = subService;}
    public static void fillCommentContext(Comment comment){ currentComment = comment; }
    public static void fillOfferContext(Offer offer){ currentOffer = offer; }

}
