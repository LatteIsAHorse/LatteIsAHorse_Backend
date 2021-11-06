package team.latte.LatteIsAHorse.config.exception;

import lombok.AllArgsConstructor;
import team.latte.LatteIsAHorse.config.response.ExceptionStatus;

@AllArgsConstructor
public class CustomException extends RuntimeException {
    private ExceptionStatus exceptionStatus;
}
