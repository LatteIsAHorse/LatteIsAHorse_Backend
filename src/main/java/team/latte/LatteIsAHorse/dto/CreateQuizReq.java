package team.latte.LatteIsAHorse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateQuizReq {


    @NotBlank(message = "퀴즈 제목을 입력해주세요.")
    private String title;

    @NotNull(message = "두 개 이상의 선택지가 필요합니다.")
    @Size(min=2, message = "두 개 이상의 선택지가 필요합니다.")
    private List<String> answers;

    @NotNull(message = "퀴즈에 정답을 선택해주세요.")
    private int answer;

    private List<String> tags;

    private MultipartFile imageFile;
}
