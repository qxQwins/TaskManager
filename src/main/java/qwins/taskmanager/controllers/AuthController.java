package qwins.taskmanager.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qwins.taskmanager.DTO.UserDTO;
import qwins.taskmanager.enums.Role;
import qwins.taskmanager.exceptions.UserNotFoundException;
import qwins.taskmanager.models.User;
import qwins.taskmanager.services.UserService;
import qwins.taskmanager.utils.JwtTokenUtils;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO request) {
        if (userService.findByEmail(request.email()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with this email already exists");
        }

        System.out.println("New user " + request.email() + " in process");

        User newUser = new User();
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(Role.USER);

        System.out.println("New user " + request.email() + " created");

        userService.saveUser(newUser);

        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/login")
    public String getLogin(HttpServletResponse response) {
        return "login";
    }
    @PostMapping("/login")
    public String postLogin(@RequestParam String email, @RequestParam String password,
                        Model model, HttpServletResponse response) {

        User user = userService.findByEmail(email);

        if(user == null) {
            try {
                throw new UserNotFoundException("User not found");
            } catch (BadCredentialsException e) {
                model.addAttribute("errorMessage", e.getMessage());
                return "errorPage";
            }
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            try {
                throw new BadCredentialsException("Wrong password");
            } catch (BadCredentialsException e) {
                model.addAttribute("errorMessage", e.getMessage());
                return "errorPage";
            }
        }

        String token = jwtTokenUtils.generateToken(user);
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);
        System.out.println("токен добавлен");
        return "redirect:/tasks";
    }
}
