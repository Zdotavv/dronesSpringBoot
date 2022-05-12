package com.zdota.dronesspringboot.service;

import com.zdota.dronesspringboot.domain.Drone;

import java.util.List;

public interface DroneService {
    Drone create(Drone drone);

    List<Drone> viewAll();

    Drone viewById(Integer id);

    Drone updateById(Integer id, Drone bomb);

    void removeById(Integer id);

    void removeAll();

    boolean isFighter(Integer id);

    List<Drone> viewAllFighter();
    List<Drone> viewAllNotFighter();
    List<Drone> viewAllNeedUpgrade();
}
