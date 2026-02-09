package com.med.seven.api.controller;

import com.med.seven.api.paciente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CadastroPaciente paciente){
        repository.save(new Paciente(paciente));
    }

    @GetMapping
    public Page<ConsultaPaciente> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(ConsultaPaciente::new);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosRetornoPaciente> atualizar (@RequestBody @Valid AtualizaPaciente entrada, @PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.atualizar(entrada);
        return ResponseEntity.ok(new DadosRetornoPaciente(paciente));
    }
}
