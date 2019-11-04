package ieti.trello.backend.trello.persistence.stub.impl;

import ieti.trello.backend.trello.entities.User;
import ieti.trello.backend.trello.persistence.stub.IUserPersistence;
import ieti.trello.backend.trello.database.stub.DataBasesCli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserPersistence implements IUserPersistence{

    @Autowired
    @Qualifier("implDataBasesCli")
    private DataBasesCli database;

    @Override
    public List<User> getUsersList() {
        return database.getUsersList();
    }

    @Override
    public User getUserById(String userId) {
        return database.getUserById(userId);
    }

    @Override
    public User createUser(User user) {
        return database.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return database.updateUser(user);
    }

    @Override
    public void removeUser(String userId) {
        database.removeUser(userId);
    }
}
