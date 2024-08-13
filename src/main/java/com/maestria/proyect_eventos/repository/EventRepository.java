package com.maestria.proyect_eventos.repository;

import com.maestria.proyect_eventos.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository  extends JpaRepository<Event,Long> {

}
