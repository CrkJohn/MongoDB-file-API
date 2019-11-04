package ieti.trello.backend.trello.entities;


import ieti.trello.backend.trello.entities.util.Label;
import ieti.trello.backend.trello.entities.util.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Task{

    @Id
    private ObjectId _id;
    private State state;
    private Label label;
    private String expirationDate;
    private int priority;
    private String name;
    private String description;
    private String id;
    private String responsible;
    private List<Comment> comments  = new ArrayList<>();
    private List<User> members = new ArrayList<>();

    @Override
    public String toString() {
        return "Task{" +
                "_id=" + _id +
                ", state=" + state +
                ", label=" + label +
                ", expirationDate='" + expirationDate + '\'' +
                ", priority=" + priority +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", responsible='" + responsible + '\'' +
                ", comments=" + comments +
                ", members=" + members +
                '}';
    }
}
