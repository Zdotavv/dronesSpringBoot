package com.zdota.dronesspringboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DroneDeleteDto {
    @JsonIgnore

    public String message = String.format("Plane with this id  was deleted successfully");
}
