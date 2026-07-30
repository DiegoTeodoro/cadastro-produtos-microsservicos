package com.diego.estoque.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Map<String, Object>> tratar(RegraNegocioException ex) {
        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("dataHora", LocalDateTime.now());
        resposta.put("status", 400);
        resposta.put("erro", ex.getMessage());
        return ResponseEntity.badRequest().body(resposta);
    }
}
