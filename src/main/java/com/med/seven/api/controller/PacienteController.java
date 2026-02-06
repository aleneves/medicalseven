package com.med.seven.api.controller;

import com.med.seven.api.paciente.CadastroPaciente;
import com.med.seven.api.paciente.Paciente;
import com.med.seven.api.paciente.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
