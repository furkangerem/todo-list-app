package com.furkangerem.todo_list_app.services;

import com.furkangerem.todo_list_app.dtos.UserUpdateDto;
import com.furkangerem.todo_list_app.entities.User;
import com.furkangerem.todo_list_app.repositories.UserRepository;
import com.furkangerem.todo_list_app.services.abstracts.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User updateUserById(Long userId, UserUpdateDto userUpdateDto) {

        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            User tempUser = foundUser.get();
            tempUser.setPassword(userUpdateDto.getPassword());
            userRepository.save(tempUser);
            return tempUser;
        } else
            return null;
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
