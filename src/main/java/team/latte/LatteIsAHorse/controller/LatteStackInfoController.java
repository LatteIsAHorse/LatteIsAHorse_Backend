package team.latte.LatteIsAHorse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team.latte.LatteIsAHorse.config.response.ApiResponse;
import team.latte.LatteIsAHorse.config.response.ResponseMessage;
import team.latte.LatteIsAHorse.config.security.authentication.CustomUserDetails;
import team.latte.LatteIsAHorse.service.LatteStackInfoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class LatteStackInfoController {

    private final LatteStackInfoService latteStackInfoService;

    /**
     * 라떼 포인트 내역 조회
     * @param customUserDetails : 인증된 유저 객체
     * @return
     */
    @GetMapping("/mypage/lattepoint")
    public ApiResponse<Object> allBookmarkList(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ApiResponse.of(latteStackInfoService.allLatteStackInfoList(customUserDetails.getUsername()), HttpStatus.OK, ResponseMessage.LATTE_STACK_LIST_SUCCESS);
    }

}
