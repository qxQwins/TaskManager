package qwins.taskmanager.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qwins.taskmanager.enums.Priority;
import qwins.taskmanager.enums.Status;
import qwins.taskmanager.models.Task;
import qwins.taskmanager.services.TaskService;
import qwins.taskmanager.services.UserService;
import qwins.taskmanager.jwt.JwtTokenUtils;

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
        return "tasks/tasks";
    }

    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam Long taskId, @RequestParam String status) {
        Task task = taskService.getTaskById(taskId);
        task.setStatus(Status.valueOf(status));
        taskService.saveTask(task);
        return "redirect:/tasks/" + taskId;
    }

    @GetMapping("/tasks/{id}")
    public String showTask(@PathVariable long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        model.addAttribute("statuses", Status.values());
        return "tasks/task";
    }

    @GetMapping("/tasks/add")
    public String showAddTask(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("users", userService.getAllUsers());
        return "tasks/addTask";
    }

    @PostMapping("/tasks/add")
    public String addTask(@ModelAttribute Task task, HttpServletRequest request) {
        task.setAuthor(userService.findByEmail(jwtTokenUtils.getEmailFromToken(request)));
        taskService.saveTask(task);
        return "redirect:/tasks";
    }
}
