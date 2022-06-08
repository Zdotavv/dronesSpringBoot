package com.zdota.dronesspringboot;

import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.repository.DroneRepository;
import com.zdota.dronesspringboot.service.DroneServiceBean;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.zdota.dronesspringboot.util.ResourceNotFoundException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DroneServiceTest {

    @Mock
    private DroneRepository droneRepository;

    @InjectMocks
    private DroneServiceBean service;

    @Test
    public void whenSaveDrone_shouldReturnDrone() {
        Drone drone = new Drone();
        drone.setId(1);
        drone.setName("Bayraktar");

        when(droneRepository.save(ArgumentMatchers.any(Drone.class))).thenReturn(drone);

        Drone created = service.create(drone);

        assertThat(created.getName()).isSameAs(drone.getName());
        verify(droneRepository).save(drone);
    }

    @Test
    public void whenGivenId_shouldReturnDrone_ifFound() {
        Drone drone = new Drone();
        drone.setId(69);

        when(droneRepository.findById(drone.getId())).thenReturn(Optional.of(drone));

        Drone expected = service.viewById(drone.getId());

        assertThat(expected).isSameAs(drone);
//        verify(droneRepository).findById(drone.getId());
    }


    @Test(expected = ResourceNotFoundException.class)
    public void should_throw_exception_when_drone_doesnt_exist() {
        Drone drone = new Drone();
        drone.setId(70);
        drone.setName("Bayraktar");

        given(droneRepository.findById(anyInt())).willReturn(Optional.empty());
        service.viewById(drone.getId());
    }

}
