package com.Daniel.helpdesk.repositories;

import com.Daniel.helpdesk.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Clienterepositories extends JpaRepository<Cliente,Integer> {

}
