package team.latte.LatteIsAHorse.model.comment;

import lombok.*;
import team.latte.LatteIsAHorse.common.domain.BaseTimeEntity;
import team.latte.LatteIsAHorse.model.quiz.UserAnswer;
import team.latte.LatteIsAHorse.model.user.User;
import team.latte.LatteIsAHorse.model.post.Post;
import team.latte.LatteIsAHorse.model.quiz.Quiz;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<CommentLike> commentLikes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post")
    private Post post;

    @OneToMany(mappedBy = "comment")
    private List<Reply> reply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    private String content;

    private String writer;

    public void setUser(User user) {
        this.user = user;
        user.getComments().add(this);
    }

    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        quiz.getComments().add(this);
    }

    public static Comment createComment(User user, Quiz quiz, Post post, String content) {
        Comment comment = new Comment();
        comment.setUser(user);
        if (quiz != null) comment.setQuiz(quiz);
        else if (post != null) comment.setPost(post);
        comment.writer = user.getNickname();
        comment.content = content;
        return comment;
    }

}
