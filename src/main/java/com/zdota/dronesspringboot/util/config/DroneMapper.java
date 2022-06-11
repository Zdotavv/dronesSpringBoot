package com.zdota.dronesspringboot.util.config;

import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.dto.DroneDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class DroneMapper extends CustomMapper<Drone, DroneDto> {

    @Override
    public void mapBtoA(DroneDto dto, Drone entity, MappingContext context) {
        super.mapBtoA(dto, entity, context);
    }
}
