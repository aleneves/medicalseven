package com.med.seven.api.domain.consulta.validacoes.agendamento;

import com.med.seven.api.domain.consulta.DadosAgendamentoConsulta;
import com.med.seven.api.domain.exception.ValidacaoAgendamento;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamento {

    public void validar(DadosAgendamentoConsulta agendamento){
        var dataConsulta = agendamento.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoAgendamento("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
