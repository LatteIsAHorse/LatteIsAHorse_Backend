package team.latte.LatteIsAHorse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team.latte.LatteIsAHorse.config.response.ApiResponse;
import team.latte.LatteIsAHorse.config.response.ResponseMessage;
import team.latte.LatteIsAHorse.config.security.authentication.CustomUserDetails;
import team.latte.LatteIsAHorse.service.BookmarkService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    /**
     * 퀴즈 북마크 생성
     * @param quizId : 퀴즈 북마크 생성 DTO
     * @param customUserDetails : 인증된 유저 객체
     * @return
     */
    @PostMapping("/bookmark/quiz/{quizId}")
    public ApiResponse<Object> markOrCancelQuiz(@PathVariable Long quizId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ApiResponse.of(HttpStatus.OK, bookmarkService.markOrCancelQuiz(quizId, customUserDetails.getUsername()) == 1?
                ResponseMessage.BOOKMARK_QUIZ_SUCCESS : ResponseMessage.BOOKMARK_QUIZ_CANCEL_SUCCESS);
    }
}
