package com.clinica.agendamento.infrastructure.web;

import com.clinica.agendamento.application.dto.input.CadastrarMedico;
import com.clinica.agendamento.application.dto.output.MedicoOutput;
import com.clinica.agendamento.application.gateways.MedicoRepositoryGateway;
import com.clinica.agendamento.application.usecases.CadastrarMedicoUseCase;
import com.clinica.agendamento.infrastructure.persistence.entity.MedicoJpaEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
     private final CadastrarMedicoUseCase cadastrarMedicoUseCase;
     private final MedicoRepositoryGateway medicoRepositoryGateway;

     public MedicoController(CadastrarMedicoUseCase cadastrarMedicoUseCase, MedicoRepositoryGateway medicoRepositoryGateway) {
         this.cadastrarMedicoUseCase = cadastrarMedicoUseCase;
         this.medicoRepositoryGateway = medicoRepositoryGateway;
     }

     @PostMapping
    public ResponseEntity<MedicoOutput> cadastrar(@RequestBody CadastrarMedico input) {
         MedicoOutput  output = cadastrarMedicoUseCase.executar(input);
         return ResponseEntity.status(HttpStatus.CREATED).body(output);
     }

     @GetMapping
    public ResponseEntity<List<MedicoOutput>> listarTodos() {
         List<MedicoOutput> medicos = medicoRepositoryGateway.buscarTodos()
                 .stream()
                 .map(MedicoOutput:: from)
                 .toList();
         return ResponseEntity.ok(medicos);
     }

}
