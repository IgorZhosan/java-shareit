package ru.practicum.shareit.request.service;

import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDtoOutput;

import java.util.List;

public interface ItemRequestService {
    ItemRequestDtoOutput itemRequestCreate(long userId, ItemRequestDto itemRequestDto);

    List<ItemRequestDtoOutput> getAllRequestByUser(long userId);

    List<ItemRequestDtoOutput> getAllRequests(long userId);

    ItemRequestDtoOutput getRequestById(long requestId);
}