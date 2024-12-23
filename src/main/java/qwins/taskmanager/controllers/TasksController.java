package qwins.taskmanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import qwins.taskmanager.services.UserService;
import qwins.taskmanager.utils.JwtTokenUtils;

@RestController
@RequiredArgsConstructor
public class TasksController {

    private final UserService userService;

    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping("/tasks")
    public String tasks(Model model){

        //model.addAttribute("tasks", userService.findUserByEmail(jwtTokenUtils.getEmailFromToken()).getExecutedTasks());
        return "tasks";
    }
}
