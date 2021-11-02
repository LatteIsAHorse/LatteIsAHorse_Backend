package team.latte.LatteIsAHorse.model.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<QuizAnswer> quizAnswers = new ArrayList<>();

    private String content;

}
