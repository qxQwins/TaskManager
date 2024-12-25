package qwins.taskmanager.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import qwins.taskmanager.services.TaskService;
import qwins.taskmanager.services.UserService;
import qwins.taskmanager.utils.JwtTokenUtils;

@Controller
@RequiredArgsConstructor
public class TasksController {

    private final UserService userService;

    private final TaskService taskService;

    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping("/tasks")
    public String showTasks(Model model, HttpServletRequest request) {
        model.addAttribute("email", userService.findByEmail(
                        jwtTokenUtils.getEmailFromToken(request))
                .getEmail()
        );
        model.addAttribute("authoredTasks", userService.findByEmail(
                        jwtTokenUtils.getEmailFromToken(request))
                .getAuthoredTasks()
        );

        model.addAttribute("executedTasks", userService.findByEmail(
                        jwtTokenUtils.getEmailFromToken(request))
                .getExecutedTasks()
        );
        return "tasks";
    }

    @GetMapping("/tasks/{id}")
    public String showTask(@PathVariable long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "task";
    }
}
