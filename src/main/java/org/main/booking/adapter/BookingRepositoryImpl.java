package org.main.booking.adapter;

import org.main.booking.domain.Booking;
import org.main.booking.domain.BookingRepository;

import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl extends BookingRepository {
    private final List<Booking> bookings = new ArrayList<>();

    @Override
    public void save(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(bookings);
    }
}