package com.med.seven.api.domain.paciente;

import com.med.seven.api.domain.endereco.AtualizaEndereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record AtualizaPaciente(
        String nome,
        @Pattern(
                regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$",
                message = "Data de nascimento deve estar no formato dd/MM/yyyy"
        )
        String datanasc,
        @Email
        String email,
        @Pattern(
                regexp = "^\\(?[1-9]{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
                message = "Telefone inválido"
        )
        String telefone,
        String responsavel,
        AtualizaEndereco endereco) {
}
