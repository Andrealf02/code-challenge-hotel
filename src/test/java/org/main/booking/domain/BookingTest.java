package org.main.booking.domain;

import junit.framework.TestCase;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingTest extends TestCase {
    public void testGetCheckOut() {
        // Arrange
        Booking booking = new Booking();
        booking.setCheckIn(LocalDate.parse("2022-01-01"));
        booking.setNights(3);

        // Act
        LocalDate checkOutDate = booking.getCheckOut();

        // Assert
        assertEquals(LocalDate.parse("2022-01-04"), checkOutDate);
    }

    public void testGetCheckOutWithZeroNights() {
        // Arrange
        Booking booking = new Booking();
        booking.setCheckIn(LocalDate.parse("2022-01-01"));
        booking.setNights(0);

        // Act
        LocalDate checkOutDate = booking.getCheckOut();

        // Assert
        assertEquals(LocalDate.parse("2022-01-01"), checkOutDate);
    }

    public void testGetCheckOutWithNegativeNights() {
        // Arrange
        Booking booking = new Booking();
        booking.setCheckIn(LocalDate.parse("2022-01-01"));
        booking.setNights(-2);

        // Act
        LocalDate checkOutDate = booking.getCheckOut();

        // Assert
        assertEquals(LocalDate.parse("2021-12-30"), checkOutDate);
    }

    public void testSetAndGetRequestId() {
        // Arrange
        Booking booking = new Booking();
        String requestId = "test_request_id";

        // Act
        booking.setRequestId(requestId);
        String retrievedRequestId = booking.getRequestId();

        // Assert
        assertEquals(requestId, retrievedRequestId);
    }

    public void testSetAndGetSellingRate() {
        // Arrange
        Booking booking = new Booking();
        BigDecimal sellingRate = new BigDecimal("150.50");

        // Act
        booking.setSellingRate(sellingRate);
        BigDecimal retrievedSellingRate = booking.getSellingRate();

        // Assert
        assertEquals(sellingRate, retrievedSellingRate);
    }
}