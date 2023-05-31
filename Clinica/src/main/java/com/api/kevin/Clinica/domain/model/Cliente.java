package com.api.kevin.Clinica.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Cliente  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
    private String nomeDosPais;
    private String cpf;
    private String sexo;
    private String email;
    private String altura;
    private String peso;
    private String dataNascimento;
    private String telefone;
    private String cidade;
    private String texto;
    private String receitas;
    private String exames;
    private String data;
    private String hora;

}
