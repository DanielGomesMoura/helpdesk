package com.Daniel.helpdesk.domain.dto;

import com.Daniel.helpdesk.domain.Pessoa;
import com.Daniel.helpdesk.domain.Tecnico;
import com.Daniel.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class TecnicoDTO extends Pessoa {
    private static final long SerialVersionUID = 1L;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfis(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    @Override
    public LocalDate getDatacriacao() {
        return datacriacao;
    }

    @Override
    public void setDatacriacao(LocalDate datacriacao) {
        this.datacriacao = datacriacao;
    }

    protected Integer id;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate datacriacao = LocalDate.now();

    public TecnicoDTO(){

    }
    public TecnicoDTO(Tecnico obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        this.datacriacao = obj.getDatacriacao();
    }
}
