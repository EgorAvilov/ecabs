package com.example.producer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("passengerName")
    @NotBlank(message = "Passenger name is mandatory")
    private String passengerName;

    @JsonProperty("passengerContactNumber")
    @NotBlank(message = "Passenger contact number is mandatory")
    private String passengerContactNumber;

    @JsonProperty("pickupTime")
    @NotNull(message = "Pick up time is mandatory")
    private LocalDateTime pickupTime;

    @JsonProperty("asap")
    @NotNull(message = "Asap is mandatory")
    private Boolean asap;

    @JsonProperty("waitingTime")
    @NotNull(message = "Waiting time is mandatory")
    private LocalDateTime waitingTime;

    @JsonProperty("numberOfPassengers")
    @NotNull(message = "Number of passengers is mandatory")
    private Integer numberOfPassengers;

    @JsonProperty("price")
    @NotNull(message = "Price is mandatory")
    private BigDecimal price;

    @JsonProperty("rating")
    @NotNull(message = "Rating is mandatory")
    private Double rating;

    @JsonProperty("createdOn")
    private LocalDateTime createdOn;

    @JsonProperty("lastModifiedOn")
    private LocalDateTime lastModifiedOn;

    @JsonProperty("tripWayPoints")
    @NotNull(message = "Trip waypoints are mandatory")
    private List<TripWayPointDto> tripWayPoints;
}
