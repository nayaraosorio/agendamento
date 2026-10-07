package com.clinica.agendamento.application.dto.output;

import com.clinica.agendamento.domain.entity.Paciente;

import java.util.UUID;

public record PacienteOutput(
        UUID id,
        String nome,
        String cpf,
        String email,
        String telefone,
        boolean ativo
) {
    public static PacienteOutput from(Paciente paciente) {
        return new PacienteOutput(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.isAtivo()
        );
    }
}
