package com.furkangerem.todo_list_app.services.abstracts;

import com.furkangerem.todo_list_app.dtos.TodoCreateDto;
import com.furkangerem.todo_list_app.dtos.TodoGetResponseDto;
import com.furkangerem.todo_list_app.dtos.TodoUpdateDto;
import com.furkangerem.todo_list_app.entities.Todo;

import java.util.List;
import java.util.Optional;

public interface ITodoService {

    public List<TodoGetResponseDto> getAllTodos(Optional<Long> userId);

    public Todo createTodo(TodoCreateDto todoCreateDto);

    public Todo getTodoById(Long todoId);

    public Todo updateTodoById(Long todoId, TodoUpdateDto todoUpdateDto);

    public void deleteTodoById(Long todoId);
}
