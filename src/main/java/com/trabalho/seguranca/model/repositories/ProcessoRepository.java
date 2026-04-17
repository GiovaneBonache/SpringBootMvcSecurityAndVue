package com.trabalho.seguranca.model.repositories;


import com.trabalho.seguranca.model.entity.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
}
