package org.main.booking.adapter.dto;

import lombok.Getter;
import org.main.booking.application.BookingStatsResponse;

import java.math.BigDecimal;


@Getter
public class BookingStatsResponseDTO {
    private BigDecimal avgNight;
    private BigDecimal minNight;
    private BigDecimal maxNight;

    public BookingStatsResponseDTO(BookingStatsResponse bookingStatsResponse) {
        this.avgNight = bookingStatsResponse.getAvgNight();
        this.minNight = bookingStatsResponse.getMinNight();
        this.maxNight = bookingStatsResponse.getMaxNight();
    }
}

