package team.latte.LatteIsAHorse.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.latte.LatteIsAHorse.common.domain.BaseTimeEntity;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LatteStackInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long latteStackInfoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    public void setUser(User user) {
        this.user = user;
        user.getLatteStackInfos().add(this);
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        quiz.getLatteStackInfos().add(this);
    }
}
