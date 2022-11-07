package com.example.consumer.mapper;

import com.example.consumer.dto.BookingDto;
import com.example.consumer.entity.Booking;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BookingMapper {
    public BookingDto entityToDto(Booking booking) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(booking, BookingDto.class);
    }

    public String toJSON(BookingDto bookingDto) throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(bookingDto);
    }

    public Booking toJavaObject(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        return mapper.readValue(json, Booking.class);
    }

    public Long toLong(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        return mapper.readValue(json, Long.class);
    }
}
