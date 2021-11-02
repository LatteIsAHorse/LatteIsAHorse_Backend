package team.latte.LatteIsAHorse.model.coupon;

import team.latte.LatteIsAHorse.common.domain.EnumCommonType;

public enum CouponState implements EnumCommonType {
    EXPIRED("만료", 1),
    VALID("유효",2);

    private String desc;
    private int code;

    CouponState(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
