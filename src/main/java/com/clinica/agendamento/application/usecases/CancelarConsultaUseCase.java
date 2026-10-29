package com.clinica.agendamento.application.usecases;

import com.clinica.agendamento.application.dto.input.CancelarConsulta;
import com.clinica.agendamento.application.dto.output.ConsultaOutput;
import com.clinica.agendamento.application.gateways.ConsultaRepositoryGateway;
import com.clinica.agendamento.domain.entity.Consulta;

import java.util.UUID;

public class CancelarConsultaUseCase {

    private final ConsultaRepositoryGateway consultaRepositoryGateway;

    public CancelarConsultaUseCase(ConsultaRepositoryGateway consultaRepositoryGateway) {
        this.consultaRepositoryGateway = consultaRepositoryGateway;
    }

    public ConsultaOutput executar(UUID consultaId, CancelarConsulta input) {
        Consulta consulta = consultaRepositoryGateway.buscarPorId(consultaId).orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada, confira novamente o ID"));

        consulta.cancelar(input.motivo());

        Consulta consultaCancelada = consultaRepositoryGateway.salvar(consulta);

        return ConsultaOutput.de(consultaCancelada);
    }
}
