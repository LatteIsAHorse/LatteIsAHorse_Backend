package team.latte.LatteIsAHorse.model.quiz;

import lombok.*;
import team.latte.LatteIsAHorse.model.tag.Tag;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class QuizTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizTagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag")
    private Tag tag;

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        quiz.getQuizTags().add(this);
    }

    public void setTag(Tag tag) {
        this.tag = tag;
        tag.getQuizTags().add(this);
    }

    public static QuizTag createQuizReq(Quiz quiz, Tag tag) {
        QuizTag quizTag = new QuizTag();
        quizTag.setQuiz(quiz);
        quizTag.setTag(tag);
        return quizTag;
    }
}
