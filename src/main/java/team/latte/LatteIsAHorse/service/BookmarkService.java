package team.latte.LatteIsAHorse.service;

import team.latte.LatteIsAHorse.dto.AllBookmarkRes;

import java.util.List;

public interface BookmarkService {

    int markOrCancelQuiz(Long quizId, String userEmail);

    List<AllBookmarkRes> allBookmarkList(String userEmail);
}
