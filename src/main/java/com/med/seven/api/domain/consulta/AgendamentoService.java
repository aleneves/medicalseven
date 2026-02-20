package com.med.seven.api.domain.consulta;

import com.med.seven.api.domain.consulta.validacoes.agendamento.ValidadorAgendamento;
import com.med.seven.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamento;
import com.med.seven.api.domain.exception.ValidacaoAgendamento;
import com.med.seven.api.domain.medico.Medico;
import com.med.seven.api.domain.medico.MedicoRepository;
import com.med.seven.api.domain.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamento> validaAgendamento;

    @Autowired
    private List<ValidadorCancelamento> validaCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoAgendamento("Paciente não encontrado!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoAgendamento("Médico não encontrado!");
        }

        validaAgendamento.forEach(validador -> validador.validar(dados));

        var medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoAgendamento("Não existem médicos disponíveis para a data solicitada!");
        }
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() != null) {
            throw new ValidacaoAgendamento("Especialidade deve ser informado quando o médico não for indicado.");
        }

        return medicoRepository.findMedicoByEspecialidadeAndData(dados.especialidade(), dados.data());
    }

    public void cancelar(@Valid DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoAgendamento("Id da consulta informado não existe!");
        }

        validaCancelamento.forEach(validador -> validador.validar(dados));


        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
