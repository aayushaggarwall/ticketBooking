package com.ticketBooking.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue
    private Long id;
    private Long eventId;
    private Long userId;

    @ElementCollection
    private List<Integer> seatNumbers;

    private LocalDateTime createdAt;
    private String status; // CONFIRMED, CANCELED
}
