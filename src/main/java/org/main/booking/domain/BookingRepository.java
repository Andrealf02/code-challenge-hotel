package org.main.booking.domain;

import org.main.booking.exceptions.OverlappingBookingException;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private final List<Booking> bookings = new ArrayList<>();

    public void save(Booking booking) {
        if (hasOverlap(booking)) {
            throw new OverlappingBookingException("Booking overlaps with existing bookings.");
        }
        bookings.add(booking);
    }

    public List<Booking> findAll() {
        return new ArrayList<>(bookings);
    }

    private boolean hasOverlap(Booking newBooking) {
        return bookings.stream()
                .anyMatch(existingBooking -> checkOverlap(existingBooking, newBooking)
                );
    }

    private boolean checkOverlap(Booking booking1, Booking booking2) {
        return booking1.getCheckIn().isBefore(booking2.getCheckOut()) &&
                booking2.getCheckIn().isBefore(booking1.getCheckOut());
    }

    public List<Booking> findNonOverlappingBookings(List<Booking> bookingRequests) {
        List<Booking> nonOverlappingBookings = new ArrayList<>();

        for (Booking currentBooking : bookingRequests) {
            if (!hasOverlap(currentBooking)) {
                nonOverlappingBookings.add(currentBooking);
            }
        }

        return nonOverlappingBookings;
    }
}
