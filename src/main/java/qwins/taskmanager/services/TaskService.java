package qwins.taskmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qwins.taskmanager.model.Task;
import qwins.taskmanager.repositories.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    Task getTaskById(long id) {
        return taskRepository.findById(id).orElse(null);
    }

    Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    Task updateTask(long id, Task updatedTask) {
        updatedTask.setId(id);
        return taskRepository.save(updatedTask);
    }

    void deleteTaskById(long id) {
        taskRepository.deleteById(id);
    }

}
