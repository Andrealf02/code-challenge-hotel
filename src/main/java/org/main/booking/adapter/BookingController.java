package org.main.booking.adapter;

import org.main.booking.application.BookingService;
import org.main.booking.domain.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/stats")
    public ResponseEntity<BookingStatsResponse> calculateBookingStats(@RequestBody List<Booking> bookingRequests) {
        BookingStatsResponse statsResponse = bookingService.calculateBookingStats(bookingRequests);
        return ResponseEntity.ok(statsResponse);
    }

    @PostMapping("/maximize")
    public ResponseEntity<MaximizeBookingResponse> maximizeProfits(@RequestBody List<Booking> bookingRequests) {
        MaximizeBookingResponse maximizeBookingResponse = bookingService.maximizeProfits(bookingRequests);
        return ResponseEntity.ok(maximizeBookingResponse);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> allBookings = bookingService.getAllBookings();
        return ResponseEntity.ok(allBookings);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}
