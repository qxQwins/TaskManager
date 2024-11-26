package qwins.taskmanager.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @OneToMany(mappedBy = "task")
    private List<Comment> comment;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    private User executor;
}
enum Status {
    PENDING, IN_PROGRESS, COMPLETED
}

enum Priority {
    HIGH, MEDIUM, LOW
}