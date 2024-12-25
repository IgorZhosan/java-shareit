package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(long userId);

    UserDto userCreate(UserDto userDto);

    UserDto userUpdate(long userId, UserDto userDto);

    void userDelete(Long userId);
}