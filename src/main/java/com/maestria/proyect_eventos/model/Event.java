package com.maestria.proyect_eventos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String ubicacion;
    private boolean requiereRegistro;
    private String enlaceAsistencia;
    private String enlaceControl;

    @ManyToMany(mappedBy = "eventosAsistidos")
    private Set<Asistente> asistentes;

    @PrePersist
    private void crearEnlaces() {
        this.enlaceAsistencia = generarEnlace(this.nombre, false);
        this.enlaceControl = generarEnlace(this.nombre, true);
    }

    private String generarEnlace(String nombreEvento, boolean esControl) {
        String baseLink = "https://abc.com.pe/";
        String identificadorUnico = nombreEvento.toLowerCase().replaceAll(" ", "-") + "-" + System.currentTimeMillis();
        return baseLink + identificadorUnico + (esControl ? "-ctrl" : "");
    }
}
