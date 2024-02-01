package org.main.booking.application;

import org.junit.jupiter.api.BeforeEach;
import org.main.booking.domain.Booking;
import org.main.booking.domain.BookingRepository;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    private BookingRepository bookingRepository;
    private BookingStatsService bookingStatsService;
    private BookingMaximizationService bookingMaximizationService;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingRepository = Mockito.mock(BookingRepository.class);
        bookingStatsService = Mockito.mock(BookingStatsService.class);
        bookingMaximizationService = Mockito.mock(BookingMaximizationService.class);

        bookingService = new BookingService(
                bookingRepository,
                bookingStatsService,
                bookingMaximizationService
        );
    }

    @Test
    void saveBooking_ShouldAddBookingToRepository() {
        // Arrange
        Booking booking = createTestBooking();

        // Act
        bookingService.saveBooking(booking);

        // Assert
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void getAllBookings_ShouldReturnAllBookingsFromRepository() {
        // Arrange
        Booking booking1 = createTestBooking();
        Booking booking2 = createTestBooking();
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking1, booking2));

        // Act
        List<Booking> allBookings = bookingService.getAllBookings();

        // Assert
        assertEquals(2, allBookings.size());
        assertTrue(allBookings.contains(booking1));
        assertTrue(allBookings.contains(booking2));
    }

    // Other test methods...

    private Booking createTestBooking() {
        Booking booking = new Booking();
        booking.setRequestId("test_request");
        booking.setCheckIn(LocalDate.parse("2022-01-01"));
        booking.setNights(3);
        booking.setSellingRate(new BigDecimal("100"));
        booking.setMargin(15);
        return booking;
    }
}
