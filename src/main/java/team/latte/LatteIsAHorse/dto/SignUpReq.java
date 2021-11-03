package team.latte.LatteIsAHorse.dto;

import lombok.*;

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

}
