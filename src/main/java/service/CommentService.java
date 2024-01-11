package service;

import base.service.BaseEntityService;
import entity.Comment;
import entity.Order;

public interface CommentService extends BaseEntityService<Comment,Integer> {
    Comment setCommentInfo(Order order);
}
