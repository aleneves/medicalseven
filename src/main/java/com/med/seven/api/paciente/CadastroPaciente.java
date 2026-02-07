package com.med.seven.api.paciente;

import com.med.seven.api.endereco.CadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastroPaciente(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(
                regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$",
                message = "Data de nascimento deve estar no formato dd/MM/yyyy"
        )
        String datanasc,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "^\\(?[1-9]{2}\\)?\\s?9?\\d{4}-?\\d{4}$")
        String telefone,
        @NotBlank
        String responsavel,
        @NotNull
        @Valid
        CadastroEndereco endereco

) {
}
