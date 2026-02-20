package com.med.seven.api.controller;

import com.med.seven.api.domain.paciente.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosRetornoPaciente> cadastrar(@RequestBody @Valid CadastroPaciente entrada, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(entrada);
        repository.save(paciente);
        var URI = uriBuilder.path("pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(URI).body(new DadosRetornoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaPaciente>> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page =  repository.findAll(paginacao).map(ConsultaPaciente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar (@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosRetornoPaciente(paciente));
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosRetornoPaciente> atualizar (@RequestBody @Valid AtualizaPaciente entrada, @PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.atualizar(entrada);
        return ResponseEntity.ok(new DadosRetornoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir (@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
