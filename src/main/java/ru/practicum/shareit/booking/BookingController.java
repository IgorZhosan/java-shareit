package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

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
import ru.practicum.shareit.booking.dto.BookingDtoInput;
import ru.practicum.shareit.booking.dto.BookingDtoOutput;
import ru.practicum.shareit.booking.model.State;
import ru.practicum.shareit.booking.service.BookingService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Validated
public class BookingController {
    private static final String HEADER_USER_ID = "X-Sharer-User-Id";
    private final BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingDtoOutput createBooking(@RequestHeader(HEADER_USER_ID) Long userId,
                                          @RequestBody BookingDtoInput bookingDtoInput) {
        log.info("Создание бронирования: userId={}, bookingDtoInput={}", userId, bookingDtoInput);
        return bookingService.createBooking(userId, bookingDtoInput);
    }

    @PatchMapping("/{bookingId}")
    public BookingDtoOutput confirmationBooking(@RequestHeader(HEADER_USER_ID) Long userId,
                                                @PathVariable Long bookingId,
                                                @RequestParam Boolean approved) {
        log.info("Подтверждение бронирования: userId={}, bookingId={}, approved={}", userId, bookingId, approved);
        return bookingService.confirmationBooking(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingDtoOutput getBookingById(@RequestHeader(HEADER_USER_ID) Long userId,
                                           @PathVariable Long bookingId) {
        log.info("Получение бронирования по ID: userId={}, bookingId={}", userId, bookingId);
        return bookingService.getBookingById(userId, bookingId);
    }

    @GetMapping
    public Collection<BookingDtoOutput> getAllBookingsFromUser(@RequestHeader(HEADER_USER_ID) Long userId,
                                                               @RequestParam(defaultValue = "ALL") State state) {
        log.info("Получение всех бронирований пользователя: userId={}, state={}", userId, state);
        return bookingService.getAllBookingsFromUser(userId, state);
    }

    @GetMapping("/owner")
    public Collection<BookingDtoOutput> getAllBookingsFromOwner(@RequestHeader(HEADER_USER_ID) Long userId,
                                                                @RequestParam(defaultValue = "ALL") State state) {
        log.info("Получение всех бронирований владельца: userId={}, state={}", userId, state);
        return bookingService.getAllBookingsFromOwner(userId, state);
    }
}
