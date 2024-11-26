package qwins.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qwins.taskmanager.models.Comment;
import qwins.taskmanager.models.Task;
import qwins.taskmanager.repositories.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void createNewComment(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> getCommentsByTask(Task task) {
        return task.getComment();
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
