package com.maestria.proyect_eventos.mapper;

import com.maestria.proyect_eventos.Dto.AsistenteDto;
import com.maestria.proyect_eventos.model.Asistente;
import com.maestria.proyect_eventos.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AsistenteMapper {

    @Mapping(target = "eventIds", source = "eventosAsistidos", qualifiedByName = "mapEventIds")
    AsistenteDto toDto(Asistente asistente);

    @Mapping(target = "eventosAsistidos", ignore = true)
    Asistente toEntity(AsistenteDto asistenteDto);

    @Named("mapEventIds")
    static Set<Long> mapEventIds(Set<Event> eventosAsistidos) {
        return eventosAsistidos.stream()
                .map(Event::getId)
                .collect(Collectors.toSet());
    }
}
