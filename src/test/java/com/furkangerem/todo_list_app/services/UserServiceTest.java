package com.furkangerem.todo_list_app.services;

import com.furkangerem.todo_list_app.dtos.UserUpdateDto;
import com.furkangerem.todo_list_app.entities.User;
import com.furkangerem.todo_list_app.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    // This method runs before each test. It performs the necessary mock and inject operations.
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        // Preparation: Create test users.
        User testUser = new User();
        testUser.setUserName("testUser");
        User testUser1 = new User();
        testUser1.setUserName("testUser1");

        // Mocking: Return this user list when userRepository.findAll() is called.
        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser, testUser1));

        // Test: Call the userService.getAllUsers() method.
        List<User> users = userService.getAllUsers();

        // Verification: The user list must have 2 elements and contain the usernames “testUser” and “testUser1” respectively.
        assertEquals(2, users.size());
        assertEquals("testUser", users.get(0).getUserName());
        assertEquals("testUser1", users.get(1).getUserName());
    }

    @Test
    public void testCreateUser() {
        // Preparation: Create a test user.
        User testUser = new User();
        testUser.setUserName("testUser");

        // Mocking: Return this user when userRepository.save() is called.
        when(userRepository.save(testUser)).thenReturn(testUser);

        // Test: Call the userService.createUser() method.
        User createdUser = userService.createUser(testUser);

        // Verification: The created user must not be null and the username must be “testUser”.
        assertNotNull(createdUser);
        assertEquals("testUser", createdUser.getUserName());
    }

    @Test
    public void testGetUserById() {
        // Preparation: Create a test user.
        User testUser = new User();
        testUser.setUserName("testUser");

        // Mocking: Return this user when userRepository.findById() is called.
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        // Test: Call the userService.getUserById() method.
        User foundUser = userService.getUserById(1L);

        // Verification: The user found must not be null and the username must be “testUser”.
        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUserName());
    }

    @Test
    public void testUpdateUserById() {
        // Preparation: Create the current and will be updated user.
        User existingUser = new User();
        existingUser.setPassword("currentTestUserPassword");
        UserUpdateDto updatedUserUpdateDto = new UserUpdateDto();
        updatedUserUpdateDto.setPassword("updatedTestUserPassword");

        // Mocking: Return the current user when userRepository.findById() is called.
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        // Test: Call the userService.updateUserById() method.
        User resultUser = userService.updateUserById(1L, updatedUserUpdateDto);

        // Verification: The updated user must not be null and the username must be currentTestUser.
        assertNotNull(updatedUserUpdateDto);
        assertEquals("updatedTestUserPassword", resultUser.getPassword());

    }

    @Test
    public void testDeleteUserById() {
        // Test: Call the userService.deleteUserById() method.
        userService.deleteUserById(1L);

        // Verification: Verify that the userRepository.deleteById() method was called.
        verify(userRepository, times(1)).deleteById(1L);
    }
}
