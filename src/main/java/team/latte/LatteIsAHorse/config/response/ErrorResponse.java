package team.latte.LatteIsAHorse.config.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static javafx.scene.input.KeyCode.T;

@Getter
@SuperBuilder
public class ErrorResponse extends CommonResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldError> errors;

    @Getter
    public static class FieldError<T>{
        private String field;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String value;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<T> values;
        private String cause;

        @Builder
        public FieldError(String field, String value, List<T> values, String reason) {
            this.field = field;
            this.value = value;
            this.values = values;
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
