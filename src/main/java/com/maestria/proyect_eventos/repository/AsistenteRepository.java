package com.maestria.proyect_eventos.repository;

import com.maestria.proyect_eventos.model.Asistente;
import com.maestria.proyect_eventos.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenteRepository extends JpaRepository<Asistente, Long> {
    List<Asistente> findByAttendedEvents(Event event);
}
