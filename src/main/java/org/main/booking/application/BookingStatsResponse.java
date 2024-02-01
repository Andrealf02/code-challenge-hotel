package org.main.booking.application;

import org.main.booking.domain.Booking;

import java.math.BigDecimal;
import java.util.List;

public class BookingStatsResponse {
    private BigDecimal avgNight;
    private BigDecimal minNight;
    private BigDecimal maxNight;

    public BookingStatsResponse(BigDecimal avgNight, BigDecimal minNight, BigDecimal maxNight) {
        this.avgNight = avgNight;
        this.minNight = minNight;
        this.maxNight = maxNight;
    }

    public static BookingStatsResponse emptyStats() {
        return new BookingStatsResponse(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public BigDecimal getAvgNight() {
        return avgNight;
    }

    public BigDecimal getMinNight() {
        return minNight;
    }

    public BigDecimal getMaxNight() {
        return maxNight;
    }

    public BigDecimal getMaxProfit() {
        return BigDecimal.ZERO;
    }

    public BigDecimal getMinProfit() {
        return BigDecimal.ZERO;
    }

    public BigDecimal getAverageProfit() {
        return BigDecimal.ZERO;
    }

    private BigDecimal calculateProfit(Booking booking) {
        return booking.getSellingRate().multiply(BigDecimal.valueOf(booking.getMargin()))
                .divide(BigDecimal.valueOf(booking.getNights()), 2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal calculateTotalProfit(List<Booking> bookings) {
        return bookings.stream()
                .map(this::calculateProfit)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
