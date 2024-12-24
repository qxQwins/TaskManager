package qwins.taskmanager.DTO;

import java.io.Serializable;
//использовалось как DTO для RequestBody в Rest запросах
public record UserDTO(String email, String password) implements Serializable {
}
