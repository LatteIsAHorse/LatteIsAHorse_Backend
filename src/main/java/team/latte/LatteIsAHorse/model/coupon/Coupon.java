package team.latte.LatteIsAHorse.model.coupon;

import lombok.*;
import team.latte.LatteIsAHorse.common.domain.CouponStateConverter;
import team.latte.LatteIsAHorse.model.franchisee.Franchisee;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private List<CouponList> couponList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchisee")
    private Franchisee franchisee;

    private String seq;

    private Long validTerm;

    @Convert(converter = CouponStateConverter.class)
    private CouponState couponState;

    public void setFranchisee(Franchisee franchisee) {
        this.franchisee = franchisee;
        franchisee.getCoupon().add(this);
    }

    public static Coupon createCoupon(Franchisee franchisee) {
        Coupon coupon = new Coupon();
        coupon.setFranchisee(franchisee);
        return coupon;
    }
}
