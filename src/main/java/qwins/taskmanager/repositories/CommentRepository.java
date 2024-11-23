package qwins.taskmanager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import qwins.taskmanager.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
