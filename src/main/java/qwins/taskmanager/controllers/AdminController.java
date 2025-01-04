package qwins.taskmanager.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qwins.taskmanager.services.TaskService;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final TaskService taskService;

    @GetMapping("/tasks/admin")
    public String showTasks(Model model, @RequestParam(required = false) String searchRequest) {
        if (searchRequest != null && !searchRequest.isEmpty()) {
            model.addAttribute("tasks", taskService.searchTaskByTitle(searchRequest));
        } else {
            model.addAttribute("tasks", taskService.getAllTasks());
        }
        return "admin"; // Имя представления
    }

}
