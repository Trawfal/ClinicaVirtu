package com.api.kevin.Clinica.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    public String nome;
    public String cpf;
    public String email;
    public String telefone;
    public String cidade;
    public Pessoa(String nome, String cpf, String email, String telefone, String cidade) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.cidade = cidade;
    }
}

