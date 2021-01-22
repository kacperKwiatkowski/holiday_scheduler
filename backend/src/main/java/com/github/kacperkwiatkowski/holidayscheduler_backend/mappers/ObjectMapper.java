package com.github.kacperkwiatkowski.holidayscheduler_backend.mappers;

import org.mapstruct.Mapper;

@Mapper
public interface ObjectMapper<Dto, Entity> {
    Entity mapToEntity(Dto dto);
    Dto mapToDto(Entity entity);
}