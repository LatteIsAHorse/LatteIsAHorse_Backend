package team.latte.LatteIsAHorse.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import team.latte.LatteIsAHorse.model.tag.Tag;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static team.latte.LatteIsAHorse.model.tag.QTag.tag;

@Repository
public class TagRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public TagRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Tag save(Tag tag) {
        em.persist(tag);
        em.flush();
        return tag;
    }

    public Optional<Tag> findById(Long id) {
        Tag findTag = em.find(Tag.class, id);
        return Optional.ofNullable(findTag);
    }


    public List<Tag> findByNames(List<String> tagNames) {

        BooleanBuilder builder = new BooleanBuilder();
        for (String tagName : tagNames) {
            builder.or(tag.name.eq(tagName));
        }
        return queryFactory
                .selectFrom(tag)
                .where(builder)
                .fetch();
    }

}
