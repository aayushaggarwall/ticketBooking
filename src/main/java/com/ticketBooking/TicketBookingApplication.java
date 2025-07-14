package com.ticketBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TicketBookingApplication {

	public static void main(String[] args) {
		System.out.println("HEllo and welcome to my Project!!!!");
		SpringApplication.run(TicketBookingApplication.class, args);
	}

}
