package com.clinica.agendamento.infrastructure.persistence.gateway;

import com.clinica.agendamento.application.gateways.PacienteRepositoryGateway;
import com.clinica.agendamento.domain.entity.Paciente;
import com.clinica.agendamento.infrastructure.persistence.entity.PacienteJpaEntity;
import com.clinica.agendamento.infrastructure.persistence.repository.PacienteJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PacienteRepositoryGatewayImpl implements PacienteRepositoryGateway {

    private final PacienteJpaRepository pacienteJpaRepository;

    public PacienteRepositoryGatewayImpl(PacienteJpaRepository pacienteJpaRepository) {
        this.pacienteJpaRepository = pacienteJpaRepository;
    }

    @Override
    public Paciente salvar(Paciente paciente) {
        PacienteJpaEntity jpaEntity = PacienteJpaEntity.from(paciente);
        PacienteJpaEntity entidadeSalva = pacienteJpaRepository.save(jpaEntity);
        return entidadeSalva.paraDominio();
    }

    @Override
    public Optional<Paciente> buscarPorId(UUID id){
        return pacienteJpaRepository.findById(id)
                .map(PacienteJpaEntity::paraDominio);
    }

    @Override
    public boolean existePorId(UUID id) {
        return pacienteJpaRepository.existsById(id);
    }
    @Override
    public Optional<Paciente> buscarPorCpf(String cpf) {
        return pacienteJpaRepository.findByCpf(cpf)
                .map(PacienteJpaEntity::paraDominio);
    }
    @Override
    public boolean existePorCpf(String cpf) {
        return pacienteJpaRepository.existsByCpf(cpf);
    }
    @Override
    public List<Paciente> buscarTodos() {
        return pacienteJpaRepository.findAllByAtivoTrue()
                .stream()
                .map(PacienteJpaEntity::paraDominio)
                .toList();
    }




}
