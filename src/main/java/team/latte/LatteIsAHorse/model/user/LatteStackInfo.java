package team.latte.LatteIsAHorse.model.user;

import lombok.*;
import team.latte.LatteIsAHorse.common.domain.BaseTimeEntity;
import team.latte.LatteIsAHorse.model.coupon.Coupon;
import team.latte.LatteIsAHorse.model.coupon.CouponList;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class LatteStackInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long latteStackInfoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 쿠폰을 먼저 발급하고 라떼스택을 만들어 저장한다.
    @JoinColumn(name = "coupon_list_id")
    private CouponList couponList;

    private int deltaStack;

    private LatteStackInfoReason reason;

    public void setUser(User user) {
        this.user = user;
        user.getLatteStackInfos().add(this);
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        quiz.getLatteStackInfos().add(this);
    }

    public void setCouponList(CouponList couponList) {
        this.couponList = couponList;
        couponList.setLatteStackInfo(this);
    }

    public static LatteStackInfo createLatteStackInfo(Quiz quiz, User user, LatteStackInfoReason reason) {
        LatteStackInfo latteStackInfo = new LatteStackInfo();
        latteStackInfo.setUser(user);
        latteStackInfo.setQuiz(quiz);
        user.addLatteStack();
        latteStackInfo.deltaStack = 1;
        latteStackInfo.reason = reason;
        return latteStackInfo;
    }

    public static LatteStackInfo createLatteStackInfo(CouponList couponList, User user) {
        LatteStackInfo latteStackInfo = new LatteStackInfo();
        latteStackInfo.setUser(user);
        latteStackInfo.setCouponList(couponList);
        user.subtractLatteStack();
        latteStackInfo.deltaStack = -30;
        return latteStackInfo;
    }
}
