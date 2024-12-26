package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDtoInput;
import ru.practicum.shareit.booking.model.State;

@Slf4j
@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Validated
public class BookingController {

    public static final String HEADER_USER_ID = "X-Sharer-User-Id";

    private final BookingClient bookingClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createBooking(@RequestHeader(HEADER_USER_ID) Long userId,
                                                @RequestBody BookingDtoInput bookingDtoInput) {
        log.info("Creating booking for userId={}, bookingDtoInput={}", userId, bookingDtoInput);
        return bookingClient.createBooking(userId, bookingDtoInput);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> confirmationBooking(@RequestHeader(HEADER_USER_ID) Long userId,
                                                      @PathVariable Long bookingId,
                                                      @RequestParam Boolean approved) {
        log.info("Confirming booking for userId={}, bookingId={}, approved={}", userId, bookingId, approved);
        return bookingClient.confirmationBooking(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> getBookingById(@RequestHeader(HEADER_USER_ID) Long userId,
                                                 @PathVariable Long bookingId) {
        log.info("Fetching booking by ID for userId={}, bookingId={}", userId, bookingId);
        return bookingClient.getBookingById(userId, bookingId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllBookingsFromUser(@RequestHeader(HEADER_USER_ID) Long userId,
                                                         @RequestParam(defaultValue = "ALL") State state) {
        log.info("Fetching all bookings for userId={}, state={}", userId, state);
        return bookingClient.getAllBookingsFromUser(userId, state);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> getAllBookingsFromOwner(@RequestHeader(HEADER_USER_ID) Long userId,
                                                          @RequestParam(defaultValue = "ALL") State state) {
        log.info("Fetching all bookings for owner userId={}, state={}", userId, state);
        return bookingClient.getAllBookingsFromOwner(userId, state);
    }
}