package com.example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.AutenticacaoDTO;
import com.example.model.AutenticacaoRespostaDTO;
import com.example.service.PessoaService;
import com.example.utils.JwtUtil;

@RestController
public class HelloResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping(value = "/user")
	public ResponseEntity<String> getUser() {
		return new ResponseEntity<>(jwtUtil.getNomeUsuario(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping(value = "/auth/update-senhas")
	public ResponseEntity<Void> updateSenhas() {
		pessoaService.updateSenhas();
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/auth")
	public ResponseEntity<AutenticacaoRespostaDTO> autenticar(@RequestBody AutenticacaoDTO autenticacao) throws Exception {
		try {
			
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(autenticacao.getUsername(), autenticacao.getPassword())
				);
			
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid username or password", e);
		}
		
		UserDetails userDetails = pessoaService.loadUserByUsername(autenticacao.getUsername());
		String jwt = jwtUtil.generateToken(userDetails);
		
		return new ResponseEntity<>(new AutenticacaoRespostaDTO(jwt), new HttpHeaders(), HttpStatus.OK);
	}

}
