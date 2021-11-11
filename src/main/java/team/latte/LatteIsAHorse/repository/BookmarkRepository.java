package team.latte.LatteIsAHorse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.latte.LatteIsAHorse.model.bookmark.Bookmark;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.user.User;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByUserAndQuiz(User user, Quiz quiz);

    @Query(value = "FROM Bookmark b WHERE b.user = :user AND b.valid = 1")
    List<Bookmark> findByUserAndValid(@Param("user") User user);
}
