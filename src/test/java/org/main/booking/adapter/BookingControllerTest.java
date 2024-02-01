package org.main.booking.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.main.booking.application.BookingService;
import org.main.booking.domain.Booking;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

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
        // Arrange
        Booking booking = createTestBooking();

        // Mock the behavior of bookingService.saveBooking (returning void)
        doNothing().when(bookingServiceMock).saveBooking(booking);

        // Act
        ResponseEntity<List<Booking>> responseEntity = bookingController.handleBookingRequest(Collections.singletonList(booking));

        // Assert
        // Verify that saveBooking method is called once with the correct argument
        verify(bookingServiceMock, times(1)).saveBooking(booking);

        // Ensure the response has the correct status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Ensure the list of saved bookings is not null
        List<Booking> allBookings = responseEntity.getBody();
        assertEquals(1, allBookings.size());

        // Ensure the attributes of the saved booking are as expected
        Booking savedBooking = allBookings.get(0);
        assertEquals("test_request", savedBooking.getRequestId());
        assertEquals(LocalDate.parse("2022-01-01"), savedBooking.getCheckIn());
        assertEquals(3, savedBooking.getNights());
        assertEquals(new BigDecimal("100"), savedBooking.getSellingRate());
        assertEquals(15, savedBooking.getMargin());
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
