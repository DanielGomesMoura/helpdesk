package com.Daniel.helpdesk.controller;

import com.Daniel.helpdesk.domain.Tecnico;
import com.Daniel.helpdesk.domain.dto.TecnicoDTO;
import com.Daniel.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
