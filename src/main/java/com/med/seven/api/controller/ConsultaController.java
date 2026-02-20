package com.med.seven.api.controller;

import com.med.seven.api.domain.consulta.AgendamentoService;
import com.med.seven.api.domain.consulta.DadosAgendamentoConsulta;
import com.med.seven.api.domain.consulta.DadosCancelamentoConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var agendamento = agendamentoService.agendar(dados);
        return ResponseEntity.ok(agendamento);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agendamentoService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
