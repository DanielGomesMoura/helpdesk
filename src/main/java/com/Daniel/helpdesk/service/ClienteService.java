package com.Daniel.helpdesk.service;

import com.Daniel.helpdesk.domain.Pessoa;
import com.Daniel.helpdesk.domain.Cliente;
import com.Daniel.helpdesk.domain.dto.ClienteDTO;
import com.Daniel.helpdesk.repositories.PessoaRepository;
import com.Daniel.helpdesk.repositories.ClienteRepository;
import com.Daniel.helpdesk.service.exception.DataIntegrityViolationException;
import com.Daniel.helpdesk.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: "+ id));
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

      public Cliente create(ClienteDTO objDTO){

        objDTO.setId(null);
        validaCpfEmail(objDTO);

        objDTO.setId(null);  
        validaCpfEmail(objDTO);
        objDTO.setSenha(encoder.encode(objDTO.getSenha())); 

        Cliente obj = new Cliente(objDTO);
        return repository.save(obj);
    }
      
    private void validaCpfEmail(ClienteDTO objDTO) {
    	Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
    	 
    	if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
    		throw new DataIntegrityViolationException("CPF já cadastrado");
    	}
    	
    	obj = pessoaRepository.findByEmail(objDTO.getEmail());
    	if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
    		throw new DataIntegrityViolationException("Email já cadastrado");
    	}
    }

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaCpfEmail(objDTO);


		objDTO.setSenha(encoder.encode(objDTO.getSenha())); 

		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);		
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviços e não pode ser deletado");
		}
		repository.deleteById(id);
		
	}
	
}
