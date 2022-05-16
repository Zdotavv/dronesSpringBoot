package com.zdota.dronesspringboot.web;

import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.service.DroneService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class DroneController {

    private final DroneService droneService;


    @PostMapping("/drones")
    @ResponseStatus(HttpStatus.CREATED)
    public Drone createDrone(@RequestBody Drone drone){
        return droneService.create(drone);
    }

    @GetMapping("/drones")
    @ResponseStatus(HttpStatus.OK)
    public List<Drone> viewAllDrones() {
        return droneService.viewAll();
    }

    @GetMapping("/drones/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Drone viewDroneById(@PathVariable Integer id) {
        return droneService.viewById(id);
    }
    @PutMapping("/drones/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Drone updateDroneById(@PathVariable("id") Integer id, @RequestBody Drone bomb) {
        return droneService.updateById(id, bomb);
    }
    @DeleteMapping("/drones/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeDroneById(@PathVariable Integer id) {
        droneService.removeById(id);
    }

    @DeleteMapping("/drones")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllDrones() {
        droneService.removeAll();
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
    @GetMapping("/drones/noFighters")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Drone> findDroneByNoFighter() {
        return droneService.findDroneByNoFighter();
    }

//    @GetMapping("/drones/allFighter")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Drone> getAllFighter() {
//        return droneService.viewAllFighter();
//    }

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
    public Collection<Drone> findDroneByName(String name) {
            return droneService.findDroneByName(name);
        }
    @GetMapping(value = "/drones", params = {"flightDuration"})
    @ResponseStatus(HttpStatus.OK)
    public Collection<Drone> findDroneByFlightDuration(int flightDuration) {
        return droneService.findDroneByFlightDuration(flightDuration);
    }
}
