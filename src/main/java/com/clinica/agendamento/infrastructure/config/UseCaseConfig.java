package com.clinica.agendamento.infrastructure.config;



import com.clinica.agendamento.application.gateways.ConsultaRepositoryGateway;
import com.clinica.agendamento.application.gateways.MedicoRepositoryGateway;
import com.clinica.agendamento.application.gateways.PacienteRepositoryGateway;
import com.clinica.agendamento.application.usecases.AgendarConsultaUseCase;
import com.clinica.agendamento.application.usecases.CadastrarMedicoUseCase;
import com.clinica.agendamento.application.usecases.CadastrarPacienteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public AgendarConsultaUseCase agendarConsultaUseCase(
            ConsultaRepositoryGateway consultaRepositoryGateway,
            PacienteRepositoryGateway pacienteRepositoryGateway,
            MedicoRepositoryGateway medicoRepositoryGateway
    ) {
        return new AgendarConsultaUseCase(
                consultaRepositoryGateway,
                pacienteRepositoryGateway,
                medicoRepositoryGateway
        );
    }

    @Bean
   public CadastrarMedicoUseCase cadastrarMedicoUseCase(MedicoRepositoryGateway medicoRepositoryGateway) {
        return new CadastrarMedicoUseCase(medicoRepositoryGateway);
    }

    @Bean
    public CadastrarPacienteUseCase cadastrarPacienteUseCase(PacienteRepositoryGateway pacienteRepositoryGateway) {
        return new CadastrarPacienteUseCase(pacienteRepositoryGateway);
    }
}
