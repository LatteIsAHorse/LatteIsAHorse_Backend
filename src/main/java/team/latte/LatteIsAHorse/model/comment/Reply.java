package team.latte.LatteIsAHorse.model.comment;

import lombok.*;
import team.latte.LatteIsAHorse.common.domain.BaseTimeEntity;
import team.latte.LatteIsAHorse.model.post.Post;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @OneToMany(mappedBy = "reply", cascade = CascadeType.ALL)
    private List<ReplyLike> replyLikes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment")
    private Comment comment;

    private String writer;

    private String content;

    public void setUser(User user) {
        this.user = user;
        user.getReplies().add(this);
    }

    public void setComment(Comment comment) {
        this.comment = comment;
        comment.getReplies().add(this);
    }

    public static Reply createReply(User user, Comment comment, String content) {
        Reply reply = new Reply();
        reply.setUser(user);
        reply.setComment(comment);
        reply.writer = user.getNickname();
        reply.content = content;
        return reply;
    }

}
