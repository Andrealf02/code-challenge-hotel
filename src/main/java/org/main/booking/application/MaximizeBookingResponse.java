package org.main.booking.application;

import java.math.BigDecimal;
import java.util.List;

public class MaximizeBookingResponse {
    private List<String> requestIds;
    private BigDecimal totalProfit;
    private BigDecimal avgNight;
    private BigDecimal minNight;
    private BigDecimal maxNight;

    public MaximizeBookingResponse(List<String> requestIds, BigDecimal totalProfit, BigDecimal avgNight,
                                   BigDecimal minNight, BigDecimal maxNight) {
        this.requestIds = requestIds;
        this.totalProfit = totalProfit;
        this.avgNight = avgNight;
        this.minNight = minNight;
        this.maxNight = maxNight;
    }

    public List<String> getRequestIds() {
        return requestIds;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
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
}
