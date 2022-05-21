package com.zdota.dronesspringboot.service;

import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.repository.DroneRepository;
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
        return droneRepository.save(drone);
    }

//    @Override
//    public List<Drone> viewAll() {
//        return droneRepository.findAll();
//    }

    @Override
    public Drone viewById(Integer id) {
        Drone drone = checkDrone(id);
        checkDeleted(drone);
        return checkDrone(id);
    }
           private void checkDeleted(Drone drone){
            if (drone.getDeleted()==null || drone.getDeleted()) {
                throw new EntityNotFoundException("Drone was deleted");
            }
    }

    private Drone checkDrone(Integer id) {
        return droneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Drone not found with id = " + id));
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
        Drone drone =checkDrone(id);
       drone.setDeleted(Boolean.TRUE);
        droneRepository.save(drone);
    }

    @Override
    public void removeAll() {
        droneRepository.deleteAll();
    }

    @Override
    public Collection<Drone> findDroneByName(String name) {
        log.info("findDroneByName() - start: name = {}", name);
        Collection<Drone> collection = droneRepository.findByName(name);
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
    public Collection<Drone> findDroneByFighter() {
        log.info("findDroneByFighter() - start");
        Collection<Drone> collection = droneRepository.findByFighter();
        log.info("findDroneByFighter() - end: collection = {}", collection);
        return collection;
    }

    @Override
    public Collection<Drone> findDroneByNoFighter() {
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
            if (drone.getProduceDate().isBefore(LocalDateTime.of(2020, 1, 1, 0, 0))) {
                drones.add(drone);
            }
        }
        return drones;
    }

//    @Override
//    public void updateDate(Integer id, LocalDateTime dateTime) {
//        log.info("updateDateTime() - start");
//        droneRepository.updateDrone(id, dateTime);
//        log.info("updateDateTime() - end");
//    }

    @Override
    public Collection<Drone> findDroneByUsa() {
        log.info("findDroneByUSA() - start");
        Collection<Drone> collection = droneRepository.findByUsa();
        log.info("findDroneByUSA() - end: collection = {}", collection);
        return collection;
    }
    @Override
    public Collection<Drone> findAllByDeletedIsFalse() {
        log.info("findAllByDeletedIsFalse() - start");
        Collection<Drone> collection = droneRepository.findAllByDeletedIsFalse();
        log.info("findAllByDeletedIsFalse() - end: collection = {}", collection);
        return collection;

    }

}

