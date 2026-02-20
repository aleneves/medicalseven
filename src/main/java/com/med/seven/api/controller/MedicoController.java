package com.med.seven.api.controller;

import com.med.seven.api.domain.medico.*;
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
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosRetornoMedico> cadastrar(@RequestBody @Valid CadastroMedico entrada, UriComponentsBuilder uriBuilder){
        var medico = new Medico(entrada);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosRetornoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaMedico>> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page =  repository.findAll(paginacao).map(ConsultaMedico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosRetornoMedico(medico));
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosRetornoMedico> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AtualizaMedico dados) {

        Medico medico = repository.getReferenceById(id);
        medico.atualizar(dados);
        return ResponseEntity.ok(new DadosRetornoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
