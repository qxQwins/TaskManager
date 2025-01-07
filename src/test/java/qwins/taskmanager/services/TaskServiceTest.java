package qwins.taskmanager.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qwins.taskmanager.models.Task;
import qwins.taskmanager.repositories.TaskRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void testGetAllTasks() {
        List<Task> tasks = List.of(new Task("Task1"), new Task("Task2"));
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(tasks, result);
        verify(taskRepository).findAll();
    }

    @Test
    void testSearchTaskByTitle() {
        String title = "Task";
        List<Task> tasks = List.of(new Task("Task1"), new Task("Task2"));
        when(taskRepository.searchTaskByTitle(title)).thenReturn(tasks);

        List<Task> result = taskService.searchTaskByTitle(title);

        assertEquals(tasks, result);
        verify(taskRepository).searchTaskByTitle(title);
    }

    @Test
    void testSaveTask() {
        Task task = new Task("Task1");
        when(taskRepository.save(task)).thenReturn(task);

        taskService.saveTask(task);

        verify(taskRepository).save(task);
    }
}
