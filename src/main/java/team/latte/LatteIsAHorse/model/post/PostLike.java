package team.latte.LatteIsAHorse.model.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.latte.LatteIsAHorse.common.domain.BaseTimeEntity;
import team.latte.LatteIsAHorse.model.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PostLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post")
    private Post post;

    public void setUser(User user) {
        this.user = user;
        user.getPostLikes().add(this);
    }

    public void setPost(Post post) {
        this.post = post;
        post.getPostLikes().add(this);
    }
}
