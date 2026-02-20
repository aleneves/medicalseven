package com.med.seven.api.domain.medico;

import com.med.seven.api.domain.endereco.CadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastroMedico(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(
                regexp = "^\\(?[1-9]{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
                message = "Telefone inválido"
        )
        String telefone,
        @NotBlank
        @Pattern(regexp="\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        CadastroEndereco endereco) {
}
