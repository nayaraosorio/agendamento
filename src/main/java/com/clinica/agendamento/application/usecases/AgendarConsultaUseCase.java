package com.clinica.agendamento.application.usecases;

import com.clinica.agendamento.application.dto.input.AgendarConsulta;
import com.clinica.agendamento.application.dto.output.ConsultaOutput;
import com.clinica.agendamento.application.gateways.ConsultaRepositoryGateway;
import com.clinica.agendamento.application.gateways.MedicoRepositoryGateway;
import com.clinica.agendamento.application.gateways.PacienteRepositoryGateway;
import com.clinica.agendamento.domain.entity.Consulta;
import com.clinica.agendamento.domain.entity.Medico;
import com.clinica.agendamento.domain.entity.Paciente;

public class AgendarConsultaUseCase {

    private final ConsultaRepositoryGateway consultaRepositoryGateway;
    private final PacienteRepositoryGateway pacienteRepositoryGateway;
    private final MedicoRepositoryGateway medicoRepositoryGateway;

    public AgendarConsultaUseCase(ConsultaRepositoryGateway consultaRepositoryGateway, PacienteRepositoryGateway pacienteRepositoryGateway, MedicoRepositoryGateway medicoRepositoryGateway) {
        this.consultaRepositoryGateway = consultaRepositoryGateway;
        this.pacienteRepositoryGateway = pacienteRepositoryGateway;
        this.medicoRepositoryGateway = medicoRepositoryGateway;
    }

    public ConsultaOutput executar(AgendarConsulta input) {

        Paciente paciente = pacienteRepositoryGateway.buscarPorId(input.pacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com o ID informado."));
        if (!paciente.isAtivo()) {
            throw new IllegalStateException("Não é possível agendar consulta para um paciente inativo.");
        }

        Medico medico = medicoRepositoryGateway.buscarPorId(input.medicoId())
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado com o ID informado."));
        if (!medico.isAtivo()) {
            throw new IllegalStateException("Não é possível agendar consulta com um médico inativo.");
        }

        boolean medicoOcupado = consultaRepositoryGateway.consultaAgendada(
                input.medicoId(),
                input.dataHora()
        );
        if (medicoOcupado) {
            throw new IllegalStateException("Médico já possui consulta agendada para este horário.");
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
