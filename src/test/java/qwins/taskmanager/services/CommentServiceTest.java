package qwins.taskmanager.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qwins.taskmanager.models.Comment;
import qwins.taskmanager.models.Task;
import qwins.taskmanager.repositories.CommentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @Test
    void testSaveComment() {
        Comment comment = new Comment("Great task!");
        when(commentRepository.save(comment)).thenReturn(comment);

        commentService.saveComment(comment);

        verify(commentRepository).save(comment);
    }

    @Test
    void testGetCommentsByTask() {
        Comment comment1 = new Comment("Great task!");
        Comment comment2 = new Comment("Needs improvement.");
        List<Comment> comments = List.of(comment1, comment2);

        Task task = new Task("Task1");
        task.setComments(comments);

        List<Comment> result = commentService.getCommentsByTask(task);

        assertEquals(comments, result);
    }

    @Test
    void testDeleteComment() {
        Comment comment = new Comment("Great task!");

        commentService.deleteComment(comment);

        verify(commentRepository).delete(comment);
    }
}
