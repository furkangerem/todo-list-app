package com.furkangerem.todo_list_app.entities;

import com.furkangerem.todo_list_app.entities.enums.TodoPriority;
import com.furkangerem.todo_list_app.entities.enums.TodoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Table(name = "todos")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Todo extends BaseEntity {

    @Column(name = "title", length = 256, nullable = false)
    private String title;
    @Column(name = "text", length = 512, nullable = false)
    private String text;
    @Column(name = "created_date", length = 32, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "due_date", length = 32, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "todo_status")
    private TodoStatus todoStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "todo_priority")
    private TodoPriority todoPriority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
