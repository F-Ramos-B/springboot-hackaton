package com.example.api;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.EnderecoViaCep;
import com.example.model.Pessoa;
import com.example.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaRest {
	
	/**
	 * URL pra usar no postman/insomnia:
	 * http://localhost:8080/hackaton-springboot/pessoas/
	 * 
	 * URL do swagger:
	 * http://localhost:8080/hackaton-springboot/swagger-ui.html
	 */
	
	@Autowired
	private PessoaService pessoaService;
	
	/**
	 * GETS
	 */
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listarPessoas() {
		return new ResponseEntity<>(pessoaService.getAll(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/por-id")
	public ResponseEntity<List<Pessoa>> listarPessoasOrdenarPorId() {
		return new ResponseEntity<>(pessoaService.getAllOrdenarPorId(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/por")
	public ResponseEntity<List<Pessoa>> listarOrdenadasPor(
			@RequestParam(required = false, defaultValue = "true") boolean ascendente,
			@RequestParam(required = true) String atributo) {
		return new ResponseEntity<>(pessoaService.getAll(ascendente, atributo), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> recuperarPorId(@PathVariable("id") Long id) throws Exception {
		return new ResponseEntity<>(pessoaService.getById(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/paginado")
	public ResponseEntity<List<Pessoa>> listarPessoas(
				@RequestParam(defaultValue = "0") Integer numeroPagina,
	            @RequestParam(defaultValue = "10") Integer porPagina
			) {
		return new ResponseEntity<>(pessoaService.listarPaginado(numeroPagina, porPagina), new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Exemplo de query string:
	 * http://localhost:8080/hackaton-springboot/pessoas/filtro-exato?nome=JOAO&email=joaom.dev@hotmail.com1
	 */
	
	@GetMapping("/filtro-exato")
	public ResponseEntity<List<Pessoa>> recuperarPorNomeEEmail(
				@RequestParam(required = true) String nome,
				@RequestParam(required = true) String email
			) {
		return new ResponseEntity<>(pessoaService.recuperarPorNomeEEmailExato(nome, email), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/filtro-parecido")
	public ResponseEntity<List<Pessoa>> pesquisarPorNomeOuEmailParecido(
				@RequestParam(required = true) String nome,
				@RequestParam(required = true) String email
			) {
		return new ResponseEntity<>(pessoaService.pesquisarPorNomeOuEmailParecido(nome, email), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/nome-exato")
	public ResponseEntity<List<Pessoa>> listarPorNomeExato(
				@RequestParam(required = true) String nome
			) {
		return new ResponseEntity<>(pessoaService.listarPorNomeExato(nome), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/nome-parecido")
	public ResponseEntity<List<Pessoa>> listarPorNomeParecido(
				@RequestParam(required = true) String nome
			) {
		return new ResponseEntity<>(pessoaService.listarPorNomeParecido(nome), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/filtro-jpql")
	public ResponseEntity<List<Pessoa>> recuperarPorNomeOuEmailJPQL(
				@RequestParam(required = false) String nome,
	            @RequestParam(required = false) String email
			) {
		return new ResponseEntity<>(pessoaService.recuperarPorNomeOuEmailJPQL(nome, email), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/filtro-spring")
	public ResponseEntity<List<Pessoa>> listarPorNomeOuEmail(
				@RequestParam(required = true) String nome,
				@RequestParam(required = true) String email
			) {
		return new ResponseEntity<>(pessoaService.listarPorNomeOuEmail(nome, email), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/situacao/{situacao}")
	public ResponseEntity<Set<Pessoa>> listarPorSituacao(@PathVariable("situacao") Boolean situacao) {
		return new ResponseEntity<>(pessoaService.listarPorSituacao(situacao), new HttpHeaders(), HttpStatus.OK);
	} // Testando se retorno com set ao invés de list funciona normalmente.
	
	@GetMapping("/ativos")
	public ResponseEntity<List<Pessoa>> listarPessoasAtivas() {
		return new ResponseEntity<>(pessoaService.listarPessoasAtivas(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/inativos")
	public ResponseEntity<List<Pessoa>> listarPessoasInativas() {
		return new ResponseEntity<>(pessoaService.listarPessoasInativas(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Pessoa>> recuperarPorNome(@PathVariable("nome") String nome) {
		return new ResponseEntity<>(pessoaService.listarPorNomeIgnoreCase(nome), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("cep/{cep}")
	public ResponseEntity<EnderecoViaCep> pesquisarCep(@PathVariable("cep") String cep) throws Exception {
		return new ResponseEntity<>(pessoaService.buscarCep(cep), new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * POSTS
	 */
	
	@PostMapping
	public ResponseEntity<Pessoa> inserir(@RequestBody Pessoa pessoa) {
		return new ResponseEntity<>(pessoaService.inserir(pessoa), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/varias")
	public ResponseEntity<List<Pessoa>> inserirVarios(@RequestBody List<Pessoa> pessoas) {
		return new ResponseEntity<>(pessoaService.inserirVarios(pessoas), new HttpHeaders(), HttpStatus.OK);
	} // O tipo de objeto dentro desses return new ResponseEntity é opcional.
	
	/**
	 * PUTS
	 */
	
	@PutMapping
	public ResponseEntity<Pessoa> atualizar(@RequestBody Pessoa pessoa) {
		return new ResponseEntity<>(pessoaService.inserir(pessoa), new HttpHeaders(), HttpStatus.OK);
	} // Deve ser passado uma pessoa com ID no JSON pra que seja atualizado.

	/**
	 * DELETES
	 */
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
		pessoaService.remover(id);
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
	}
}
