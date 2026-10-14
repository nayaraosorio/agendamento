package com.clinica.agendamento.infrastructure.web;


import com.clinica.agendamento.application.dto.input.CadastrarPaciente;
import com.clinica.agendamento.application.dto.output.PacienteOutput;
import com.clinica.agendamento.application.gateways.PacienteRepositoryGateway;
import com.clinica.agendamento.application.usecases.CadastrarPacienteUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final CadastrarPacienteUseCase cadastrarPacienteUseCase;
    private final PacienteRepositoryGateway pacienteRepositoryGateway;
    public PacienteController(CadastrarPacienteUseCase cadastrarPacienteUseCase,
                              PacienteRepositoryGateway pacienteRepositoryGateway) {
        this.cadastrarPacienteUseCase = cadastrarPacienteUseCase;
        this.pacienteRepositoryGateway = pacienteRepositoryGateway;
    }
    @PostMapping
    public ResponseEntity<PacienteOutput> cadastrar(@RequestBody CadastrarPaciente input) {
        PacienteOutput output = cadastrarPacienteUseCase.executar(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }
    @GetMapping
    public ResponseEntity<List<PacienteOutput>> listarTodos() {
        List<PacienteOutput> pacientes = pacienteRepositoryGateway.buscarTodos()
                .stream()
                .map(PacienteOutput::from)
                .toList();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PacienteOutput> buscarPorCpf(@PathVariable String cpf) {
        return pacienteRepositoryGateway.buscarPorCpf(cpf)
                .map(PacienteOutput::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
