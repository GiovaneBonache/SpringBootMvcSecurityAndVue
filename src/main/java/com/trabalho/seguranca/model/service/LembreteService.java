package com.trabalho.seguranca.model.service;

import com.trabalho.seguranca.model.entity.Lembrete;
import com.trabalho.seguranca.model.repositories.LembreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LembreteService {

    @Autowired
    private LembreteRepository lembreteRepository;

    public Boolean criarLembrete(Lembrete lembrete) {

        try {
            lembreteRepository.save(lembrete);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Lembrete getLembretePorId(Long id) {
        return lembreteRepository.findById(id).orElse(null);
    }

    public List<Lembrete> getLembretes() {
        return lembreteRepository.findAll();
    }

    public Boolean deletarLembretePorId(Long id) {

        try {
            lembreteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Lembrete atualizarLembrete(Long id, Lembrete lembreteAtualizado) {

        Lembrete lembrete = lembreteRepository.findById(id).orElse(null);

        if (lembrete == null) {
            return null;
        }

        lembrete.setDescricao(lembreteAtualizado.getDescricao());
        lembrete.setData(lembreteAtualizado.getData());
        lembrete.setUsuario(lembreteAtualizado.getUsuario());

        return lembreteRepository.save(lembrete);
    }
}