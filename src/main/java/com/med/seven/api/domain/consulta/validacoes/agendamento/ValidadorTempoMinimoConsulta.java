package com.med.seven.api.domain.consulta.validacoes.agendamento;

import com.med.seven.api.domain.consulta.DadosAgendamentoConsulta;
import com.med.seven.api.domain.exception.ValidacaoAgendamento;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorTempoMinimoConsulta implements ValidadorAgendamento {
    public void validar(DadosAgendamentoConsulta agendamento){
        var dataAgendamento = agendamento.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(dataAgendamento, agora).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoAgendamento("Tempo mínimo de consulta deve ser 30 minutos ou mais.");
        }
    }
}
