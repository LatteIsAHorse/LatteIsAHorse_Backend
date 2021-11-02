package team.latte.LatteIsAHorse.common.domain;

import team.latte.LatteIsAHorse.model.coupon.CouponState;

import javax.persistence.Converter;

@Converter
public class CouponStateConverter extends AbstractEnumAttributeConverter{
    public static final String ENUM_NAME = "쿠폰 상태";

    public CouponStateConverter() {
        super(CouponState.class,false,ENUM_NAME);
    }
}
