package team.latte.LatteIsAHorse.model.quiz;

import lombok.*;
import team.latte.LatteIsAHorse.model.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class QuizLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    private int valid;

    public void setUser(User user) {
        this.user = user;
        user.getQuizLikes().add(this);
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        quiz.getQuizLikes().add(this);
    }

    public void changeValid() {
        if (this.valid == 1) this.valid = 0;
        else if (this.valid == 0) this.valid = 1;
    }

    public static QuizLike createQuizLike(User user, Quiz quiz) {
        QuizLike quizLike = new QuizLike();
        quizLike.setUser(user);
        quizLike.setQuiz(quiz);
        quizLike.valid = 1;
        return quizLike;
    }
}

