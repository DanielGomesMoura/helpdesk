package com.Daniel.helpdesk.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Daniel.helpdesk.domain.Chamado;
import com.Daniel.helpdesk.domain.dto.ChamadoDTO;
import com.Daniel.helpdesk.service.ChamadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {
	
	@Autowired
	private ChamadoService chamadoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO>findById(@PathVariable Integer id){
		Chamado obj = chamadoService.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>>findAll(){
		List<Chamado> chamadoList = chamadoService.findAll();
		List<ChamadoDTO> chamadoDTOList = chamadoList.stream().map(ChamadoDTO::new).collect(Collectors.toList());
	return ResponseEntity.ok().body(chamadoDTOList);
	}
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objDTO){
		Chamado obj = chamadoService.create(objDTO);
		URI uri =ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").
				buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO>update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO objDTO){
		Chamado obj = chamadoService.update(id,objDTO);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}


}
