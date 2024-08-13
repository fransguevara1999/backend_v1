package com.maestria.proyect_eventos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "asistentes")
@Data
public class Asistente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Cambiar a "nombre"
    private String dni; // Suponiendo que es un identificador único

    @ManyToMany
    @JoinTable(
            name = "event_attendee", // Tal vez cambiar a "evento_asistente"
            joinColumns = @JoinColumn(name = "asistente_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> attendedEvents; // Cambiar a "eventosAsistidos"
}
