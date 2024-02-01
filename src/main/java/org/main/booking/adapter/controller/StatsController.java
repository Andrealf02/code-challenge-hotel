package org.main.booking.adapter.controller;

import org.main.booking.adapter.dto.BookingStatsResponseDTO;
import org.main.booking.application.BookingService;
import org.main.booking.application.BookingStatsResponse;
import org.main.booking.domain.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final BookingService bookingService;

    @Autowired
    public StatsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingStatsResponseDTO> calculateBookingStats(@RequestBody List<Booking> bookingRequests) {
        BookingStatsResponse statsResponse = bookingService.calculateBookingStats(bookingRequests);
        BookingStatsResponseDTO statsResponseDTO = new BookingStatsResponseDTO(statsResponse);
        return ResponseEntity.ok(statsResponseDTO);
    }
}
