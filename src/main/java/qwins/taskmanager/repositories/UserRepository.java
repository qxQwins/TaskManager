package qwins.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qwins.taskmanager.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
