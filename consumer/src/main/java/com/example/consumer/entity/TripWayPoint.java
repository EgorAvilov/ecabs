package com.example.consumer.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "trip_way_points")
public class TripWayPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "locality")
    private String locality;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
}
