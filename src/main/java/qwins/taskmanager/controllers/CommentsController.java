package qwins.taskmanager.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qwins.taskmanager.jwt.JwtTokenUtils;
import qwins.taskmanager.models.Comment;
import qwins.taskmanager.services.CommentService;
import qwins.taskmanager.services.TaskService;
import qwins.taskmanager.services.UserService;

@Controller
@RequiredArgsConstructor
public class CommentsController {
   private final CommentService commentService;
   private final UserService userService;
   private final TaskService taskService;
   private final JwtTokenUtils jwtTokenUtils;
    @PostMapping("/addComment")
    public String addComment(HttpServletRequest request, @RequestParam String message,
                             @RequestParam("task.id") long taskId, Model model) {
        Comment comment = new Comment();
        System.out.println(model.asMap());
        comment.setAuthor(userService.findByEmail(jwtTokenUtils.getEmailFromToken(request)));
        comment.setText(message);
        comment.setTask(taskService.getTaskById(taskId));
        commentService.saveComment(comment);
        return "redirect:" + request.getHeader("Referer");
    }
}
