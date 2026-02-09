package com.med.seven.api.paciente;

import com.med.seven.api.endereco.Endereco;

public record DadosRetornoPaciente(Long id, String nome, String datanasc, String email, String telefone, String responsavel, Endereco endereco) {

    public DadosRetornoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getDatanasc(), paciente.getEmail(), paciente.getTelefone(), paciente.getResponsavel(), paciente.getEndereco());
    }
}
