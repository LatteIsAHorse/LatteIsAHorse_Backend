package team.latte.LatteIsAHorse.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.quiz.QuizTag;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static team.latte.LatteIsAHorse.model.quiz.QQuiz.*;

@Repository
public class QuizRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public QuizRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Quiz save(Quiz quiz) {
        em.persist(quiz);
        em.flush();
        return quiz;
    }

    public Optional<Quiz> findById(Long id) {
        Quiz findQuiz = em.find(Quiz.class, id);
        return Optional.ofNullable(findQuiz);
    }


    public List<Quiz> findByUserEmail(String userEmail) {
        return queryFactory
                .selectFrom(quiz)
                .where(quiz.user.email.eq(userEmail))
                .fetch();
    }

    public Long deleteById(Long quizId) {
        return queryFactory
                .delete(quiz)
                .where(quiz.quizId.eq(quizId))
                .execute();
    }

    public List<Quiz> findAll() {
        return queryFactory
                .selectFrom(quiz)
                .fetch();
    }

    public List<Quiz> findByQuizTag(QuizTag quizTag) {
        return queryFactory
                .selectFrom(quiz)
                .where(quiz.quizTags.contains(quizTag))
                .fetch();
    }
}
