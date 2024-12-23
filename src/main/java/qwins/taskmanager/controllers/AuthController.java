package qwins.taskmanager.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import qwins.taskmanager.DTO.UserDTO;
import qwins.taskmanager.enums.Role;
import qwins.taskmanager.models.User;
import qwins.taskmanager.services.UserService;
import qwins.taskmanager.utils.JwtTokenUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping("/show")
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO request, HttpServletResponse response) {
        User user = userService.findByEmail(request.email());
        System.out.println("User " + user + " found");

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String token = jwtTokenUtils.generateToken(user);
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}
