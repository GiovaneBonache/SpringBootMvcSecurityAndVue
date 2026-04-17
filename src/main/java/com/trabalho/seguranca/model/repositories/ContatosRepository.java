package com.trabalho.seguranca.model.repositories;


import com.trabalho.seguranca.model.entity.Contatos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatosRepository extends JpaRepository<Contatos, Long> {
}
