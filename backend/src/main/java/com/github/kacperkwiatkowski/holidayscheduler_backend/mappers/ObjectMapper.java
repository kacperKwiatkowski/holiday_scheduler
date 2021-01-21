package com.github.kacperkwiatkowski.holidayscheduler_backend.mappers;

import org.mapstruct.Mapper;

@Mapper
public interface ObjectMapper<Dto, Entity> {
    Entity mapToDto(Dto dto);
    Dto mapToEntity(Entity entity);
}