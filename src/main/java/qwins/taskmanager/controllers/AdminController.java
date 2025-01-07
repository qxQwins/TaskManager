package qwins.taskmanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qwins.taskmanager.services.TaskService;
import qwins.taskmanager.services.UserService;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final TaskService taskService;

    private final UserService userService;

    @GetMapping("/admin")
    public String showTasks(Model model, @RequestParam(required = false) String tasksSearchRequest,
                            @RequestParam(required = false) String usersSearchRequest) {
        if (tasksSearchRequest != null && !tasksSearchRequest.isEmpty()) {
            model.addAttribute("tasks", taskService.searchTaskByTitle(tasksSearchRequest));
            model.addAttribute("users", userService.getAllUsers());
        }
        else if(usersSearchRequest != null && !usersSearchRequest.isEmpty()) {
            model.addAttribute("users", userService.searchUserByEmail(usersSearchRequest));
            model.addAttribute("tasks", taskService.getAllTasks());
        }
        else {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("tasks", taskService.getAllTasks());
        }
        return "admin";
    }

}
