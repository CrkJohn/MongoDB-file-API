package ieti.trello.backend.trello.persistence;

import ieti.trello.backend.trello.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IUserRepository  extends MongoRepository<User, String> {
    List<User> findByName(String name);
}
