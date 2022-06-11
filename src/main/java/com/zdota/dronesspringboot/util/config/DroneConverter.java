package com.zdota.dronesspringboot.util.config;

import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.dto.DroneDeleteDto;
import com.zdota.dronesspringboot.dto.DroneDto;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

@Component
public class DroneConverter {
    private final MapperFacade mapperFacade;

    public DroneConverter(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }

    public MapperFacade getMapperFacade() {
        return mapperFacade;
    }

    public DroneDto toDto(Drone entity){
        return mapperFacade.map(entity, DroneDto.class);
    }

    public DroneDeleteDto toDeleteDto(Drone entity){

        return mapperFacade.map(entity, DroneDeleteDto.class);
    }

    public  Drone fromDto(DroneDto dto) {
        return mapperFacade.map(dto, Drone.class);
    }

}
