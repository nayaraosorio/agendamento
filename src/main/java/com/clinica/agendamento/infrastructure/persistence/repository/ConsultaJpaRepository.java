package com.clinica.agendamento.infrastructure.persistence.repository;


import com.clinica.agendamento.domain.entity.Consulta;
import com.clinica.agendamento.infrastructure.persistence.entity.ConsultaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface ConsultaJpaRepository extends JpaRepository<ConsultaJpaEntity, UUID> {

    boolean existsByMedicoIdAndDataHora(UUID medicoId, LocalDateTime dataHora);

}
