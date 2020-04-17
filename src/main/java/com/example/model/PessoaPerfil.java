package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_pessoa_perfil")
public class PessoaPerfil implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 202621675300285974L;

	@Id
    @Column(name = "co_seq_pessoal_perfil")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "co_seq_perfil", referencedColumnName = "co_seq_perfil", nullable = false)
    private Perfil perfil;
    @ManyToOne
    @JoinColumn(name = "co_seq_pessoa", referencedColumnName = "co_seq_pessoa", nullable = false)
    private Pessoa pessoa;

}
