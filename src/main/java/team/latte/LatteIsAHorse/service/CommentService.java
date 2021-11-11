package team.latte.LatteIsAHorse.service;

import team.latte.LatteIsAHorse.dto.AllCommentRes;
import team.latte.LatteIsAHorse.dto.CreateCommentReq;
import team.latte.LatteIsAHorse.model.comment.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Long quizId, String userEmail, CreateCommentReq req);

    List<AllCommentRes> allCommentList(Long quizId);
}
