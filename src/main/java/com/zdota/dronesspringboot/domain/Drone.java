package com.zdota.dronesspringboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="drones")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id",nullable = false)
    @Schema(description = "id of drone", pattern = "sequence")
    private Integer id;
    @Schema(description = "Name of drone.", example = "Bayraktar", required = true)
    private String name;
    @Schema(description = "Name of country.", example = "Ukraine")
    private String country;
    @Schema(description = "weigh of drone(kg).", example = "400.0")
    private double weight;
    @Schema(description = "Maximum weight of bombs that can capacity the drone(kg).", example = "100.0")
    private double maxLoadCapacity;
    @Schema(description = "Maximum duration of flight (min).", example = "60")
    private int flightDuration;
    @Schema(description = "Maximum height of flight the drone(m).", example = "2500.0")
    private double maxHeight;
    @Schema(description = "Maximum speed of drone(km/h)", example = "350")
    private int maxSpeed;
    @Schema(description = "Flag which should be set if drone is fighter", allowableValues = {"true", "false"}, name = "Fighter")
    private boolean isFighter;
    @JsonIgnore
//    @Column(name = "is_deleted")
//    private boolean isDeleted = Boolean.FALSE;
    @Schema(hidden = true)
    private Boolean isDeleted= Boolean.FALSE;
    @Schema(description = "Date and time of plane creation", required = true, pattern = "yyyy-MM-dd HH:mm")
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
    public String   toString() {
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
