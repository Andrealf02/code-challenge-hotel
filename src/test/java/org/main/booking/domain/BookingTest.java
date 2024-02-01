package org.main.booking.domain;

import junit.framework.TestCase;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingTest extends TestCase {
    public void testGetCheckOut() {
        Booking booking = new Booking();
        booking.setCheckIn(LocalDate.parse("2022-01-01"));
        booking.setNights(3);

        LocalDate checkOutDate = booking.getCheckOut();

        assertEquals(LocalDate.parse("2022-01-04"), checkOutDate);
    }

    public void testGetCheckOutWithZeroNights() {
        Booking booking = new Booking();
        booking.setCheckIn(LocalDate.parse("2022-01-01"));
        booking.setNights(0);

        LocalDate checkOutDate = booking.getCheckOut();

        assertEquals(LocalDate.parse("2022-01-01"), checkOutDate);
    }

    public void testGetCheckOutWithNegativeNights() {
        Booking booking = new Booking();
        booking.setCheckIn(LocalDate.parse("2022-01-01"));
        booking.setNights(-2);

        LocalDate checkOutDate = booking.getCheckOut();

        assertEquals(LocalDate.parse("2021-12-30"), checkOutDate);
    }

    public void testSetAndGetRequestId() {
        Booking booking = new Booking();
        String requestId = "test_request_id";

        booking.setRequestId(requestId);
        String retrievedRequestId = booking.getRequestId();

        assertEquals(requestId, retrievedRequestId);
    }

    public void testSetAndGetSellingRate() {
        Booking booking = new Booking();
        BigDecimal sellingRate = new BigDecimal("150.50");

        booking.setSellingRate(sellingRate);
        BigDecimal retrievedSellingRate = booking.getSellingRate();

        assertEquals(sellingRate, retrievedSellingRate);
    }
}