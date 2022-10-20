package com.example.consumer.mapper;

import com.example.consumer.dto.TripWayPointDto;
import com.example.consumer.entity.TripWayPoint;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripWayPointMapper {

    public TripWayPointDto entityToDto(TripWayPoint tripWaypoint) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(tripWaypoint, TripWayPointDto.class);
    }

    public TripWayPoint dtoToEntity(TripWayPointDto tripWaypointDto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(tripWaypointDto, TripWayPoint.class);
    }

    public List<TripWayPointDto> entityToDto(List<TripWayPoint> tripWaypoints) {
        return tripWaypoints.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<TripWayPoint> dtoToEntity(List<TripWayPointDto> tripWaypointDtos) {
        return tripWaypointDtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
