package com.furkangerem.todo_list_app.services;

import com.furkangerem.todo_list_app.dtos.TodoCreateDto;
import com.furkangerem.todo_list_app.dtos.TodoUpdateDto;
import com.furkangerem.todo_list_app.entities.Todo;
import com.furkangerem.todo_list_app.entities.User;
import com.furkangerem.todo_list_app.repositories.TodoRepository;
import com.furkangerem.todo_list_app.services.abstracts.ITodoService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TodoService implements ITodoService {

    private final TodoRepository todoRepository;
    private final UserService userService;

    public TodoService(TodoRepository todoRepository, UserService userService) {
        this.todoRepository = todoRepository;
        this.userService = userService;
    }

    @Override
    public List<Todo> getAllTodos(Optional<Long> userId) {
        return todoRepository.findByUserId(userId.get());
    }

    @Override
    public Todo createTodo(TodoCreateDto todoCreateDto) {

        User user = userService.getUserById(todoCreateDto.getUserId());
        if (user == null)
            return null;

        Todo tempTodo = new Todo();
        tempTodo.setTitle(todoCreateDto.getTitle());
        tempTodo.setText(todoCreateDto.getText());
        tempTodo.setTodoPriority(todoCreateDto.getTodoPriority());
        tempTodo.setTodoStatus(todoCreateDto.getTodoStatus());
        tempTodo.setUser(user);
        tempTodo.setCreatedDate(new Date());
        tempTodo.setDueDate(new Date());

        return todoRepository.save(tempTodo);
    }

    @Override
    public Todo getTodoById(Long todoId) {
        return todoRepository.findById(todoId).orElse(null);
    }

    @Override
    public Todo updateTodoById(Long todoId, TodoUpdateDto todoUpdateDto) {

        Optional<Todo> foundTodo = todoRepository.findById(todoId);
        if (foundTodo.isPresent()) {
            Todo tempTodo = foundTodo.get();
            tempTodo.setText(todoUpdateDto.getText());
            tempTodo.setTitle(todoUpdateDto.getTitle());
            return todoRepository.save(tempTodo);
        } else
            return null;
    }

    @Override
    public void deleteTodoById(Long todoId) {
        todoRepository.deleteById(todoId);
    }
}
