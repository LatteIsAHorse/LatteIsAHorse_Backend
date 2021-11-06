package team.latte.LatteIsAHorse.model.quiz;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Slf4j
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    private String content;

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        quiz.getAnswers().add(this);
    }

    public static Answer createAnswer(Quiz quiz, String content) {
        Answer answer = new Answer();
        answer.content = content;
        answer.setQuiz(quiz);
        return answer;
    }
}
