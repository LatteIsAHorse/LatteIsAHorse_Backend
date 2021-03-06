package team.latte.LatteIsAHorse.model.quiz;

import lombok.*;
import team.latte.LatteIsAHorse.common.domain.BaseTimeEntity;
import team.latte.LatteIsAHorse.model.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class UserAnswer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAnswerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    private int choiceNum;

    public void setUser(User user) {
        this.user = user;
        user.getUserAnswers().add(this);
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        quiz.getUserAnswers().add(this);
    }

    public static UserAnswer createUserAnswer(Quiz quiz, User user, int choiceNum) {
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUser(user);
        userAnswer.setQuiz(quiz);
        userAnswer.choiceNum = choiceNum;
        return userAnswer;
    }
}
