package team.latte.LatteIsAHorse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.latte.LatteIsAHorse.model.quiz.Answer;
import team.latte.LatteIsAHorse.model.quiz.Quiz;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizRes {

    private Long quizId;

    private List<String> images;

    private String title;

    private List<AnswerDto> answers;

    private boolean quizLike;

    private boolean reportSuspicion;

    private Long reportSuspicionCnt;

    public static QuizRes of(Quiz quiz, boolean quizLike, boolean reportSuspicion) {

        return QuizRes.builder()
                .quizId(quiz.getQuizId())
                .images(quiz.getImages().stream().map(image -> image.getUrl()).collect(Collectors.toList()))
                .title(quiz.getTitle())
                .answers(quiz.getAnswers().stream().map(answer -> new AnswerDto(answer)).collect(Collectors.toList()))
                .quizLike(quizLike)
                .reportSuspicion(reportSuspicion)
                .reportSuspicionCnt(Long.valueOf(quiz.getReportSuspicions().size()))
                .build();
    }

    @Data
    static class AnswerDto {

        private Long answerId;

        private String content;

        public AnswerDto(Answer answer) {
            this.answerId = answer.getAnswerId();
            this.content = answer.getContent();
        }
    }

    @Data
    static class imageDto {

        private String url;

        public imageDto(String url) {
            this.url = url;
        }
    }
}
