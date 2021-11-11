package team.latte.LatteIsAHorse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReplyReq {

    @Length(min = 2, message = "댓글은 2자 이상 입력하셔야 합니다.")
    private String content;
}
