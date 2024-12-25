package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Validated
public class ItemController {

    private static final String HEADER_USER_ID = "X-Sharer-User-Id";

    private final ItemClient itemClient;

    @GetMapping
    public ResponseEntity<Object> getAllItems(@RequestHeader(HEADER_USER_ID) @Positive long userId) {
        return itemClient.getAllItems(userId);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Object> getItemById(@RequestHeader(HEADER_USER_ID) long userId,
                                              @PathVariable @Positive long itemId) {
        return itemClient.getItemById(userId, itemId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> itemCreate(@RequestHeader(HEADER_USER_ID) @Positive long userId,
                                             @Valid @RequestBody ItemDto itemDto) {
        return itemClient.itemCreate(userId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> itemUpdate(@RequestHeader(HEADER_USER_ID) @Positive long userId,
                                             @PathVariable @Positive long itemId, @RequestBody ItemDto itemDto) {
        return itemClient.itemUpdate(userId, itemId, itemDto);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> itemSearch(@RequestHeader(HEADER_USER_ID) @Positive long userId,
                                             @RequestParam(required = false) String text) {
        return itemClient.itemSearch(userId, text);
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void itemDelete(@PathVariable @Positive Long itemId) {
        itemClient.itemDelete(itemId);
    }

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<Object> addComments(@RequestHeader(HEADER_USER_ID) long userId,
                                              @PathVariable long itemId, @Valid @RequestBody CommentDto commentDto) {
        return itemClient.addComments(userId, itemId, commentDto);
    }
}