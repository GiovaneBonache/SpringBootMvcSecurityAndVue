package com.trabalho.seguranca.controller;

import com.trabalho.seguranca.model.entity.Chamado;
import com.trabalho.seguranca.model.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @PostMapping("/{idUsuario}")
    public ResponseEntity<?> criarChamado(
            @RequestBody Chamado chamado,
            @PathVariable Long idUsuario) {

        try {
            Chamado novo = chamadoService.criarChamado(chamado, idUsuario);
            return ResponseEntity.status(201).body(novo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Chamado>> listarChamados() {
        return ResponseEntity.ok(chamadoService.listarChamados());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarChamado(@PathVariable Long id) {
        boolean deletou = chamadoService.deletarChamado(id);

        return deletou ? ResponseEntity.ok(true)
                : ResponseEntity.status(404).body(false);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> atualizarStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        boolean atualizou = chamadoService.atualizarStatus(id, status);

        return atualizou ? ResponseEntity.ok(true)
                : ResponseEntity.status(404).body(false);
    }
}