package com.med.seven.api.paciente;

import com.med.seven.api.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String datanasc;
    private String email;
    private String telefone;
    private String responsavel;
    @Embedded
    private Endereco endereco;

    public Paciente (CadastroPaciente paciente){
        this.nome = paciente.nome();
        this.datanasc = paciente.datanasc();
        this.email = paciente.email();
        this.telefone = paciente.telefone();
        this.responsavel = paciente.responsavel();
        this.endereco = new Endereco(paciente.endereco());
    }
}
