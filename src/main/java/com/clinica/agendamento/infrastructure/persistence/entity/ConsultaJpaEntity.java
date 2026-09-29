package com.clinica.agendamento.infrastructure.persistence.entity;

import com.clinica.agendamento.domain.entity.Consulta;
import com.clinica.agendamento.domain.enums.ModalidadeAtendimento;
import com.clinica.agendamento.domain.enums.StatusConsulta;
import com.clinica.agendamento.domain.enums.TipoAtendimento;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_consultas")
public class ConsultaJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID pacienteId;

    @Column(nullable = false)
    private UUID medicoId;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConsulta status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAtendimento tipoAtendimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModalidadeAtendimento modalidadeAtendimento;

    private String motivoCancelamento;

    public ConsultaJpaEntity() {
    }

    public ConsultaJpaEntity(UUID id, UUID pacienteId, UUID medicoId, LocalDateTime dataHora,
                             StatusConsulta status, TipoAtendimento tipoAtendimento,
                             ModalidadeAtendimento modalidadeAtendimento, String motivoCancelamento) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataHora = dataHora;
        this.status = status;
        this.tipoAtendimento = tipoAtendimento;
        this.modalidadeAtendimento = modalidadeAtendimento;
        this.motivoCancelamento = motivoCancelamento;
    }

    public static ConsultaJpaEntity da(Consulta consulta) {
        return new ConsultaJpaEntity(
                consulta.getId(),
                consulta.getPacienteId(),
                consulta.getMedicoId(),
                consulta.getDataHora(),
                consulta.getStatus(),
                consulta.getTipoAtendimento(),
                consulta.getModalidadeAtendimento(),
                consulta.getMotivoCancelamento()
        );
    }

    public Consulta paraDominio() {
        return new Consulta(
                this.pacienteId,
                this.medicoId,
                this.dataHora,
                this.tipoAtendimento,
                this.modalidadeAtendimento
        );
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getPacienteId() { return pacienteId; }
    public void setPacienteId(UUID pacienteId) { this.pacienteId = pacienteId; }

    public UUID getMedicoId() { return medicoId; }
    public void setMedicoId(UUID medicoId) { this.medicoId = medicoId; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public StatusConsulta getStatus() { return status; }
    public void setStatus(StatusConsulta status) { this.status = status; }

    public TipoAtendimento getTipoAtendimento() { return tipoAtendimento; }
    public void setTipoAtendimento(TipoAtendimento tipoAtendimento) { this.tipoAtendimento = tipoAtendimento; }

    public ModalidadeAtendimento getModalidadeAtendimento() { return modalidadeAtendimento; }
    public void setModalidadeAtendimento(ModalidadeAtendimento modalidadeAtendimento) { this.modalidadeAtendimento = modalidadeAtendimento; }

    public String getMotivoCancelamento() { return motivoCancelamento; }
    public void setMotivoCancelamento(String motivoCancelamento) { this.motivoCancelamento = motivoCancelamento; }
}
