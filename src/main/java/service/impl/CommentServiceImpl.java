package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Comment;
import entity.MyOrder;
import repository.CommentRepository;
import service.CommentService;
import utility.InputHandling;

public class CommentServiceImpl extends BaseEntityServiceImpl<Comment,Integer, CommentRepository> implements CommentService {
    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }

    @Override
    public Comment setCommentInfo(MyOrder myOrder) {
        System.out.println("*** Comment ***");

        System.out.println("How Would You Score The Specialist From 1 to 5?");
        System.out.println();
        System.out.println("Score:");
        int score = InputHandling.switchInput(1,5);
        myOrder.getSpecialist().getSpecialistScores().add(score);
        System.out.println("Please Leave Us A comment For Improvement:");
        String comment = InputHandling.sentenceInput();
        return new Comment(myOrder,score,comment);
    }
}
