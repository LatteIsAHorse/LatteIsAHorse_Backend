package team.latte.LatteIsAHorse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.quiz.QuizTag;

public interface QuizTagRepository extends JpaRepository<QuizTag, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM QuizTag q WHERE q.quiz = :quiz")
    void deleteAllByQuiz(@Param("quiz") Quiz quiz);
}
