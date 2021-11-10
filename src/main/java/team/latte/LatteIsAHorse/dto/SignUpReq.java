package team.latte.LatteIsAHorse.dto;

import lombok.*;
import team.latte.LatteIsAHorse.model.user.UserGrade;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpReq {

    @Email(message = "이메일을 확인해주세요.")
    @NotBlank
    private String email;

    private String test =" asd";

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,15}$", message = "패스워드는 8~12자리로 구성되어야합니다.")
    private String password;

    @NotBlank(message = "대학교를 확인해주세요.")
    private String college;

    @NotBlank(message = "닉네임을 확인해주세요.")
    private String nickname;

    private UserGrade grade;


}
