package team.latte.LatteIsAHorse.model.user;

import team.latte.LatteIsAHorse.common.domain.EnumCommonType;

public enum UserState implements EnumCommonType {

    VALID("활동 가능한 유저",1),
    SUSPEND("정지된 유저",2);

    private String desc;
    private int code;

    UserState(String desc, int code) {
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
