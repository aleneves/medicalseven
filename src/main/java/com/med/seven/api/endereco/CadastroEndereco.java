package com.med.seven.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastroEndereco(
        @NotBlank
        String logradouro,
        @NotBlank
        @Pattern(regexp="\\d{8}")
        String cep,
        String numero,
        String complemento,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        String uf) {
}
