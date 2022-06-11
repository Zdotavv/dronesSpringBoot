package com.zdota.dronesspringboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DroneDeleteDto {
//    @JsonIgnore
//    public Integer id;

    public String message = String.format("Drone with this id was deleted");

}
