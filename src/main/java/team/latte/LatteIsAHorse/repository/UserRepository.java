package team.latte.LatteIsAHorse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.latte.LatteIsAHorse.model.user.RoleType;
import team.latte.LatteIsAHorse.model.user.User;
import team.latte.LatteIsAHorse.model.user.UserState;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "FROM User u WHERE u.email = :email AND u.role <> :role")
    Optional<User> findByEmailAndRoleOnlyNotLeave(@Param("email") String email, @Param("role") RoleType role);

    Optional<User> findByEmail(String email);

    @Query(value = "FROM User u WHERE u.email = :email AND u.state = :state")
    Optional<User> findByEmailAndState(@Param("email") String email, @Param("state") UserState state);
}
