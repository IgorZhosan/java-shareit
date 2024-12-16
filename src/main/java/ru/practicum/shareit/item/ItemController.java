package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ItemController {
    private final ItemService itemService;
    private static final String HEADER_USER_ID = "X-Sharer-User-Id";

    @GetMapping
    public List<ItemDto> getAllItems(@RequestHeader(HEADER_USER_ID) @Positive final long userId) {
        log.info("Получен запрос getAllItems с userId: {}", userId);
        return itemService.getAllItems(userId);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable @Positive final long itemId) {
        log.info("Получен запрос getItemById с itemId: {}", itemId);
        return itemService.getItemById(itemId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto itemCreate(@RequestHeader(HEADER_USER_ID) @Positive final long userId,
                              @Valid @RequestBody final ItemDto itemDto) {
        log.info("Получен запрос itemCreate с itemId: {}, itemDto : {}", userId, itemDto);
        return itemService.itemCreate(userId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public ItemDto itemUpdate(@RequestHeader(HEADER_USER_ID) @Positive final long userId,
                              @PathVariable @Positive final long itemId,
                              @RequestBody final ItemDto itemDto) {
        log.info("Получен запрос itemUpdate с itemId: {}, itemDto : {}", userId, itemDto);
        return itemService.itemUpdate(userId, itemId, itemDto);
    }

    @GetMapping("/search")
    public List<ItemDto> itemSearch(@RequestParam(required = false) final String text) {
        log.info("Получен запрос itemSearch с text: {}", text);
        return itemService.itemSearch(text);
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void itemDelete(@PathVariable @Positive final Long itemId) {
        log.info("Получен запрос itemDelete с itemId: {}", itemId);
        itemService.itemDelete(itemId);
    }
}