package com.med.seven.api.controller;

import com.med.seven.api.endereco.Endereco;
import com.med.seven.api.medico.CadastroMedico;
import com.med.seven.api.medico.ConsultaMedico;
import com.med.seven.api.medico.Medico;
import com.med.seven.api.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
