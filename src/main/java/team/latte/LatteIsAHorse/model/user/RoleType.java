package team.latte.LatteIsAHorse.model.user;

import team.latte.LatteIsAHorse.common.domain.EnumCommonType;

public enum RoleType implements EnumCommonType {

    ROEL_UNKNOWN(1,"알수없음"),
    ROLE_GUEST(2,"대학 인증 대기중"),
    ROLE_USER(3, "유저"),
    ROLE_ADMIN(4,"관리자");

    private int code;
    private String desc;

    RoleType(int code, String desc) {
        this.code = code;
        this.desc = desc;
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
