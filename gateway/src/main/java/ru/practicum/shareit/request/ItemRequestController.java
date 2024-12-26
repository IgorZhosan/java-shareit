package ru.practicum.shareit.request;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.BookingController;
import ru.practicum.shareit.request.dto.ItemRequestDto;

@Slf4j
@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
@Validated
public class ItemRequestController {

    private final ItemRequestClient itemRequestClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> itemRequestCreate(@RequestHeader(BookingController.HEADER_USER_ID) @NotNull long userId,
                                                    @RequestBody ItemRequestDto itemRequestDto) {
        log.info("Creating item request for userId={}, itemRequestDto={}", userId, itemRequestDto);
        return itemRequestClient.itemRequestCreate(userId, itemRequestDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllRequestByUser(@RequestHeader(BookingController.HEADER_USER_ID) long userId) {
        log.info("Fetching all requests for userId={}", userId);
        return itemRequestClient.getAllRequestByUser(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllRequests(@RequestHeader(BookingController.HEADER_USER_ID) long userId) {
        log.info("Fetching all item requests for all users by userId={}", userId);
        return itemRequestClient.getAllRequests(userId);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Object> getRequestById(@PathVariable long requestId) {
        log.info("Fetching request by requestId={}", requestId);
        return itemRequestClient.getRequestById(requestId);
    }
}