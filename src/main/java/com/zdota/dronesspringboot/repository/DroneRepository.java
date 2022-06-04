package com.zdota.dronesspringboot.repository;

import com.zdota.dronesspringboot.domain.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface DroneRepository extends JpaRepository<Drone,Integer> {

    @Query("select i from Drone i where i.isDeleted=false")
    List<Drone> findByName(String name);
    List<Drone> findByFlightDuration(int flightDuration);

    @Query("select p from Drone p where p.isFighter=true")
    List<Drone>findByFighter();
    @Query("select p from Drone p where p.isFighter=false")
    List<Drone>findByNoFighter();
    @Query("select i from Drone i where i.isDeleted=false")
    List<Drone>findAllByDeletedIsFalse();
    @Modifying
    @Transactional
    @Query("update Drone p set p.produceDate =:dateTime where p.id =:id")
    void updateDrone(Integer id, LocalDateTime dateTime);


}
