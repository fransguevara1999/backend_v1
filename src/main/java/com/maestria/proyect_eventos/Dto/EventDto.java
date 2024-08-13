package com.maestria.proyect_eventos.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private String nombre;  // Cambiado de "name"
    private LocalDateTime fechaHoraInicio;  // Cambiado de "startDateTime"
    private LocalDateTime fechaHoraFin;  // Cambiado de "endDateTime"
    private String ubicacion;  // Cambiado de "location"
    private boolean requiereRegistro;  // Cambiado de "requiresRegistration"
    private String enlaceAsistencia;  // Cambiado de "attendanceLink"
    private String enlaceControl;  // Cambiado de "controlLink"
    private Set<Long> attendeeIds;  // Este se mantiene igual, ya que es un campo propio de Dto
}
