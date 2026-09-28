package com.clinica.agendamento.application.gateways;

import com.clinica.agendamento.domain.entity.Consulta;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface ConsultaRepositoryGateway {
    Consulta salvar(Consulta consulta);

    Optional<Consulta> buscarPorId(UUID id);

    boolean consultaAgendada(UUID medicoId, LocalDateTime dataHora);
}
