package com.github.kacperkwiatkowski.holidayscheduler_backend.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ObjectMapper<Dto, Entity> {
    Entity mapToEntity(Dto dto);
    Dto mapToDto(Entity entity);
}