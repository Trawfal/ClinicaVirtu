package com.api.kevin.Clinica.domain.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter

public class Medica extends Pessoa{
    public Medica(String nome, String cpf, String email,String telefone, String cidade){
        super(nome, cpf, email, telefone, cidade);

    }
}
