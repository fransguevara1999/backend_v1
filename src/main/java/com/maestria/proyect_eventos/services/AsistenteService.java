package com.maestria.proyect_eventos.services;

import com.maestria.proyect_eventos.model.Asistente;
import com.maestria.proyect_eventos.model.Event;
import com.maestria.proyect_eventos.repository.AsistenteRepository;
import com.maestria.proyect_eventos.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenteService {
    @Autowired
    private AsistenteRepository asistenteRepository;
    @Autowired
    private EventRepository eventRepository;

    public List<Asistente> getAllAsistentes() {
        return asistenteRepository.findAll();
    }

    public Optional<Asistente> getAsistenteById(Long id) {
        return asistenteRepository.findById(id);
    }

    public Asistente createOrUpdateAsistente(Asistente asistente) {
        return asistenteRepository.save(asistente);
    }

    public void deleteAsistente(Long id) {
        asistenteRepository.deleteById(id);
    }

    public List<Asistente> getAsistentesPorEvento(Event event) {
        return asistenteRepository.findByAttendedEvents(event);
    }
    public Asistente registrarAsistente(Long eventId, Asistente asistente) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            asistente.getAttendedEvents().add(event);
            event.getAttendees().add(asistente);

            asistenteRepository.save(asistente);
            eventRepository.save(event);

            return asistente;
        } else {
            throw new RuntimeException("Event not found");
        }
    }
}
