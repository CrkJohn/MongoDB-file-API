package ieti.trello.backend.trello.persistence.stub.impl;

import ieti.trello.backend.trello.database.stub.DataBasesCli;
import ieti.trello.backend.trello.entities.Task;
import ieti.trello.backend.trello.entities.User;
import ieti.trello.backend.trello.persistence.stub.ITaskPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("taskPersistence")
public class TaskPersistence implements ITaskPersistence {

    @Autowired
    @Qualifier("implDataBasesCli")
    private DataBasesCli database;

    @Override
    public List<Task> geTasksList() {
        return database.geTasksList();
    }

    @Override
    public Task getTaskById(String id) {
        return database.getTaskById(id);
    }

    @Override
    public List<Task> getTasksByUserId(String userId) {
        return database.getTasksByUserId(userId);
    }

    @Override
    public Task assignTaskToUser(String taskId, User user) {
        return database.assignTaskToUser(taskId, user);
    }

    @Override
    public void removeTask(String taskId) {
        database.removeTask(taskId);
    }

    @Override
    public Task updateTask(Task task) {
        return database.updateTask(task);
    }

    @Override
    public void saveTask(Task task) { database.saveTask(task);
    }
}
