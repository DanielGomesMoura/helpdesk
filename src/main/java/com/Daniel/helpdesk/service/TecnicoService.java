package com.Daniel.helpdesk.service;

import com.Daniel.helpdesk.domain.Tecnico;
import com.Daniel.helpdesk.domain.dto.TecnicoDTO;
import com.Daniel.helpdesk.repositories.TecnicoRepository;
import com.Daniel.helpdesk.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: "+ id));
    }

    public List<Tecnico> findAll(){
        return repository.findAll();
    }

      public Tecnico create(TecnicoDTO objDTO){
        objDTO.setId(null);
        Tecnico obj = new Tecnico(objDTO);
        return repository.save(obj);
    }

}
