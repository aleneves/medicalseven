package com.med.seven.api.infra;

import com.med.seven.api.domain.medico.DadosRetornoMedico;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErroNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErroBadRequest(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosRetornoErros::new).toList());
    }

    private record DadosRetornoErros(String campo, String mensagem){
        public DadosRetornoErros(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
