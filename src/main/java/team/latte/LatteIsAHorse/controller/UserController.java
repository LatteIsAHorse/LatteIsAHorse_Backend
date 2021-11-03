package team.latte.LatteIsAHorse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import team.latte.LatteIsAHorse.config.response.ApiResponse;
import team.latte.LatteIsAHorse.config.response.ExceptionStatus;
import team.latte.LatteIsAHorse.config.response.ResponseMessage;
import team.latte.LatteIsAHorse.config.security.jwt.JwtTokenProvider;
import team.latte.LatteIsAHorse.dto.SignInReq;
import team.latte.LatteIsAHorse.dto.SignUpReq;
import team.latte.LatteIsAHorse.service.UserService;
import team.latte.LatteIsAHorse.model.user.User;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class UserController {

    private final RedisTemplate<String, String> redis;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 서비스 자체 유저로 회원 가입
     * @param req : 회원가입 DTO
     * @param response : HTTPResponse
     * @return
     */
    @PostMapping("/signup")
    public ApiResponse<Object> signup(@Valid @RequestBody SignUpReq req, HttpServletResponse response) {
        User user = userService.join(req);
        if (user == null)
            return ApiResponse.of(HttpStatus.FORBIDDEN, ResponseMessage.SIGNUP_FAIL, ExceptionStatus.DUPLICATED_USER_EMAIL);

        String token = jwtTokenProvider.generateToken(user.getUserId(), user.getEmail(), user.getRole());
        response.setHeader("AUTH", token);
        return ApiResponse.of(HttpStatus.CREATED, ResponseMessage.SIGNUP_SUCCESS);
    }

    /**
     * 서비스 자체 유저 로그인
     * @param req : 로그인 DTO
     * @param response : HTTPResponse
     * @return
     */
    @PostMapping("/signin")
    public ApiResponse<Object> signin(@RequestBody SignInReq req, HttpServletResponse response) {
        User user = userService.login(req);
        if (user == null)
            return ApiResponse.of(HttpStatus.FORBIDDEN, ResponseMessage.SIGNIN_FAIL,ExceptionStatus.NOT_FOUND_USER_EMAIL);
        if(!passwordEncoder.matches(req.getPassword(),user.getPassword())) {
            return ApiResponse.of(HttpStatus.FORBIDDEN, ResponseMessage.SIGNIN_FAIL,ExceptionStatus.NOT_FOUND_USER_EMAIL);
        }
        String token = jwtTokenProvider.generateToken(user.getUserId(), user.getEmail(), user.getRole());
        response.setHeader("AUTH", token);

        redis.opsForValue().set(user.getEmail().toString(), "Login");
        return ApiResponse.of(HttpStatus.OK,ResponseMessage.SIGNIN_SUCCESS);
    }

}
