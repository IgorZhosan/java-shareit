package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("Получение списка всех пользователей.");
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable @Positive long userId) {
        log.info("Получение пользователя с ID: {}", userId);
        return userService.getUserById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto userCreate(@Valid @RequestBody UserDto userDto) {
        log.info("Создание нового пользователя. Данные: {}", userDto);
        return userService.userCreate(userDto);
    }

    @PatchMapping("/{userId}")
    public UserDto userUpdate(@PathVariable @Positive long userId, @RequestBody UserDto userDto) {
        log.info("Обновление пользователя с ID: {}. Новые данные: {}", userId, userDto);
        return userService.userUpdate(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void userDelete(@PathVariable @Positive Long userId) {
        log.info("Удаление пользователя с ID: {}", userId);
        userService.userDelete(userId);
    }
}