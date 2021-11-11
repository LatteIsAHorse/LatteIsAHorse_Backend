package team.latte.LatteIsAHorse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.latte.LatteIsAHorse.model.comment.Reply;
import team.latte.LatteIsAHorse.model.comment.ReplyLike;
import team.latte.LatteIsAHorse.model.user.User;

import java.util.Optional;

public interface ReplyLikeRepository extends JpaRepository<ReplyLike, Long> {

    Optional<ReplyLike> findByUserAndReply(User user, Reply reply);
}
