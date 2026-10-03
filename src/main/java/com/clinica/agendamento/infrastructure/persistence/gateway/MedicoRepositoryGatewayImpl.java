package com.clinica.agendamento.infrastructure.persistence.gateway;

import com.clinica.agendamento.application.gateways.MedicoRepositoryGateway;
import com.clinica.agendamento.domain.entity.Medico;
import com.clinica.agendamento.infrastructure.persistence.entity.MedicoJpaEntity;
import com.clinica.agendamento.infrastructure.persistence.repository.MedicoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class MedicoRepositoryGatewayImpl implements MedicoRepositoryGateway {

    private final MedicoJpaRepository medicoJpaRepository;

    public MedicoRepositoryGatewayImpl(MedicoJpaRepository medicoJpaRepository) {
        this.medicoJpaRepository = medicoJpaRepository;
    }

    @Override
    public Medico salvar(Medico medico) {
        MedicoJpaEntity jpaEntity = MedicoJpaEntity.from(medico);
        MedicoJpaEntity entidadeSalva = medicoJpaRepository.save(jpaEntity);
        return entidadeSalva.paraDominio();
    }

    @Override
    public Optional<Medico> buscarPorId(UUID id) {
        return medicoJpaRepository.findById(id)
                .map(MedicoJpaEntity::paraDominio);
    }

    @Override
    public boolean existePorId(UUID id) {
        return medicoJpaRepository.existsById(id);
    }

    @Override
    public boolean existePorCrm(String crm) {
        return medicoJpaRepository.existsByCrm(crm);
    }

    @Override
    public List<Medico> buscarTodos() {
        return medicoJpaRepository.findAllByAtivoTrue()
                .stream()
                .map(MedicoJpaEntity::paraDominio)
                .toList();
    }
}
