package qwins.taskmanager.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import qwins.taskmanager.exceptions.BadRequestException;
import qwins.taskmanager.exceptions.UserNotFoundException;
import qwins.taskmanager.models.User;
import qwins.taskmanager.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    public void saveUser(User user) {
        Boolean exists = userRepository.existsByEmail(user.getEmail());
        if (exists) {
            throw new BadRequestException("Email" + user.getEmail() + " already exists");
        }
        userRepository.save(user);
    }

    public User updateUser(long id, User updatedUser) {
        updatedUser.setId(id);
        return userRepository.save(updatedUser);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmail(email);
    }

}
