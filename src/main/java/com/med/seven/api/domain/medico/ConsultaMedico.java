package com.med.seven.api.domain.medico;

public record ConsultaMedico(Long id, String nome, String crm, String email, Especialidade especialidade) {

    public ConsultaMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEmail(),medico.getEspecialidade());
    }
}
