package qwins.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qwins.taskmanager.models.Comment;
import qwins.taskmanager.models.Task;
import qwins.taskmanager.repositories.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> getCommentsByTask(Task task) {
        return task.getComments();
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
