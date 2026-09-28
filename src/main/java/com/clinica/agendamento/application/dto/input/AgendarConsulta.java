package com.clinica.agendamento.application.dto.input;

import com.clinica.agendamento.domain.enums.ModalidadeAtendimento;
import com.clinica.agendamento.domain.enums.TipoAtendimento;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgendarConsulta(
        UUID pacienteId,
        UUID medicoId,
        LocalDateTime dataHora,
        TipoAtendimento tipoAtendimento,
        ModalidadeAtendimento modalidadeAtendimento
) {
}
