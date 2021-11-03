package team.latte.LatteIsAHorse.config.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionStatus {

    // Param, DTO
    BAD_REQUEST_ERROR("유효한 입력값이 아닙니다."),

    // JWT
    NOT_AUTHENTICATED_USER("로그인이 필요합니다."),
    EMPTY_JWT("로그인이 필요합니다."),

    // USER
    DUPLICATED_USER_EMAIL("이미 존재하는 이메일입니다."),
    NOT_FOUND_USER_EMAIL("이메일 또는 패스워드를 다시 확인해주세요."),

    // SERVER
    SERVER_ERROR("서버 에러");

    private final String message;

}
