package com.maestria.proyect_eventos.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsistenteDto {

    private Long id;
    private String nombre;
    private String dni;
    private Set<Long> eventIds;  // Sólo IDs para evitar bucles
}
