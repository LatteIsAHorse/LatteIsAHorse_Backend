package team.latte.LatteIsAHorse.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import team.latte.LatteIsAHorse.dto.CreateQuizReq;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.user.RoleType;
import team.latte.LatteIsAHorse.model.user.User;
import team.latte.LatteIsAHorse.model.user.UserState;
import team.latte.LatteIsAHorse.repository.*;

import java.util.Arrays;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class QuizServiceImplTest {

//    @Autowired
//    private QuizRepository quizRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private TagRepository tagRepository;
//
//    @Autowired
//    private AnswerRepository answerRepository;
//
//    @Autowired
//    private QuizTagRepository quizTagRepository;
//
//    private QuizServiceImpl quizServiceImpl;
//
//    @BeforeEach
//    void setUp() {
//        quizServiceImpl = new QuizServiceImpl(quizRepository, userRepository, tagRepository, answerRepository, quizTagRepository);
//    }
//
//    @Test
//    @DisplayName("퀴즈 생성하기")
//    void createQuiz() {
//        // given
//        User user = User.builder()
//                .email("test@naver.com")
//                .password("password")
//                .state(UserState.VALID)
//                .role(RoleType.ROLE_USER)
//                .nickname("닉네임머하지")
//                .build();
//        User savedUser = userRepository.save(user);
//
//        CreateQuizReq req = CreateQuizReq.builder()
//                .title("제목")
//                .answer(1)
//                .tags(Arrays.asList("태그1","태그2"))
//                .answers(Arrays.asList("선택지1","선택지2"))
//                .build();
//
//        // when
//        Quiz quiz = quizServiceImpl.createQuiz(req, savedUser.getEmail());
//
//        // then
//        Assertions.assertThat(quiz.getQuizId()).isEqualTo(1L);
//    }
//
//    @Test
//    @DisplayName("퀴즈 삭제하기")
//    void deleteTempSavedQuiz() {
////        // given
////        User user = User.builder()
////                .email("test@naver.com")
////                .password("password")
////                .state(UserState.VALID)
////                .role(RoleType.ROLE_USER)
////                .nickname("닉네임머하지")
////                .build();
////        User savedUser = userRepository.save(user);
////
////        CreateQuizReq req = CreateQuizReq.builder()
////                .title("제목")
////                .answer(1)
////                .tags(Arrays.asList("태그1","태그2"))
////                .answers(Arrays.asList("선택지1","선택지2"))
////                .build();
////
////        // when
////        Long quizId = quizServiceImpl.createQuiz(req, savedUser.getEmail());
////        quizRepository.delete(quizId);
//
//    }
//
//    @Test
//    void updateQuizImage() {
//    }
//
//    @Test
//    void updateQuizTags() {
//    }
}