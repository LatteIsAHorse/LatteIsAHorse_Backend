package team.latte.LatteIsAHorse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.latte.LatteIsAHorse.dto.AllQuizRes;
import team.latte.LatteIsAHorse.dto.ChooseAnswerReq;
import team.latte.LatteIsAHorse.dto.CreateQuizReq;
import team.latte.LatteIsAHorse.dto.QuizRes;
import team.latte.LatteIsAHorse.model.post.Image;
import team.latte.LatteIsAHorse.model.quiz.*;
import team.latte.LatteIsAHorse.model.tag.Tag;
import team.latte.LatteIsAHorse.model.user.LatteStackInfo;
import team.latte.LatteIsAHorse.model.user.LatteStackInfoReason;
import team.latte.LatteIsAHorse.model.user.User;
import team.latte.LatteIsAHorse.model.user.UserState;
import team.latte.LatteIsAHorse.repository.*;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final AnswerRepository answerRepository;
    private final QuizTagRepository quizTagRepository;
    private final ImageRepository imageRepository;
    private final LatteStackInfoRepository latteStackInfoRepository;
    private final QuizLikeRepository quizLikeRepository;
    private final ReportSuspicionRepository reportSuspicionRepository;
    private final UserAnswerRepository userAnswerRepository;

    /**
     * 퀴즈를 생성한다.
     * @param createQuizReq : 퀴즈 생성 DTO
     * @param userEmail : 작성자 이메일
     * @return
     */
    @Transactional
    @Override
    public Quiz createQuiz(CreateQuizReq createQuizReq, String userEmail) {
        User user = userRepository.findByEmailAndState(userEmail, UserState.VALID)
                .orElse(null);

        if (createQuizReq.getAnswers().size() < createQuizReq.getAnswer()) {
            return null;
        }

        Quiz quiz = Quiz.builder()
                .user(user)
                .answer(createQuizReq.getAnswer())
                .title(createQuizReq.getTitle())
                .writer(user.getNickname())

                .build();

        for (String answerContent : createQuizReq.getAnswers()) {
            Answer.createAnswer(quiz, answerContent);
        }
        Quiz savedQuiz = quizRepository.save(quiz);

        updateQuizTags(savedQuiz,createQuizReq.getTags());

        return savedQuiz;
    }

    /**
     * 퀴즈 업데이트
     * @param quiz : 퀴즈
     * @param url : 이미지가 저장된 URL
     * @return
     */
    @Transactional
    @Override
    public Long updateQuizImage(Quiz quiz, String url) {
        Image image = Image.createImage(quiz, null, url);
        return imageRepository.save(image).getImageId();
    }

    /**
     * 태그가 존재하는지 확인하고 없으면 태그를 생성한다.
     * 그리고 퀴즈와 태그의 연관관계를 매핑한다.
     * @param quiz 퀴즈
     * @param tags 태그
     */
    @Transactional
    public void updateQuizTags(Quiz quiz, List<String> tags) {

        List<Tag> existTags = tagRepository.findByNames(tags);

        for (String tagName : tags) {
            boolean isExist = false;
            for (Tag existTag : existTags) {
                if (tagName.equals(existTag.getName())) {
                    log.warn(tagName);
                    QuizTag quizTag = QuizTag.createQuizReq(quiz, existTag);
                    quizTagRepository.save(quizTag);
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                log.warn(tagName);
                Tag tag = Tag.createTag(tagName);
                Tag savedTag = tagRepository.save(tag);
                log.warn(savedTag.getTagId().toString());
                QuizTag quizTag = QuizTag.createQuizReq(quiz, savedTag);
                quizTagRepository.save(quizTag);
            }
        }
    }

    /**
     * 이미지 파일 업로드 실패시 임시 저장된 Quiz를 삭제한다.
     * @param quiz : 퀴즈
     * @return
     */
    @Transactional
    @Override
    public Long deleteTempSavedQuiz(Quiz quiz) {
        answerRepository.delteAllByQuiz(quiz);
        quizTagRepository.deleteAllByQuiz(quiz);
        return quizRepository.deleteById(quiz.getQuizId());
    }

    /**
     * 유저에게 라떼 스택을 발급해줍니다.
     * @param quizId : 퀴즈 번호
     * @param userEmail : 유저 이메일
     * @return
     */
    @Transactional
    @Override
    public Long issueLatteStack(Long quizId, String userEmail, LatteStackInfoReason reason) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElse(null);
        User user = userRepository.findByEmail(userEmail)
                .orElse(null);

        LatteStackInfo latteStackInfo = LatteStackInfo.createLatteStackInfo(quiz, user, reason);
        LatteStackInfo savedLatteStackInfo = latteStackInfoRepository.save(latteStackInfo);
        return savedLatteStackInfo.getLatteStackInfoId();
    }

    /**
     * 퀴즈 목록 조회
     * @return
     */
    @Cacheable(value = "quizList", cacheManager = "quizCacheManager")
    @Override
    public List<AllQuizRes> allQuizList() {
        List<Quiz> allQuiz = quizRepository.findAll();
        return AllQuizRes.listOf(allQuiz);
    }

    /**
     * 퀴즈 세부 조회
     * @return
     */
    @Cacheable(value = "quiz", key = "#quizId", cacheManager = "quizCacheManager")
    @Override
    public QuizRes detail(Long quizId, String userEmail) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElse(null);

        User user = userRepository.findByEmail(userEmail)
                .orElse(null);

        if (quiz == null || user == null) {
            return null;
        }

        boolean quizLike = false;
        boolean reportSuspicion = false;

        if(quizLikeRepository.findByUserAndQuiz(user, quiz).orElse(null) != null)
            quizLike = true;

        if(reportSuspicionRepository.findByUserAndQuiz(user, quiz).orElse(null) != null)
            reportSuspicion = true;

        UserAnswer userAnswer = userAnswerRepository.findByUserAndQuiz(user, quiz).orElse(null);

        return QuizRes.of(quiz, quizLike, reportSuspicion, userAnswer);
    }

    /**
     * 퀴즈 풀기 : 답을 선택한다.
     * @param quizId : 조회중인 퀴즈 번호
     * @param userEmail : 답을 고르는 유저 이메일
     * @param req : 퀴즈 풀기 DTO
     */
    @Transactional
    @Override
    public UserAnswer chooseUserAnswer(Long quizId, String userEmail, ChooseAnswerReq req) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElse(null);
        User user = userRepository.findByEmail(userEmail)
                .orElse(null);
        UserAnswer userAnswer = UserAnswer.createUserAnswer(quiz, user, req.getUserAnswer());
        UserAnswer savedUserAnswer = userAnswerRepository.save(userAnswer);
        return savedUserAnswer;
    }

    /**
     * 유저가 고른 퀴즈의 선택지가 정답인지 확인한다.
     * @param userAnswer : 유저 답안
     * @return
     */
    public boolean isCorrectUserAnswer(UserAnswer userAnswer) {
        if (userAnswer.getChoiceNum() == userAnswer.getQuiz().getAnswer()) return true;
        return false;
    }

    /**
     * 퀴즈 좋아요
     * @param quizId : 좋아요하는 퀴즈 번호
     * @param userEmail : 유저 이메일
     * @return
     */
    @Transactional
    @Override
    public int likeOrCancelQuiz(Long quizId, String userEmail) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElse(null);
        User user = userRepository.findByEmail(userEmail)
                .orElse(null);

        QuizLike quizLike = quizLikeRepository.findByUserAndQuiz(user, quiz)
                .orElse(null);

        if (quizLike == null) {
            quizLike = QuizLike.createQuizLike(user, quiz);
            quizLikeRepository.save(quizLike);
            return 1;
        }
        quizLike.changeValid();
        return quizLike.getValid();
    }

    /**
     * 내가 만든 퀴즈 목록 조회
     * @param userEmail : 유저 이메일
     * @return
     */
    @Override
    public List<AllQuizRes> allQuizListByUsername(String userEmail) {
        List<Quiz> allQuiz = quizRepository.findByUserEmail(userEmail);
        return AllQuizRes.listOf(allQuiz);
    }

    /**
     * 태그별로 퀴즈 목록 조회
     * @param tagId : 태그 ID
     * @return
     */
    @Override
    public List<AllQuizRes> allQuizListByTag(Long tagId) {
        Tag tag = tagRepository.findById(tagId).orElse(null);
        QuizTag quizTag = quizTagRepository.findByTag(tag).orElse(null);
        List<Quiz> allQuiz = quizRepository.findByQuizTag(quizTag);
        return AllQuizRes.listOf(allQuiz);
    }
}
