package org.main.booking.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.main.booking.adapter.controller.BookingController;
import org.main.booking.application.BookingService;
import org.main.booking.domain.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class BookingControllerTest {

    private BookingController bookingController;
    private BookingService bookingServiceMock;

    @BeforeEach
    void setUp() {
        bookingServiceMock = mock(BookingService.class);
        bookingController = new BookingController(bookingServiceMock);
    }

    @Test
    void handleBookingRequest_ShouldAddBookingToRepository() {
        Booking booking = createTestBooking();

        doNothing().when(bookingServiceMock).saveBooking(booking);

        ResponseEntity<List<Booking>> responseEntity = bookingController.handleBookingRequest(Collections.singletonList(booking));

        verify(bookingServiceMock, times(1)).saveBooking(booking);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<Booking> allBookings = responseEntity.getBody();
        assertNotNull(allBookings);
        assertEquals(0, allBookings.size());
    }

    private Booking createTestBooking() {
        Booking booking = new Booking();
        booking.setRequestId("test_request");
        booking.setCheckIn(parseDate("2022-01-01"));
        booking.setNights(3);
        booking.setSellingRate(new BigDecimal("100"));
        booking.setMargin(15);
        return booking;
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
