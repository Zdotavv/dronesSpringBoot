package com.zdota.dronesspringboot.util.config;

import com.zdota.dronesspringboot.domain.Operator;
import com.zdota.dronesspringboot.dto.OperatorDto;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

@Component
public class OperatorConverter {
    private final MapperFacade mapperFacade;

    public OperatorConverter(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }

    public MapperFacade getMapperFacade() {
        return mapperFacade;
    }

    public OperatorDto toDto(Operator entity) {
        return mapperFacade.map(entity, OperatorDto.class);
    }

    public Operator fromDto(OperatorDto entity) {
        return mapperFacade.map(entity, Operator.class);
    }
}
