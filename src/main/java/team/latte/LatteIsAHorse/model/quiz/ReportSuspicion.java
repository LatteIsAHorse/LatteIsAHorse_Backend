package team.latte.LatteIsAHorse.model.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.latte.LatteIsAHorse.model.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReportSuspicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportSuspicionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    public void setUser(User user) {
        this.user = user;
        user.getReportSuspicions().add(this);
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        quiz.getReportSuspicions().add(this);
    }

    public static ReportSuspicion createReportSuspicion(User user, Quiz quiz) {
        ReportSuspicion reportSuspicion = new ReportSuspicion();
        reportSuspicion.setUser(user);
        reportSuspicion.setQuiz(quiz);
        return reportSuspicion;
    }
}
