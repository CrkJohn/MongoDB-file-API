package ieti.trello.backend.trello.services.impl.mongo;

import ieti.trello.backend.trello.entities.User;
import ieti.trello.backend.trello.persistence.IUserRepository;
import ieti.trello.backend.trello.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userServiceMongo")
public class UserService implements IUserService {

    @Autowired
    IUserRepository iUserRepository;

    @Override
    public List<User> getUsersList() {
        return iUserRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return null;
    }

    @Override
    public List<User> getUserByName(String name) {
        return iUserRepository.findByName(name);
    }

    @Override
    public User createUser(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void removeUser(String userId) {

    }

    @Override
    public void removeAllUsers() {
        iUserRepository.deleteAll();
    }
}
