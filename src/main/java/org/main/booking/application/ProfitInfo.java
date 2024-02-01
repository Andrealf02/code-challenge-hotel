package org.main.booking.application;

import java.math.BigDecimal;
import java.util.List;

public class ProfitInfo {
    public BigDecimal totalProfit;
    public List<String> requestIds;
    public int totalNights;

    public ProfitInfo(BigDecimal totalProfit, List<String> requestIds, int totalNights) {
        this.totalProfit = totalProfit;
        this.requestIds = requestIds;
        this.totalNights = totalNights;
    }
}
