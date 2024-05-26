package com.Daniel.helpdesk.domain;

import com.Daniel.helpdesk.domain.dto.ClienteDTO;
import com.Daniel.helpdesk.domain.dto.TecnicoDTO;
import com.Daniel.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Cliente extends Pessoa{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>    ();

    public List<Chamado> getChamados() {
		return chamados;
	}
	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }
    public Cliente(){
        super();
        addPerfil(Perfil.CLIENTE);
    }
    
    public Cliente(ClienteDTO objDTO) {
        super();
        this.id = objDTO.getId();
        this.nome = objDTO.getNome();
        this.cpf = objDTO.getCpf();
        this.email = objDTO.getEmail();
        this.senha = objDTO.getSenha();
        this.perfis = objDTO.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        this.datacriacao = objDTO.getDatacriacao();

    }
}
