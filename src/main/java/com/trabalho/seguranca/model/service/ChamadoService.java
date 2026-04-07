package com.trabalho.seguranca.model.service;

import com.trabalho.seguranca.model.entity.Chamado;
import com.trabalho.seguranca.model.entity.Usuario;
import com.trabalho.seguranca.model.repositories.UsuarioRepository;
import com.trabalho.seguranca.model.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Chamado criarChamado(Chamado chamado, Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()) {
            chamado.setUsuario(usuario.get());
            return chamadoRepository.save(chamado);
        }

        throw new RuntimeException("Usuário não encontrado");
    }

    public List<Chamado> listarChamados() {
        return chamadoRepository.findAll();
    }

    public boolean deletarChamado(Long id) {
        if (chamadoRepository.existsById(id)) {
            chamadoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean atualizarStatus(Long id, String status) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);

        if (chamado.isPresent()) {
            Chamado c = chamado.get();
            c.setStatus(status);
            chamadoRepository.save(c);
            return true;
        }

        return false;
    }
}