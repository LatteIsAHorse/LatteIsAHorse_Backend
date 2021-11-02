package team.latte.LatteIsAHorse.model.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import team.latte.LatteIsAHorse.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
@Entity
public class CouponList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponListId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @CreatedDate
    private LocalDateTime pubDate;

    public void setUser(User user) {
        this.user = user;
        user.getCouponList().add(this);
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
        coupon.getCouponList().add(this);
    }
}
