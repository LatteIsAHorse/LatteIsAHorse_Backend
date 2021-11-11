package team.latte.LatteIsAHorse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.latte.LatteIsAHorse.dto.SignInReq;
import team.latte.LatteIsAHorse.dto.SignUpReq;
import team.latte.LatteIsAHorse.model.user.RoleType;
import team.latte.LatteIsAHorse.model.user.User;
import team.latte.LatteIsAHorse.model.user.UserState;
import team.latte.LatteIsAHorse.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User join(SignUpReq req) {
        if(userRepository.findByEmailAndRoleOnlyNotLeave(req.getEmail(), RoleType.ROLE_UNKNOWN).isPresent())
            return null;

        User user = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .state(UserState.VALID)
                .nickname(req.getNickname())
                .college(req.getCollege())
                .grade(req.getGrade())
                .role(RoleType.ROLE_USER) // 임시로 USER로 해둠. 운영시에는 GUEST로 다시 바꿔야함
                .build();
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Transactional
    public User login(SignInReq req) {
        User user = userRepository.findByEmailAndRoleOnlyNotLeave(req.getEmail(), RoleType.ROLE_UNKNOWN)
            .orElse(null);
        return user;
    }

    @Transactional
    public void addLatteStack(User user) {
        user.addLatteStack();
    }
}
