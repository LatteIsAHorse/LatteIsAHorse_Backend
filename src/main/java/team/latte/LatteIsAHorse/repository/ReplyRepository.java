package team.latte.LatteIsAHorse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.latte.LatteIsAHorse.model.comment.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
