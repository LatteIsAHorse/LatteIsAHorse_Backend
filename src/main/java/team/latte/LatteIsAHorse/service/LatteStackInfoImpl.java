package team.latte.LatteIsAHorse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.latte.LatteIsAHorse.dto.AllLatteStackInfoRes;
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
     * 라떼 포인트 내역을 모두 조회한다.
     * @param userEmail
     * @return
     */
    @Override
    public List<AllLatteStackInfoRes> allLatteStackInfoList(String userEmail) {
        List<LatteStackInfo> latteStackInfos = latteStackInfoRepository.findAll();

        return AllLatteStackInfoRes.listOf(latteStackInfos);
    }
}
