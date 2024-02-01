package org.main.booking.adapter;

import org.main.booking.domain.Booking;
import org.main.booking.domain.BookingRepository;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookingRepositoryTest {
    @Test
    void save_BookingDoesNotOverlap_ShouldAddBooking() {
        BookingRepository bookingRepository = new BookingRepository();
        Booking booking = createBooking("2022-01-01", 3);

        assertDoesNotThrow(() -> bookingRepository.save(booking));
        assertTrue(bookingRepository.findAll().contains(booking));
    }

    @Test
    void save_BookingOverlaps_ShouldThrowException() {
        BookingRepository bookingRepository = new BookingRepository();
        Booking booking1 = createBooking("2022-01-01", 3);
        Booking booking2 = createBooking("2022-01-02", 2);

        bookingRepository.save(booking1);

        assertThrows(IllegalArgumentException.class, () -> bookingRepository.save(booking2));
    }

    private Booking createBooking(String checkInDate, int nights) {
        Booking booking = new Booking();
        booking.setCheckIn(LocalDate.parse(checkInDate));
        booking.setNights(nights);
        booking.setSellingRate(BigDecimal.TEN);
        booking.setMargin(15);
        return booking;
    }
}
