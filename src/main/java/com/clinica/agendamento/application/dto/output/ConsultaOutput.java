package com.clinica.agendamento.application.dto.output;

import com.clinica.agendamento.domain.entity.Consulta;
import com.clinica.agendamento.domain.enums.ModalidadeAtendimento;
import com.clinica.agendamento.domain.enums.StatusConsulta;
import com.clinica.agendamento.domain.enums.TipoAtendimento;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaOutput (
        UUID id,
        UUID pacienteId,
        UUID medicoId,
        LocalDateTime dataHora,
        StatusConsulta status,
        TipoAtendimento tipoAtendimento,
        ModalidadeAtendimento modalidadeAtendimento

){
    public static ConsultaOutput de(Consulta consulta) {
        return new ConsultaOutput(
                consulta.getId(),
                consulta.getPacienteId(),
                consulta.getMedicoId(),
                consulta.getDataHora(),
                consulta.getStatus(),
                consulta.getTipoAtendimento(),
                consulta.getModalidadeAtendimento()
        );
    }




}
