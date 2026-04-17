package com.trabalho.seguranca.model.repositories;
import com.trabalho.seguranca.model.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
