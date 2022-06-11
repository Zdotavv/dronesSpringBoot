package com.zdota.dronesspringboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;

public class DroneDto {

    public Integer id;

    @NotNull(message = "Name must not be null")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    public String name;

    @NotNull
    @Size(min = 2, max = 32, message = "Country must be between 2 and 32 characters long")
    public String country;

    public double weight;
    public double maxLoadCapacity;
    public int flightDuration;
    public double maxHeight;
    private int maxSpeed;

    @NotNull(message = "field isFighter must be filled")
    public boolean isFighter;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime produceDate;
    @JsonIgnore
    public Boolean isDeleted = Boolean.FALSE;
}
