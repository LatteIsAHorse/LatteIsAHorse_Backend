package team.latte.LatteIsAHorse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import team.latte.LatteIsAHorse.model.comment.Comment;
import team.latte.LatteIsAHorse.model.quiz.Answer;
import team.latte.LatteIsAHorse.model.quiz.Quiz;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
