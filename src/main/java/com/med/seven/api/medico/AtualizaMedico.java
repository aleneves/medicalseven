package com.med.seven.api.medico;

import com.med.seven.api.endereco.AtualizaEndereco;
import com.med.seven.api.endereco.CadastroEndereco;
import jakarta.validation.constraints.NotNull;
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
