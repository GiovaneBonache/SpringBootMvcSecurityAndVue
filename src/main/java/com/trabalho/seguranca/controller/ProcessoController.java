package com.trabalho.seguranca.controller;

import com.trabalho.seguranca.model.entity.Processo;
import com.trabalho.seguranca.model.service.ProcessoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @PostMapping("/novo")
    public ResponseEntity<?> criarProcesso(@Valid @RequestBody Processo processo) {

        Boolean criou = processoService.criarProcesso(processo);

        if (criou) {
            return ResponseEntity.status(201).body(true);
        } else {
            return ResponseEntity.status(400).body(false);
        }
    }

    @GetMapping
    public ResponseEntity<List<Processo>> getProcessos() {

        List<Processo> processos = processoService.getProcessos();
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Processo> getProcessoPorId(@PathVariable("id") Long id) {

        Processo processo = processoService.getProcessoPorId(id);

        if (processo != null) {
            return ResponseEntity.ok(processo);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarProcessoPorId(@PathVariable("id") Long id) {

        Boolean deletou = processoService.deletarProcessoPorId(id);

        if (deletou) {
            return ResponseEntity.status(200).body(true);
        } else {
            return ResponseEntity.status(404).body(false);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Processo> atualizarProcesso(@PathVariable Long id, @RequestBody Processo processo) {

        Processo atualizado = processoService.atualizarProcesso(id, processo);

        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}