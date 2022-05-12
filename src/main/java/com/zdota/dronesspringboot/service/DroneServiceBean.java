package com.zdota.dronesspringboot.service;

import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.repository.DroneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
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
                    entity.setMaxFlightTime(drone.getMaxFlightTime());
                    entity.setMaxHeight(drone.getMaxHeight());
                    entity.setMaxSpeed(drone.getMaxSpeed());
                    entity.setFighter(drone.isFighter());
                    entity.setDate(drone.getDate());
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
    public boolean isFighter(Integer id) {
        return droneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found with id: " + id)).isFighter();

    }

    @Override
    public List<Drone> viewAllFighter() {
        List<Drone> allDrones = droneRepository.findAll();
        List<Drone> drones = new ArrayList<>();
        for (Drone drone : allDrones) {
            if (drone.isFighter()) {
                drones.add(drone);
            }
        }
        return drones;
    }

    @Override
    public List<Drone> viewAllNotFighter() {
        List<Drone> allDrones = droneRepository.findAll();
        List<Drone> drones = new ArrayList<>();
        for (Drone drone : allDrones) {
            if (!drone.isFighter()) {
                drones.add(drone);
            }
        }
        return drones;
    }

    @Override
    public List<Drone> viewAllNeedUpgrade() {
        List<Drone> allDrones = droneRepository.findAll();
        List<Drone> drones = new ArrayList<>();
        for (Drone drone : allDrones) {
            if (drone.getDate().isBefore(LocalDate.of(2020, 1, 1))) {
                drones.add(drone);
            }
        }
        return drones;
    }

    }

