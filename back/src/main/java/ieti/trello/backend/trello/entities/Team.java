package ieti.trello.backend.trello.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team{

    private List<User> members;
    private List<Dashboard> dashboard;
    private String name;
    private String description;

}