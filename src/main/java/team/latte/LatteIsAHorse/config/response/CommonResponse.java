package team.latte.LatteIsAHorse.config.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class CommonResponse {

    private int code;

    private String message;
}
