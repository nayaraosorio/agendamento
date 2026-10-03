package com.clinica.agendamento.infrastructure.persistence.entity;

import com.clinica.agendamento.domain.entity.Medico;
import com.clinica.agendamento.domain.enums.EspecialidadeMedica;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_medicos")
public class MedicoJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String crm;

    @ElementCollection(targetClass = EspecialidadeMedica.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_medico_especialidades", joinColumns = @JoinColumn(name = "medico_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "especialidade", nullable = false)
    private Set<EspecialidadeMedica> especialidades = new HashSet<>();

    @Column(nullable = false)
    private boolean ativo;

    public MedicoJpaEntity() {
    }

    public MedicoJpaEntity(UUID id, String nome, String crm, Set<EspecialidadeMedica> especialidades, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.especialidades = especialidades;
        this.ativo = ativo;
    }

    public static MedicoJpaEntity from(Medico medico) {
        return new MedicoJpaEntity(
                medico.getId(),
                medico.getNome(),
                medico.getCrm(),
                medico.getEspecialidades(),
                medico.isAtivo()
        );
    }

    public Medico paraDominio() {
        return new Medico(
                this.id,
                this.nome,
                this.crm,
                this.especialidades,
                this.ativo
        );
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }

    public Set<EspecialidadeMedica> getEspecialidades() { return especialidades; }
    public void setEspecialidades(Set<EspecialidadeMedica> especialidades) { this.especialidades = especialidades; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
