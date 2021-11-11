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
import team.latte.LatteIsAHorse.model.comment.Comment;
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
}
