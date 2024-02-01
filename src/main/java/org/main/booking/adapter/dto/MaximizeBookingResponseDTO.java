package org.main.booking.adapter.dto;

import lombok.Getter;
import org.main.booking.application.MaximizeBookingResponse;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class MaximizeBookingResponseDTO {
    private List<String> requestIds;
    private BigDecimal totalProfit;
    private BigDecimal avgNight;
    private BigDecimal minNight;
    private BigDecimal maxNight;

    public MaximizeBookingResponseDTO(MaximizeBookingResponse maximizeBookingResponse) {
        this.requestIds = maximizeBookingResponse.getRequestIds();
        this.totalProfit = maximizeBookingResponse.getTotalProfit();
        this.avgNight = maximizeBookingResponse.getAvgNight();
        this.minNight = maximizeBookingResponse.getMinNight();
        this.maxNight = maximizeBookingResponse.getMaxNight();
    }
}

