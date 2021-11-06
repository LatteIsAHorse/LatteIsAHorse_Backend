package team.latte.LatteIsAHorse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.user.LatteStackInfo;

public interface LatteStackInfoRepository extends JpaRepository<LatteStackInfo, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Answer a WHERE a.quiz = :quiz")
    void delteAllByQuiz(@Param("quiz") Quiz quiz);
}
