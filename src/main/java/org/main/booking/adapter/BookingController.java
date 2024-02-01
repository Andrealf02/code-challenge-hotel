package org.main.booking.adapter;

import org.main.booking.adapter.dto.BookingStatsResponseDTO;
import org.main.booking.adapter.dto.MaximizeBookingResponseDTO;
import org.main.booking.application.BookingService;
import org.main.booking.application.BookingStatsResponse;
import org.main.booking.application.MaximizeBookingResponse;
import org.main.booking.domain.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/stats")
    public ResponseEntity<BookingStatsResponseDTO> calculateBookingStats(@RequestBody List<Booking> bookingRequests) {
        BookingStatsResponse statsResponse = bookingService.calculateBookingStats(bookingRequests);
        BookingStatsResponseDTO statsResponseDTO = new BookingStatsResponseDTO(statsResponse);
        return ResponseEntity.ok(statsResponseDTO);
    }


    @PostMapping("/maximize")
    public ResponseEntity<MaximizeBookingResponseDTO> maximizeProfits(@RequestBody List<Booking> bookingRequests) {
        MaximizeBookingResponse maximizeResponse = bookingService.maximizeProfits(bookingRequests);
        MaximizeBookingResponseDTO maximizeResponseDTO = new MaximizeBookingResponseDTO(maximizeResponse);
        return ResponseEntity.ok(maximizeResponseDTO);
    }

    @PostMapping("/bookings")
    public ResponseEntity<List<Booking>> handleBookingRequest(@RequestBody List<Booking> bookingRequests) {
        for (Booking booking : bookingRequests) {
            bookingService.saveBooking(booking);
        }
        List<Booking> savedBookings = bookingService.getAllBookings();
        return ResponseEntity.ok(savedBookings);
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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder errorMessage = new StringBuilder("Validation error(s): ");
        for (FieldError fieldError : fieldErrors) {
            errorMessage.append(fieldError.getDefaultMessage()).append("; ");
        }
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }
}