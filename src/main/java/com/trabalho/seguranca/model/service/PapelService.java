package com.trabalho.seguranca.model.service;

import com.trabalho.seguranca.model.entity.Papel;
import com.trabalho.seguranca.model.repositories.PapelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PapelService {

    @Autowired
    private PapelRepository papelRepository;

    public Papel criar(Papel papel) {
        return papelRepository.save(papel);
    }

    public List<Papel> listar() {
        return papelRepository.findAll();
    }


    public Papel buscarPorId(Long id) {
        return papelRepository.findById(id).orElse(null);
    }

    public boolean deletar(Long id) {
        if (papelRepository.existsById(id)) {
            papelRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Papel atualizar(Long id, Papel papel) {
        Optional<Papel> existente = papelRepository.findById(id);

        if (existente.isPresent()) {
            Papel p = existente.get();
            p.setPapel(papel.getPapel());

            return papelRepository.save(p);
        }

        return null;
    }
}
