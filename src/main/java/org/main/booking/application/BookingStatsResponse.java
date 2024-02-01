package org.main.booking.application;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
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

}
