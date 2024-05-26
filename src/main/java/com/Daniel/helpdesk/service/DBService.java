package com.Daniel.helpdesk.service;

import com.Daniel.helpdesk.domain.Chamado;
import com.Daniel.helpdesk.domain.Cliente;
import com.Daniel.helpdesk.domain.Tecnico;
import com.Daniel.helpdesk.domain.enums.Perfil;
import com.Daniel.helpdesk.domain.enums.Prioridade;
import com.Daniel.helpdesk.domain.enums.Status;
import com.Daniel.helpdesk.repositories.ChamadoRepository;
import com.Daniel.helpdesk.repositories.ClienteRepository;
<<<<<<< HEAD
import com.Daniel.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.Daniel.helpdesk.repositories.PessoaRepository;
import com.Daniel.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
>>>>>>> master
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
    
    @Autowired
    private BCryptPasswordEncoder encoder;
    
    

    public void InstanciaDB() {

        Tecnico tec1 = new Tecnico(null, "Valdir Cesar", "98024434253", "daniel.tecnicop@gmail.com", encoder.encode("Daniel0101"));
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Daniel Moura", "79537731200", "daniels_p10@hotmail.com", encoder.encode("123"));

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);

        pessoaRepository.saveAll(Arrays.asList(cli1,tec1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
