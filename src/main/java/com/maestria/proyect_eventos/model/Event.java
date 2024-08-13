package com.maestria.proyect_eventos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String location;
    private boolean requiresRegistration;
    private String attendanceLink;  // Enlace para que los asistentes se registren
    private String controlLink;  // Enlace para que los organizadores controlen el evento

    @ManyToMany(mappedBy = "attendedEvents")
    private Set<Asistente> attendees;

    @PrePersist
    private void createLinks() {
        this.attendanceLink = generateLink(this.name, false);
        this.controlLink = generateLink(this.name, true);
    }

    private String generateLink(String eventName, boolean isControl) {
        // Genera un link único basado en el nombre del evento
        String baseLink = "https://abc.com.pe/";
        String uniqueIdentifier = eventName.toLowerCase().replaceAll(" ", "-") + "-" + System.currentTimeMillis();
        return baseLink + uniqueIdentifier + (isControl ? "-ctrl" : "");
    }
}
