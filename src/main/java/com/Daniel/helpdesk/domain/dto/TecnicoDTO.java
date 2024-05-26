package com.Daniel.helpdesk.domain.dto;

import com.Daniel.helpdesk.domain.Tecnico;
import com.Daniel.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class TecnicoDTO implements Serializable {
   
	private static final long serialVersionUID = 1L;
	
	protected Integer id;
	
	@NotNull(message = "O campo NOME é requerido")
    protected String nome;
	@NotNull(message = "O campo CPF é requerido")
    protected String cpf;
	@NotNull(message = "O campo EMAIL é requerido")
    protected String email;
	@NotNull(message = "O campo SENHA é requerido")
    protected String senha;
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate datacriacao = LocalDate.now();
	 
	protected Set<Integer> perfis = new HashSet<>();
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }
    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(LocalDate datacriacao) {
        this.datacriacao = datacriacao;
    }

    public TecnicoDTO(){
    super();
    addPerfil(Perfil.TECNICO);
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
        addPerfil(Perfil.TECNICO);
    }
}
