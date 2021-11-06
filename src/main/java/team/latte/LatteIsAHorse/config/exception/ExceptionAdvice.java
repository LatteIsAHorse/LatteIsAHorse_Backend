package team.latte.LatteIsAHorse.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.latte.LatteIsAHorse.config.response.ErrorResponse;
import team.latte.LatteIsAHorse.config.response.ExceptionStatus;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    /**
     * GET 파라미터 유효성 에러를 처리합니다.
     * ConstraintViolationException은 ValidationException을 상속한다.
     */
    @ExceptionHandler(ValidationException.class)
    protected ErrorResponse handleValidationException (ValidationException e) {
        int idx = e.getMessage().indexOf(" ");
        String message = e.getMessage().substring(idx+1);
        return ErrorResponse.ErrorOf(400, message);
    }

    /**
     * POST body의 유효성 에러를 처리합니다.
     * MethodArgumentNotValidException은 BindException을 상속한다.
     */
    @ExceptionHandler(BindException.class)
    protected ErrorResponse handleMethodArgumentNotValidException(BindException e) {
        /* FieldError를 받아온다*/
        List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(e.getBindingResult());
        return ErrorResponse.FieldErrorOf(400, ExceptionStatus.BAD_REQUEST_ERROR.getMessage(), fieldErrors);
    }

    private List<ErrorResponse.FieldError> getFieldErrors(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();

        if (errors.get(0).getRejectedValue() instanceof List) {
            return errors.parallelStream().map(error -> ErrorResponse.FieldError.builder()
                    .field(error.getField())
                    .values((List) error.getRejectedValue())
                    .reason(error.getDefaultMessage())
                    .build())
                    .collect(Collectors.toList());
        }
        return errors.parallelStream().map(error -> ErrorResponse.FieldError.builder()
                .field(error.getField())
                .value((String) error.getRejectedValue())
                .reason(error.getDefaultMessage())
                .build())
                .collect(Collectors.toList());
    }

    /**
     * 나머지 에러를 처리합니다.
     */
    @ExceptionHandler(Exception.class)
    protected ErrorResponse handleRestException (Exception e) {
        log.warn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) + ":" + e.getMessage());
        e.printStackTrace();
        return ErrorResponse.ErrorOf(500, ExceptionStatus.SERVER_ERROR.getMessage());
    }

}
