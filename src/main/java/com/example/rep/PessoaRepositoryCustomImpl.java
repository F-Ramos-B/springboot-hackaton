package com.example.rep;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.example.model.Pessoa;

public class PessoaRepositoryCustomImpl implements PessoaRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Set<Pessoa> recuperarPorCriteriaQuery(Boolean isAtivo) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pessoa> query = cb.createQuery(Pessoa.class);
		Root<Pessoa> pessoa = query.from(Pessoa.class);
		
		query.select(pessoa).where(cb.equal(pessoa.get("situacao"), isAtivo));
		return em.createQuery(query).getResultStream().collect(Collectors.toSet());
	}
	
	@Override
	public List<Pessoa> recuperarPorNomeOuEmailJPQL(String nome, String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p FROM Pessoa p WHERE 1=1 ");
		
		if (Objects.nonNull(nome)) {
			sql.append(" AND p.nome LIKE :nome ");
		}
		
		if (Objects.nonNull(email)) {
			sql.append(" AND p.email LIKE :email ");
		}
		
		TypedQuery<Pessoa> query = em.createQuery(sql.toString(), Pessoa.class);
		
		if (Objects.nonNull(nome)) {
			query.setParameter("nome", "%" + nome + "%");
		}
		
		if (Objects.nonNull(email)) {
			query.setParameter("email", "%" + email + "%");
		}
		
		return query.getResultList();
	}

}
