package org.main.booking.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Booking {
    private String requestId;
    private LocalDate checkIn;
    private int nights;
    private BigDecimal sellingRate;
    private int margin;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public BigDecimal getSellingRate() {
        return sellingRate;
    }

    public void setSellingRate(BigDecimal sellingRate) {
        this.sellingRate = sellingRate;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public LocalDate getCheckOut() {return checkIn.plusDays(nights); }
}
