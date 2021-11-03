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
        if(userRepository.findByEmailAndRole(req.getEmail(), RoleType.ROEL_UNKNOWN.getCode()).isPresent())
            return null;

        User user = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .state(UserState.VALID)
                .role(RoleType.ROLE_GUEST)
                .build();
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Transactional
    public User login(SignInReq req) {
        User user = userRepository.findByEmailAndRole(req.getEmail(), RoleType.ROEL_UNKNOWN.getCode())
            .orElse(null);
        return user;
    }

}
