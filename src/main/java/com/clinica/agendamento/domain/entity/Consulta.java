package com.clinica.agendamento.domain.entity;

import com.clinica.agendamento.domain.enums.ModalidadeAtendimento;
import com.clinica.agendamento.domain.enums.StatusConsulta;
import com.clinica.agendamento.domain.enums.TipoAtendimento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Consulta {

    private UUID id;
    private UUID pacienteId;
    private UUID medicoId;
    private LocalDateTime dataHora;
    private StatusConsulta status;
    private ModalidadeAtendimento modalidadeAtendimento;
    private TipoAtendimento tipoAtendimento;
    private String motivoCancelamento;

    public Consulta(UUID pacienteId, UUID medicoId, LocalDateTime dataHora, TipoAtendimento tipoAtendimento, String motivoCancelamento, ModalidadeAtendimento modalidadeAtendimento) {

        if(pacienteId == null) {
            throw new IllegalArgumentException("Para agendar a consulta, deve adiconar um Paciente.");
        }
        if(medicoId == null) {
            throw new IllegalArgumentException("Para agendar a consulta, deve adiconar um Médico.");
        }
        if(dataHora == null || dataHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("PA data da consulta deve ser em um dia futuro.");
        }
        if(tipoAtendimento == null) {
            throw new IllegalArgumentException("Tipo atendimento é obrigatório.");
        }
        if(modalidadeAtendimento == null) {
            throw new IllegalArgumentException("Modalidade de atendimento é obrigatório");
        }

        this.id = UUID.randomUUID();
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataHora = dataHora;
        this.tipoAtendimento = tipoAtendimento;
        this.modalidadeAtendimento = modalidadeAtendimento;
        this.status = StatusConsulta.AGENDADA;
    }



}
