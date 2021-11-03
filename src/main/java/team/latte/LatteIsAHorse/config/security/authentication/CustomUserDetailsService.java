package team.latte.LatteIsAHorse.config.security.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.latte.LatteIsAHorse.config.response.ExceptionStatus;
import team.latte.LatteIsAHorse.model.user.RoleType;
import team.latte.LatteIsAHorse.model.user.User;
import team.latte.LatteIsAHorse.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAndRole(email, RoleType.ROEL_UNKNOWN.getCode())
                .orElseThrow(() -> new UsernameNotFoundException(ExceptionStatus.NOT_AUTHENTICATED_USER.getMessage()));
        return new CustomUserDetails(user);
    }
}
