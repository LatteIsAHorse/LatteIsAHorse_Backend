package team.latte.LatteIsAHorse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team.latte.LatteIsAHorse.common.util.firebase.FirebaseFileService;
import team.latte.LatteIsAHorse.config.response.ApiResponse;
import team.latte.LatteIsAHorse.config.response.ResponseMessage;
import team.latte.LatteIsAHorse.config.security.authentication.CustomUserDetails;
import team.latte.LatteIsAHorse.dto.*;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.quiz.UserAnswer;
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
        quizService.issueLatteStack(quiz.getQuizId(), customUserDetails.getUsername());
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
     * @param quizId : 조회할 퀴즈 ID
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

    /**
     * 퀴즈 풀기
     * @param quizId : 조회중인 퀴즈 ID
     * @param customUserDetails : 인증된 유저 객체
     * @param req : 퀴즈 풀기 DTO
     * @return
     */
    @PostMapping("/quiz/{quizId}")
    public ApiResponse<Object> chooseAnswer(@PathVariable Long quizId, @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                            @RequestBody ChooseAnswerReq req) {
        UserAnswer userAnswer = quizService.chooseUserAnswer(quizId, customUserDetails.getUsername(), req);
        if (userAnswer == null)
            return ApiResponse.of(HttpStatus.FORBIDDEN, ResponseMessage.QUIZ_CHOOSE_ANSWER_FAIL);

        boolean isCorrect = quizService.isCorrectUserAnswer(userAnswer);
        if (isCorrect)
            quizService.issueLatteStack(quizId, customUserDetails.getUsername());

        return ApiResponse.of(new ChooseAnswerRes(isCorrect), HttpStatus.OK, ResponseMessage.QUIZ_CHOOSE_ANSWER_SUCCESS);
    }

    /**
     * 퀴즈 좋아요
     * @param quizId : 조회중인 퀴즈 ID
     * @param customUserDetails : 인증된 유저 객체
     * @return
     */
    @PostMapping("/quiz/{quizId}/likers")
    public ApiResponse<Object> likeOrCancelQuiz(@PathVariable Long quizId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ApiResponse.of(HttpStatus.OK, quizService.likeOrCancelQuiz(quizId, customUserDetails.getUsername()) == 1 ?
                ResponseMessage.QUIZ_LIKE_SUCCESS : ResponseMessage.QUIZ_LIKE_CANCEL_SUCCESS);
    }
}
