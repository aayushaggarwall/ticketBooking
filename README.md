# 🎟️ Ticket Booking System – Backend API

This is a backend service for managing **event ticket bookings**. Built using **Java Spring Boot**, the system handles:

- CRUD operations for events and bookings
- Temporary seat reservations (holds)
- Confirming and cancelling bookings
- Real-time availability checks
- Automatic release of expired holds

---

## 🚀 Tech Stack

| Layer         | Technology            |
|---------------|------------------------|
| Language      | Java 17                |
| Framework     | Spring Boot 3.x        |
| Database      | H2 (in-memory)         |
| ORM           | Spring Data JPA        |
| Scheduler     | Spring Task Scheduling |
| Build Tool    | Maven                  |

---

## 📁 Project Structure

```plaintext
ticket-booking-system/
├── controller         # REST controllers
├── service            # Business logic
├── repository         # Spring Data JPA interfaces
├── model              # Entity models
├── dto                # Request DTOs
├── scheduler          # Seat hold expiration cleanup
└── resources/
    ├── application.properties
    └── data.sql       # (Optional) Initial data
