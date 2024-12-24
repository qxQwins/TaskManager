package qwins.taskmanager.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qwins.taskmanager.enums.Role;
import qwins.taskmanager.models.User;
import qwins.taskmanager.services.UserService;
import qwins.taskmanager.utils.JwtTokenUtils;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password,
                           @RequestParam String confirmPassword, Model model) {
        if (userService.findByEmail(email) != null) {
            model.addAttribute("error", "User already exists");
            return "register";
        }

        System.out.println("New user " + email + " in process");

        if(!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "register";
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(Role.USER);

        System.out.println("New user " + email + " created");

        userService.saveUser(newUser);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin(HttpServletResponse response) {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam String email, @RequestParam String password,
                            Model model, HttpServletResponse response) {

        User user = userService.findByEmail(email);

        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
            return "login";
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("errorMessage", "Wrong password");
            return "login";
        }

        String token = jwtTokenUtils.generateToken(user);
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);
        System.out.println("токен добавлен");
        return "redirect:/tasks";
    }
}
