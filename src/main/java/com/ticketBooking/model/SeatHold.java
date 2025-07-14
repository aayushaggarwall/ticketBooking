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
public class SeatHold {
    @Id
    @GeneratedValue
    private Long id;
    private Long eventId;
    private Long userId;

    @ElementCollection
    private List<Integer> seatNumbers;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private String status; // HELD, EXPIRED
}
