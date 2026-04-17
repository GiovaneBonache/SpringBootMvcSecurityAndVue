package com.trabalho.seguranca.controller;

import com.trabalho.seguranca.model.entity.Contatos;
import com.trabalho.seguranca.model.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping("/novo")
    public ResponseEntity<?> criarContato(@Valid @RequestBody Contatos contato) {

        Boolean criou = contatoService.criarContato(contato);

        if (criou) {
            return ResponseEntity.status(201).body(true);
        } else {
            return ResponseEntity.status(400).body(false);
        }
    }

    @GetMapping
    public ResponseEntity<List<Contatos>> getContatos() {

        List<Contatos> contatos = contatoService.getContatos();
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contatos> getContatoPorId(@PathVariable("id") Long id) {

        Contatos contato = contatoService.getContatoPorId(id);

        if (contato != null) {
            return ResponseEntity.ok(contato);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarContatoPorId(@PathVariable("id") Long id) {

        Boolean deletou = contatoService.deletarContatoPorId(id);

        if (deletou) {
            return ResponseEntity.status(200).body(true);
        } else {
            return ResponseEntity.status(404).body(false);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contatos> atualizarContato(@PathVariable Long id, @RequestBody Contatos contato) {

        Contatos atualizado = contatoService.atualizarContato(id, contato);

        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
