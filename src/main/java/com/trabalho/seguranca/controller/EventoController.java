package com.trabalho.seguranca.controller;

import com.trabalho.seguranca.model.entity.Evento;
import com.trabalho.seguranca.model.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/novo")
    public ResponseEntity<?> criarEvento(@Valid @RequestBody Evento evento) {

        Boolean criou = eventoService.criarEvento(evento);

        if (criou) {
            return ResponseEntity.status(201).body(true);
        } else {
            return ResponseEntity.status(400).body(false);
        }
    }

    @GetMapping
    public ResponseEntity<List<Evento>> getEventos() {

        List<Evento> eventos = eventoService.getEventos();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoPorId(@PathVariable("id") Long id) {

        Evento evento = eventoService.getEventoPorId(id);

        if (evento != null) {
            return ResponseEntity.ok(evento);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarEventoPorId(@PathVariable("id") Long id) {

        Boolean deletou = eventoService.deletarEventoPorId(id);

        if (deletou) {
            return ResponseEntity.status(200).body(true);
        } else {
            return ResponseEntity.status(404).body(false);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {

        Evento atualizado = eventoService.atualizarEvento(id, evento);

        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}