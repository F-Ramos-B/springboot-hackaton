package com.example.rep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Pessoa;

public interface PessoaRepository 
        extends JpaRepository<Pessoa, Long>, PessoaRepositoryCustom {
	
	@Query(value = "SELECT p FROM Pessoa p WHERE p.situacao = true")
	List<Pessoa> listarPessoasAtivas();
	
	@Query(value = "SELECT p FROM Pessoa p WHERE p.situacao = false")
	List<Pessoa> listarPessoasInativas();
	
	@Query(value = "SELECT p FROM Pessoa p WHERE p.nome = ?1 and p.email = ?2")
	List<Pessoa> recuperarPorNomeEEmailExato(String nome, String email);
	
	@Query(value = "SELECT p FROM Pessoa p WHERE p.nome LIKE %?1% or p.email LIKE %?2%")
	List<Pessoa> pesquisarPorNomeOuEmailParecido(String nome, String email);
	
	List<Pessoa> findByNome(String nome);
	
	List<Pessoa> findByNomeIgnoreCase(String nome);
	
	List<Pessoa> findByNomeContaining(String nome);
	
	List<Pessoa> findByNomeOrEmailContaining(String nome, String email);
	
	List<Pessoa> findByOrderByIdAsc();
	
}