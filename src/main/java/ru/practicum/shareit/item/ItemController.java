package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoOutput;
import ru.practicum.shareit.item.service.ItemService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Validated
public class ItemController {

    private static final String HEADER_USER_ID = "X-Sharer-User-Id";
    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAllItems(@RequestHeader(HEADER_USER_ID) @Positive long userId) {
        log.info("Получение всех вещей для пользователя с ID: {}", userId);
        return itemService.getAllItems(userId);
    }

    @GetMapping("/{itemId}")
    public ItemDtoOutput getItemById(@RequestHeader(HEADER_USER_ID) Integer userId,
                                     @PathVariable @Positive long itemId) {
        log.info("Получение вещи с ID: {} для пользователя с ID: {}", itemId, userId);
        return itemService.getItemById(userId, itemId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto itemCreate(@RequestHeader(HEADER_USER_ID) @Positive long userId,
                              @Valid @RequestBody ItemDto itemDto) {
        log.info("Создание новой вещи пользователем с ID: {}. Данные: {}", userId, itemDto);
        return itemService.itemCreate(userId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public ItemDto itemUpdate(@RequestHeader(HEADER_USER_ID) @Positive long userId,
                              @PathVariable @Positive long itemId,
                              @RequestBody ItemDto itemDto) {
        log.info("Обновление вещи с ID: {} пользователем с ID: {}. Новые данные: {}", itemId, userId, itemDto);
        return itemService.itemUpdate(userId, itemId, itemDto);
    }

    @GetMapping("/search")
    public List<ItemDto> itemSearch(@RequestParam(required = false) String text) {
        log.info("Поиск вещей по тексту: \"{}\"", text);
        return itemService.itemSearch(text);
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void itemDelete(@PathVariable @Positive Long itemId) {
        log.info("Удаление вещи с ID: {}", itemId);
        itemService.itemDelete(itemId);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto addComments(@RequestHeader(HEADER_USER_ID) long userId,
                                  @PathVariable long itemId,
                                  @Valid @RequestBody CommentDto commentDto) {
        log.info("Добавление комментария к вещи с ID: {} пользователем с ID: {}. Данные комментария: {}",
                itemId, userId, commentDto);
        return itemService.addComments(userId, itemId, commentDto);
    }
}