package com.example.consumer.mapper;


import com.example.consumer.entity.Booking;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BookingMapperInterface {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBooking(Booking bookingDto, @MappingTarget Booking persistBooking);
}
