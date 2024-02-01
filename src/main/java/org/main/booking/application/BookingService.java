package org.main.booking.application;

import org.main.booking.domain.Booking;
import org.main.booking.domain.BookingFactory;
import org.main.booking.domain.BookingRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingStatsService bookingStatsService;
    private final BookingMaximizationService bookingMaximizationService;

    public BookingService(BookingRepository bookingRepository,
                          BookingStatsService bookingStatsService,
                          BookingMaximizationService bookingMaximizationService) {
        this.bookingRepository = bookingRepository;
        this.bookingStatsService = bookingStatsService;
        this.bookingMaximizationService = bookingMaximizationService;
    }

    public void createAndSaveBooking(String requestId, LocalDate checkIn, int nights, BigDecimal sellingRate, int margin) {
        Booking booking = BookingFactory.createBooking(requestId, checkIn, nights, sellingRate, margin);
        saveBooking(booking);
    }

    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public BookingStatsResponse calculateBookingStats(List<Booking> bookingRequests) {
        return bookingStatsService.calculateBookingStats(bookingRequests);
    }

    public MaximizeBookingResponse maximizeProfits(List<Booking> bookingRequests) {
        return bookingMaximizationService.maximizeProfits(bookingRequests);
    }

}
