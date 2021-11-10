package team.latte.LatteIsAHorse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import team.latte.LatteIsAHorse.model.quiz.Answer;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.quiz.QuizLike;
import team.latte.LatteIsAHorse.model.user.User;

import java.util.Optional;

public interface QuizLikeRepository extends JpaRepository<QuizLike, Long> {

    Optional<QuizLike> findByUserAndQuiz(User user, Quiz quiz);
}
