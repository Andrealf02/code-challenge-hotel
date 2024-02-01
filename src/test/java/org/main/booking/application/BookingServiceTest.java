package org.main.booking.application;

import junit.framework.TestCase;
import org.main.booking.domain.Booking;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingServiceTest{
    @Test
    void saveBooking_ShouldAddBookingToRepository() {
        // Arrange
        BookingRepositoryMock bookingRepository = new BookingRepositoryMock();
        BookingService bookingService = new BookingService(bookingRepository);
        Booking booking = createTestBooking();

        // Act
        bookingService.saveBooking(booking);

        // Assert
        assertTrue(bookingRepository.savedBookings.contains(booking));
    }

    @Test
    void getAllBookings_ShouldReturnAllBookingsFromRepository() {
        // Arrange
        BookingRepositoryMock bookingRepository = new BookingRepositoryMock();
        BookingService bookingService = new BookingService(bookingRepository);
        Booking booking1 = createTestBooking();
        Booking booking2 = createTestBooking();
        bookingRepository.savedBookings.addAll(Arrays.asList(booking1, booking2));

        // Act
        List<Booking> allBookings = bookingService.getAllBookings();

        // Assert
        assertEquals(2, allBookings.size());
        assertTrue(allBookings.contains(booking1));
        assertTrue(allBookings.contains(booking2));
    }

    @Test
    void calculateBookingStats_EmptyList_ShouldReturnEmptyStats() {
        // Arrange
        BookingService bookingService = new BookingService(new BookingRepositoryMock());
        List<Booking> emptyList = new ArrayList<>();

        // Act
        BookingStatsResponse statsResponse = bookingService.calculateBookingStats(emptyList);

        // Assert
        assertEquals(BigDecimal.ZERO, statsResponse.getAverageProfit());
        assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), statsResponse.getMinProfit());
        assertEquals(BigDecimal.valueOf(Double.MIN_VALUE), statsResponse.getMaxProfit());
    }

    @Test
    void calculateBookingStats_NonEmptyList_ShouldCalculateStats() {
        // Arrange
        BookingService bookingService = new BookingService(new BookingRepositoryMock());
        Booking booking1 = createTestBooking();
        Booking booking2 = createTestBooking();
        List<Booking> bookingList = Arrays.asList(booking1, booking2);

        // Act
        BookingStatsResponse statsResponse = bookingService.calculateBookingStats(bookingList);

        // Assert
        assertEquals(BigDecimal.valueOf(75.0), statsResponse.getAverageProfit());
        assertEquals(BigDecimal.valueOf(112.5), statsResponse.getMinProfit());
        assertEquals(BigDecimal.valueOf(150.0), statsResponse.getMaxProfit());
    }

    @Test
    void maximizeProfits_ShouldMaximizeProfits() {
        // Arrange
        BookingService bookingService = new BookingService(new BookingRepositoryMock());
        Booking booking1 = createTestBooking();
        Booking booking2 = createTestBooking();
        List<Booking> bookingList = Arrays.asList(booking1, booking2);

        // Act
        MaximizeBookingResponse maximizeResponse = bookingService.maximizeProfits(bookingList);

        // Assert
        assertEquals(BigDecimal.valueOf(75.0), maximizeResponse.getAvgNight());
        assertEquals(BigDecimal.valueOf(112.5), maximizeResponse.getMinNight());
        assertEquals(BigDecimal.valueOf(150.0), maximizeResponse.getMaxNight());
    }

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