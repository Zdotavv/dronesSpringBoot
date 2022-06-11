package com.zdota.dronesspringboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

public class DroneDeleteDto {
//    @JsonIgnore
//    @NotNull
//    public Integer id;

    public String message = String.format("Drone with this id was deleted successfully");
}
