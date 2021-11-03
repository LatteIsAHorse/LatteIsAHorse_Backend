package team.latte.LatteIsAHorse.config.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@SuperBuilder
@Slf4j
public class ApiResponse<T> extends CommonResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cause;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    /**
     * 성공한 요청을 반환
     */
    public static <T> ApiResponse<T> of(HttpStatus status, ResponseMessage responseMessage) {
        return of(null, status, responseMessage);
    }

    public static <T> ApiResponse<T> of(T t, HttpStatus status, ResponseMessage responseMessage) {
        return ApiResponse.<T>builder()
                .code(status.value())
                .message(responseMessage.getMessage())
                .data(t)
                .build();
    }

    /**
     * 실패한 요청을 반환
     */
    public static <T> ApiResponse<T> of(HttpStatus status, ResponseMessage responseMessage, ExceptionStatus exceptionStatus) {
        return ApiResponse.<T>builder()
                .code(status.value())
                .message(responseMessage.getMessage())
                .cause(exceptionStatus.getMessage())
                .build();
    }
}