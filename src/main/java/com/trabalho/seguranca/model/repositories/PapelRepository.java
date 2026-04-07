package com.trabalho.seguranca.model.repositories;
import com.trabalho.seguranca.model.entity.Papel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PapelRepository extends JpaRepository<Papel, Long> {

    Optional<Papel> findByPapel(String papel);

}