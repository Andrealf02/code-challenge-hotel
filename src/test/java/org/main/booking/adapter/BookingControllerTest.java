package org.main.booking.adapter;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.main.booking.domain.Booking;
import org.testng.annotations.Test;

public class BookingControllerTest {
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        setupBookingController();
    }

    @Test
    void handleBookingRequest_ShouldAddBookingToRepository() {
        Booking booking = createTestBooking();

        bookingController.handleBookingRequest(booking);

        List<Booking> allBookings = bookingController.getAllBookings();

        assertEquals(1, allBookings.size());
        assertEquals("test_request", allBookings.get(0).getRequestId());
    }

    private void setupBookingController() {
        BookingService bookingService = new BookingService(new TestBookingRepository());
        bookingController = new BookingController(bookingService);
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