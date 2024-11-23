package qwins.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qwins.taskmanager.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
