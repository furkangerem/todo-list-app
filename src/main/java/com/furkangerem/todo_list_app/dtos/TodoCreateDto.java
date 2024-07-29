package com.furkangerem.todo_list_app.dtos;

import com.furkangerem.todo_list_app.entities.enums.TodoPriority;
import com.furkangerem.todo_list_app.entities.enums.TodoStatus;
import lombok.Data;

@Data
public class TodoCreateDto {

    private String title;
    private String text;
    private TodoStatus todoStatus;
    private TodoPriority todoPriority;
    private Long userId;
}
