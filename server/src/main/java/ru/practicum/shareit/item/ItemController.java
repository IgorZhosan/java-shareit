package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
import ru.practicum.shareit.booking.BookingController;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoOutput;
import ru.practicum.shareit.item.service.ItemService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {



    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAllItems(@RequestHeader(BookingController.HEADER_USER_ID) long userId) {
        log.info("Получение всех вещей пользователя с ID: {}", userId);
        return itemService.getAllItems(userId);
    }

    @GetMapping("/{itemId}")
    public ItemDtoOutput getItemById(@RequestHeader(BookingController.HEADER_USER_ID) long userId, @PathVariable long itemId) {
        log.info("Получение вещи с ID: {} пользователем с ID: {}", itemId, userId);
        return itemService.getItemById(userId, itemId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto itemCreate(@RequestHeader(BookingController.HEADER_USER_ID) long userId, @RequestBody ItemDto itemDto) {
        log.info("Создание вещи пользователем с ID: {}", userId);
        return itemService.itemCreate(userId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public ItemDto itemUpdate(@RequestHeader(BookingController.HEADER_USER_ID) long userId, @PathVariable long itemId,
                              @RequestBody ItemDto itemDto) {
        log.info("Обновление вещи с ID: {} пользователем с ID: {}", itemId, userId);
        return itemService.itemUpdate(userId, itemId, itemDto);
    }

    @GetMapping("/search")
    public List<ItemDto> itemSearch(@RequestParam String text) {
        log.info("Поиск вещей по тексту: {}", text);
        return itemService.itemSearch(text);
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void itemDelete(@PathVariable Long itemId) {
        log.info("Удаление вещи с ID: {}", itemId);
        itemService.itemDelete(itemId);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto addComments(@RequestHeader(BookingController.HEADER_USER_ID) long userId, @PathVariable long itemId,
                                  @RequestBody CommentDto commentDto) {
        log.info("Добавление комментария к вещи с ID: {} пользователем с ID: {}", itemId, userId);
        return itemService.addComments(userId, itemId, commentDto);
    }
}
