package com.med.seven.api.controller;

import com.med.seven.api.endereco.Endereco;
import com.med.seven.api.medico.CadastroMedico;
import com.med.seven.api.medico.Medico;
import com.med.seven.api.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
