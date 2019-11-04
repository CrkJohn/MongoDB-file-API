package ieti.trello.backend.trello.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment{
    private int idUser;
    private String comment;
    private int id;
}