package team.latte.LatteIsAHorse.model.post;

import lombok.*;
import team.latte.LatteIsAHorse.model.quiz.Quiz;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    private String url;

    public void setPost(Post post) {
        this.post = post;
        post.getImages().add(this);
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        quiz.getImages().add(this);
    }

    public void changeUrl(String url) {
        this.url = url;
    }

    public static Image createImage(Quiz quiz, Post post, String url) {
        Image image = new Image();

        if (quiz != null) image.setQuiz(quiz);
        if (post != null) image.setPost(post);
        image.changeUrl(url);
        return image;
    }
}
