package com.furkangerem.todo_list_app.services.abstracts;

import com.furkangerem.todo_list_app.dtos.UserUpdateDto;
import com.furkangerem.todo_list_app.entities.User;

import java.util.List;

public interface IUserService {

    public List<User> getAllUsers();

    public User createUser(User user);

    public User getUserById(Long userId);

    public User updateUserById(Long userId, UserUpdateDto userUpdateDto);

    public void deleteUserById(Long userId);

}
