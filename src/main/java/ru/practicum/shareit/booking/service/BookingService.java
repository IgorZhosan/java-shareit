package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingDtoInput;
import ru.practicum.shareit.booking.dto.BookingDtoOutput;
import ru.practicum.shareit.booking.model.State;

import java.util.Collection;

public interface BookingService {
    BookingDtoOutput createBooking(Long userId, BookingDtoInput bookingDtoInput);

    BookingDtoOutput confirmationBooking(Long userId, Long bookingId, Boolean approved);

    BookingDtoOutput getBookingById(Long userId, Long bookingId);

    Collection<BookingDtoOutput> getAllBookingsFromUser(Long userId, State state);

    Collection<BookingDtoOutput> getAllBookingsFromOwner(Long userId, State state);
}
