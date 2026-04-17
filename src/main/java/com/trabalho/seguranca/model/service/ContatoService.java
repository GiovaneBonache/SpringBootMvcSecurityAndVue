package com.trabalho.seguranca.model.service;

import com.trabalho.seguranca.model.entity.Contatos;
import com.trabalho.seguranca.model.repositories.ContatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatosRepository contatoRepository;

    public Boolean criarContato(Contatos contato) {
        try {
            contatoRepository.save(contato);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Contatos getContatoPorId(Long id) {
        return contatoRepository.findById(id).orElse(null);
    }

    public List<Contatos> getContatos() {
        return contatoRepository.findAll();
    }

    public Boolean deletarContatoPorId(Long id) {
        try {
            contatoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Contatos atualizarContato(Long id, Contatos contatoAtualizado) {

        Contatos contato = contatoRepository.findById(id).orElse(null);

        if (contato == null) {
            return null;
        }

        contato.setNome(contatoAtualizado.getNome());
        contato.setCelular(contatoAtualizado.getCelular());
        contato.setEmail(contatoAtualizado.getEmail());
        contato.setRua(contatoAtualizado.getRua());
        contato.setEstado(contatoAtualizado.getEstado());
        contato.setBairro(contatoAtualizado.getBairro());
        contato.setIdade(contatoAtualizado.getIdade());
        contato.setEstadoCivil(contatoAtualizado.getEstadoCivil());
        contato.setProfissao(contatoAtualizado.getProfissao());
        contato.setCnpj(contatoAtualizado.getCnpj());
        contato.setOrigemCliente(contatoAtualizado.getOrigemCliente());
        contato.setObservacoes(contatoAtualizado.getObservacoes());

        return contatoRepository.save(contato);
    }
}