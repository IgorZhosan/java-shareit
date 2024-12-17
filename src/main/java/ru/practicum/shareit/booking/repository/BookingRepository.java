package ru.practicum.shareit.booking.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b JOIN b.item i ON b.item = i WHERE b.id = :bookingId AND i.owner.id = :userId")
    Optional<Booking> findByIdAndOwnerId(long bookingId, long userId);

    List<Booking> findAllByBookerId(long userId, Sort sort);

    List<Booking> findAllByBookerIdAndStartBeforeAndEndAfter(long userId, LocalDateTime time1,
                                                             LocalDateTime time2, Sort sort);

    List<Booking> findAllByBookerIdAndEndBefore(long userId, LocalDateTime time, Sort sort);

    List<Booking> findAllByBookerIdAndStartAfter(long userId, LocalDateTime time, Sort sort);

    List<Booking> findAllByBookerIdAndStatusIs(long userId, BookingStatus status, Sort sort);

    List<Booking> findAllByItemOwnerId(long userId, Sort sort);

    List<Booking> findAllByItemOwnerIdAndStartBeforeAndEndAfter(long userId, LocalDateTime time1,
                                                                LocalDateTime time2, Sort sort);

    List<Booking> findAllByItemOwnerIdAndEndBefore(long userId, LocalDateTime time, Sort sort);

    List<Booking> findAllByItemOwnerIdAndStartAfter(long userId, LocalDateTime time, Sort sort);

    List<Booking> findAllByItemOwnerIdAndStatusIs(long userId, BookingStatus status, Sort sort);

    List<Booking> findAllByBookerIdAndItemIdAndStatusEqualsAndEndIsBefore(long userId,
                                                                          long itemId, BookingStatus status,
                                                                          LocalDateTime time);

    Optional<Booking> findTopByItemIdAndEndBeforeAndStatusInOrderByEndDesc(long itemId,
                                                                           LocalDateTime time,
                                                                           List<BookingStatus> status);

    Optional<Booking> findTopByItemIdAndStartAfterAndStatusInOrderByStartAsc(long itemId,
                                                                             LocalDateTime time,
                                                                             List<BookingStatus> status);
}