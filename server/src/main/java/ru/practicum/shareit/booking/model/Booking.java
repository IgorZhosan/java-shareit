package ru.practicum.shareit.booking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Start date cannot be null")
    @Column(name = "start_date")
    private LocalDateTime start; // дата и время начала бронирования;

    @NotNull(message = "End date cannot be null")
    @Column(name = "end_date")
    private LocalDateTime end; // дата и время конца бронирования;

    @NotNull(message = "Item cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; // вещь, которую пользователь бронирует;

    @NotNull(message = "Booker cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booker_id")
    private User booker; // пользователь, который осуществляет бронирование;

    @NotNull(message = "Status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;
}