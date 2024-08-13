package com.maestria.proyect_eventos.services;

import com.maestria.proyect_eventos.Dto.AsistenteDto;
import com.maestria.proyect_eventos.mapper.AsistenteMapper;
import com.maestria.proyect_eventos.model.Asistente;
import com.maestria.proyect_eventos.model.Event;
import com.maestria.proyect_eventos.repository.AsistenteRepository;
import com.maestria.proyect_eventos.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class AsistenteService {
    @Autowired
    private AsistenteRepository asistenteRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AsistenteMapper asistenteMapper;

    public List<Asistente> getAllAsistentes() {
        return asistenteRepository.findAll();
    }

    public Optional<Asistente> getAsistenteById(Long id) {
        return asistenteRepository.findById(id);
    }

    public void deleteAsistente(Long id) {
        asistenteRepository.deleteById(id);
    }

    public List<Asistente> getAsistentesPorEvento(Event event) {
        return asistenteRepository.findByEventosAsistidos(event);
    }

    public AsistenteDto createOrUpdateAsistente(AsistenteDto asistenteDto, Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + eventId));

        Optional<Asistente> optionalAsistente = asistenteRepository.findByDni(asistenteDto.getDni());

        Asistente existingAsistente;

        if (optionalAsistente.isPresent()) {
            existingAsistente = optionalAsistente.get();
        } else {
            existingAsistente = asistenteMapper.toEntity(asistenteDto);
            existingAsistente.setEventosAsistidos(new HashSet<>()); // Inicializar eventosAsistidos
        }

        // Asegúrate de que eventosAsistidos esté inicializado
        if (existingAsistente.getEventosAsistidos() == null) {
            existingAsistente.setEventosAsistidos(new HashSet<>());
        }

        // Asocia el asistente al evento
        existingAsistente.getEventosAsistidos().add(event);

        // Guarda el asistente en el repositorio
        Asistente savedAsistente = asistenteRepository.save(existingAsistente);

        return asistenteMapper.toDto(savedAsistente);
    }

    public Asistente registrarAsistencia(Long eventId, AsistenteDto asistenteDto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + eventId));

        Optional<Asistente> optionalAsistente = asistenteRepository.findByDni(asistenteDto.getDni());

        Asistente existingAsistente;

        if (optionalAsistente.isPresent()) {
            existingAsistente = optionalAsistente.get();
        } else {
            existingAsistente = asistenteMapper.toEntity(asistenteDto);
            existingAsistente.setEventosAsistidos(new HashSet<>()); // Inicializar eventosAsistidos
        }

        // Asegúrate de que eventosAsistidos esté inicializado
        if (existingAsistente.getEventosAsistidos() == null) {
            existingAsistente.setEventosAsistidos(new HashSet<>());
        }

        existingAsistente.getEventosAsistidos().add(event);

        return asistenteRepository.save(existingAsistente);
    }
}
