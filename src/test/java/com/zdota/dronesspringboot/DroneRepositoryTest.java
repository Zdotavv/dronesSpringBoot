package com.zdota.dronesspringboot;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.repository.DroneRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DroneRepositoryTest {
    @Autowired
    private DroneRepository droneRepository;
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveDroneTest() {
        Drone drone = Drone.builder().name("Bayraktar").country("Turkey").weight(240).isDeleted(Boolean.FALSE).flightDuration(60).isFighter(Boolean.TRUE).build();
        droneRepository.save(drone);

        Drone drone2 = Drone.builder().name("MQ-9 Reaper").country("USA").weight(240).isDeleted(Boolean.TRUE).flightDuration(60).isFighter(Boolean.FALSE).build();
        droneRepository.save(drone2);

        Assertions.assertThat(drone.getId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void getDroneTest() {
        Drone drone = droneRepository.findById(1).orElseThrow();
        Assertions.assertThat(drone.getId()).isEqualTo(1);
    }
    @Test
    @Order(3)
    public void findByNameTest() {

        List<Drone> droneList = droneRepository.findByName("Bayraktar");
        Assertions.assertThat(droneList.size()).isGreaterThan(0);
    }
    @Test
    @Order(4)
    public void findByFlightDurationTest() {

        List<Drone> droneList = droneRepository.findByFlightDuration(60);
        Assertions.assertThat(droneList.size()).isGreaterThan(0);
    }
    @Test
    @Order(5)
    public void getListOfDroneTest() {

        List<Drone> dronesList = droneRepository.findAll();

        Assertions.assertThat(dronesList.size()).isGreaterThan(0);
    }

    @Test
    @Order(6)
    public void findByFighterTest() {
        List<Drone> droneList = droneRepository.findByFighter();
        Assertions.assertThat(droneList.size()).isGreaterThan(0);
    }

    @Test
    @Order(7)
    public void findByNoFighterTest() {
        List<Drone> droneList = droneRepository.findByNoFighter();
        Assertions.assertThat(droneList.size()).isGreaterThan(0);
    }
    @Test
    @Order(8)
    public void findAllByDeletedIsFalseTest() {
        List<Drone> droneList = droneRepository.findAllByDeletedIsFalse();
        Assertions.assertThat(droneList.size()).isGreaterThan(0);
    }

    @Test
    @Order(9)
    @Rollback(value = false)
    public void updateDroneTest() {

        Drone drone = droneRepository.findById(1).get();

        drone.setName("CH-5");
        Drone droneUpdated = droneRepository.save(drone);

        Assertions.assertThat(droneUpdated.getName()).isEqualTo("CH-5");

    }


    @Test
    @Order(10)
    @Rollback(value = false)
    public void deleteDroneTest() {

        Drone drone = droneRepository.findById(1).get();
        droneRepository.delete(drone);

        List<Drone> drone1 = null;

        Optional<List<Drone>> optionalAuthor = Optional.ofNullable(droneRepository.findByName("CH-5"));

        if (optionalAuthor.isPresent()) {
            drone1 = optionalAuthor.get();
        }

        Assertions.assertThat(drone1).isEmpty();
    }


}

