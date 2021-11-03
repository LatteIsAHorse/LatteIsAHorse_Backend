package team.latte.LatteIsAHorse.config.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class ErrorResponse extends CommonResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldError> errors;

    @Getter
    public static class FieldError {
        private String field;
        private String value;
        private String cause;

        @Builder
        public FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.cause = reason;
        }
    }

    public static ErrorResponse ErrorOf(int code, String message) {
        return ErrorResponse.builder()
                .code(code)
                .message(message)
                .build();
    }

    public static ErrorResponse FieldErrorOf(int code, String message, List<FieldError> errors) {
        return ErrorResponse.builder()
                .code(code)
                .message(message)
                .errors(errors)
                .build();
    }

}
