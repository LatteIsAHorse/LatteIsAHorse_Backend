package team.latte.LatteIsAHorse.model.quiz;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import team.latte.LatteIsAHorse.common.domain.BaseTimeEntity;
import team.latte.LatteIsAHorse.model.comment.Comment;
import team.latte.LatteIsAHorse.model.post.Image;
import team.latte.LatteIsAHorse.model.post.Scrap;
import team.latte.LatteIsAHorse.model.user.LatteStackInfo;
import team.latte.LatteIsAHorse.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Slf4j
public class Quiz extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<QuizLike> quizLikes = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Scrap> scraps = new ArrayList<>();

    @OneToMany(mappedBy = "quiz")
    private List<LatteStackInfo> latteStackInfos = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<QuizTag> quizTags = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "quiz")
    private List<UserAnswer> userAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "quiz")
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<ReportSuspicion> reportSuspicions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    private String title;

    private int answer;

    private String writer;

    private Long views;

    public void setUser(User user) {
        this.user = user;
        user.getQuizzes().add(this);
    }

    @Builder
    public Quiz(User user, String title, int answer, String writer) {
        this.user = user;
        this.title = title;
        this.answer = answer;
        this.writer = writer;
        this.views = 0L;
    }
}
