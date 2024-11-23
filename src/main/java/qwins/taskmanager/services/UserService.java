package qwins.taskmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qwins.taskmanager.model.User;
import qwins.taskmanager.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    User findUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    User saveUser(User user) {
        return userRepository.save(user);
    }

    User updateUser(long id, User updatedUser) {
        updatedUser.setId(id);
        return userRepository.save(updatedUser);
    }
    void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

}
