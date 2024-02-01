package org.main.booking.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.main.booking.application.BookingService;
import org.main.booking.domain.Booking;
import org.main.booking.domain.BookingRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class BookingControllerTest {

    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        setupBookingController();

        BookingService bookingServiceMock = mock(BookingService.class);

        Mockito.doNothing().when(bookingServiceMock).saveBooking(Mockito.any(Booking.class));

        this.bookingController = new BookingController(bookingServiceMock);
    }

    @Test
    void handleBookingRequest_ShouldAddBookingToRepository() {
        Booking booking = createTestBooking();

        bookingController.handleBookingRequest(Collections.singletonList(booking));

        List<Booking> allBookings = (List<Booking>) bookingController.getAllBookings();

        assertEquals(1, allBookings.size());
        assertEquals("test_request", allBookings.get(0).getRequestId());
    }

    private void setupBookingController() {
        BookingService bookingService = new BookingService(new BookingRepository());
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