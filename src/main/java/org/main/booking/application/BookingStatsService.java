package org.main.booking.application;

import org.main.booking.domain.Booking;

import java.math.BigDecimal;
import java.util.List;

public class BookingStatsService {
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
}
