package team.latte.LatteIsAHorse.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import team.latte.LatteIsAHorse.config.response.ExceptionStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

    /**
     * iOS 앱에서는 Redirect가 불필요하므로 401 에러와 에러 메세지를 return한다.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.warn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) + " : " + "AuthenticationEntryPoint");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ExceptionStatus.NOT_AUTHENTICATED_USER.getMessage());
    }

}
