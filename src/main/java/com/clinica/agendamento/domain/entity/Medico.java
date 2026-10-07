package com.clinica.agendamento.domain.entity;

import com.clinica.agendamento.domain.enums.EspecialidadeMedica;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Medico {

    private UUID id;
    private String nome;
    private String crm;
    private Set<EspecialidadeMedica> especialidades;
    private boolean ativo;

    public Medico(String nome, String crm, Set<EspecialidadeMedica> especialidades) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O NOME do médico é OBRIGATÓRIO.");
        }
        if (crm == null || crm.isBlank()) {
            throw new IllegalArgumentException("O CRM do médico é OBRIGATÓRIO.");
        }
        if (especialidades == null || especialidades.isEmpty()) {
            throw new IllegalArgumentException("O médico deve ter pelo menos UMA especialidade.");
        }

        this.id = UUID.randomUUID();
        this.nome = nome;
        this.crm = crm;
        this.especialidades = new HashSet<>(especialidades);
        this.ativo = true;
    }

    public Medico(UUID id, String nome, String crm, Set<EspecialidadeMedica> especialidades, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.especialidades = new HashSet<>(especialidades);
        this.ativo = ativo;
    }

    public void adicionarEspecialidade(EspecialidadeMedica novaEspecialidade) {
        if (novaEspecialidade != null) {
            this.especialidades.add(novaEspecialidade);
        }
    }

    public void inativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getCrm() { return crm; }
    public Set<EspecialidadeMedica> getEspecialidades() {
        return Collections.unmodifiableSet(especialidades);
    }
    public boolean isAtivo() { return ativo; }
}
