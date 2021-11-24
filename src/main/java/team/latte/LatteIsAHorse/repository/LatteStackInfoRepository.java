package team.latte.LatteIsAHorse.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import team.latte.LatteIsAHorse.model.user.LatteStackInfo;
import javax.persistence.EntityManager;
import java.util.List;

import static team.latte.LatteIsAHorse.model.user.QLatteStackInfo.latteStackInfo;


@Repository
public class LatteStackInfoRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public LatteStackInfoRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<LatteStackInfo> findByUserEmailAndYearAndMonth(String userEmail, int year, int month) {
        return queryFactory
                .selectFrom(latteStackInfo)
                .where(
                        latteStackInfo.user.email.eq(userEmail)
                        .and(latteStackInfo.createdAt.year().eq(year))
                        .and(latteStackInfo.createdAt.month().eq(month))
                ).fetch();
    }

    public LatteStackInfo save(LatteStackInfo latteStackInfo) {
        em.persist(latteStackInfo);
        em.flush();
        return latteStackInfo;
    }
}
