package team.latte.LatteIsAHorse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.latte.LatteIsAHorse.dto.AllBookmarkRes;
import team.latte.LatteIsAHorse.model.bookmark.Bookmark;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.user.User;
import team.latte.LatteIsAHorse.model.user.UserState;
import team.latte.LatteIsAHorse.repository.BookmarkRepository;
import team.latte.LatteIsAHorse.repository.QuizRepository;
import team.latte.LatteIsAHorse.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BookmarkServiceImpl implements BookmarkService{

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    /**
     * 북마크를 생성한다.
     * @param quizId : 저장할 퀴즈 ID
     * @param userEmail : 유저 이메일
     * @return
     */
    @Transactional
    @Override
    public int markOrCancelQuiz(Long quizId, String userEmail) {
        User user = userRepository.findByEmailAndState(userEmail, UserState.VALID)
                .orElse(null);

        Quiz quiz = quizRepository.findById(quizId)
                .orElse(null);

        Bookmark bookmark = bookmarkRepository.findByUserAndQuiz(user, quiz)
                .orElse(null);

        if (bookmark == null) {
            bookmark = Bookmark.createBookmark(user, quiz);
            bookmarkRepository.save(bookmark);
            return 1;
        }

        bookmark.changeValid();
        return bookmark.getValid();
    }

    /**
     * 퀴즈 북마크 조회
     * @param userEmail 유저 이메일
     * @return
     */
    @Override
    public List<AllBookmarkRes> allBookmarkList(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElse(null);

        List<Bookmark> bookmarks = bookmarkRepository.findByUserAndValid(user);

        return AllBookmarkRes.listOf(bookmarks);
    }
}
