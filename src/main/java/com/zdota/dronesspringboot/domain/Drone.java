package com.zdota.dronesspringboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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
    @JsonIgnore
//    @Column(name = "is_deleted")
//    private boolean isDeleted = Boolean.FALSE;
    private Boolean isDeleted= Boolean.FALSE;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime produceDate;

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
    @JsonIgnore
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(LocalDateTime produceDate) {
        this.produceDate = produceDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drone)) return false;
        Drone drone = (Drone) o;
        return Double.compare(drone.getWeight(), getWeight()) == 0 && Double.compare(drone.getMaxLoadCapacity(), getMaxLoadCapacity()) == 0 && getFlightDuration() == drone.getFlightDuration() && Double.compare(drone.getMaxHeight(), getMaxHeight()) == 0 && getMaxSpeed() == drone.getMaxSpeed() && isFighter() == drone.isFighter() && getId().equals(drone.getId()) && getName().equals(drone.getName()) && getCountry().equals(drone.getCountry()) && isDeleted.equals(drone.isDeleted) && getProduceDate().equals(drone.getProduceDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCountry(), getWeight(), getMaxLoadCapacity(), getFlightDuration(), getMaxHeight(), getMaxSpeed(), isFighter(), isDeleted, getProduceDate());
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", weight=" + weight +
                ", maxLoadCapacity=" + maxLoadCapacity +
                ", flightDuration=" + flightDuration +
                ", maxHeight=" + maxHeight +
                ", maxSpeed=" + maxSpeed +
                ", isFighter=" + isFighter +
                ", isDeleted=" + isDeleted +
                ", produceDate=" + produceDate +
                '}';
    }
}
