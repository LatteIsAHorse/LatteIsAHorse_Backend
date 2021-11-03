package team.latte.LatteIsAHorse.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.latte.LatteIsAHorse.config.response.ApiResponse;
import team.latte.LatteIsAHorse.config.response.ErrorResponse;
import team.latte.LatteIsAHorse.config.response.ExceptionStatus;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    /**
     * GET 파라미터 유효성 에러를 처리합니다.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ErrorResponse handleConstraintViolationException (ConstraintViolationException e) {
        int idx = e.getMessage().indexOf(" ");
        String message = e.getMessage().substring(idx+1);
        return ErrorResponse.ErrorOf(400, message);
    }

    /**
     * POST body의 유효성 에러를 처리합니다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        /* FieldError를 받아온다*/
        List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(e.getBindingResult());
        return ErrorResponse.FieldErrorOf(400, ExceptionStatus.BAD_REQUEST_ERROR.getMessage(), fieldErrors);
    }

    private List<ErrorResponse.FieldError> getFieldErrors(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();

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
        return ErrorResponse.ErrorOf(500, ExceptionStatus.SERVER_ERROR.getMessage());
    }
}
