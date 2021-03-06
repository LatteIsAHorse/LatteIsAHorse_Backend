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
    COLLEGE_AUTH_FAIL("대학 인증에 실패했습니다."),

    QUIZ_CREATED_SUCCESS("퀴즈 등록이 성공했습니다."),
    QUIZ_CREATED_FAIL("퀴즈 등록이 실패했습니다."),

    QUIZ_LIST_SUCCESS("퀴즈 목록 조회가 성공했습니다."),
    QUIZ_LIST_FAIL("퀴즈 목록 조회가 실패했습니다."),

    QUIZ_DETAIL_SUCCESS("퀴즈 조회가 성공했습니다."),
    QUIZ_DETAIL_FAIL("퀴즈 조회가 실패했습니다."),

    QUIZ_CHOOSE_ANSWER_SUCCESS("퀴즈 답 투표에 성공했습니다."),
    QUIZ_CHOOSE_ANSWER_FAIL("퀴즈 답 투표에 실패했습니다."),

    COMMENT_CREATED_SUCCESS("댓글이 성공적으로 등록되었습니다."),
    COMMENT_CREATED_FAIL("댓글 등록에 실패했습니다."),

    COMMENT_LIST_SUCCESS("댓글 조회가 성공했습니다."),
    COMMENT_LIST_FAIL("댓글 조회가 실패했습니다."),

    QUIZ_LIKE_SUCCESS("좋아요가 성공했습니다."),
    QUIZ_LIKE_CANCEL_SUCCESS("좋아요가 취소되었습니다."),
    QUIZ_LIKE_OR_CANCEL_FAIL("좋아요 또는 취소 요청이 실패했습니다."),

    REPLY_CREATED_SUCCESS("답글이 성공적으로 등록되었습니다."),
    REPLY_CREATED_FAIL("답글 등록에 실패했습니다."),

    COMMENT_LIKE_SUCCESS("좋아요가 성공했습니다."),
    COMMENT_LIKE_CANCEL_SUCCESS("좋아요가 취소되었습니다."),
    COMMENT_LIKE_OR_CANCEL_FAIL("좋아요 또는 취소 요청이 실패했습니다."),

    REPLY_LIKE_SUCCESS("좋아요가 성공했습니다."),
    REPLY_LIKE_CANCEL_SUCCESS("좋아요가 취소되었습니다."),
    REPLY_LIKE_OR_CANCEL_FAIL("좋아요 또는 취소 요청이 실패했습니다."),

    BOOKMARK_QUIZ_SUCCESS("퀴즈를 북마크에 추가했습니다."),
    BOOKMARK_QUIZ_CANCEL_SUCCESS("퀴즈를 북마크에서 해제했습니다."),
    BOOKMARK_QUIZ_OR_CANCEL_FAIL("퀴즈 북마크 또는 해제 요청이 실패했습니다."),

    BOOKMARK_LIST_SUCCESS("북마크 조회가 성공했습니다."),
    BOOKMARK_LIST_FAIL("북마크 조회에 실패했습니다."),

    LATTE_STACK_HISTORY_SUCCESS("라떼 포인트 내역 조회가 성공했습니다."),
    LATTE_STACK_HISTORY_FAIL("라떼 포인트 내역 조회에 실패했습니다."),

    GET_AVAILABLE_LATTE_STACK_SUCCESS("사용 가능한 라떼 포인트 조회가 성공했습니다."),
    GET_AVAILABLE_LATTE_STACK_FAIL("사용 가능한 라떼 포인트 조회에 실패했습니다.");

    private final String message;
}
