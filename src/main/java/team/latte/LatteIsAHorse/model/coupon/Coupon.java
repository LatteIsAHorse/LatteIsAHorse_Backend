package team.latte.LatteIsAHorse.model.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.latte.LatteIsAHorse.common.domain.CouponStateConverter;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private List<CouponList> couponList;

    private String seq;

    private Long validTerm;

    @Convert(converter = CouponStateConverter.class)
    private CouponState couponState;
}
