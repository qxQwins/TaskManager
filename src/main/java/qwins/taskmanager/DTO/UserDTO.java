package qwins.taskmanager.DTO;

import java.io.Serializable;

public record UserDTO(String email, String password) implements Serializable {
}
