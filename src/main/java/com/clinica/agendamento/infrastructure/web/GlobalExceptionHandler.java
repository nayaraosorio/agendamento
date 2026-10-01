package com.clinica.agendamento.infrastructure.web;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> tratarConflitos(IllegalStateException ex) {
        Map<String, Object>  respostaConflito = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.CONFLICT.value(),
                "mensagem", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(respostaConflito);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> tratarDadosInvalidos(IllegalArgumentException ex) {
        Map<String, Object>  respostaErro = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "mensagem", ex.getMessage()

        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaErro);
    }
}
