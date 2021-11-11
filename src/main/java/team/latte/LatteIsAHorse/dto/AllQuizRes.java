package team.latte.LatteIsAHorse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.latte.LatteIsAHorse.model.quiz.Quiz;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllQuizRes {

    private Long quizId;

    private String title;

    private String writer;

    private Long views;

    private Long quizLikesCnt;

    private Long commentsCnt;

    private String imageUrl;

    private boolean isScrap;

    private List<String> tags;

    public static AllQuizRes of(Quiz quiz) {
        return AllQuizRes.builder()
                .quizId(quiz.getQuizId())
                .title(quiz.getTitle())
                .writer(quiz.getWriter())
                .views(quiz.getViews())
                .quizLikesCnt(Long.valueOf(quiz.getQuizLikes().stream().filter(quizLike -> quizLike.getValid() == 1).count()))
                .commentsCnt(Long.valueOf(quiz.getComments().size()))
                .imageUrl(quiz.getImages().size() > 0 ? quiz.getImages().get(0).getUrl() : null)
                .isScrap(quiz.getBookmarks().size() > 0 ? true : false)
                .tags(quiz.getQuizTags().size() > 0 ? quiz.getQuizTags().stream().map(quizTag -> quizTag.getTag().getName()).collect(Collectors.toList()): null)
                .build();
    }

    public static List<AllQuizRes> listOf(List<Quiz> quizzes) {
        return quizzes.stream().map(AllQuizRes::of)
                .collect(Collectors.toList());
    }
}
