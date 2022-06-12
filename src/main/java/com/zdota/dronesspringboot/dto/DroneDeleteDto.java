package com.zdota.dronesspringboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

public class DroneDeleteDto {

@Schema(description = "This is message that should be shown when user deletes plane")
    public String message = "Drone with this id was deleted successfully";

}
