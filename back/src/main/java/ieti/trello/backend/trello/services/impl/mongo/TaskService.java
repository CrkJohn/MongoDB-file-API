package ieti.trello.backend.trello.services.impl.mongo;

import ieti.trello.backend.trello.entities.Task;
import ieti.trello.backend.trello.entities.User;
import ieti.trello.backend.trello.persistence.ITaskRepository;
import ieti.trello.backend.trello.services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("taskServiceMongo")
public class TaskService implements ITaskService {


    @Autowired
    ITaskRepository iTaskRepository;

    @Override
    public List<Task> geTasksList() {
        return null;
    }

    @Override
    public Task getTaskById(String id) {
        return null;
    }

    @Override
    public List<Task> getTasksByUserId(String userId) {
        return null;
    }

    @Override
    public Task assignTaskToUser(String taskId, User user) {
        return null;
    }

    @Override
    public void removeTask(String taskId) {

    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public void saveTasks(Task task){
        iTaskRepository.save(task);
    }

    @Override
    public void deleteAll() {
        iTaskRepository.deleteAll();
    }

    @Override
    public List<Task> findByResponsible(String responsible) {
        return iTaskRepository.findByResponsible(responsible);
    }

    @Override
    public int countTaskByResponsible(String name) {
        return iTaskRepository.countTaskByResponsible(name);
    }

    @Override
    public List<Task> findByResponsibleAndPriorityIsGreaterThan(String responsible, int value) {
        return iTaskRepository.findByResponsibleAndPriorityIsGreaterThan(responsible,value);
    }

    @Override
    public List<Task> findByDescriptionIsGreaterThan(int value) {
        return iTaskRepository.findByDescriptionIsGreaterThan(value);
    }
}
