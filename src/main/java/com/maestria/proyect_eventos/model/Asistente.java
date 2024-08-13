package com.maestria.proyect_eventos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "asistentes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asistente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String dni;

    @ManyToMany
    @JoinTable(
            name = "evento_asistente",
            joinColumns = @JoinColumn(name = "asistente_id"),
            inverseJoinColumns = @JoinColumn(name = "evento_id")
    )
    private Set<Event> eventosAsistidos;
}
