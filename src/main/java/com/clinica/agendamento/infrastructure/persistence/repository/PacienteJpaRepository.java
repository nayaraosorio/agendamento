package com.clinica.agendamento.infrastructure.persistence.repository;


import com.clinica.agendamento.domain.entity.Paciente;
import com.clinica.agendamento.infrastructure.persistence.entity.PacienteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PacienteJpaRepository extends JpaRepository<PacienteJpaEntity, UUID> {

    boolean existsByCpf(String cpf);

    Optional<PacienteJpaEntity> findByCpf(String cpf);

    List<PacienteJpaEntity> findAllByAtivoTrue();
}


