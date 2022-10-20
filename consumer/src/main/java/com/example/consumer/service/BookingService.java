package com.example.consumer.service;

import java.io.IOException;

public interface BookingService {
    String createBooking(String message) throws IOException;
    String updateBooking(String message) throws IOException;
    void deleteBooking(String message) throws IOException;
}
