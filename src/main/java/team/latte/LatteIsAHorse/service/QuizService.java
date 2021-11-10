package team.latte.LatteIsAHorse.service;

import team.latte.LatteIsAHorse.dto.AllQuizRes;
import team.latte.LatteIsAHorse.dto.CreateQuizReq;
import team.latte.LatteIsAHorse.model.quiz.Quiz;

import java.util.List;

public interface QuizService {

    Quiz createQuiz(CreateQuizReq createQuizReq, String userEmail);

    Long updateQuizImage(Quiz quiz, String url);

    Long deleteTempSavedQuiz(Quiz quiz);

    Long issueLatteStack(Quiz quiz, String userEmail);

    List<AllQuizRes> allQuizList();
}
