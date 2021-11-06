package team.latte.LatteIsAHorse.model.tag;

import lombok.*;
import team.latte.LatteIsAHorse.model.post.PostTag;
import team.latte.LatteIsAHorse.model.quiz.QuizTag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<PostTag> postTags = new ArrayList<>();

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<QuizTag> quizTags = new ArrayList<>();

    private String name;

    public static Tag createTag(String name) {
        Tag tag = new Tag();
        tag.name = name;
        return tag;
    }
}
