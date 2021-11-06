package team.latte.LatteIsAHorse.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.user.RoleType;
import team.latte.LatteIsAHorse.model.user.User;
import team.latte.LatteIsAHorse.model.user.UserState;

import javax.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class QuizRepositoryTest {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {

    }

    @Test
    void save() {
        // given
        Quiz quiz = Quiz.builder()
                .answer(1)
                .build();
        // when
        Quiz savedQuiz = quizRepository.save(quiz);
        // then
        assertThat(savedQuiz.getQuizId()).isEqualTo(1L);
    }

    @Test
    void findById() {
        // gvien
        Quiz quiz = Quiz.builder()
                .answer(1)
                .build();
        quizRepository.save(quiz);

        // when
        Quiz findQuiz = quizRepository.findById(1L)
                .orElseThrow(()->new RuntimeException("error"));

        // then
        assertAll(
                () -> assertThat(findQuiz.getQuizId()).isEqualTo(1L),
                ()-> assertThat(findQuiz.getAnswer()).isEqualTo(1)
        );
    }

    @Test
    void findByUser() {

        // given
        User user = User.builder()
                .email("1234")
                .state(UserState.VALID)
                .role(RoleType.ROLE_GUEST)
                .build();
        userRepository.save(user);

        Quiz quiz1 = Quiz.builder()
                .user(user)
                .build();

        Quiz quiz2 = Quiz.builder()
                .user(user)
                .build();
        quizRepository.save(quiz1);
        quizRepository.save(quiz2);

        // when
        List<Quiz> QuizzesbyUser = quizRepository.findByUser(user);

        // then
        List<Quiz> expectedQuizzes = Arrays.asList(quiz1,quiz2);
        assertAll(
                () -> assertThat(QuizzesbyUser.size()).isEqualTo(2),
                ()-> assertThat(QuizzesbyUser).containsAll(expectedQuizzes)
        );
    }
}