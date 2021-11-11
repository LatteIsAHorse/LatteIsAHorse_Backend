package team.latte.LatteIsAHorse.service;

import team.latte.LatteIsAHorse.dto.AllQuizRes;
import team.latte.LatteIsAHorse.dto.ChooseAnswerReq;
import team.latte.LatteIsAHorse.dto.CreateQuizReq;
import team.latte.LatteIsAHorse.dto.QuizRes;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.quiz.UserAnswer;

import java.util.List;

public interface QuizService {

    Quiz createQuiz(CreateQuizReq createQuizReq, String userEmail);

    Long updateQuizImage(Quiz quiz, String url);

    Long deleteTempSavedQuiz(Quiz quiz);

    Long issueLatteStack(Long quizId, String userEmail);

    List<AllQuizRes> allQuizList();

    QuizRes detail(Long quizId, String userEmail);

    UserAnswer chooseUserAnswer(Long quizId, String userEmail, ChooseAnswerReq req);

    boolean isCorrectUserAnswer(UserAnswer userAnswer);
}
