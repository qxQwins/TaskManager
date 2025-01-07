package qwins.taskmanager.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qwins.taskmanager.enums.Role;
import qwins.taskmanager.jwt.JwtTokenUtils;
import qwins.taskmanager.models.User;
import qwins.taskmanager.services.UserService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password,
                           @RequestParam String confirmPassword, Model model) {
        if (userService.findByEmail(email) != null) {
            model.addAttribute("error", "User already exists");
            return "auth/register";
        }

        System.out.println("New user " + email + " in process");

        if(!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "auth/register";
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(Role.USER);

        System.out.println("New user " + email + " created");

        userService.saveUser(newUser);

        return "redirect:/login";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam String email, @RequestParam String password,
                            Model model, HttpServletResponse response) {

        User user = userService.findByEmail(email);

        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
            return "auth/login";
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("errorMessage", "Wrong password");
            return "auth/login";
        }

        String token = jwtTokenUtils.generateToken(user);
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);
        return "redirect:/tasks";
    }
}
