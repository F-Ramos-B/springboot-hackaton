package com.example.rep;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.PessoaPerfil;

public interface PessoaPerfilRepository extends JpaRepository<PessoaPerfil, Long> {

}
