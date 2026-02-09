package com.med.seven.api.paciente;

public record ConsultaPaciente(Long id, String nome, String email, String datanasc, String responsavel) {

    public ConsultaPaciente(Paciente paciente){
        this(paciente.getId(),paciente.getNome(),paciente.getEmail(), paciente.getDatanasc(), paciente.getResponsavel());
    }
}
