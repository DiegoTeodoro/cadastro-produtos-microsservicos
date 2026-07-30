package com.diego.produto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Map<String, Object>> tratarRegra(RegraNegocioException ex) {
        return ResponseEntity.badRequest().body(erro(ex.getMessage(), 400));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> campos = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> campos.put(e.getField(), e.getDefaultMessage()));

        Map<String, Object> resposta = erro("Dados inválidos", 400);
        resposta.put("campos", campos);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    private Map<String, Object> erro(String mensagem, int status) {
        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("dataHora", LocalDateTime.now());
        resposta.put("status", status);
        resposta.put("erro", mensagem);
        return resposta;
    }
}
