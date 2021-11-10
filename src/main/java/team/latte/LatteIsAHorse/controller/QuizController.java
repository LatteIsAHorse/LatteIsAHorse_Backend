package team.latte.LatteIsAHorse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team.latte.LatteIsAHorse.common.util.firebase.FirebaseFileService;
import team.latte.LatteIsAHorse.config.response.ApiResponse;
import team.latte.LatteIsAHorse.config.response.ResponseMessage;
import team.latte.LatteIsAHorse.config.security.authentication.CustomUserDetails;
import team.latte.LatteIsAHorse.dto.AllQuizRes;
import team.latte.LatteIsAHorse.dto.CreateQuizReq;
import team.latte.LatteIsAHorse.dto.QuizRes;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.service.QuizService;
import team.latte.LatteIsAHorse.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class QuizController {

    private final FirebaseFileService firebaseFileService;
    private final QuizService quizService;

    /**
     * 퀴즈 생성
     * @param req : 퀴즈 생성 DTO
     * @param customUserDetails : 인증된 유저 객체
     * @return
     */
    @PostMapping("/quiz")
    public ApiResponse<Object> createQuiz(@Valid @ModelAttribute CreateQuizReq req,
                                          @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Quiz quiz = quizService.createQuiz(req, customUserDetails.getUsername());
        if(quiz == null)
            return ApiResponse.of(HttpStatus.FORBIDDEN, ResponseMessage.QUIZ_CREATED_FAIL);

        if (req.getImageFile() != null) {
            String url = firebaseFileService.upload(req.getImageFile());
            if (quizService.updateQuizImage(quiz, url) == null) {
                quizService.deleteTempSavedQuiz(quiz);
                return ApiResponse.of(HttpStatus.FORBIDDEN, ResponseMessage.QUIZ_CREATED_FAIL);
            }
        }
        quizService.issueLatteStack(quiz, customUserDetails.getUsername());
        return ApiResponse.of(HttpStatus.CREATED, ResponseMessage.QUIZ_CREATED_SUCCESS);
    }

    /**
     * 퀴즈 목록 조회
     * @return
     */
    @GetMapping("/quiz")
    public ApiResponse<Object> allQuizList() {
        List<AllQuizRes> allQuizRes = quizService.allQuizList();

        return ApiResponse.of(allQuizRes, HttpStatus.OK, ResponseMessage.QUIZ_LIST_SUCCESS);
    }

    /**
     * 퀴즈 세부 조회
     * @param quizId 조회할 퀴즈 ID
     * @param customUserDetails : 인증된 유저 객체
     * @return
     */
    @GetMapping("/quiz/{quizId}")
    public ApiResponse<Object> detail(@PathVariable Long quizId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        QuizRes quizRes = quizService.detail(quizId, customUserDetails.getUsername());
        if (quizRes == null)
            return ApiResponse.of(HttpStatus.FORBIDDEN, ResponseMessage.QUIZ_DETAIL_FAIL);

        return ApiResponse.of(quizRes, HttpStatus.OK, ResponseMessage.QUIZ_DETAIL_SUCCESS);
    }
}
