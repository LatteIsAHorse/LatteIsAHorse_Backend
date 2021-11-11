package team.latte.LatteIsAHorse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.latte.LatteIsAHorse.dto.AllCommentRes;
import team.latte.LatteIsAHorse.dto.CreateCommentReq;
import team.latte.LatteIsAHorse.model.comment.Comment;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.user.User;
import team.latte.LatteIsAHorse.model.user.UserState;
import team.latte.LatteIsAHorse.repository.CommentRepository;
import team.latte.LatteIsAHorse.repository.QuizRepository;
import team.latte.LatteIsAHorse.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    /**
     * 댓글을 생성한다.
     * @param quizId 댓글을 다는 퀴즈 ID
     * @param userEmail 유저 이메일
     * @param req 댓글 작성 DTO
     * @return
     */
    @Transactional
    @Override
    public Comment createComment(Long quizId, String userEmail, CreateCommentReq req) {

        User user = userRepository.findByEmailAndState(userEmail, UserState.VALID)
                .orElse(null);

        if (user == null) return null;

        Quiz quiz = quizRepository.findById(quizId)
                .orElse(null);

        Comment comment = Comment.createComment(user, quiz, null, req.getContent());
        Comment savedComment = commentRepository.save(comment);

        return savedComment;
    }

    /**
     * 댓글을 모두 조회한다.
     * @return
     */
    @Override
    public List<AllCommentRes> allCommentList(Long quizId) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElse(null);
        List<Comment> comments = commentRepository.findByQuiz(quiz);
        return AllCommentRes.listOf(comments);
    }
}
