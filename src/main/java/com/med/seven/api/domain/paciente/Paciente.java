package com.med.seven.api.domain.paciente;

import com.med.seven.api.domain.endereco.Endereco;
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

    public void atualizar(AtualizaPaciente entrada) {
        if(entrada.nome() != null){
            this.nome = entrada.nome();
        }
        if(entrada.email() != null){
            this.email = entrada.email();
        }
        if(entrada.datanasc() != null){
            this.datanasc = entrada.datanasc();
        }
        if(entrada.telefone() != null){
            this.telefone = entrada.telefone();
        }
        if(entrada.responsavel() != null){
            this.responsavel = entrada.responsavel();
        }
        if(entrada.endereco() != null){
            this.endereco.atualizar(entrada.endereco());
        }
    }
}
