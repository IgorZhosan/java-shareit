package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;

@Slf4j
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserClient userClient;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        log.info("Fetching all users");
        return userClient.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable @Positive long userId) {
        log.info("Fetching user by ID userId={}", userId);
        return userClient.getUserById(userId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> userCreate(@Valid @RequestBody UserDto userDto) {
        log.info("Creating user with userDto={}", userDto);
        return userClient.userCreate(userDto);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> userUpdate(@PathVariable @Positive long userId, @RequestBody UserDto userDto) {
        log.info("Updating user with userId={}, userDto={}", userId, userDto);
        return userClient.userUpdate(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void userDelete(@PathVariable @Positive Long userId) {
        log.info("Deleting user with userId={}", userId);
        userClient.userDelete(userId);
    }
}