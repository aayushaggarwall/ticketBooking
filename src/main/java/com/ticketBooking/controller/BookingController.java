package com.ticketBooking.controller;

import com.ticketBooking.dto.SeatRequestDto;
import com.ticketBooking.model.Booking;
import com.ticketBooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/hold")
    public ResponseEntity<Long> holdSeats(@RequestBody SeatRequestDto request) {
        return ResponseEntity.ok(bookingService.holdSeats(
                request.getUserId(), request.getEventId(), request.getSeatNumbers()));
    }

    @PostMapping("/confirm/{holdId}")
    public ResponseEntity<Booking> confirm(@PathVariable Long holdId) {
        return ResponseEntity.ok(bookingService.confirmHold(holdId));
    }

    @GetMapping("/availability/{eventId}")
    public ResponseEntity<Integer> available(@PathVariable Long eventId) {
        return ResponseEntity.ok(bookingService.getAvailableSeats(eventId));
    }

    @PostMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancel(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking canceled");
    }
}
