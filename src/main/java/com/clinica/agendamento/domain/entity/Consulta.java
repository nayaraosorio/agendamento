package com.clinica.agendamento.domain.entity;

import com.clinica.agendamento.domain.enums.ModalidadeAtendimento;
import com.clinica.agendamento.domain.enums.StatusConsulta;
import com.clinica.agendamento.domain.enums.TipoAtendimento;

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

    public Consulta(UUID pacienteId, UUID medicoId, LocalDateTime dataHora, TipoAtendimento tipoAtendimento, ModalidadeAtendimento modalidadeAtendimento) {

        if (pacienteId == null) {
            throw new IllegalArgumentException("Para agendar a consulta, deve adiconar um Paciente.");
        }
        if (medicoId == null) {
            throw new IllegalArgumentException("Para agendar a consulta, deve adiconar um Médico.");
        }
        if (dataHora == null || dataHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data da consulta deve ser em um dia futuro.");
        }
        if (tipoAtendimento == null) {
            throw new IllegalArgumentException("Tipo atendimento é obrigatório.");
        }
        if (modalidadeAtendimento == null) {
            throw new IllegalArgumentException("Modalidade de atendimento é obrigatória");
        }

        this.id = UUID.randomUUID();
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataHora = dataHora;
        this.tipoAtendimento = tipoAtendimento;
        this.modalidadeAtendimento = modalidadeAtendimento;
        this.status = StatusConsulta.AGENDADA;
    }

    public void cancelar(String motivo) {
        if (this.status != StatusConsulta.AGENDADA) {
            throw new IllegalArgumentException("Apenas consulta com status AGENDADA pode ser CANCELADA.");
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException("O motivo de cancelamento deve ser informado.");
        }

        this.status = StatusConsulta.CANCELADA;
        this.motivoCancelamento = motivo;
    }

    public void realizar() {
        if (this.status != StatusConsulta.AGENDADA) {
            throw new IllegalStateException("Esta consulta não está agendada");

        }
        this.status = StatusConsulta.REALIZADA;
    }

    public void reagendar(LocalDateTime dataHora) {
        if (this.status != StatusConsulta.AGENDADA) {
            throw new IllegalStateException("Apenas consultas AGENDADAS podem ser REAGENDADAS.");
        }
        if (dataHora == null || dataHora.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("A nova data da consulta deve ser em um dia futuro.");
        }
        this.dataHora = dataHora;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPacienteId() {
        return pacienteId;
    }

    public UUID getMedicoId() {
        return medicoId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public ModalidadeAtendimento getModalidadeAtendimento() {
        return modalidadeAtendimento;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }
}

