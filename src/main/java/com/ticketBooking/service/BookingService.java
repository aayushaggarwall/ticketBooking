package com.ticketBooking.service;

import com.ticketBooking.model.Booking;
import com.ticketBooking.model.Event;
import com.ticketBooking.model.SeatHold;
import com.ticketBooking.repository.BookingRepository;
import com.ticketBooking.repository.EventRepository;
import com.ticketBooking.repository.SeatHoldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final EventRepository eventRepo;
    private final SeatHoldRepository seatHoldRepo;
    private final BookingRepository bookingRepo;

    public Long holdSeats(Long userId, Long eventId, List<Integer> seatNumbers) {
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        int available = getAvailableSeats(eventId);
        if (seatNumbers.size() > available)
            throw new RuntimeException("Not enough seats");

        SeatHold hold = new SeatHold();
        hold.setEventId(eventId);
        hold.setUserId(userId);
        hold.setSeatNumbers(seatNumbers);
        hold.setCreatedAt(LocalDateTime.now());
        hold.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        hold.setStatus("HELD");

        return seatHoldRepo.save(hold).getId();
    }

    public Booking confirmHold(Long holdId) {
        SeatHold hold = seatHoldRepo.findById(holdId)
                .orElseThrow(() -> new RuntimeException("Hold not found"));

        if (!"HELD".equals(hold.getStatus()) || hold.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new RuntimeException("Hold expired");

        Booking booking = new Booking();
        booking.setEventId(hold.getEventId());
        booking.setUserId(hold.getUserId());
        booking.setSeatNumbers(hold.getSeatNumbers());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setStatus("CONFIRMED");

        hold.setStatus("EXPIRED");
        seatHoldRepo.save(hold);

        return bookingRepo.save(booking);
    }

    public int getAvailableSeats(Long eventId) {
        Event event = eventRepo.findById(eventId).orElseThrow();
        int total = event.getTotalSeats();

        List<SeatHold> held = seatHoldRepo.findAll().stream()
                .filter(h -> h.getEventId().equals(eventId) &&
                        "HELD".equals(h.getStatus()) &&
                        h.getExpiresAt().isAfter(LocalDateTime.now()))
                .toList();

        List<Booking> booked = bookingRepo.findAll().stream()
                .filter(b -> b.getEventId().equals(eventId) && !"CANCELED".equals(b.getStatus()))
                .toList();

        int heldCount = held.stream().mapToInt(h -> h.getSeatNumbers().size()).sum();
        int bookedCount = booked.stream().mapToInt(b -> b.getSeatNumbers().size()).sum();

        return total - heldCount - bookedCount;
    }

    public void cancelBooking(Long bookingId) {
        Booking b = bookingRepo.findById(bookingId).orElseThrow();
        b.setStatus("CANCELED");
        bookingRepo.save(b);
    }
}

