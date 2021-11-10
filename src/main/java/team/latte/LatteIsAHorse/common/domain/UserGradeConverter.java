package team.latte.LatteIsAHorse.common.domain;

import team.latte.LatteIsAHorse.model.user.UserGrade;

import javax.persistence.Converter;

@Converter
public class UserGradeConverter extends AbstractEnumAttributeConverter{
    private static final String ENUM_NAME = "유저 학년";

    public UserGradeConverter() {
        super(UserGrade.class, false, ENUM_NAME);
    }
}
