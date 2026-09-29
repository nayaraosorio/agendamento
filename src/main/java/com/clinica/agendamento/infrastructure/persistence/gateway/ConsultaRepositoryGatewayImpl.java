package com.clinica.agendamento.infrastructure.persistence.gateway;

import com.clinica.agendamento.application.gateways.ConsultaRepositoryGateway;
import com.clinica.agendamento.domain.entity.Consulta;
import com.clinica.agendamento.infrastructure.persistence.entity.ConsultaJpaEntity;
import com.clinica.agendamento.infrastructure.persistence.repository.ConsultaJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class ConsultaRepositoryGatewayImpl implements ConsultaRepositoryGateway {

    private final ConsultaJpaRepository consultaJpaRepository;

    public ConsultaRepositoryGatewayImpl(ConsultaJpaRepository consultaJpaRepository) {
        this.consultaJpaRepository = consultaJpaRepository;
    }

    @Override
    public Consulta salvar(Consulta consulta) {
        ConsultaJpaEntity jpaEntity = ConsultaJpaEntity.da(consulta);
        ConsultaJpaEntity entidadeSalva = consultaJpaRepository.save(jpaEntity);
        return entidadeSalva.paraDominio();
    }

    @Override
    public Optional<Consulta> buscarPorId(UUID id) {
        return consultaJpaRepository.findById(id)
                .map(ConsultaJpaEntity::paraDominio);
    }

    @Override
    public boolean consultaAgendada(UUID medicoId, LocalDateTime dataHora) {
        return consultaJpaRepository.existsByMedicoIdAndDataHora(medicoId, dataHora);
    }
}
