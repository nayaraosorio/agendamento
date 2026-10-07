package com.clinica.agendamento.domain.entity;

import java.util.UUID;

public class Paciente {

    private UUID id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private boolean ativo;

    public Paciente(String nome, String cpf, String email, String telefone) {
        validarNome(nome);
        validarCpf(cpf);
        validarEmail(email);
        validarTelefone(telefone);

        this.id = UUID.randomUUID();
        this.nome = nome.trim();
        this.cpf = cpf.replaceAll("\\D", "");
        this.email = (email != null && !email.isBlank()) ? email.trim().toLowerCase() : null;
        this.telefone = telefone.replaceAll("\\D", "");
        this.ativo = true;
    }


    public Paciente(UUID id, String nome, String cpf, String email, String telefone, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.ativo = ativo;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O NOME do paciente é OBRIGATÓRIO.");
        }
    }

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("O CPF do paciente é OBRIGATÓRIO.");
        }
        String cpfLimpo = cpf.replaceAll("\\D", "");
        if (cpfLimpo.length() != 11 || cpfLimpo.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("O CPF informado é INVÁLIDO (deve conter 11 dígitos válidos).");
        }
    }

    private void validarEmail(String email) {
        if (email == null || email.isBlank()) {
            return;
        }
        String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.trim().matches(regexEmail)) {
            throw new IllegalArgumentException("O EMAIL informado é INVÁLIDO.");
        }
    }

    private void validarTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("O TELEFONE do paciente é OBRIGATÓRIO.");
        }
        String telefoneLimpo = telefone.replaceAll("\\D", "");
        if (telefoneLimpo.length() < 10 || telefoneLimpo.length() > 11) {
            throw new IllegalArgumentException("O TELEFONE deve conter DDD e ter entre 10 e 11 dígitos.");
        }
    }


    public void inativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void atualizarContato(String email, String telefone) {
        validarEmail(email);
        validarTelefone(telefone);
        this.email = (email != null && !email.isBlank()) ? email.trim().toLowerCase() : null;
        this.telefone = telefone.replaceAll("\\D", "");
    }


    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
