package team.latte.LatteIsAHorse.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.latte.LatteIsAHorse.model.comment.Comment;
import team.latte.LatteIsAHorse.model.comment.Reply;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllCommentRes {

    private Long commentId;

    private String writer;

    private String content;

    private String writeDate;

    private List<ReplyDto> replies;

    @JsonIgnore
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");

    public static AllCommentRes of(Comment comment) {

        return AllCommentRes.builder()
                .commentId(comment.getCommentId())
                .writer(comment.getWriter())
                .content(comment.getContent())
                .writeDate(decideDateFormat(comment.getCreatedAt()))
                .replies(comment.getReply().stream().map(reply -> new ReplyDto(reply)).collect(Collectors.toList()))
                .build();
    }

    public static List<AllCommentRes> listOf(List<Comment> comments) {
        return comments.stream().map(AllCommentRes::of)
                .collect(Collectors.toList());
    }

    private static String decideDateFormat(LocalDateTime ld) {
        String writeDate = formatter.format(ld);
        String curYear = LocalDateTime.now().toString().substring(2, 4);
        if (writeDate.substring(0,2).equals(curYear))
            writeDate = writeDate.substring(3);
        return writeDate;
    }

    @Data
    static class ReplyDto {

        private Long replyId;

        private String writer;

        private String content;

        private String writeDate;

        public ReplyDto(Reply reply) {
            this.replyId = reply.getReplyId();
            this.content = reply.getContent();
            this.writer = reply.getWriter();
            this.writeDate = decideDateFormat(reply.getCreatedAt());
        }
    }
}
