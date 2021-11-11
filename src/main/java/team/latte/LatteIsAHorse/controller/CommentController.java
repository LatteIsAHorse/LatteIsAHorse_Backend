package team.latte.LatteIsAHorse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team.latte.LatteIsAHorse.config.response.ApiResponse;
import team.latte.LatteIsAHorse.config.response.ResponseMessage;
import team.latte.LatteIsAHorse.config.security.authentication.CustomUserDetails;
import team.latte.LatteIsAHorse.dto.AllCommentRes;
import team.latte.LatteIsAHorse.dto.CreateCommentReq;
import team.latte.LatteIsAHorse.dto.CreateReplyReq;
import team.latte.LatteIsAHorse.model.comment.Comment;
import team.latte.LatteIsAHorse.model.comment.Reply;
import team.latte.LatteIsAHorse.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 작성
     * @param quizId : 조회중인 퀴즈 ID
     * @param req : 댓글 작성 DTO
     * @param customUserDetails : 인증된 유저 객체
     * @return
     */
    @PostMapping("/{quizId}/comment")
    public ApiResponse<Object> createComment(@PathVariable Long quizId, @Valid @RequestBody CreateCommentReq req,
                                             @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Comment comment = commentService.createComment(quizId, customUserDetails.getUsername(), req);

        if (comment == null)
            return ApiResponse.of(HttpStatus.FORBIDDEN, ResponseMessage.COMMENT_CREATED_FAIL);

        return ApiResponse.of(HttpStatus.OK, ResponseMessage.COMMENT_CREATED_SUCCESS);
    }

    /**
     * 댓글 조회
     * @param quizId : 조회중인 퀴즈 ID
     * @return
     */
    @GetMapping("/{quizId}/comment")
    public ApiResponse<Object> allCommentList(@PathVariable Long quizId) {
        List<AllCommentRes> allCommentRes = commentService.allCommentList(quizId);

        return ApiResponse.of(allCommentRes, HttpStatus.OK, ResponseMessage.COMMENT_LIST_SUCCESS);
    }

    /**
     * 답글 작성
     * @param quizId : 조회중인 퀴즈 ID
     * @param commentId : 답글을 달 원본 댓글 ID
     * @param req : 답글 작성 DTO
     * @param customUserDetails : 인증된 유저 객체
     * @return
     */
    @PostMapping("/{quizId}/comment/{commentId}")
    public ApiResponse<Object> createReply(@PathVariable Long quizId, @PathVariable Long commentId,
                                           @Valid @RequestBody CreateReplyReq req,
                                             @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Reply reply = commentService.createReply(commentId, customUserDetails.getUsername(), req);

        if (reply == null)
            return ApiResponse.of(HttpStatus.FORBIDDEN, ResponseMessage.REPLY_CREATED_FAIL);

        return ApiResponse.of(HttpStatus.OK, ResponseMessage.REPLY_CREATED_SUCCESS);
    }

    /**
     * 댓글 좋아요
     * @param quizId : 조회중인 퀴즈 ID
     * @param customUserDetails : 인증된 유저 객체
     * @return
     */
    @PostMapping("/{quizId}/comment/{commentId}/likers")
    public ApiResponse<Object> likeOrCancelQuiz(@PathVariable Long quizId, @PathVariable Long commentId,
                                                @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ApiResponse.of(HttpStatus.OK, commentService.likeOrCancelQuiz(commentId, customUserDetails.getUsername()) == 1 ?
                ResponseMessage.COMMENT_LIKE_SUCCESS : ResponseMessage.COMMENT_LIKE_CANCEL_SUCCESS);
    }
}
