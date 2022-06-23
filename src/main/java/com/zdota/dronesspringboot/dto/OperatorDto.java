package com.zdota.dronesspringboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OperatorDto {

//    @NotNull(message = "Field must not be null")
    @Schema(description = "First name of operator", example = "Alex", required = true)
    @Size(min = 2, max = 32, message = "First name of operator must be between 2 and 32 characters")
    public String firstName;

    @Schema(description = "Last name of operator", example = "Stone", required = true)
    @Size(min = 2, max = 32, message = "Last name of operator must be between 2 and 32 characters")
//    @NotNull(message = "Field must not be null")
    public String lastName;

    @Schema(description = "Callsign of operator", example = "Eagle", required = true)
//    @NotNull(message = "Field must not be null")
    public String callsign;

    @Schema(description = "Age of operator")
    public Integer age;


}
