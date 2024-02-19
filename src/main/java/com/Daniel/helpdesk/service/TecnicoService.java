package com.Daniel.helpdesk.service;

import com.Daniel.helpdesk.domain.Pessoa;
import com.Daniel.helpdesk.domain.Tecnico;
import com.Daniel.helpdesk.domain.dto.TecnicoDTO;
import com.Daniel.helpdesk.repositories.PessoaRepository;
import com.Daniel.helpdesk.repositories.TecnicoRepository;
import com.Daniel.helpdesk.service.exception.DataIntegrityViolationException;
import com.Daniel.helpdesk.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: "+ id));
    }

    public List<Tecnico> findAll(){
        return repository.findAll();
    }

      public Tecnico create(TecnicoDTO objDTO){
        objDTO.setId(null);
        validaCpfEmail(objDTO);
        Tecnico obj = new Tecnico(objDTO);
        return repository.save(obj);
    }
      
    private void validaCpfEmail(TecnicoDTO objDTO) {
    	Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
    	 
    	if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
    		throw new DataIntegrityViolationException("CPF já cadastrado");
    	}
    	
    	obj = pessoaRepository.findByEmail(objDTO.getEmail());
    	if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
    		throw new DataIntegrityViolationException("Email já cadastrado");
    	}
    }

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
		validaCpfEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		return repository.save(oldObj);		
	}

	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviços e não pode ser deletado");
		}
		repository.deleteById(id);
		
	}
	
}
