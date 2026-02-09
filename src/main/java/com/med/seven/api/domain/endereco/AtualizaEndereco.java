package com.med.seven.api.domain.endereco;

public record AtualizaEndereco(
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String uf,
        String complemento
) {}
