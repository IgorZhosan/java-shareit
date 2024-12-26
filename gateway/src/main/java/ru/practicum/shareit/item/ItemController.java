package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.BookingController;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Validated
public class ItemController {

    private final ItemClient itemClient;

    @GetMapping
    public ResponseEntity<Object> getAllItems(@RequestHeader(BookingController.HEADER_USER_ID) @Positive long userId) {
        log.info("Fetching all items for userId={}", userId);
        return itemClient.getAllItems(userId);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Object> getItemById(@RequestHeader(BookingController.HEADER_USER_ID) long userId,
                                              @PathVariable @Positive long itemId) {
        log.info("Fetching item by ID for userId={}, itemId={}", userId, itemId);
        return itemClient.getItemById(userId, itemId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> itemCreate(@RequestHeader(BookingController.HEADER_USER_ID) @Positive long userId,
                                             @Valid @RequestBody ItemDto itemDto) {
        log.info("Creating item for userId={}, itemDto={}", userId, itemDto);
        return itemClient.itemCreate(userId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> itemUpdate(@RequestHeader(BookingController.HEADER_USER_ID) @Positive long userId,
                                             @PathVariable @Positive long itemId, @RequestBody ItemDto itemDto) {
        log.info("Updating item for userId={}, itemId={}, itemDto={}", userId, itemId, itemDto);
        return itemClient.itemUpdate(userId, itemId, itemDto);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> itemSearch(@RequestHeader(BookingController.HEADER_USER_ID) @Positive long userId,
                                             @RequestParam(required = false) String text) {
        log.info("Searching items for userId={}, text='{}'", userId, text);
        return itemClient.itemSearch(userId, text);
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void itemDelete(@PathVariable @Positive Long itemId) {
        log.info("Deleting item with itemId={}", itemId);
        itemClient.itemDelete(itemId);
    }

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<Object> addComments(@RequestHeader(BookingController.HEADER_USER_ID) long userId,
                                              @PathVariable long itemId, @Valid @RequestBody CommentDto commentDto) {
        log.info("Adding comment for userId={}, itemId={}, commentDto={}", userId, itemId, commentDto);
        return itemClient.addComments(userId, itemId, commentDto);
    }
}