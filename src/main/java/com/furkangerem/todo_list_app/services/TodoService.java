package com.furkangerem.todo_list_app.services;

import com.furkangerem.todo_list_app.dtos.TodoCreateDto;
import com.furkangerem.todo_list_app.dtos.TodoGetResponseDto;
import com.furkangerem.todo_list_app.dtos.TodoUpdateDto;
import com.furkangerem.todo_list_app.entities.Todo;
import com.furkangerem.todo_list_app.entities.User;
import com.furkangerem.todo_list_app.repositories.TodoRepository;
import com.furkangerem.todo_list_app.services.abstracts.ITodoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService implements ITodoService {

    private final TodoRepository todoRepository;
    private final UserService userService;

    public TodoService(TodoRepository todoRepository, UserService userService) {
        this.todoRepository = todoRepository;
        this.userService = userService;
    }

    @Override
    public List<TodoGetResponseDto> getAllTodos(Optional<Long> userId) {

        List<Todo> todos;
        if (userId.isPresent()) {
            todos = todoRepository.findByUserId(userId.get());
        } else {
            todos = todoRepository.findAll();
        }
        return todos.stream().map(todo -> new TodoGetResponseDto(
                todo.getId(),
                todo.getUser().getId(),
                todo.getTitle(),
                todo.getText(),
                todo.getTodoStatus(),
                todo.getTodoPriority()
        )).collect(Collectors.toList());
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
            tempTodo.setTodoStatus(todoUpdateDto.getTodoStatus());
            tempTodo.setTodoPriority(todoUpdateDto.getTodoPriority());
            return todoRepository.save(tempTodo);
        } else
            return null;
    }

    @Override
    public void deleteTodoById(Long todoId) {
        todoRepository.deleteById(todoId);
    }
}
