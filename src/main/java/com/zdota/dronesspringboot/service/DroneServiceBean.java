package com.zdota.dronesspringboot.service;

import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.repository.DroneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class DroneServiceBean implements DroneService {

    private final DroneRepository droneRepository;

    @Override
    public Drone create(Drone drone) {
        return droneRepository.save(drone);
    }

    @Override
    public List<Drone> viewAll() {
        return droneRepository.findAll();
    }

    @Override
    public Drone viewById(Integer id) {
        return droneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found with id: " + id));
    }

    @Override
    public Drone updateById(Integer id, Drone drone) {
        return droneRepository.findById(id)
                .map(entity -> {
                    entity.setName(drone.getName());
                    entity.setCountry(drone.getCountry());
                    entity.setWeight(drone.getWeight());
                    entity.setMaxLoadCapacity(drone.getMaxLoadCapacity());
                    entity.setFlightDuration(drone.getFlightDuration());
                    entity.setMaxHeight(drone.getMaxHeight());
                    entity.setMaxSpeed(drone.getMaxSpeed());
                    entity.setFighter(drone.isFighter());
                    entity.setProduceDate(drone.getProduceDate());
                    return droneRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Bomb not found with id = " + id));
    }

    @Override
    public void removeById(Integer id) {
        droneRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        droneRepository.deleteAll();
    }
   @Override
    public Collection<Drone> findDroneByName(String name) {
       log.info("findDroneByName() - start: name = {}", name);
        Collection<Drone> collection=droneRepository.findByName(name);
       log.info("findDroneByName() - end: collection = {}", collection);
        return collection;
    }
    @Override
    public Collection<Drone> findDroneByFlightDuration(int flightDuration) {
        log.info("findDroneByFlightDuration() - start: flightDuration = {}", flightDuration);
        Collection<Drone> collection = droneRepository.findByFlightDuration(flightDuration);
        log.info("findDroneByFlightDuration() - end: collection = {}", collection);
        return collection;
    }
    @Override
    public Collection<Drone>findDroneByFighter() {
        log.info("findDroneByFighter() - start");
        Collection<Drone> collection = droneRepository.findByFighter();
        log.info("findDroneByFighter() - end: collection = {}", collection);
        return collection;
    }
    @Override
    public Collection<Drone>findDroneByNoFighter() {
        log.info("findDroneByNoFighter() - start");
        Collection<Drone> collection = droneRepository.findByNoFighter();
        log.info("findDroneByNoFighter() - end: collection = {}", collection);
        return collection;
    }
    @Override
    public boolean isFighter(Integer id) {
        return droneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found with id: " + id)).isFighter();

    }

//    @Override
//    public List<Drone> viewAllFighter() {
//        List<Drone> allDrones = droneRepository.findAll();
//        List<Drone> drones = new ArrayList<>();
//        for (Drone drone : allDrones) {
//            if (drone.isFighter()) {
//                drones.add(drone);
//            }
//        }
//        return drones;
//    }


//    @Override
//    public List<Drone> viewAllNotFighter() {
//        List<Drone> allDrones = droneRepository.findAll();
//        List<Drone> drones = new ArrayList<>();
//        for (Drone drone : allDrones) {
//            if (!drone.isFighter()) {
//                drones.add(drone);
//            }
//        }
//        return drones;
//    }

    @Override
    public List<Drone> viewAllNeedUpgrade() {
        List<Drone> allDrones = droneRepository.findAll();
        List<Drone> drones = new ArrayList<>();
        for (Drone drone : allDrones) {
            if (drone.getProduceDate().isBefore(LocalDate.of(2020, 1, 1))) {
                drones.add(drone);
            }
        }
        return drones;
    }


    }

