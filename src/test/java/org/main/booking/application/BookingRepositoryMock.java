package org.main.booking.application;

import org.main.booking.domain.Booking;
import org.main.booking.domain.BookingRepository;

import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryMock extends BookingRepository {
    final List<Booking> savedBookings = new ArrayList<>();

    @Override
    public void save(Booking booking) {
        savedBookings.add(booking);
    }

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(savedBookings);
    }
}
