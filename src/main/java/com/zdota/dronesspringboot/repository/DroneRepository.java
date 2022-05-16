package com.zdota.dronesspringboot.repository;

import com.zdota.dronesspringboot.domain.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone,Integer> {
    List<Drone> findByName(String name);
    List<Drone> findByFlightDuration(int flightDuration);
    @Query("select p from Drone p where p.isFighter=true")
    List<Drone>findByFighter();
    @Query("select p from Drone p where p.isFighter=false")
    List<Drone>findByNoFighter();

}
