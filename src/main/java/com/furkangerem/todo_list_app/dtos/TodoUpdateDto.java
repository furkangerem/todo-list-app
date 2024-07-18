package com.furkangerem.todo_list_app.dtos;

import com.furkangerem.todo_list_app.entities.enums.TodoPriority;
import com.furkangerem.todo_list_app.entities.enums.TodoStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoUpdateDto {

    private String title;
    private String text;
    private LocalDateTime dueDate;
    private TodoStatus todoStatus;
    private TodoPriority todoPriority;
}
