package com.zdota.dronesspringboot;


import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.repository.DroneRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;

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

        Drone drone = Drone.builder().name("Bayraktar").country("Turkey").weight(240).build();

        droneRepository.save(drone);

        Assertions.assertThat(drone.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getDroneTest() {

        Drone drone = droneRepository.findById(1).orElseThrow();

        Assertions.assertThat(drone.getId()).isEqualTo(1);
    }
//    @Test
//    @Order(3)
//    public void getListOfDroneTest() {
//
//        List<Drone> dronesList = droneRepository.findAll();
//
//        Assertions.assertThat(dronesList.size()).isGreaterThan(0);
//    }
//
//    @Test
//    @Order(4)
//    @Rollback(value = false)
//    public void updateDroneTest() {
//
//        Drone drone = droneRepository.findById(1).get();
//
//        drone.setName("Bayraktar");
//        Drone droneUpdated = droneRepository.save(drone);
//
//        Assertions.assertThat(droneUpdated.getName()).isEqualTo("Bayraktar");
//
//    }
//    @Test
//    @Order(5)
//    @Rollback(value = false)
//    public void deleteDroneTest() {
//
//        Drone drone = droneRepository.findById(1).get();
//        droneRepository.delete(drone);
//
//        //repository.deleteById(1L);
//
//        Drone drone1 = null;
//
//        Optional<Drone> optionalAuthor = Optional.ofNullable(droneRepository.findByName("Bayraktar"));
//
//        if (optionalAuthor.isPresent()) {
//            drone1 = optionalAuthor.get();
//        }
//
//        Assertions.assertThat(drone1).isNull();
//    }
}

