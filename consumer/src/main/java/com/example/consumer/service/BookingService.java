package com.example.consumer.service;

import java.io.IOException;

public interface BookingService {
    String create(String message) throws IOException;
    String update(String message) throws IOException;
    void delete(String message) throws IOException;
}
