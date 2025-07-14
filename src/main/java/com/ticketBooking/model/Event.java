package com.ticketBooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDate date;
    private String location;
    private int totalSeats;
}
