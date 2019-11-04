package ieti.trello.backend.trello.services;

import ieti.trello.backend.trello.entities.User;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public interface IUserService {
    List<User> getUsersList();

    User getUserById(String userId);

    List<User> getUserByName(String name);

    User createUser(User user);

    User updateUser(User user);

    void removeUser(String userId);

    void removeAllUsers();
}