package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Comment;
import entity.Order;
import repository.CommentRepository;
import service.CommentService;
import utility.InputHandling;

public class CommentServiceImpl extends BaseEntityServiceImpl<Comment,Integer, CommentRepository> implements CommentService {
    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }

    @Override
    public Comment setCommentInfo(Order order) {
        System.out.println("*** Comment ***");

        System.out.println("How Would You Score The Specialist From 1 to 5?");
        System.out.println();
        System.out.println("Score:");
        int score = InputHandling.switchInput(1,5);
        order.getSpecialist().getSpecialistScores().add(score);
        System.out.println("Please Leave Us A comment For Improvement:");
        String comment = InputHandling.stringInput();
        return new Comment(order,score,comment);
    }
}
