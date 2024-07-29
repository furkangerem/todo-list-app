package com.furkangerem.todo_list_app.controllers;

import com.furkangerem.todo_list_app.dtos.TodoCreateDto;
import com.furkangerem.todo_list_app.dtos.TodoGetResponseDto;
import com.furkangerem.todo_list_app.dtos.TodoUpdateDto;
import com.furkangerem.todo_list_app.entities.Todo;
import com.furkangerem.todo_list_app.services.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TodoControllerTest {

    @InjectMocks
    private TodoController todoController;

    @Mock
    private TodoService todoService;

    private Todo todo;
    private TodoCreateDto todoCreateDto;
    private TodoUpdateDto todoUpdateDto;
    private TodoGetResponseDto todoGetResponseDto;

    // This method runs before each test. It creates objects for whole test cases.
    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

        // Create the To-do object for the test scenarios.
        todo = new Todo();
        todo.setId(1L);
        todo.setTitle("Test Title");
        todo.setText("Test Text");

        // Create the TodoCreateDto object for the test scenarios.
        todoCreateDto = new TodoCreateDto();
        todoCreateDto.setTitle("Test Title");
        todoCreateDto.setText("Test Text");
        todoCreateDto.setUserId(1L);

        // Create the TodoUpdateDto object for the test scenarios.
        todoUpdateDto = new TodoUpdateDto();
        todoUpdateDto.setTitle("Updated Title");
        todoUpdateDto.setText("Updated Text");
    }

    @Test
    public void testGetAllTodos() {

        // Mocking the todoService.getAllTodos() method.
        when(todoService.getAllTodos(Optional.empty())).thenReturn(Arrays.asList(todoGetResponseDto));

        // Call the getAllTodos() method and check the result.
        ResponseEntity<List<TodoGetResponseDto>> response = todoController.getAllTodos(Optional.empty());

        // Check the Status and Body.
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(todoService, times(1)).getAllTodos(Optional.empty());
    }

    @Test
    public void testCreateTodo_Success() {

        // Mocking the todoService.createTodo() method.
        when(todoService.createTodo(any(TodoCreateDto.class))).thenReturn(todo);

        // Call the createTodo() method and check the result.
        ResponseEntity<String> response = todoController.createTodo(todoCreateDto);

        // Check the Status and Body.
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Todo created successfully!", response.getBody());
        verify(todoService, times(1)).createTodo(any(TodoCreateDto.class));
    }

    @Test
    public void testCreateTodo_Failure() {

        // Mocking the todoService.createTodo() method.
        when(todoService.createTodo(any(TodoCreateDto.class))).thenReturn(null);

        // Call the createTodo() method and check the result.
        ResponseEntity<String> response = todoController.createTodo(todoCreateDto);

        // Check the Status and Body.
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to create Todo!", response.getBody());
        verify(todoService, times(1)).createTodo(any(TodoCreateDto.class));
    }

    @Test
    public void testGetTodoById_Success() {

        // Mocking the todoService.getTodoById() method.
        when(todoService.getTodoById(1L)).thenReturn(todo);

        // Call the getTodoById() method and check the result.
        ResponseEntity<?> response = todoController.getTodoById(1L);

        // Check the Status and Body.
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todo, response.getBody());
        verify(todoService, times(1)).getTodoById(1L);
    }

    @Test
    public void testGetTodoById_Failure() {

        // Mocking the todoService.getTodoById() method.
        when(todoService.getTodoById(1L)).thenReturn(null);

        // Call the getTodoById() method and check the result.
        ResponseEntity<?> response = todoController.getTodoById(1L);

        // Check the Status and Body.
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Todo not found!", response.getBody());
        verify(todoService, times(1)).getTodoById(1L);
    }

    @Test
    public void testUpdateTodoById_Success() {

        // Mocking the todoService.updateTodoById() method.
        when(todoService.updateTodoById(1L, todoUpdateDto)).thenReturn(todo);

        // Call the updateTodoById() method and check the result.
        ResponseEntity<String> response = todoController.updateTodoById(1L, todoUpdateDto);

        // Check the Status and Body.
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Todo updated successfully!", response.getBody());
        verify(todoService, times(1)).updateTodoById(1L, todoUpdateDto);
    }

    @Test
    public void testUpdateTodoById_Failure() {

        // Mocking the todoService.updateTodoById() method.
        when(todoService.updateTodoById(1L, todoUpdateDto)).thenReturn(null);

        // Call the updateTodoById() method and check the result.
        ResponseEntity<String> response = todoController.updateTodoById(1L, todoUpdateDto);

        // Check the Status and Body.
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Failed to update Todo!", response.getBody());
        verify(todoService, times(1)).updateTodoById(1L, todoUpdateDto);
    }

    @Test
    public void testDeleteTodoById() {

        // Call the deleteTodoById() method and check the result.
        ResponseEntity<String> response = todoController.deleteTodoById(1L);

        // Check the Status and Body.
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Todo deleted successfully!", response.getBody());
        verify(todoService, times(1)).deleteTodoById(1L);
    }
}