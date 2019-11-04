package ieti.trello.backend.trello.services;

import ieti.trello.backend.trello.entities.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public interface ITaskService {
    List<Task> geTasksList();

    Task getTaskById(String id);

    List<Task> getTasksByUserId(String userId);

    Task assignTaskToUser(String taskId, User user);

    void removeTask(String taskId);

    Task updateTask(Task task);

    void saveTasks(Task task);

    void deleteAll();

    List<Task> findByResponsible(String responsible);

    int countTaskByResponsible(String  name);

    List<Task> findByResponsibleAndPriorityIsGreaterThan(String responsible , int value);

    List<Task> findByDescriptionIsGreaterThan(int value);

}