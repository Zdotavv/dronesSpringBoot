package com.zdota.dronesspringboot.web;

import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.dto.DroneDeleteDto;
import com.zdota.dronesspringboot.dto.DroneDto;
import com.zdota.dronesspringboot.dto.OperatorDto;
import com.zdota.dronesspringboot.service.DroneService;
import com.zdota.dronesspringboot.util.config.DroneConverter;
import com.zdota.dronesspringboot.util.config.OperatorConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class DroneController implements DroneControllerSwagger {

    private final DroneService droneService;
    private final DroneConverter droneConverter;
    private final OperatorConverter operatorConverter;
    @PostMapping("/drones")
    @ResponseStatus(value =HttpStatus.CREATED,reason = "Drone Created")
//    public Drone createDrone(@RequestBody @Valid Drone drone) {
//        return droneService.create(drone);
    public DroneDto createDrone(@RequestBody @Valid DroneDto droneForSave){
        var drone =droneConverter.getMapperFacade().map(droneForSave, Drone.class);
        var dto = droneConverter.toDto(droneService.create(drone));
        return dto;
    }


    @GetMapping("/drones")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Drone>findAllByDeletedIsFalse(){
        return droneService.findAllByDeletedIsFalse();
//    public List<Drone> viewAllDrones() {
//        return droneService.viewAll();
    }

    @GetMapping("/drones/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DroneDto viewDroneById(@PathVariable Integer id) {
        log.debug("viewById() Controller - start: id = {}", id);
        var drone=droneService.viewById(id);
        log.debug("viewById() Controller - to dto start: id = {}", id);
        var dto = droneConverter.toDto(drone);
        log.debug("getById() Controller - end: name = {}", drone.getName());
        return dto;
    }
    @DeleteMapping("/drones")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllDrones() {
        droneService.removeAll();
    }
    @PutMapping("/drones/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DroneDto updateDroneById(@PathVariable ("id")  Integer id, @RequestBody @Valid DroneDto droneForUpdate) {
        log.debug("updateDroneById() Controller - start: id = {}", id);
        var drone = droneConverter.getMapperFacade().map(droneForUpdate, Drone.class);
        log.debug("updateDroneById() Controller - end: id = {}", id);
        return droneConverter.toDto(droneService.updateById(id, drone));
    }


    @GetMapping("/drones/{id}/fighter")
    @ResponseStatus(HttpStatus.OK)
    public boolean droneIsFighter(@PathVariable Integer id) {
        return droneService.isFighter(id);
    }

    @GetMapping("/drones/fighters")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Drone> findDroneByFighter() {

        return droneService.findDroneByFighter();
    }


//    @GetMapping("/drones/notFighter")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Drone> getAllNotFighter() {
//        return droneService.viewAllNotFighter();
//    }

    @GetMapping("/drones/needUpgrade")
    @ResponseStatus(HttpStatus.OK)
    public List<Drone> viewAllNeedUpgrade() {
        return droneService.viewAllNeedUpgrade();
    }

    @GetMapping(value = "/drones", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public DroneDto findDroneByName(String name) {
        log.debug("findDroneByName() Controller - start: name = {}", name);
        var dto =droneConverter.toDto(droneService.findDroneByName(name));
        log.debug("findPlaneByName() Controller - end: id = {}", droneService.findDroneByName(name).getId());
        return dto;
    }

//    @GetMapping(value = "/drones", params = {"flightDuration"})
//    @ResponseStatus(HttpStatus.OK)
//    public Collection<Drone> findDroneByFlightDuration(int flightDuration) {
//        return droneService.findDroneByFlightDuration(flightDuration);.

//    }

    @PatchMapping(value = "/drones/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public DroneDto updateDate(@RequestParam("dateTime")
                           @DateTimeFormat() String ldc,
                           @PathVariable Integer id) {
        var localDateTime = LocalDateTime.parse(ldc, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        log.debug("updateDate() Controller - finished parsing of datetime: datetime = {}", localDateTime);
        droneService.updateDate(id, localDateTime);
        return droneConverter.toDto(droneService.viewById(id));
    }
    @PatchMapping("/drones/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public DroneDeleteDto removeDrone(@PathVariable Integer id) {
        var droneToReturn=droneConverter.toDeleteDto(droneService.viewById(id));
        droneService.removeById(id);
        return droneToReturn;
    }

    @PutMapping(value = "/drones/{id}/operator")
    @Override
    public DroneDto addMainOperator(@PathVariable Integer id, @RequestBody OperatorDto mainOperator) {
        log.debug("addMainOperator() Controller - start: id = {}, operator = {}", id, mainOperator);
        var operator = operatorConverter.fromDto(mainOperator);
        var drone = droneService.addMainOperator(id, operator);
        var dto = droneConverter.toDto(drone);
        log.debug("addMainOperator() Controller - end: id = {}, drone = {}", id, drone);
        return dto;
    }

    @GetMapping(value = "/operators/{id}")
    @Override
    public OperatorDto getOperatorByDroneId(@PathVariable Integer id) {
        log.debug("getOperatorByDroneId() Controller - start: id of drone = {}", id);
        var operator = droneService.getOperatorByDroneId(id);
        log.debug("getOperatorByDroneId() Controller - got operator = {}", operator);
        var dto = operatorConverter.toDto(operator);
        log.debug("getOperatorByDroneId() Controller - end : dto = {}", dto);
        return dto;
    }

}

