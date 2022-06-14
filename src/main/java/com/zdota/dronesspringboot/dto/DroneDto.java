package com.zdota.dronesspringboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;

public class DroneDto {

    public Integer id;

    @NotNull(message = "Name must not be null")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of an drone.", example = "Bayraktar", required = true)
    public String name;

    @NotNull
    @Size(min = 2, max = 32, message = "Country must be between 2 and 32 characters long")
    @Schema(description = "Name of country.", example = "Ukraine")
    public String country;
    @Schema(description = "weigh of drone(kg).", example = "400.0")
    public double weight;
    @Schema(description = "Maximum weight of bombs that can capacity the drone(kg).", example = "100.0")
    public double maxLoadCapacity;
    @Schema(description = "Maximum duration of flight (min).", example = "60")
    public int flightDuration;
    @Schema(description = "Maximum height of flight the drone(m).", example = "2500.0")
    public double maxHeight;
    @Schema(description = "Maximum speed of drone(km/h)", example = "350")
    public int maxSpeed;

    @NotNull
    @Schema(description = "Flag which should be true if drone is fighter", required = true)
    public boolean isFighter;

    @Schema(description = "Date and time of plane creation", required = true, pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime produceDate;
    @JsonIgnore
    @Schema(hidden = true)
    public Boolean isDeleted = Boolean.FALSE;
}
