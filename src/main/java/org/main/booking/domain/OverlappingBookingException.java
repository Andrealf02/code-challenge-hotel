package org.main.booking.domain;

public class OverlappingBookingException extends RuntimeException {
    public OverlappingBookingException(String message) {
        super(message);
    }
}

