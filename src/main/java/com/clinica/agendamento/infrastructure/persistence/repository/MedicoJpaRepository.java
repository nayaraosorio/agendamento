package com.clinica.agendamento.infrastructure.persistence.repository;

import com.clinica.agendamento.infrastructure.persistence.entity.MedicoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicoJpaRepository extends JpaRepository<MedicoJpaEntity, UUID> {

    boolean existsByCrm(String crm);

    List<MedicoJpaEntity> findAllByAtivoTrue();
}
