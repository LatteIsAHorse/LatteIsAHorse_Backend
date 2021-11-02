package team.latte.LatteIsAHorse.common.domain;

import team.latte.LatteIsAHorse.model.user.UserState;

import javax.persistence.Converter;

@Converter
public class UserStateConverter extends AbstractEnumAttributeConverter{
    private static final String ENUM_NAME = "유저 상태";

    public UserStateConverter() {
        super(UserState.class, false, ENUM_NAME);
    }
}
