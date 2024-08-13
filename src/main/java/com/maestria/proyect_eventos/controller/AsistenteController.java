package com.maestria.proyect_eventos.controller;

import com.maestria.proyect_eventos.Dto.AsistenteDto;
import com.maestria.proyect_eventos.model.Asistente;
import com.maestria.proyect_eventos.model.Event;
import com.maestria.proyect_eventos.services.AsistenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistentes")
public class AsistenteController {
    @Autowired
    private AsistenteService asistenteService;

    @GetMapping
    public List<Asistente> getAllAsistentes() {
        return asistenteService.getAllAsistentes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistente> getAsistenteById(@PathVariable Long id) {
        Optional<Asistente> asistente = asistenteService.getAsistenteById(id);
        return asistente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{eventId}")
    public ResponseEntity<AsistenteDto> createAsistente(@PathVariable Long eventId, @RequestBody AsistenteDto asistenteDto) {
        AsistenteDto createdAsistente = asistenteService.createOrUpdateAsistente(asistenteDto, eventId);
        return ResponseEntity.ok(createdAsistente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistente(@PathVariable Long id) {
        Optional<Asistente> asistente = asistenteService.getAsistenteById(id);
        if (asistente.isPresent()) {
            asistenteService.deleteAsistente(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/evento/{eventId}")
    public List<Asistente> getAsistentesPorEvento(@PathVariable Long eventId) {
        Event event = new Event();
        event.setId(eventId);
        return asistenteService.getAsistentesPorEvento(event);
    }

    @PostMapping("/evento/{eventId}/registrar")
    public ResponseEntity<Asistente> registrarAsistente(@PathVariable Long eventId, @RequestBody AsistenteDto asistenteDto) {
        Asistente registrado = asistenteService.registrarAsistencia(eventId, asistenteDto);
        return ResponseEntity.ok(registrado);
    }
}
