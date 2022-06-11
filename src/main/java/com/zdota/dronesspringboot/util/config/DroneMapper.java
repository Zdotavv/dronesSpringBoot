package com.zdota.dronesspringboot.util.config;

import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.dto.DroneDeleteDto;
import com.zdota.dronesspringboot.dto.DroneDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


//public class DroneMapper extends CustomMapper<Drone, DroneDto> {
    @Mapper
    public interface DroneMapper {
        DroneMapper INSTANCE  = Mappers.getMapper(DroneMapper.class);

    DroneDto droneToDroneDto (Drone drone);
    Drone droneDtoToDrone (DroneDto dto);
    DroneDeleteDto droneToDroneDeleteDto (Drone drone);

//    @Override
//    public void mapBtoA(DroneDto dto, Drone entity, MappingContext context) {
//        super.mapBtoA(dto, entity, context);
//    }
}
