package com.zdota.dronesspringboot.util.config;

import com.zdota.dronesspringboot.domain.Operator;
import com.zdota.dronesspringboot.dto.OperatorDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class OperatorMapper extends CustomMapper<Operator, OperatorDto> {
    @Override
    public void mapAtoB(Operator operator, OperatorDto operatorDto, MappingContext context) {
        super.mapAtoB(operator, operatorDto, context);
    }
}