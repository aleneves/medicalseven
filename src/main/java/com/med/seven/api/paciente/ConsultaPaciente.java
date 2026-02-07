package com.med.seven.api.paciente;

public record ConsultaPaciente(String nome, String email, String datanasc, String responsavel) {

    public ConsultaPaciente(Paciente paciente){
        this(paciente.getNome(),paciente.getEmail(), paciente.getDatanasc(), paciente.getResponsavel());
    }
}
