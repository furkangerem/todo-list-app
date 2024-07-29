package com.furkangerem.todo_list_app.dtos;

import com.furkangerem.todo_list_app.entities.enums.TodoPriority;
import com.furkangerem.todo_list_app.entities.enums.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoGetResponseDto {

    private Long id;
    private Long userId;
    private String title;
    private String text;
    private TodoStatus todoStatus;
    private TodoPriority todoPriority;
}
