package org.main.booking.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.main.booking.domain.Booking;
import org.main.booking.domain.BookingRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingStatsService bookingStatsService;

    @Mock
    private BookingMaximizationService bookingMaximizationService;

    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookingService = new BookingService(bookingRepository, bookingStatsService, bookingMaximizationService);
    }

    @Test
    void saveBooking_ShouldAddBookingToRepository() {
        Booking booking = createTestBooking();

        bookingService.saveBooking(booking);

        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void getAllBookings_ShouldReturnAllBookingsFromRepository() {
        Booking booking1 = createTestBooking("1");
        Booking booking2 = createTestBooking("2");
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking1, booking2));

        List<Booking> allBookings = bookingService.getAllBookings();

        assertEquals(2, allBookings.size());
        assertTrue(allBookings.contains(booking1));
        assertTrue(allBookings.contains(booking2));
    }

    private Booking createTestBooking() {
        return createTestBooking("");
    }

    private Booking createTestBooking(String requestIdSuffix) {
        Booking booking = new Booking();
        booking.setRequestId("test_request_" + requestIdSuffix);
        booking.setCheckIn(LocalDate.parse("2022-01-01"));
        booking.setNights(3);
        booking.setSellingRate(new BigDecimal("100"));
        booking.setMargin(15);
        return booking;
    }
}
