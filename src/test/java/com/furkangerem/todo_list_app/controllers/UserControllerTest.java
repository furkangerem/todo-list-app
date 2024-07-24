package com.furkangerem.todo_list_app.controllers;

import com.furkangerem.todo_list_app.entities.User;
import com.furkangerem.todo_list_app.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private User user;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

        // Create the User object for the entire test cases.
        user = new User();
        user.setId(1L);
        user.setFirstName("Test");
        user.setLastName("User");
        user.setUserName("testuser");
        user.setPassword("password");
        user.setEmail("testuser@example.com");
    }

    @Test
    public void testGetAllUsers() {

        // Mocking the userService.getAllUsers() method.
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user));

        // Call the getAllUsers() method and check the result.
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Check the Status and Body.
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testCreateUser_Success() {

        // Mocking the userService.createUser() method.
        when(userService.createUser(any(User.class))).thenReturn(user);

        // Call the createUser() method and check the result.
        ResponseEntity<String> response = userController.createUser(user);

        // Check the Status and Body.
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User created successfully!", response.getBody());
        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    public void testCreateUser_Failure() {

        // Mocking the userService.createUser() method.
        when(userService.createUser(any(User.class))).thenReturn(null);

        // Call the createUser() method and check the result.
        ResponseEntity<String> response = userController.createUser(user);

        // Check the Status and Body.
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to create User!", response.getBody());
        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    public void testGetUserById_Success() {

        // Mocking the userService.getUserById() method.
        when(userService.getUserById(1L)).thenReturn(user);

        // Call the getUserById() method and check the result.
        ResponseEntity<?> response = userController.getUserById(1L);

        // Check the Status and Body.
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testGetUserById_Failure() {

        // Mocking the userService.getUserById() method.
        when(userService.getUserById(1L)).thenReturn(null);

        // Call the getUserById() method and check the result.
        ResponseEntity<?> response = userController.getUserById(1L);

        // Check the Status and Body.
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found!", response.getBody());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testUpdateUserById_Success() {

        // Mocking the userService.updateUserById() method.
        when(userService.updateUserById(1L, user)).thenReturn(user);

        // Call the updateUserById() method and check the result.
        ResponseEntity<String> response = userController.updateUserById(1L, user);

        // Check the Status and Body.
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User updated successfully!", response.getBody());
        verify(userService, times(1)).updateUserById(1L, user);
    }

    @Test
    public void testUpdateUserById_Failure() {

        // Mocking the userService.updateUserById() method.
        when(userService.updateUserById(1L, user)).thenReturn(null);

        // Call the updateUserById() method and check the result.
        ResponseEntity<String> response = userController.updateUserById(1L, user);

        // Check the Status and Body.
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Failed to update User!", response.getBody());
        verify(userService, times(1)).updateUserById(1L, user);
    }

    @Test
    public void testDeleteUserById() {

        // Call the deleteUserById() method and check the result.
        ResponseEntity<String> response = userController.deleteUserById(1L);

        // Check the Status and Body.
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("User deleted successfully!", response.getBody());
        verify(userService, times(1)).deleteUserById(1L);
    }
}
