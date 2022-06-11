package com.zdota.dronesspringboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.dto.DroneDto;
import com.zdota.dronesspringboot.util.config.DroneMapper;
import com.zdota.dronesspringboot.web.DroneController;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DronesSpringBootApplication.class)
@AutoConfigureMockMvc
public class DroneControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    DroneController controller;
//    @Autowired
//    private DroneConverter converter;

    @Ignore
    @Test
    public void createDrone_success() throws Exception {
        Drone drone = Drone.builder()
                .name("Bayraktar")
                .isFighter(true)
                .isDeleted(false)
                .build();
        DroneDto dto = DroneMapper.INSTANCE.droneToDroneDto(drone);
        Mockito.when(controller.createDrone(dto)).thenReturn(dto);

            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/drones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(drone));

        mockMvc.perform(mockRequest)
                .andExpect(status().is(201));
//                .andExpect(jsonPath("$", notNullValue()))
//                .andExpect(jsonPath("$.Name", is("Bayraktar")));
    }


    @Test
    public void getDroneById_success() throws Exception {

        Drone drone = Drone.builder()
                .name("Bayraktar")
                .country("Turkey")
                .build();
//        DroneDto dto = converter.toDto(drone);
        DroneDto dto = DroneMapper.INSTANCE.droneToDroneDto(drone);
        Mockito.when(controller.viewDroneById(drone.getId())).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/drones/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$", notNullValue()))
//                .andExpect(jsonPath("$.Name", is("Bayraktar")));
    }


    @Test
    public void getAllDrones_success() throws Exception {

        Drone drone = Drone.builder()
                .name("Bayraktar")
                .country("Turkey")
                .isFighter(true)
                .isDeleted(false)
                .build();

        DroneDto dto = DroneMapper.INSTANCE.droneToDroneDto(drone);
        Collection<Drone> records = new ArrayList<>(List.of(drone));

        controller.createDrone(dto);

        Mockito.when(controller.findAllByDeletedIsFalse()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/drones")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is("Bayraktar")));
    }

}
