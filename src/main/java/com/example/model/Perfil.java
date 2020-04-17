package com.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "TB_PERFIL")
public class Perfil implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8047465170055728702L;
	/**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_seq_perfil")
    private Long id;
    /**
     *
     */
    @NotNull
    @Column(name = "no_perfil")
    private String nome;
    /**
     *
     */
    @NotNull
    @Column(name = "ds_perfil")
    private String descricao;
    /**
     *
     */
    @Column(name = "dt_hora_inclusao")
    @NotNull
    private LocalDateTime dataHoraInclusao;
    /**
     *
     */
    @Column(name = "dt_hora_alteracao")
    private LocalDateTime dataHoraAlteracao;

//    /**
//     * Mapeamento de Pessoa
//     */
//    @ManyToMany(mappedBy = "perfils")
//    private Set<Pessoa> pessoas;


}
