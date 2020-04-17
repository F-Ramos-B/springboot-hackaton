package com.example.rep;

import java.util.List;
import java.util.Set;

import com.example.model.Pessoa;

public interface PessoaRepositoryCustom {
	
	Set<Pessoa> recuperarPorCriteriaQuery(Boolean isAtivo);

	List<Pessoa> recuperarPorNomeOuEmailJPQL(String nome, String email);

}
