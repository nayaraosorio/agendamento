package com.clinica.agendamento.application.dto.input;

import com.clinica.agendamento.domain.enums.EspecialidadeMedica;

import java.util.Set;

public record CadastrarMedico (

        String nome,
        String crm,
        Set<EspecialidadeMedica> especialidades
){

}
