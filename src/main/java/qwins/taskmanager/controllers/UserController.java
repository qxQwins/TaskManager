package qwins.taskmanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qwins.taskmanager.enums.Role;
import qwins.taskmanager.models.User;
import qwins.taskmanager.services.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String user(Model model, @PathVariable long id) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", Role.values());
        return "user";
    }

    @PostMapping("/updateRole")
    public String updateRole(@RequestParam long userId, @RequestParam Role role) {
        User user = userService.findById(userId);
        user.setRole(role);
        userService.updateUser(userId, user);
        return "redirect:/user/" + user.getId();
    }

    @PostMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
