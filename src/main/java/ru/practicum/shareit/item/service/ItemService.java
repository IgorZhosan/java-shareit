package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoOutput;

import java.util.List;

public interface ItemService {
    List<ItemDto> getAllItems(long userId);

    ItemDtoOutput getItemById(long userId, long itemId);

    ItemDto itemCreate(long userId, ItemDto itemDto);

    ItemDto itemUpdate(long userId, long itemId, ItemDto itemDto);

    List<ItemDto> itemSearch(String text);

    void itemDelete(Long itemId);

    CommentDto addComments(long userId, long itemId, CommentDto commentDto);
}
