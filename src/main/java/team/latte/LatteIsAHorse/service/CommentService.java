package team.latte.LatteIsAHorse.service;

import team.latte.LatteIsAHorse.dto.AllCommentRes;
import team.latte.LatteIsAHorse.dto.CreateCommentReq;
import team.latte.LatteIsAHorse.dto.CreateReplyReq;
import team.latte.LatteIsAHorse.model.comment.Comment;
import team.latte.LatteIsAHorse.model.comment.Reply;

import java.util.List;

public interface CommentService {

    Comment createComment(Long quizId, String userEmail, CreateCommentReq req);

    List<AllCommentRes> allCommentList(Long quizId);

    Reply createReply(Long commentId, String userEmail, CreateReplyReq req);
}
