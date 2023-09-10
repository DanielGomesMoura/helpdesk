package com.Daniel.helpdesk;

import com.Daniel.helpdesk.domain.Chamado;
import com.Daniel.helpdesk.domain.Cliente;
import com.Daniel.helpdesk.domain.Tecnico;
import com.Daniel.helpdesk.domain.enums.Perfil;
import com.Daniel.helpdesk.domain.enums.Prioridade;
import com.Daniel.helpdesk.domain.enums.Status;
import com.Daniel.helpdesk.repositories.ChamadoRepository;
import com.Daniel.helpdesk.repositories.ClienteRepository;
import com.Daniel.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Valdir Cesar", "98024434253","daniel.tecnicop@gmail.com","Daniel0101");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null,"Daniel Moura","98024434252","daniels_p10@hotmail.com","123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1 );

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}
