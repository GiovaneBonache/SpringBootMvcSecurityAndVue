package com.trabalho.seguranca.controller;

import com.trabalho.seguranca.controller.dto.TokenDTO;
import com.trabalho.seguranca.model.entity.Usuario;
import com.trabalho.seguranca.model.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody Usuario usuario) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha());
        Authentication authentication = manager.authenticate(authenticationToken);
        String token = tokenService.gerarToken((UserDetails) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }
}
