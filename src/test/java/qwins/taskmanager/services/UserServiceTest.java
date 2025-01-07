package qwins.taskmanager.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qwins.taskmanager.exceptions.BadRequestException;
import qwins.taskmanager.models.User;
import qwins.taskmanager.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetAllUsers() {
        List<User> users = List.of(new User("user1@example.com"), new User("user2@example.com"));
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(users, result);
        verify(userRepository).findAll();
    }

    @Test
    void testFindByEmail() {
        String email = "user1@example.com";
        User user = new User("user1@example.com");
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User result = userService.findByEmail(email);

        assertEquals(user, result);
        verify(userRepository).findByEmail(email);
    }

    @Test
    void testSaveUser_EmailAlreadyExists() {
        User user = new User("user1@example.com");
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> userService.saveUser(user));
        assertEquals("Emailuser1@example.com already exists", exception.getMessage());
        verify(userRepository).existsByEmail(user.getEmail());
        verify(userRepository, never()).save(any());
    }
}
