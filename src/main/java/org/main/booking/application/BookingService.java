package org.main.booking.application;

import org.main.booking.domain.Booking;
import org.main.booking.domain.BookingRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public BookingStatsResponse calculateBookingStats(List<Booking> bookingRequests) {
        if (bookingRequests.isEmpty()) {
            return BookingStatsResponse.emptyStats();
        }

        BigDecimal totalProfit = BigDecimal.ZERO;
        BigDecimal minProfit = BigDecimal.valueOf(Double.MAX_VALUE);
        BigDecimal maxProfit = BigDecimal.valueOf(Double.MIN_VALUE);

        for (Booking booking : bookingRequests) {
            BigDecimal profitPerNight = calculateProfitPerNight(booking);
            totalProfit = totalProfit.add(profitPerNight);
            minProfit = minProfit.min(profitPerNight);
            maxProfit = maxProfit.max(profitPerNight);
        }

        BigDecimal averageProfit = totalProfit.divide(BigDecimal.valueOf(bookingRequests.size()), 2, BigDecimal.ROUND_HALF_UP);

        return new BookingStatsResponse(averageProfit, minProfit, maxProfit);
    }

    public MaximizeBookingResponse maximizeProfits(List<Booking> bookingRequests) {
        List<Booking> validBookings = findNonOverlappingBookings(bookingRequests);

        ProfitInfo profitInfo = maximizeProfitsHelper(validBookings, 0, Integer.MAX_VALUE);

        BigDecimal avgNight = profitInfo.totalProfit.divide(BigDecimal.valueOf(validBookings.size()), 2, BigDecimal.ROUND_HALF_UP);

        BigDecimal minNight = BigDecimal.valueOf(Double.MAX_VALUE);
        BigDecimal maxNight = BigDecimal.valueOf(Double.MIN_VALUE);

        for (Booking booking : validBookings) {
            BigDecimal profitPerNight = calculateProfitPerNight(booking);
            minNight = minNight.min(profitPerNight);
            maxNight = maxNight.max(profitPerNight);
        }

        return new MaximizeBookingResponse(profitInfo.requestIds, profitInfo.totalProfit, avgNight, minNight, maxNight);
    }

    private ProfitInfo maximizeProfitsHelper(List<Booking> bookings, int index, int remainingNights) {
        if (index == bookings.size() || remainingNights == 0) {
            return new ProfitInfo(BigDecimal.ZERO, new ArrayList<>(), 0);
        }

        ProfitInfo excludeCurrent = maximizeProfitsHelper(bookings, index + 1, remainingNights);

        Booking currentBooking = bookings.get(index);
        ProfitInfo includeCurrent = new ProfitInfo(BigDecimal.ZERO, new ArrayList<>(), 0);
        if (currentBooking.getNights() <= remainingNights) {
            ProfitInfo next = maximizeProfitsHelper(bookings, index + 1, remainingNights - currentBooking.getNights());
            BigDecimal currentProfit = calculateProfitPerNight(currentBooking);
            includeCurrent.totalProfit = currentProfit.add(next.totalProfit);
            includeCurrent.requestIds.add(currentBooking.getRequestId());
            includeCurrent.requestIds.addAll(next.requestIds);
            includeCurrent.totalNights = currentBooking.getNights() + next.totalNights;
        }

        if (includeCurrent.totalProfit.compareTo(excludeCurrent.totalProfit) > 0) {
            return includeCurrent;
        } else {
            return excludeCurrent;
        }
    }

    private BigDecimal calculateProfitPerNight(Booking booking) {
        return booking.getSellingRate().multiply(BigDecimal.valueOf(booking.getMargin()).divide(BigDecimal.valueOf(100)))
                .divide(BigDecimal.valueOf(booking.getNights()), 2, BigDecimal.ROUND_HALF_UP);
    }

    private List<Booking> findNonOverlappingBookings(List<Booking> bookingRequests) {
        List<Booking> nonOverlappingBookings = new ArrayList<>();

        bookingRequests.sort(Comparator.comparing(Booking::getCheckIn));

        Booking lastBooking = null;
        for (Booking currentBooking : bookingRequests) {
            if (lastBooking == null || lastBooking.getCheckOut().isBefore(currentBooking.getCheckIn())) {
                nonOverlappingBookings.add(currentBooking);
                lastBooking = currentBooking;
            }
        }

        return nonOverlappingBookings;
    }
}
