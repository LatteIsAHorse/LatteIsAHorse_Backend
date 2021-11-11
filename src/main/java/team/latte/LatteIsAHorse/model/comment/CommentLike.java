package team.latte.LatteIsAHorse.model.comment;

import lombok.*;
import team.latte.LatteIsAHorse.common.domain.BaseTimeEntity;
import team.latte.LatteIsAHorse.model.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class CommentLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment")
    private Comment comment;

    private int valid;

    public void setUser(User user) {
        this.user = user;
        user.getCommentLikes().add(this);
    }

    public void setComment(Comment comment) {
        this.comment = comment;
        comment.getCommentLikes().add(this);
    }

    public void changeValid() {
        if (this.valid == 1) this.valid = 0;
        else if (this.valid == 0) this.valid = 1;
    }

    public static CommentLike createCommentLike(User user, Comment comment) {
        CommentLike commentLike = new CommentLike();
        commentLike.setUser(user);
        commentLike.setComment(comment);
        commentLike.valid = 1;
        return commentLike;
    }
}
