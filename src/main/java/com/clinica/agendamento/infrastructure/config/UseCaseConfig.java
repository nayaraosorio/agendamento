package com.clinica.agendamento.infrastructure.config;



import com.clinica.agendamento.application.gateways.ConsultaRepositoryGateway;
import com.clinica.agendamento.application.usecases.AgendarConsultaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public AgendarConsultaUseCase agendarConsultaUseCase(ConsultaRepositoryGateway consultaRepositoryGateway) {
        return new AgendarConsultaUseCase(consultaRepositoryGateway);
    }
}
