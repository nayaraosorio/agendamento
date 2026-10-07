package com.clinica.agendamento.application.gateways;

import com.clinica.agendamento.domain.entity.Paciente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PacienteRepositoryGateway {
    Paciente salvar(Paciente paciente);
    Optional<Paciente> buscarPorId(UUID id);
    boolean existePorId(UUID id);

    Optional<Paciente> buscarPorCpf(String cpf);
    boolean existePorCpf(String cpf);

    List<Paciente> buscarTodos();
}
