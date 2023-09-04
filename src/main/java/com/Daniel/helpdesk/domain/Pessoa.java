package com.Daniel.helpdesk.domain;

import com.Daniel.helpdesk.domain.enums.Perfil;
import lombok.Data;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public abstract class Pessoa {

    protected Integer id;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();
    protected LocalDate datacriacao = LocalDate.now();


    public Pessoa(){
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
    }
    public Set<Perfil> getPerfis(){
    return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
}
    public void addPerfil(Perfil perfil){
    this.perfis.add(perfil.getCodigo());
}



}
