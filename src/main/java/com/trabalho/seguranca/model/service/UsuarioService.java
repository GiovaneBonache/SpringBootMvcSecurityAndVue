package com.trabalho.seguranca.model.service;

import com.trabalho.seguranca.model.entity.Papel;
import com.trabalho.seguranca.model.entity.Usuario;
import com.trabalho.seguranca.model.repositories.PapelRepository;
import com.trabalho.seguranca.model.repositories.UsuarioRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PapelRepository papelRepository;


    public Boolean criarUsuario(Usuario usuario) throws Exception {
        try {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            Papel papelUser = papelRepository.findByPapel("USER")
                    .orElseThrow(() -> new RuntimeException("Papel USER não encontrado"));
            usuario.setPapeis(List.of(papelUser));
            usuarioRepository.save(usuario);
            return true;

        } catch (RuntimeException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            return false;
        }
    }
    public Usuario getUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Boolean deletarUsuarioPorId(Long id) {

        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}


