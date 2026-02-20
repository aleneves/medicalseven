package com.med.seven.api.domain.medico;

import com.med.seven.api.domain.endereco.AtualizaEndereco;
import jakarta.validation.constraints.Pattern;

public record AtualizaMedico(
        String nome,
        @Pattern(
                regexp = "^\\(?[1-9]{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
                message = "Telefone inválido"
        )
        String telefone,
        AtualizaEndereco endereco) {
}
