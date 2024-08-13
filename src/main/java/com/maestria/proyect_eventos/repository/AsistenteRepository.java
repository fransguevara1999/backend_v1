package com.maestria.proyect_eventos.repository;

import com.maestria.proyect_eventos.model.Asistente;
import com.maestria.proyect_eventos.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsistenteRepository extends JpaRepository<Asistente, Long> {
    List<Asistente> findByEventosAsistidos(Event event);
    Optional<Asistente> findByDni(String dni);
    void deleteById(Long id);
}
