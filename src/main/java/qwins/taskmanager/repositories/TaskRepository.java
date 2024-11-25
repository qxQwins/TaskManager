package qwins.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qwins.taskmanager.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
