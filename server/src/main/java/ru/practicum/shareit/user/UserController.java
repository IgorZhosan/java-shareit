package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("Получение списка всех пользователей.");
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable long userId) {
        log.info("Получение пользователя с ID: {}", userId);
        return userService.getUserById(userId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto userCreate(@RequestBody UserDto userDto) {
        log.info("Создание нового пользователя: {}", userDto);
        return userService.userCreate(userDto);
    }

    @PatchMapping("/{userId}")
    public UserDto userUpdate(@PathVariable long userId, @RequestBody UserDto userDto) {
        log.info("Обновление данных пользователя с ID: {}. Новые данные: {}", userId, userDto);
        return userService.userUpdate(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void userDelete(@PathVariable Long userId) {
        log.info("Удаление пользователя с ID: {}", userId);
        userService.userDelete(userId);
    }
}