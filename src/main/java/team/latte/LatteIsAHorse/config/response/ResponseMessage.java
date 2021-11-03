package team.latte.LatteIsAHorse.config.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {

    SIGNUP_SUCCESS("회원가입이 성공했습니다."),
    SIGNUP_FAIL("회원가입이 실패했습니다."),

    SIGNIN_SUCCESS("로그인이 성공했습니다."),
    SIGNIN_FAIL("로그인이 실패했습니다."),

    COLLEGE_AUTH_SUCCESS("대학 인증이 완료었습니다."),
    COLLEGE_AUTH_FAIL("대학 인증에 실패했습니다.");

    private final String message;
}
