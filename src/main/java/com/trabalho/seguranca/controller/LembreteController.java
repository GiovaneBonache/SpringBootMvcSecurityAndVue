package com.trabalho.seguranca.controller;

import com.trabalho.seguranca.model.entity.Lembrete;
import com.trabalho.seguranca.model.service.LembreteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lembretes")
public class LembreteController {

    @Autowired
    private LembreteService lembreteService;

    @PostMapping("/novo")
    public ResponseEntity<?> criarLembrete(@Valid @RequestBody Lembrete lembrete) {

        Boolean criou = lembreteService.criarLembrete(lembrete);

        if (criou) {
            return ResponseEntity.status(201).body(true);
        } else {
            return ResponseEntity.status(400).body(false);
        }
    }

    @GetMapping
    public ResponseEntity<List<Lembrete>> getLembretes() {

        List<Lembrete> lembretes = lembreteService.getLembretes();
        return ResponseEntity.ok(lembretes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lembrete> getLembretePorId(@PathVariable("id") Long id) {

        Lembrete lembrete = lembreteService.getLembretePorId(id);

        if (lembrete != null) {
            return ResponseEntity.ok(lembrete);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarLembretePorId(@PathVariable("id") Long id) {

        Boolean deletou = lembreteService.deletarLembretePorId(id);

        if (deletou) {
            return ResponseEntity.status(200).body(true);
        } else {
            return ResponseEntity.status(404).body(false);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lembrete> atualizarLembrete(@PathVariable Long id, @RequestBody Lembrete lembrete) {

        Lembrete atualizado = lembreteService.atualizarLembrete(id, lembrete);

        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}