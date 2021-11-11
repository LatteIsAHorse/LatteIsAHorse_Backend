package team.latte.LatteIsAHorse.service;

import team.latte.LatteIsAHorse.dto.CreateCommentReq;
import team.latte.LatteIsAHorse.model.comment.Comment;

public interface CommentService {
    Comment createComment(Long quizId, String userEmail, CreateCommentReq req);
}
