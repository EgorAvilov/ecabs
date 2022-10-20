package com.example.producer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TripWayPointDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("locality")
    private String locality;

    @JsonProperty("latitude")
    private Float latitude;

    @JsonProperty("longitude")
    private Float longitude;
}
