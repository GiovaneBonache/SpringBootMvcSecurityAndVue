package com.trabalho.seguranca.controller;

import com.trabalho.seguranca.model.entity.Papel;
import com.trabalho.seguranca.model.service.PapelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/papeis")
public class PapelController {

    @Autowired
    private PapelService papelService;

    @PostMapping
    public ResponseEntity<Papel> criar(@RequestBody Papel papel) {
        return ResponseEntity.status(201).body(papelService.criar(papel));
    }

    @GetMapping
    public ResponseEntity<List<Papel>> listar() {
        return ResponseEntity.ok(papelService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Papel> buscar(@PathVariable Long id) {
        Papel papel = papelService.buscarPorId(id);
        if (papel != null) {
            return ResponseEntity.ok(papel);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (papelService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Papel papel) {
        try {
            Papel atualizado = papelService.atualizar(id, papel);

            if (atualizado != null) {
                return ResponseEntity.ok(atualizado);
            } else {
                return ResponseEntity.status(404).body("Papel não encontrado");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
