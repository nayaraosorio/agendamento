package com.clinica.agendamento.application.usecases;

import com.clinica.agendamento.application.dto.input.CadastrarMedico;
import com.clinica.agendamento.application.dto.output.MedicoOutput;
import com.clinica.agendamento.application.gateways.MedicoRepositoryGateway;
import com.clinica.agendamento.domain.entity.Medico;

public class CadastrarMedicoUseCase {

    private final MedicoRepositoryGateway medicoRepositoryGateway;

    public CadastrarMedicoUseCase(MedicoRepositoryGateway medicoRepositoryGateway) {
        this.medicoRepositoryGateway = medicoRepositoryGateway;
    }

    public MedicoOutput executar(CadastrarMedico input) {
        if (medicoRepositoryGateway.existePorCrm(input.crm())) {
            throw new IllegalStateException("Já existe um médico cadastrado com o CRM informado.");
        }

        Medico novoMedico = new Medico(
                input.nome(),
                input.crm(),
                input.especialidades()
        );

        Medico medicoSalvo = medicoRepositoryGateway.salvar(novoMedico);

        return MedicoOutput.from(medicoSalvo);
    }
}
