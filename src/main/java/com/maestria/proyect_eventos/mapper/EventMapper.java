package com.maestria.proyect_eventos.mapper;

import com.maestria.proyect_eventos.Dto.EventDto;
import com.maestria.proyect_eventos.model.Asistente;
import com.maestria.proyect_eventos.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "attendeeIds", source = "asistentes", qualifiedByName = "mapAttendeeIds")
    EventDto toDto(Event event);

    @Mapping(target = "asistentes", ignore = true)
    Event toEntity(EventDto eventDto);

    @Named("mapAttendeeIds")
    static Set<Long> mapAttendeeIds(Set<Asistente> asistentes) {
        return asistentes.stream()
                .map(Asistente::getId)
                .collect(Collectors.toSet());
    }
}
