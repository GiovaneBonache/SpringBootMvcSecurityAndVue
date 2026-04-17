package com.trabalho.seguranca.model.repositories;

import com.trabalho.seguranca.model.entity.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LembreteRepository extends JpaRepository<Lembrete, Long> {
}
