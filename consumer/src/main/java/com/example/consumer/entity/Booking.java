package com.example.consumer.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "passenger_name", nullable = false)
    private String passengerName;

    @Column(name = "passenger_contact_number", nullable = false)
    private String passengerContactNumber;

    @Column(name = "pickup_time",nullable = false)
    private LocalDateTime pickupTime;

    @Column(name = "asap", nullable = false)
    private Boolean asap;

    @Column(name = "waiting_time", nullable = false)
    private LocalDateTime waitingTime;

    @Column(name = "number_of_passengers", nullable = false)
    private Integer numberOfPassengers;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "last_modified_on", nullable = false)
    private LocalDateTime lastModifiedOn;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE},  fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private List<TripWayPoint> tripWayPoints;

}
