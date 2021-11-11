package team.latte.LatteIsAHorse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.latte.LatteIsAHorse.dto.AllCommentRes;
import team.latte.LatteIsAHorse.dto.CreateCommentReq;
import team.latte.LatteIsAHorse.dto.CreateReplyReq;
import team.latte.LatteIsAHorse.model.comment.Comment;
import team.latte.LatteIsAHorse.model.comment.CommentLike;
import team.latte.LatteIsAHorse.model.comment.Reply;
import team.latte.LatteIsAHorse.model.comment.ReplyLike;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.quiz.QuizLike;
import team.latte.LatteIsAHorse.model.user.User;
import team.latte.LatteIsAHorse.model.user.UserState;
import team.latte.LatteIsAHorse.repository.*;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final ReplyRepository replyRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final ReplyLikeRepository replyLikeRepository;


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

        if (user == null) return null; // SUSPEND인 유저 예외처리

        Quiz quiz = quizRepository.findById(quizId)
                .orElse(null);

        Comment comment = Comment.createComment(user, quiz, null, req.getContent());
        Comment savedComment = commentRepository.save(comment);

        return savedComment;
    }

    /**
     * 댓글을 모두 조회한다.
     * @param quizId 조회중인 퀴즈 ID
     * @return
     */
    @Override
    public List<AllCommentRes> allCommentList(Long quizId) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElse(null);
        List<Comment> comments = commentRepository.findByQuiz(quiz);

        return AllCommentRes.listOf(comments);
    }

    /**
     * 답글을 생성한다.
     * @param commentId 답글을 달 원본 댓글의 ID
     * @param userEmail 답글을 달 유저의 이메일
     * @param req 답글 작성 DTO
     * @return
     */
    @Transactional
    @Override
    public Reply createReply(Long commentId, String userEmail, CreateReplyReq req) {

        User user = userRepository.findByEmailAndState(userEmail, UserState.VALID)
                .orElse(null);

        if (user == null) return null; // SUSPEND인 유저 예외처리

        Comment comment = commentRepository.findById(commentId)
                .orElse(null);

        Reply reply = Reply.createReply(user, comment, req.getContent());
        Reply savedReply = replyRepository.save(reply);

        return savedReply;
    }

    /**
     * 댓글 좋아요
     * @param commentId : 좋아요 하는 댓글 번호
     * @param userEmail : 유저 이메일
     * @return
     */
    @Transactional
    @Override
    public int likeOrCancelComment(Long commentId, String userEmail) {

        Comment comment = commentRepository.findById(commentId)
                .orElse(null);
        User user = userRepository.findByEmail(userEmail)
                .orElse(null);

        CommentLike commentLike = commentLikeRepository.findByUserAndComment(user, comment)
                .orElse(null);

        if (commentLike == null) {
            commentLike = CommentLike.createCommentLike(user, comment);
            commentLikeRepository.save(commentLike);
            return 1;
        }
        commentLike.changeValid();
        return commentLike.getValid();
    }

    /**
     * 답글 좋아요
     * @param replyId
     * @param userEmail
     * @return
     */
    @Transactional
    @Override
    public int likeOrCancelReply(Long replyId, String userEmail) {
        Reply reply = replyRepository.findById(replyId)
                .orElse(null);
        User user = userRepository.findByEmail(userEmail)
                .orElse(null);

        ReplyLike replyLike = replyLikeRepository.findByUserAndReply(user, reply)
                .orElse(null);

        if (replyLike == null) {
            replyLike = ReplyLike.createReplyLike(user, reply);
            replyLikeRepository.save(replyLike);
            return 1;
        }
        replyLike.changeValid();
        return replyLike.getValid();
    }
}
