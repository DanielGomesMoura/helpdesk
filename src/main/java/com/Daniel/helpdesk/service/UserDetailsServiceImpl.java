package com.Daniel.helpdesk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Daniel.helpdesk.domain.Pessoa;
import com.Daniel.helpdesk.repositories.PessoaRepository;
import com.Daniel.helpdesk.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	PessoaRepository pessoaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Pessoa> user = pessoaRepository.findByEmail(email);
		if(user.isPresent()) {
			return new UserSS(user.get().getId(),user.get().getEmail(),user.get().getSenha(),user.get().getPerfis());
		}
		throw new UsernameNotFoundException(email);
	}

}
