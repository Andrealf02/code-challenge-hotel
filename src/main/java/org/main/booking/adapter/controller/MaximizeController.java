package org.main.booking.adapter.controller;

import org.main.booking.adapter.dto.MaximizeBookingResponseDTO;
import org.main.booking.application.BookingService;
import org.main.booking.application.MaximizeBookingResponse;
import org.main.booking.domain.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maximize")
public class MaximizeController {

    private final BookingService bookingService;

    @Autowired
    public MaximizeController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<MaximizeBookingResponseDTO> maximizeProfits(@RequestBody List<Booking> bookingRequests) {
        MaximizeBookingResponse maximizeResponse = bookingService.maximizeProfits(bookingRequests);
        MaximizeBookingResponseDTO maximizeResponseDTO = new MaximizeBookingResponseDTO(maximizeResponse);
        return ResponseEntity.ok(maximizeResponseDTO);
    }
}
