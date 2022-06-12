package com.zdota.dronesspringboot.web;

import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.dto.DroneDeleteDto;
import com.zdota.dronesspringboot.dto.DroneDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;

public interface DroneControllerSwagger {
    @Operation(summary = "This is endpoint to add a new drone.", description = "Create request to add a new drone.", tags = {"Create drones"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new drone is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified plane request not found."),
            @ApiResponse(responseCode = "409", description = "Plane already exists")})
    DroneDto createDrone(DroneDto droneForSave);


    @Operation(summary = "This is endpoint to get all existing drones.", description = "Read request to get all drones which exist in database", tags = {"Find drones"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Here are your drones."),
            @ApiResponse(responseCode = "204", description = "Whoops, looks like there are no drones in database"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
            @ApiResponse(responseCode = "403", description = "Sorry, you don't have enough access rights to get all planes."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified drone request not found.")
    })
    Collection<Drone> findAllByDeletedIsFalse();


    @Operation(summary = "This is endpoint to get 1 existing drone by its id.", description = "Read request to get 1 drone by id", tags = {"Find drones"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Here is your drone."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Sorry, there are no drones with such id in database"),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
    })
    DroneDto viewDroneById(@PathVariable Integer id);


    @Operation(summary = "This is endpoint to update 1 existing drone by its id.", description = "Update request to change all fields of 1 drone by id", tags = {"Update drone"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Here is your updated drone."),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
            @ApiResponse(responseCode = "404", description = "Sorry, there are no planes with such id in database")
    })
    DroneDto updateDroneById(Integer id, DroneDto droneForUpdate);


    @Operation(summary = "This is endpoint to check 1 existing drone for is fighter.", description = "Read request to check drone fot is fighter from database by its id", tags = {"Check drones"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Drone with this id exist in database "),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
            @ApiResponse(responseCode = "404", description = "Sorry, there are no drones which are fighters in database")
    })
    boolean droneIsFighter(Integer id);


    @Operation(summary = "This is endpoint to find all existing drones which are fighters.", description = "Read request to get all drones from database which are fighters", tags = {"Find drones"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. All fighters drones here"),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
            @ApiResponse(responseCode = "404", description = "Sorry, there are no drones which are fighters in database")
    })
    Collection<Drone> findDroneByFighter();


    @Operation(summary = "This is endpoint to find all existing drones which are needs upgrade.", description = "Read request to get all drones from database which needs upgrade", tags = {"Find drones"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. All  drones here which are needs upgrade"),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
            @ApiResponse(responseCode = "404", description = "Sorry, there are no drones which needs upgrade in database")
    })
    List<Drone> viewAllNeedUpgrade();

    @Operation(summary = "This is endpoint to find 1 existing drone by its name.", description = "Read request to get 1 drone from database by its name", tags = {"Find drones"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Here is your drone"),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
            @ApiResponse(responseCode = "404", description = "Sorry, there are no drones with such id in database")
    })
    DroneDto findDroneByName(String name);


    @Operation(summary = "This is endpoint to update date and time of creation of 1 drone by its id.", description = "Update request to update date of creation of 1 drone from database by its id", tags = {"Update date"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Date of your drone was updated"),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
            @ApiResponse(responseCode = "404", description = "Sorry, the date not updated")
    })
    DroneDto updateDate(String ldc, Integer id);


    @Operation(summary = "This is endpoint to delete 1 existing drone by its id.", description = "Delete request to remove 1 drone from database by its id", tags = {"Delete"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Drone with this id was deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
            @ApiResponse(responseCode = "404", description = "Sorry, there are no Drones with such id in database")
    })
    DroneDeleteDto removeDrone(Integer id);
}

