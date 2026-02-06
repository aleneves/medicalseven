package com.med.seven.api.medico;

import com.med.seven.api.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(CadastroMedico entrada) {
        this.nome = entrada.nome();
        this.email = entrada.email();
        this.telefone = entrada.telefone();
        this.crm = entrada.crm();
        this.especialidade = entrada.especialidade();
        this.endereco = new Endereco(entrada.endereco());
    }
}
