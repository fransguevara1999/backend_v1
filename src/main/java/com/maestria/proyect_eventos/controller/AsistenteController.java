package com.maestria.proyect_eventos.controller;

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

    @PostMapping
    public Asistente createAsistente(@RequestBody Asistente asistente) {
        return asistenteService.createOrUpdateAsistente(asistente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistente> updateAsistente(@PathVariable Long id, @RequestBody Asistente asistenteDetails) {
        Optional<Asistente> asistente = asistenteService.getAsistenteById(id);
        if (asistente.isPresent()) {
            asistenteDetails.setId(id);
            return ResponseEntity.ok(asistenteService.createOrUpdateAsistente(asistenteDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistente(@PathVariable Long id) {
        asistenteService.deleteAsistente(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/evento/{eventId}")
    public List<Asistente> getAsistentesPorEvento(@PathVariable Long eventId) {
          Event event = new Event();
        event.setId(eventId);
        return asistenteService.getAsistentesPorEvento(event);
    }
    @PostMapping("/evento/{eventId}/registrar")
    public Asistente registrarAsistente(@PathVariable Long eventId, @RequestBody Asistente asistente) {
        return asistenteService.registrarAsistente(eventId, asistente);
    }
}
