package org.main.booking.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingFactory {
        public static Booking createBooking(String requestId, LocalDate checkIn, int nights, BigDecimal sellingRate, int margin) {
            Booking booking = new Booking();
            booking.setRequestId(requestId);
            booking.setCheckIn(checkIn);
            booking.setNights(nights);
            booking.setSellingRate(sellingRate);
            booking.setMargin(margin);
            return booking;
        }

}

