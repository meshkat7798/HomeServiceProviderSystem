package service;

import base.service.BaseEntityService;
import entity.Comment;
import entity.MyOrder;

public interface CommentService extends BaseEntityService<Comment,Integer> {
    Comment setCommentInfo(MyOrder myOrder);
}
