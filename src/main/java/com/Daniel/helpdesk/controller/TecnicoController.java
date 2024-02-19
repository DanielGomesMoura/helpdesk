package com.Daniel.helpdesk.controller;

import com.Daniel.helpdesk.domain.Tecnico;
import com.Daniel.helpdesk.domain.dto.TecnicoDTO;
import com.Daniel.helpdesk.service.TecnicoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnico")
public class TecnicoController {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        Tecnico obj = this.service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> list = service.findAll();
        List<TecnicoDTO> listDTO = list.stream().map(TecnicoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

     @PostMapping
    public ResponseEntity<TecnicoDTO> create(@RequestBody TecnicoDTO objDTO){
        Tecnico obj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
     
     @PutMapping(value = "/{id}")
     public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO){
    	 Tecnico obj = service.update(id, objDTO);
    	 return ResponseEntity.ok().body(new TecnicoDTO(obj));
     }
     @DeleteMapping(value = "/{id}")
 	public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
		service.delete(id);
	return ResponseEntity.noContent().build();
}
     
}
