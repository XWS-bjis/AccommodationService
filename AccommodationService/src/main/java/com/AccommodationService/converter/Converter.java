package com.AccommodationService.converter;

public interface Converter<Entity, Dto> {
    Dto entityToDto(Entity entity);
    Entity dtoToEntity(Dto dto);
}
