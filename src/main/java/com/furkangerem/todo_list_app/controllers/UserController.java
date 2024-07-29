package com.furkangerem.todo_list_app.controllers;

import com.furkangerem.todo_list_app.dtos.UserUpdateDto;
import com.furkangerem.todo_list_app.entities.User;
import com.furkangerem.todo_list_app.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        if (createdUser != null)
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully!");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create User!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null)
            return ResponseEntity.ok(user);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto) {
        User updatedUser = userService.updateUserById(id, userUpdateDto);
        if (updatedUser != null)
            return ResponseEntity.ok("User updated successfully!");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to update User!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully!");
    }
}
