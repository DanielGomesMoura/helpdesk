package com.Daniel.helpdesk.repositories;

import com.Daniel.helpdesk.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado,Integer> {

}
