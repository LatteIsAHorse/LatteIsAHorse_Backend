package team.latte.LatteIsAHorse.model.user;

import team.latte.LatteIsAHorse.common.domain.EnumCommonType;

public enum LatteStackInfoReason implements EnumCommonType {

    GIFTICON(1,"기프티콘"),
    QUIZ_POST(2,"퀴즈 만들기"),
    QUIZ_CORRECT(3, "퀴즈 정답 맞춤");

    private int code;
    private String desc;

    LatteStackInfoReason(int code, String desc) {
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
