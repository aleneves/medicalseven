package com.med.seven.api.controller;

import com.med.seven.api.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CadastroMedico entrada){
        repository.save(new Medico(entrada));
    }

    @GetMapping
    public Page<ConsultaMedico> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(ConsultaMedico::new);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AtualizaMedico dados) {

        Medico medico = repository.getReferenceById(id);
        medico.atualizar(dados);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
