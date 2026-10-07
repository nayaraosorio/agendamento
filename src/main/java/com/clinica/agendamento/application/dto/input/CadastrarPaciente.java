package com.clinica.agendamento.application.dto.input;

public record CadastrarPaciente(
        String nome,
        String email,
        String cpf,
        String telefone
) {
}
