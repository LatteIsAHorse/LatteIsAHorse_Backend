package team.latte.LatteIsAHorse.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.extern.slf4j.Slf4j;
import team.latte.LatteIsAHorse.common.domain.EnumCommonType;

@Slf4j
public enum UserGrade implements EnumCommonType {

    FRESHMAN("1학년",1),
    SOPHOMORE("2학년",2),
    JUNIOR("3학년",3),
    SENIOR("4학년",4),
    GRADUATED("졸업생",5);

    private String desc;
    private int code;

    UserGrade(String desc, int code) {
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

    @JsonCreator
    public static UserGrade fromUserGrade(String val){
        for(UserGrade userGrade : UserGrade.values()){
            if(userGrade.getDesc().equals(val)){
                return userGrade;
            }
        }
        return null;
    }
}
