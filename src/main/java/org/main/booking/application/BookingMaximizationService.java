package org.main.booking.application;

import org.main.booking.domain.Booking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookingMaximizationService {
    public MaximizeBookingResponse maximizeProfits(List<Booking> bookingRequests) {
        List<Booking> validBookings = findNonOverlappingBookings(bookingRequests);

        ProfitInfo profitInfo = maximizeProfitsHelper(validBookings, 0, Integer.MAX_VALUE);

        BigDecimal avgNight = profitInfo.totalProfit.divide(BigDecimal.valueOf(validBookings.size()), 2, BigDecimal.ROUND_HALF_UP);

        BigDecimal minNight = calculateMinOrMaxNight(validBookings, true);
        BigDecimal maxNight = calculateMinOrMaxNight(validBookings, false);

        return new MaximizeBookingResponse(profitInfo.requestIds, profitInfo.totalProfit, avgNight, minNight, maxNight);
    }

    private BigDecimal calculateMinOrMaxNight(List<Booking> validBookings, boolean isMin) {
        return validBookings.stream()
                .map(this::calculateProfitPerNight)
                .reduce(isMin ? BigDecimal.valueOf(Double.MAX_VALUE) : BigDecimal.valueOf(Double.MIN_VALUE),
                        isMin ? BigDecimal::min : BigDecimal::max);
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

        return includeCurrent.totalProfit.compareTo(excludeCurrent.totalProfit) > 0 ? includeCurrent : excludeCurrent;
    }

    private BigDecimal calculateProfitPerNight(Booking booking) {
        BigDecimal marginFactor = BigDecimal
                .valueOf(booking.getMargin())
                .divide(BigDecimal.valueOf(100));

        return booking.getSellingRate()
                .multiply(marginFactor)
                .divide(BigDecimal.valueOf(booking.getNights()),
                        2,
                        BigDecimal.ROUND_HALF_UP
                );
    }

    private List<Booking> findNonOverlappingBookings(List<Booking> bookingRequests) {
        return bookingRequests.stream()
                .sorted(Comparator
                        .comparing(Booking::getCheckIn)
                )
                .reduce(new ArrayList<>(),
                        this::accumulateNonOverlappingBookings,
                        this::combineNonOverlappingBookings
                );
    }

    private List<Booking> accumulateNonOverlappingBookings(List<Booking> accumulator, Booking currentBooking) {
        if (accumulator.isEmpty() || accumulator.get(accumulator.size() - 1).getCheckOut().isBefore(currentBooking.getCheckIn())) {
            accumulator.add(currentBooking);
        }
        return accumulator;
    }

    private List<Booking> combineNonOverlappingBookings(List<Booking> list1, List<Booking> list2) {
        list1.addAll(list2);
        return list1;
    }
}
