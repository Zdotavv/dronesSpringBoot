package com.zdota.dronesspringboot.web;

import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.dto.DroneDeleteDto;
import com.zdota.dronesspringboot.dto.DroneDto;
import com.zdota.dronesspringboot.service.DroneService;
//import com.zdota.dronesspringboot.util.config.DroneConverter;
import com.zdota.dronesspringboot.util.config.DroneMapper;
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
public class DroneController {

    private final DroneService droneService;
//    private final DroneConverter converter;
    @PostMapping("/drones")
    @ResponseStatus(value =HttpStatus.CREATED,reason = "Drone Created")

    public DroneDto createDrone(@RequestBody @Valid DroneDto droneForSave){
        Drone drone = DroneMapper.INSTANCE.droneDtoToDrone(droneForSave);
        return DroneMapper.INSTANCE.droneToDroneDto(drone);
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
        DroneDto dto=DroneMapper.INSTANCE.droneToDroneDto(drone);
        log.debug("getById() Controller - end: name = {}", dto.name);
        return dto;
    }

    @PutMapping("/drones/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DroneDto updateDroneById(@PathVariable ("id")  Integer id, @RequestBody @Valid DroneDto droneForUpdate) {
        log.debug("updateDroneById() Controller - start: id = {}", id);
        Drone drone=DroneMapper.INSTANCE.droneDtoToDrone(droneForUpdate);
        log.debug("updateDroneById() Controller - end: id = {}", id);
return DroneMapper.INSTANCE.droneToDroneDto(drone);
//        return converter.toDto(droneService.updateById(id, drone));
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


    @GetMapping("/drones/needUpgrade")
    @ResponseStatus(HttpStatus.OK)
    public List<Drone> viewAllNeedUpgrade() {
        return droneService.viewAllNeedUpgrade();
    }

    @GetMapping(value = "/drones", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
        public DroneDto findDroneByName(String name) {
        log.debug("findDroneByName() Controller - start: name = {}", name);
        DroneDto dto=DroneMapper.INSTANCE.droneToDroneDto(droneService.findDroneByName(name));
        log.debug("findPlaneByName() Controller - end: id = {}", droneService.findDroneByName(name).getId());
        return dto;
    }


    @PatchMapping(value = "/drones/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public DroneDto updateDate(@RequestParam("dateTime")
                           @DateTimeFormat() String ldc,
                           @PathVariable Integer id) {
        var localDateTime = LocalDateTime.parse(ldc, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        droneService.updateDate(id, localDateTime);
        return DroneMapper.INSTANCE.droneToDroneDto(droneService.viewById(id));
    }
    @PatchMapping("/drones/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
        public DroneDeleteDto removeDrone(@PathVariable Integer id) {
DroneDeleteDto droneDeleteDto=DroneMapper.INSTANCE.droneToDroneDeleteDto(droneService.viewById(id));
        droneService.removeById(id);
        return droneDeleteDto;
    }


}
