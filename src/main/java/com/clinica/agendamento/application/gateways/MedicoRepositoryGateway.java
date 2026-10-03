package com.clinica.agendamento.application.gateways;

import com.clinica.agendamento.domain.entity.Medico;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicoRepositoryGateway {

    Medico salvar(Medico medico);
    Optional<Medico> buscarPorId(UUID id);
    boolean existePorId(UUID id);
    boolean existePorCrm(String crm);
    List<Medico> buscarTodos();
}
