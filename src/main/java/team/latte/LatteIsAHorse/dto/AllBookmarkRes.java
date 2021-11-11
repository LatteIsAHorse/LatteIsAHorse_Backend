package team.latte.LatteIsAHorse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.latte.LatteIsAHorse.model.bookmark.Bookmark;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllBookmarkRes {

    private Long quizId;

    private String imageUrl;

    private String title;

    private String grade;

    private String writer;

    private Long views;

    private Long quizLikesCnt;

    public static AllBookmarkRes of(Bookmark bookmark) {
        return AllBookmarkRes.builder()
                .quizId(bookmark.getQuiz().getQuizId())
                .title(bookmark.getQuiz().getTitle())
                .grade(bookmark.getQuiz().getUser().getGrade().getDesc().substring(0,1))
                .writer(bookmark.getQuiz().getWriter())
                .views(bookmark.getQuiz().getViews())
                .quizLikesCnt(Long.valueOf(bookmark.getQuiz().getQuizLikes().stream().filter(quizLike -> quizLike.getValid() == 1).count()))
                .imageUrl(bookmark.getQuiz().getImages().size() > 0 ? bookmark.getQuiz().getImages().get(0).getUrl() : null)
                .build();
    }

    public static List<AllBookmarkRes> listOf(List<Bookmark> bookmarks) {
        return bookmarks.stream().map(AllBookmarkRes::of)
                .collect(Collectors.toList());
    }
}
