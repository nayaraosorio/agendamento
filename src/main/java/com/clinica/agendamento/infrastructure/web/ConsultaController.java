package com.clinica.agendamento.infrastructure.web;

import com.clinica.agendamento.application.dto.input.AgendarConsulta;
import com.clinica.agendamento.application.dto.output.ConsultaOutput;
import com.clinica.agendamento.application.usecases.AgendarConsultaUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final AgendarConsultaUseCase agendarConsultaUseCase;

    public ConsultaController(AgendarConsultaUseCase agendarConsultaUseCase) {
        this.agendarConsultaUseCase = agendarConsultaUseCase;
    }

    @PostMapping
    public ResponseEntity<ConsultaOutput> agendar(@RequestBody AgendarConsulta input){
        ConsultaOutput output = agendarConsultaUseCase.executar(input);

        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }
}
