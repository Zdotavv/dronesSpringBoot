package com.zdota.dronesspringboot.service;

import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.domain.Operator;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
@org.springframework.stereotype.Service

public interface DroneService {
    Drone create(Drone drone);

//    List<Drone> viewAll();

    Drone viewById(Integer id);

    Drone updateById(Integer id, Drone bomb);

    void removeById(Integer id);
    void removeAll();

    boolean isFighter(Integer id);

    Drone findDroneByName(String name);

    Collection<Drone>findDroneByFighter();

    List<Drone> viewAllNeedUpgrade();
    void updateDate(Integer id, LocalDateTime dateTime);
    Collection<Drone>findAllByDeletedIsFalse();

    Operator getOperatorByDroneId(Integer id);

    Drone addMainOperator(Integer id, Operator operator);

}
