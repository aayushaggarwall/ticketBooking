package com.ticketBooking.scheduler;

import com.ticketBooking.model.SeatHold;
import com.ticketBooking.repository.SeatHoldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HoldExpiryScheduler {

    private final SeatHoldRepository seatHoldRepo;

    @Scheduled(fixedRate = 60000) // Every 1 minute
    public void cleanupExpiredHolds() {
        List<SeatHold> expired = seatHoldRepo.findAll().stream()
                .filter(h -> "HELD".equals(h.getStatus()) && h.getExpiresAt().isBefore(LocalDateTime.now()))
                .toList();

        expired.forEach(h -> {
            h.setStatus("EXPIRED");
            seatHoldRepo.save(h);
        });
    }
}
