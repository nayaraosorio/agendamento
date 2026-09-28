package com.clinica.agendamento.application.usecases;

import com.clinica.agendamento.application.dto.input.AgendarConsulta;
import com.clinica.agendamento.application.dto.output.ConsultaOutput;
import com.clinica.agendamento.application.gateways.ConsultaRepositoryGateway;
import com.clinica.agendamento.domain.entity.Consulta;

public class AgendarConsultaUseCase {

    private final ConsultaRepositoryGateway consultaRepositoryGateway;

    public AgendarConsultaUseCase(ConsultaRepositoryGateway consultaRepositoryGateway) {
        this.consultaRepositoryGateway = consultaRepositoryGateway;
    }

    public ConsultaOutput executar(AgendarConsulta input) {
        boolean medicoOcupado = consultaRepositoryGateway.consultaAgendada(
                input.medicoId(),
                input.dataHora()
        );

        if(medicoOcupado) {
            throw new IllegalStateException("Médico já possui consulta para o horário.");
        }

        Consulta novaConsulta = new Consulta(
                input.pacienteId(),
                input.medicoId(),
                input.dataHora(),
                input.tipoAtendimento(),
                input.modalidadeAtendimento()

        );

        Consulta consultaAgendada = consultaRepositoryGateway.salvar(novaConsulta);

        return ConsultaOutput.de(consultaAgendada);
    }




}
