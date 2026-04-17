package com.trabalho.seguranca.model.service;


import com.trabalho.seguranca.model.entity.Processo;
import com.trabalho.seguranca.model.repositories.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    public Boolean criarProcesso(Processo processo) {

        try {
            processoRepository.save(processo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Processo getProcessoPorId(Long id) {
        return processoRepository.findById(id).orElse(null);
    }

    public List<Processo> getProcessos() {
        return processoRepository.findAll();
    }

    public Boolean deletarProcessoPorId(Long id) {

        try {
            processoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Processo atualizarProcesso(Long id, Processo processoAtualizado) {

        Processo processo = processoRepository.findById(id).orElse(null);

        if (processo == null) {
            return null;
        }

        processo.setNomeProcesso(processoAtualizado.getNomeProcesso());
        processo.setNumeroProcesso(processoAtualizado.getNumeroProcesso());
        processo.setTipoProcesso(processoAtualizado.getTipoProcesso());
        processo.setAreaDireito(processoAtualizado.getAreaDireito());
        processo.setTribunal(processoAtualizado.getTribunal());
        processo.setVara(processoAtualizado.getVara());
        processo.setCidade(processoAtualizado.getCidade());
        processo.setEstado(processoAtualizado.getEstado());
        processo.setFaseProcesso(processoAtualizado.getFaseProcesso());
        processo.setNomeCliente(processoAtualizado.getNomeCliente());
        processo.setProximaAudiencia(processoAtualizado.getProximaAudiencia());
        processo.setHoraInicioAudiencia(processoAtualizado.getHoraInicioAudiencia());
        processo.setHoraFimAudiencia(processoAtualizado.getHoraFimAudiencia());
        processo.setResponsavel(processoAtualizado.getResponsavel());
        processo.setObservacoes(processoAtualizado.getObservacoes());

        return processoRepository.save(processo);
    }
}