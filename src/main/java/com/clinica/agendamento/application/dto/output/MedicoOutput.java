package com.clinica.agendamento.application.dto.output;

import com.clinica.agendamento.domain.entity.Medico;
import com.clinica.agendamento.domain.enums.EspecialidadeMedica;

import java.util.Set;
import java.util.UUID;

public record MedicoOutput(
        UUID id,
        String nome,
        String crm,
        Set<EspecialidadeMedica> especialidades,
        boolean ativo
) {
    public static MedicoOutput from(Medico medico) {
        return new MedicoOutput(
                medico.getId(),
                medico.getNome(),
                medico.getCrm(),
                medico.getEspecialidades(),
                medico.isAtivo()

        );
    }
}
