package team.latte.LatteIsAHorse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.latte.LatteIsAHorse.model.post.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
