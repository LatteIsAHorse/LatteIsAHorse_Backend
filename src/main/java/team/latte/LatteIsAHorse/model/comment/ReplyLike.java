package team.latte.LatteIsAHorse.model.comment;

import lombok.*;
import team.latte.LatteIsAHorse.common.domain.BaseTimeEntity;
import team.latte.LatteIsAHorse.model.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class ReplyLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply")
    private Reply reply;

    private int valid;

    public void setUser(User user) {
        this.user = user;
        user.getReplyLikes().add(this);
    }

    public void setReply(Reply reply) {
        this.reply = reply;
        reply.getReplyLikes().add(this);
    }

    public void changeValid() {
        if (this.valid == 1) this.valid = 0;
        else if (this.valid == 0) this.valid = 1;
    }

    public static ReplyLike createReplyLike(User user, Reply reply) {
        ReplyLike replyLike = new ReplyLike();
        replyLike.setUser(user);
        replyLike.setReply(reply);
        replyLike.valid = 1;
        return replyLike;
    }
}
