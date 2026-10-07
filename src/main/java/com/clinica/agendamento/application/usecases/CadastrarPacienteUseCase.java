package com.clinica.agendamento.application.usecases;

import com.clinica.agendamento.application.dto.input.CadastrarPaciente;
import com.clinica.agendamento.application.dto.output.PacienteOutput;
import com.clinica.agendamento.application.gateways.PacienteRepositoryGateway;
import com.clinica.agendamento.domain.entity.Paciente;

public class CadastrarPacienteUseCase {

    private final PacienteRepositoryGateway pacienteRepositoryGateway;

    public CadastrarPacienteUseCase(PacienteRepositoryGateway pacienteRepositoryGateway) {
        this.pacienteRepositoryGateway = pacienteRepositoryGateway;
    }

    public PacienteOutput executar(CadastrarPaciente input){
        if (pacienteRepositoryGateway.existePorCpf(input.cpf())) {
            throw new IllegalStateException("Já existe um paciente cadastrado com o CPF informado.");
        }
        Paciente novoPaciente = new Paciente(
                input.nome(),
                input.cpf(),
                input.email(),
                input.telefone()
        );

        Paciente pacienteSalvo = pacienteRepositoryGateway.salvar(novoPaciente);
        return PacienteOutput.from(pacienteSalvo);
    }
}
