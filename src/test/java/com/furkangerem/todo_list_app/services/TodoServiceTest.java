package com.furkangerem.todo_list_app.services;

import com.furkangerem.todo_list_app.dtos.TodoCreateDto;
import com.furkangerem.todo_list_app.dtos.TodoUpdateDto;
import com.furkangerem.todo_list_app.entities.Todo;
import com.furkangerem.todo_list_app.entities.User;
import com.furkangerem.todo_list_app.entities.enums.TodoPriority;
import com.furkangerem.todo_list_app.entities.enums.TodoStatus;
import com.furkangerem.todo_list_app.repositories.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private UserService userService;

    // This method runs before each test. It performs the necessary mock and inject operations.
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTodos() {
        // Preparation: Create the todos.
        Todo todo = new Todo();
        Todo todo1 = new Todo();

        // Mocking: Call todoRepository.findByUserId() to return this todos list.
        when(todoRepository.findByUserId(1L)).thenReturn(Arrays.asList(todo, todo1));

        // Test: Call the todoService.getAllTodos() method.
        List<Todo> todoList = todoService.getAllTodos(Optional.of(1L));

        // Verification: Todolist must have 2 elements.
        assertEquals(2, todoList.size());
    }

    @Test
    public void testCreateTodo_UserNotFound() {
        // Preparation: Create TodoCreateDto object.
        TodoCreateDto todoCreateDto = new TodoCreateDto();
        todoCreateDto.setUserId(1L); // Not existing userId given into the method as a parameter.

        // Mocking: Return null when the userService.getUserById() method.
        when(userService.getUserById(1L)).thenReturn(null);

        // Test: If userService.getUserById() returns null, createTodo should return null.
        Todo createdTodo = todoService.createTodo(todoCreateDto);

        // Verification: Created to-do must be null.
        assertNull(createdTodo);
    }

    @Test
    public void testCreateTodo_Success() {
        // Preparation: Create User and TodoCreateDto objects.
        User user = new User();

        TodoCreateDto todoCreateDto = new TodoCreateDto();
        todoCreateDto.setTitle("Test Todo Title");
        todoCreateDto.setText("Todo test text");
        todoCreateDto.setUserId(1L);
        todoCreateDto.setTodoPriority(TodoPriority.HIGH);
        todoCreateDto.setTodoStatus(TodoStatus.ONGOING);
        todoCreateDto.setDueDate(new Date());

        Todo newTodo = new Todo();
        newTodo.setTitle(todoCreateDto.getTitle());
        newTodo.setText(todoCreateDto.getText());
        newTodo.setTodoPriority(todoCreateDto.getTodoPriority());
        newTodo.setTodoStatus(todoCreateDto.getTodoStatus());
        newTodo.setUser(user);
        newTodo.setCreatedDate(new Date());
        newTodo.setDueDate(new Date());

        // Mocking: Return the test user when userService.getUserById() is called.
        // Mocking: Return this new to-do when todoRepository.save() is called.
        when(userService.getUserById(1L)).thenReturn(user);
        when(todoRepository.save(any(Todo.class))).thenReturn(newTodo);

        // Test: Call the todoService().createTodo() method.
        Todo createdTodo = todoService.createTodo(todoCreateDto);

        // Verification: The created to-do must not be null and the title must be “Test To-do Title”.
        assertNotNull(createdTodo);
        assertEquals("Test Todo Title", createdTodo.getTitle());
    }

    @Test
    public void testGetTodoById() {
        // Preparation: Create a testTodo object.
        Todo testTodo = new Todo();
        testTodo.setTitle("Test Todo Title");

        // Mocking: Return testTodo when the todoRepository.findById() method is called.
        when(todoRepository.findById(1L)).thenReturn(Optional.of(testTodo));

        // Test: Call the todoService.getTodoById() method.
        Todo foundTodo = todoService.getTodoById(1L);

        // Verification: Found to-do must not be null and the title must be "Test To-do Title"
        assertNotNull(foundTodo);
        assertEquals("Test Todo Title", foundTodo.getTitle());
    }

    @Test
    public void testUpdateTodoById() {
        // Preparation: Create the current and updated todos.
        Todo existingTodo = new Todo();
        existingTodo.setTitle("Old Title");
        existingTodo.setText("Old Description");

        TodoUpdateDto todoUpdateDto = new TodoUpdateDto();
        todoUpdateDto.setTitle("Updated Title");
        todoUpdateDto.setText("Updated Description");

        Todo updatedTodo = new Todo();
        updatedTodo.setTitle(todoUpdateDto.getTitle());
        updatedTodo.setText(todoUpdateDto.getText());

        // Mocking: Return existing to-do when the todoRepository.findById() method is called.
        when(todoRepository.findById(1L)).thenReturn(Optional.of(existingTodo));
        // Mocking: Return updated to-do when the todoRepository.save() method is called.
        when(todoRepository.save(any(Todo.class))).thenReturn(updatedTodo);

        // Test: Call the todoService.updateTodoById() method.
        Todo result = todoService.updateTodoById(1L, todoUpdateDto);

        // Verification: Updated to-do should not be null and title should be “Updated Title”.
        assertNotNull(updatedTodo);
        assertEquals("Updated Title", updatedTodo.getTitle());
    }

    @Test
    public void testUpdateTodoById_TodoNotFound() {
        // Preparation: Create updated todoObject.
        TodoUpdateDto todoUpdateDto = new TodoUpdateDto();
        todoUpdateDto.setTitle("Updated Title");

        // Mocking: Return null when the todoRepository.findById() method is called.
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        // Test: Call the todoService.updateTodoById() method.
        Todo updatedTodo = todoService.updateTodoById(1L, todoUpdateDto);

        // Verification: Updated to-do must be null.
        assertNull(updatedTodo);
    }

    @Test
    public void testDeleteTodoById() {
        // Test: Call the todoService.deleteTodoById() method.
        todoService.deleteTodoById(1L);

        // Verification: Verify that todoRepository.deleteById() method is called.
        verify(todoRepository, times(1)).deleteById(1L);
    }
}
