package qwins.taskmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qwins.taskmanager.model.Comment;
import qwins.taskmanager.model.Task;
import qwins.taskmanager.repositories.CommentRepository;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByTask(Task task) {
        return task.getComment();
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
