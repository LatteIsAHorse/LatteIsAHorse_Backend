package team.latte.LatteIsAHorse.service;

import team.latte.LatteIsAHorse.dto.AllLatteStackInfoRes;
import team.latte.LatteIsAHorse.dto.LatteStackInfoSearch;

import java.util.List;

public interface LatteStackInfoService {

    List<AllLatteStackInfoRes> allLatteStackInfoList(LatteStackInfoSearch latteStackInfoSearch, String userEmail);

    int getAvailableLattepoint(String userEmail);

}
