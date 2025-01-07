package qwins.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import qwins.taskmanager.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email LIKE CONCAT('%', :email, '%')")
    List<User> searchUserByEmail(@Param("email") String email);

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
