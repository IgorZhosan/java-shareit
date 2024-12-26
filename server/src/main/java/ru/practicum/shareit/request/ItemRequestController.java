package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.booking.BookingController;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDtoOutput;
import ru.practicum.shareit.request.service.ItemRequestService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class ItemRequestController {

    private final ItemRequestService itemRequestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemRequestDtoOutput itemRequestCreate(@RequestHeader(BookingController.HEADER_USER_ID) long userId,
                                                  @RequestBody ItemRequestDto itemRequestDto) {
        log.info("Создание запроса пользователем с ID: {}", userId);
        return itemRequestService.itemRequestCreate(userId, itemRequestDto);
    }

    @GetMapping
    public List<ItemRequestDtoOutput> getAllRequestByUser(@RequestHeader(BookingController.HEADER_USER_ID) long userId) {
        log.info("Получение всех запросов пользователя с ID: {}", userId);
        return itemRequestService.getAllRequestByUser(userId);
    }

    @GetMapping("/all")
    public List<ItemRequestDtoOutput> getAllRequests(@RequestHeader(BookingController.HEADER_USER_ID) long userId) {
        log.info("Получение всех запросов, созданных другими пользователями, для пользователя с ID: {}", userId);
        return itemRequestService.getAllRequests(userId);
    }

    @GetMapping("/{requestId}")
    public ItemRequestDtoOutput getRequestById(@PathVariable long requestId) {
        log.info("Получение запроса с ID: {}", requestId);
        return itemRequestService.getRequestById(requestId);
    }
}