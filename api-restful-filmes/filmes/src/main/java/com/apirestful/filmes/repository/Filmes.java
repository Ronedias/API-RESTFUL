package com.apirestful.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirestful.filmes.model.Filme;

public interface Filmes extends JpaRepository<Filme, Long> {

}
