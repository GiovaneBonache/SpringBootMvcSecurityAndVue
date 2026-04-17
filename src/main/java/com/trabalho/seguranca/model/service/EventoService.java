package com.trabalho.seguranca.model.service;

import com.trabalho.seguranca.model.entity.Evento;
import com.trabalho.seguranca.model.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Boolean criarEvento(Evento evento) {

        try {
            eventoRepository.save(evento);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Evento getEventoPorId(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public List<Evento> getEventos() {
        return eventoRepository.findAll();
    }

    public Boolean deletarEventoPorId(Long id) {

        try {
            eventoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Evento atualizarEvento(Long id, Evento eventoAtualizado) {

        Evento evento = eventoRepository.findById(id).orElse(null);

        if (evento == null) {
            return null;
        }

        evento.setDescricao(eventoAtualizado.getDescricao());
        evento.setData(eventoAtualizado.getData());
        evento.setHoraInicio(eventoAtualizado.getHoraInicio());
        evento.setHoraFim(eventoAtualizado.getHoraFim());
        evento.setResponsavel(eventoAtualizado.getResponsavel());
        evento.setProcesso(eventoAtualizado.getProcesso());

        return eventoRepository.save(evento);
    }
}