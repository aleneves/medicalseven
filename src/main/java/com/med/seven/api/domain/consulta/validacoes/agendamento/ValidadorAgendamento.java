package com.med.seven.api.domain.consulta.validacoes.agendamento;

import com.med.seven.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamento {
    void validar(DadosAgendamentoConsulta agendamento);
}
