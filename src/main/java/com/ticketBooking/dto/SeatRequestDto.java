package com.ticketBooking.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeatRequestDto {
    private Long userId;
    private Long eventId;
    private List<Integer> seatNumbers;
}
