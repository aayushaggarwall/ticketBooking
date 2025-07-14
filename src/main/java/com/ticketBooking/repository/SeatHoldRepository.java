package com.ticketBooking.repository;

import com.ticketBooking.model.SeatHold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatHoldRepository extends JpaRepository<SeatHold, Long> {}