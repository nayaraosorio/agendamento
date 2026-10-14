package com.clinica.agendamento.infrastructure.persistence.entity;


import com.clinica.agendamento.domain.entity.Paciente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table( name = "tb_pacientes")
public class PacienteJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = true)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private boolean ativo;


    public PacienteJpaEntity() {}

    public PacienteJpaEntity(UUID id, String nome, String cpf, String email, String telefone, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.ativo = ativo;
    }

    public static PacienteJpaEntity from(Paciente paciente) {
        return new PacienteJpaEntity(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.isAtivo()
        );
    }

    public Paciente paraDominio(){
        return new Paciente(this.id, this.nome, this.cpf, this.email, this.telefone, this.ativo);
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
