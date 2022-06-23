package com.zdota.dronesspringboot.util.config;

import com.zdota.dronesspringboot.domain.Drone;
import com.zdota.dronesspringboot.domain.Operator;
import com.zdota.dronesspringboot.dto.DroneDeleteDto;
import com.zdota.dronesspringboot.dto.DroneDto;

import com.zdota.dronesspringboot.dto.OperatorDto;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

public class MappingConfig implements OrikaMapperFactoryConfigurer {
    @Override
    public void configure(MapperFactory mapperFactory) {

        mapperFactory.classMap(Drone.class, DroneDto.class)
                .customize(new DroneMapper())
                .byDefault()
                .register();

        mapperFactory.classMap(Drone.class, DroneDto.class)
                .byDefault()
                .register();

        mapperFactory.classMap(Drone.class, DroneDeleteDto.class)
                .byDefault()
                .register();

        mapperFactory.classMap(Operator.class, OperatorDto.class)
                .byDefault()
                .register();
    }
}
