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

    private int quizLikes;

    private int commentsCnt;

    private String imageUrl;

    private boolean isScrap;

    public static AllQuizRes of(Quiz quiz) {
        return AllQuizRes.builder()
                .quizId(quiz.getQuizId())
                .title(quiz.getTitle())
                .writer(quiz.getWriter())
                .views(quiz.getViews())
                .quizLikes(quiz.getQuizLikes().size())
                .commentsCnt(quiz.getComments().size())
                .imageUrl(quiz.getImages().size() > 0 ? quiz.getImages().get(0).getUrl() : null)
                .isScrap(quiz.getScraps().size() > 0 ? true : false)
                .build();
    }

    public static List<AllQuizRes> listOf(List<Quiz> quizzes) {
        return quizzes.stream().map(AllQuizRes::of)
                .collect(Collectors.toList());
    }
}
