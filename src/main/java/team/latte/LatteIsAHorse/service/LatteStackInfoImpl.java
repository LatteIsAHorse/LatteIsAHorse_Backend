package team.latte.LatteIsAHorse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.latte.LatteIsAHorse.config.exception.CustomException;
import team.latte.LatteIsAHorse.config.response.ExceptionStatus;
import team.latte.LatteIsAHorse.dto.AllLatteStackInfoRes;
import team.latte.LatteIsAHorse.dto.LatteStackInfoSearch;
import team.latte.LatteIsAHorse.model.user.LatteStackInfo;
import team.latte.LatteIsAHorse.model.user.RoleType;
import team.latte.LatteIsAHorse.model.user.User;
import team.latte.LatteIsAHorse.repository.LatteStackInfoRepository;
import team.latte.LatteIsAHorse.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LatteStackInfoImpl implements LatteStackInfoService{

    private final LatteStackInfoRepository latteStackInfoRepository;
    private final UserRepository userRepository;

    /**
     * 사용 가능한 라떼 포인트 조회
     * @param userEmail : 유저 이메일
     * @return
     */
    @Override
    public int getAvailableLattepoint(String userEmail) {
        User user = userRepository.findByEmailAndRoleOnlyNotLeave(userEmail, RoleType.ROLE_UNKNOWN).orElse(null);

        if (user == null) throw new CustomException(ExceptionStatus.BAD_REQUEST_ERROR);

        return user.getLatteStack();
    }

    /**
     * 라떼 포인트 내역 조회
     * @Param LatteStackInfoSearch : 조회 조건
     * @param userEmail : 유저 이메일
     * @return
     */
    @Override
    public List<AllLatteStackInfoRes> allLatteStackInfoList(LatteStackInfoSearch latteStackInfoSearch, String userEmail) {

        List<LatteStackInfo> latteStackInfos = latteStackInfoRepository.findByUserEmailAndYearAndMonth(
                userEmail, latteStackInfoSearch.getYear(),latteStackInfoSearch.getMonth());

        return AllLatteStackInfoRes.listOf(latteStackInfos);
    }

}
