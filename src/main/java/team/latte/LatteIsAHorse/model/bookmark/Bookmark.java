package team.latte.LatteIsAHorse.model.bookmark;

import lombok.*;
import team.latte.LatteIsAHorse.common.domain.BaseTimeEntity;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Bookmark extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmarkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    private int valid;

    public void setUser(User user) {
        this.user = user;
        user.getBookmarks().add(this);
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        quiz.getBookmarks().add(this);
    }

    public void changeValid() {
        if (this.valid == 1) this.valid = 0;
        else if (this.valid == 0) this.valid = 1;
    }

    public static Bookmark createBookmark(User user, Quiz quiz) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setQuiz(quiz);
        bookmark.valid = 1;
        return bookmark;
    }
}
