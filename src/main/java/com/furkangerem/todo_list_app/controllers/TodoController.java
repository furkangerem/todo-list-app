package com.furkangerem.todo_list_app.controllers;

import com.furkangerem.todo_list_app.dtos.TodoCreateDto;
import com.furkangerem.todo_list_app.dtos.TodoGetResponseDto;
import com.furkangerem.todo_list_app.dtos.TodoUpdateDto;
import com.furkangerem.todo_list_app.entities.Todo;
import com.furkangerem.todo_list_app.services.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoGetResponseDto>> getAllTodos(@RequestParam Optional<Long> userId) {
        List<TodoGetResponseDto> todos = todoService.getAllTodos(userId);
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody TodoCreateDto todoCreateDto) {
        Todo createdTodo = todoService.createTodo(todoCreateDto);
        if (createdTodo != null)
            return ResponseEntity.status(HttpStatus.CREATED).body("Todo created successfully!");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create Todo!");
    }

    // Todo: Only this method doesn't work properly. Make sure it's working.
    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        if (todo != null)
            return ResponseEntity.ok(todo);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodoById(@PathVariable Long id, @RequestBody TodoUpdateDto todoUpdateDto) {
        Todo updatedTodo = todoService.updateTodoById(id, todoUpdateDto);
        if (updatedTodo != null)
            return ResponseEntity.ok("Todo updated successfully!");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to update Todo!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Todo deleted successfully!");
    }
}
