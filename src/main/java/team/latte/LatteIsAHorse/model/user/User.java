package team.latte.LatteIsAHorse.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.latte.LatteIsAHorse.common.domain.UserGradeConverter;
import team.latte.LatteIsAHorse.common.domain.UserStateConverter;
import team.latte.LatteIsAHorse.model.bookmark.Bookmark;
import team.latte.LatteIsAHorse.model.comment.Comment;
import team.latte.LatteIsAHorse.model.comment.CommentLike;
import team.latte.LatteIsAHorse.model.comment.Reply;
import team.latte.LatteIsAHorse.model.comment.ReplyLike;
import team.latte.LatteIsAHorse.model.coupon.CouponList;
import team.latte.LatteIsAHorse.model.post.Post;
import team.latte.LatteIsAHorse.model.post.PostLike;
import team.latte.LatteIsAHorse.model.quiz.Quiz;
import team.latte.LatteIsAHorse.model.quiz.QuizLike;
import team.latte.LatteIsAHorse.model.quiz.ReportSuspicion;
import team.latte.LatteIsAHorse.model.quiz.UserAnswer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToMany(mappedBy = "user")
    private List<CouponList> couponList = new ArrayList<>(); // 쿠폰 발급 기록은 남아있어야함.

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LatteStackInfo> latteStackInfos = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostLike> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<QuizLike> quizLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ReplyLike> replyLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CommentLike> commentLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAnswer> userAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Quiz> quizzes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ReportSuspicion> reportSuspicions = new ArrayList<>();

    private String email;

    private String password;

    private String college;

    @Convert(converter = UserGradeConverter.class)
    private UserGrade grade;

    private String nickname;

    private LocalDateTime lastLogin;

    private int latteStack;

    private String phoneNo;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Convert(converter = UserStateConverter.class)
    private UserState state;

    @Builder
    public User(String email, String password, String college, UserGrade grade, String nickname, LocalDateTime lastLogin, int latteStack, String phoneNo, RoleType role, UserState state) {
        this.email = email;
        this.password = password;
        this.college = college;
        this.grade = grade;
        this.nickname = nickname;
        this.lastLogin = lastLogin;
        this.latteStack = latteStack;
        this.phoneNo = phoneNo;
        this.role = role;
        this.state = state;
    }

    public void addLatteStack() {
        this.latteStack++;
    }
}
