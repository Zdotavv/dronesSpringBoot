package com.zdota.dronesspringboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="drones")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id",nullable = false)
    private Integer id;
    private String name;
    private String country;
    private double weight;
    private double maxLoadCapacity;
    private int flightDuration;
    private double maxHeight;

    private int maxSpeed;
    private boolean isFighter;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate produceDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getMaxLoadCapacity() {
        return maxLoadCapacity;
    }

    public void setMaxLoadCapacity(double maxLoadCapacity) {
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public int getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(int flightDuration) {
        this.flightDuration = flightDuration;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public boolean isFighter() {
        return isFighter;
    }

    public void setFighter(boolean fighter) {
        isFighter = fighter;
    }

    public LocalDate getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(LocalDate produceDate) {
        this.produceDate = produceDate;
    }
}
