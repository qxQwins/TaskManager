package qwins.taskmanager.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comment {
    @Id
    private Long id;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
