package com.example.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author joaopedromilhome
 *
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PESSOA")
@NamedQueries(value = {
		@NamedQuery(name = "Pessoa.findByNome",
				query = "select p from Pessoa p where p.nome=:nome"),
		@NamedQuery(name = "Pessoa.findPerfilsAndEnderecosByNome",
				query = "select  p from Pessoa p  JOIN FETCH p.perfils JOIN FETCH p.enderecos  where p.nome=:nome")
})
public class Pessoa implements Serializable, UserDetails {

	
	/**
	 * Serializacao da Classe
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ID da Tabela
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CO_SEQ_PESSOA")
	private Long id;
	/**
	 * Nome da pessoa
	 */
	@NotNull
	@Column(name = "NO_NOME")
	private String nome;
	
	/**
	 * Email da Pessoa
	 */
	@NotNull
	@Column(name = "DS_EMAIL")
	private String email;
	/**
	 * Data de Nascimento 
	 */
	@NotNull
	@Column(name = "DT_NASCIMENTO")
	private LocalDate dataNascimento; 
	/**
	 * Situacao da Pessoa
	 */
	@NotNull
	@Column(name = "ST_PESSOA")
	private Boolean situacao;

	/**
	 * Mapeamento de Enderecos Unidirecional
	 */
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "CO_SEQ_PESSOA", referencedColumnName = "CO_SEQ_PESSOA")
	private Set<Endereco> enderecos;

	/**
	 * Mapeamento de Perfis Unidirecional
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "TB_PESSOA_PERFIL",
			joinColumns = {@JoinColumn(name = "CO_SEQ_PESSOA")},
			inverseJoinColumns = {@JoinColumn(name = "CO_SEQ_PERFIL")}
	)
	private Set<Perfil> perfils;
	
	@NotNull
	@Column(name = "NO_LOGIN")
	private String login;
	
	@NotNull
	@Column(name = "NO_SENHA")
	private String senha;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return this.getSenha();
	}

	@Override
	public String getUsername() {
		return this.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.getSituacao();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.getSituacao();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.getSituacao();
	}

	@Override
	public boolean isEnabled() {
		return this.getSituacao();
	}

}
