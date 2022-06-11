package com.zdota.dronesspringboot.service;

import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.dto.DroneDto;
import com.zdota.dronesspringboot.repository.DroneRepository;
import com.zdota.dronesspringboot.util.exception.ResourceNotExistException;
import com.zdota.dronesspringboot.util.exception.ResourceNotFoundException;
import com.zdota.dronesspringboot.util.exception.ResourceWasDeletedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class DroneServiceBean implements DroneService {

    private final DroneRepository droneRepository;

    @Override
    public Drone create(Drone drone) {
        log.info("create() - start: drone = {}", drone);
        var saved=droneRepository.save(drone);
        log.info("create() - end: id = {}", saved.getId());
        return saved;
    }

//    @Override
//    public List<Drone> viewAll() {
//        return droneRepository.findAll();
//    }

    @Override
    public Drone viewById(Integer id) {
        log.info("viewById() - start: id = {}", id);
        var drone = checkDrone(id);
        log.debug("viewById()->checkDeleted() - start: id = {}", id);
        checkDeleted(drone);
//        log.info("viewById() - end: drone = {}", id);
        return checkDrone(id);
    }


    private Drone checkDrone(Integer id) {
        return droneRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
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
                .orElseThrow(() -> new EntityNotFoundException("Drone not found with id = " + id));
    }

    @Override
    public void removeById(Integer id) {
        log.info("removeById() - start: id = {}", id);
//        Drone drone =checkDrone(id);
        var drone = droneRepository.findById(id)
                .orElseThrow(ResourceNotExistException::new);
        log.info("removeById() -> checkDeleted() - start: id = {}", id);
        checkDeleted(drone);
        log.info("removeById() -> checkDeleted() - end: id = {}", id);
       drone.setDeleted(Boolean.TRUE);
        droneRepository.save(drone);
    }


    private void checkDeleted(Drone drone) {
        log.info("checkDeleted() - start: id = {}", drone.getId());
        if (drone.getDeleted() == null || drone.getDeleted()) {
            throw new ResourceWasDeletedException();

        }
    }

    @Override
    public Drone findDroneByName(String name) {
        log.info("findDroneByName() - start: name = {}", name);
        var drone = droneRepository.findByName(name);
        log.info("findDroneByName() - end: checkDeleted() = {}", drone.getId());
        checkDeleted(drone);
        return drone;
    }

//    @Override
//    public Collection<Drone> findDroneByFlightDuration(int flightDuration) {
//        log.info("findDroneByFlightDuration() - start: flightDuration = {}", flightDuration);
//        Collection<Drone> collection = droneRepository.findByFlightDuration(flightDuration);
//        log.info("findDroneByFlightDuration() - end: collection = {}", collection);
//        return collection;
//    }

    @Override
    public Collection<Drone> findDroneByFighter() {
        log.info("findDroneByFighter() - start");
        var collection = droneRepository.findByFighter();
        log.info("findDroneByFighter() - end: collection = {}", collection);
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
            if (drone.getProduceDate().isBefore(LocalDateTime.of(2020, 1, 1, 0, 0))) {
                drones.add(drone);
            }
        }
        return drones;
    }

    @Override
    public void updateDate(Integer id, LocalDateTime dateTime) {
        log.info("updateDateTime() - start");
        droneRepository.updateDrone(id, dateTime);
        log.info("updateDateTime() - end");
    }

    @Override
    public Collection<Drone> findAllByDeletedIsFalse() {
        log.info("findAllByDeletedIsFalse() - start");
        Collection<Drone> collection = droneRepository.findAllByDeletedIsFalse();
        log.info("findAllByDeletedIsFalse() - end: collection = {}", collection);
        return collection;

    }

}

