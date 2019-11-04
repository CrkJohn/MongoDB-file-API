package ieti.trello.backend.trello.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dashboard{

    public String name;
    public List<Task> tasks;
    public int id;
}
