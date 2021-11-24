package team.latte.LatteIsAHorse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.latte.LatteIsAHorse.dto.AllLatteStackInfoRes;
import team.latte.LatteIsAHorse.dto.LatteStackInfoSearch;
import team.latte.LatteIsAHorse.model.user.LatteStackInfo;
import team.latte.LatteIsAHorse.repository.LatteStackInfoRepository;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LatteStackInfoImpl implements LatteStackInfoService{

    private final LatteStackInfoRepository latteStackInfoRepository;

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
