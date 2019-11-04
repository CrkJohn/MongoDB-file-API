package ieti.trello.backend.trello.entities;


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
public class User{

    @Id
    private ObjectId _id;
    private String name;
    private String email;
    private String userName;
    private String initials;
    private String password;
    private List<Dashboard> dashboards = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", initials='" + initials + '\'' +
                ", password='" + password + '\'' +
                ", dashboards=" + dashboards +
                ", teams=" + teams +
                '}';
    }
}