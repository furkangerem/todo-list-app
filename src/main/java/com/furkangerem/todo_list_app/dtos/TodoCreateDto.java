package com.furkangerem.todo_list_app.dtos;

import com.furkangerem.todo_list_app.entities.User;
import com.furkangerem.todo_list_app.entities.enums.TodoPriority;
import com.furkangerem.todo_list_app.entities.enums.TodoStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TodoCreateDto {

    private String title;
    private String text;
    private Date dueDate;
    private TodoStatus todoStatus;
    private TodoPriority todoPriority;
    private Long userId;
}
