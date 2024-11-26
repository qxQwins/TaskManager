package qwins.taskmanager.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qwins.taskmanager.services.UserService;
import qwins.taskmanager.utils.JwtTokenUtils;

import java.net.http.HttpResponse;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final JwtTokenUtils jwtTokenUtils;

    @PostMapping("/login")
    public String login(
            @RequestParam String email, @RequestParam String password, HttpServletResponse response
    ){
        String token = jwtTokenUtils.generateToken(userService.findUserByEmail(email));

        Cookie cookie = new Cookie("token", token);

        response.addCookie(cookie);

        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String login(){
        return "tasks.html";
    }
}
