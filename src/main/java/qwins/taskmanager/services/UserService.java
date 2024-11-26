package qwins.taskmanager.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import qwins.taskmanager.models.User;
import qwins.taskmanager.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findUserByEmail(String email) {
        return userRepository.findAll().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    public void createNewUser(User user) {
        userRepository.save(user);
    }

    public User updateUser(long id, User updatedUser) {
        updatedUser.setId(id);
        return userRepository.save(updatedUser);
    }
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

}
