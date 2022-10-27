package com.example.consumer.service.impl;

import com.example.consumer.dto.BookingDto;
import com.example.consumer.entity.Booking;
import com.example.consumer.mapper.BookingMapper;
import com.example.consumer.mapper.BookingMapperInterface;
import com.example.consumer.repository.BookingRepository;
import com.example.consumer.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final BookingMapperInterface bookingMapperInterface;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper, BookingMapperInterface bookingMapperInterface) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.bookingMapperInterface = bookingMapperInterface;
    }

    @Override
    public String create(String message) throws IOException {
        Booking booking = bookingMapper.toJavaObject(message);
        booking.setCreatedOn(LocalDateTime.now());
        booking.setLastModifiedOn(LocalDateTime.now());
        booking = bookingRepository.save(booking);
        BookingDto bookingDto = bookingMapper.entityToDto(booking);
        return bookingMapper.toJSON(bookingDto);
    }

    @Override
    @Transactional
    public String update(String message) throws IOException {
        Booking booking = bookingMapper.toJavaObject(message);
        Booking persistBooking=bookingRepository.getById(booking.getId());
        bookingMapperInterface.updateBooking(booking, persistBooking);
        persistBooking.setLastModifiedOn(LocalDateTime.now());
        persistBooking = bookingRepository.save(persistBooking);
        BookingDto bookingDto = bookingMapper.entityToDto(persistBooking);
        return bookingMapper.toJSON(bookingDto);

    }

    @Override
    public void delete(String message) throws IOException {
        Long bookingId=bookingMapper.toLong(message);
        if (bookingRepository.existsById(bookingId)) {
            bookingRepository.deleteById(bookingId);
        }
    }

    @Override
    public String getAll() throws IOException {
        return null;
    }

    @Override
    public String getById(String message) throws IOException {
        return null;
    }
}
