package com.example.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.EnderecoViaCep;
import com.example.model.Pessoa;
import com.example.rep.PessoaRepository;
import com.example.service.utils.ConUtil;

@Service
public class PessoaService implements UserDetailsService {
	
	@Autowired
	private PessoaRepository pessoaDAO;
	
	public List<Pessoa> getAll() {
		return pessoaDAO.findAll();
	}
	
	public List<Pessoa> getAllOrdenarPorId() {
		return pessoaDAO.findByOrderByIdAsc();
	}
	
	public List<Pessoa> getAll(boolean ascendente, String atributo) {
		return pessoaDAO.findAll(
				Sort.by(ascendente ? Sort.Direction.ASC : Sort.Direction.DESC,
						atributo)
				);
	}
	
	public List<Pessoa> listarPaginado(Integer numeroPagina, Integer porPagina) {
		Pageable paging = PageRequest.of(numeroPagina, porPagina);
		Page<Pessoa> resultadoPaginado = pessoaDAO.findAll(paging);
		return resultadoPaginado.hasContent() ? resultadoPaginado.getContent() : new ArrayList<>();
	}
	
	public List<Pessoa> listarPessoasAtivas() {
		return pessoaDAO.listarPessoasAtivas();
	}
	
	public List<Pessoa> listarPessoasInativas() {
		return pessoaDAO.listarPessoasInativas();
	}
	
	public Set<Pessoa> listarPorSituacao(Boolean isAtivo) {
		return pessoaDAO.recuperarPorCriteriaQuery(isAtivo);
	}
	
	public Pessoa getById(Long id) throws Exception {
		return pessoaDAO.findById(id).orElseThrow(() -> new Exception("Não foi localizada nenhuma pessoa com id " + id));
	}
	
	public List<Pessoa> recuperarPorNomeEEmailExato(String nome, String email) {
		return pessoaDAO.recuperarPorNomeEEmailExato(nome, email);
	}
	
	public List<Pessoa> pesquisarPorNomeOuEmailParecido(String nome, String email) {
		return pessoaDAO.pesquisarPorNomeOuEmailParecido(nome, email);
	}
	
	public List<Pessoa> listarPorNomeExato(String nome) {
		return pessoaDAO.findByNome(nome);
	}
	
	@Override
	public UserDetails loadUserByUsername(String login) {
		return pessoaDAO.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com esse login."));
	}
	
	public void updateSenhas() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedSenha = encoder.encode("senha");
		List<Pessoa> all = this.getAll();
		all.forEach(p -> {
			p.setSenha(encodedSenha);
			this.atualizar(p);
		});
	}
	
	public List<Pessoa> listarPorNomeIgnoreCase(String nome) {
		return pessoaDAO.findByNomeIgnoreCase(nome);
	}
	
	public List<Pessoa> listarPorNomeParecido(String nome) {
		return pessoaDAO.findByNomeContaining(nome);
	}
	
	public List<Pessoa> listarPorNomeOuEmail(String nome, String email) {
		return pessoaDAO.findByNomeOrEmailContaining(nome, email);
	}
	
	public List<Pessoa> recuperarPorNomeOuEmailJPQL(String nome, String email) {
		return pessoaDAO.recuperarPorNomeOuEmailJPQL(nome, email);
	}
	
	public Pessoa inserir(Pessoa pessoa) {
		return pessoaDAO.save(pessoa);
	}
	
	public List<Pessoa> inserirVarios(List<Pessoa> pessoas) {
		return pessoaDAO.saveAll(pessoas);
	}
	
	public Pessoa atualizar(Pessoa pessoa) {
		return pessoaDAO.save(pessoa);
	}
	
	public void remover(Long id) {
		pessoaDAO.deleteById(id);
	}

	public EnderecoViaCep buscarCep(String cep) throws IOException, JSONException {
		return EnderecoViaCep.criarEnderecoFromJSON(new JSONObject(ConUtil.requisicaoRest("GET", "https://viacep.com.br/ws/" + cep + "/json/")));
	}
	
}
