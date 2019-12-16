package com.restful.filmes.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestful.filme.service.ejb.FilmeEjb;
import com.apirestful.filmes.model.Filme;
import com.apirestful.filmes.repository.Filmes;

@RestController
@RequestMapping("/filmes")
public class FilmesResource {

	@Autowired
	private Filmes filmes;
	List<Filme> listaFilmes;

	@PostMapping
	public Filme adicionar(@Valid @RequestBody Filme filme) {
		return filmes.save(filme);
	}
	
	
	@GetMapping
	public List<Filme> listar() {
		FilmeEjb filmeEjb = new FilmeEjb();
		listaFilmes = filmeEjb.carregarFimes();
		return listaFilmes;
	}


	@GetMapping("/{id}")
	public ResponseEntity<Filme> buscar(@PathVariable Long id) {
		FilmeEjb filmeEjb = new FilmeEjb();
		Filme filme = filmeEjb.carregarFimePorId(id);
		this.listaFilmes = new ArrayList<Filme>();
		listaFilmes.add(filme);
		if (filme == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(filme);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Filme> atualizar(@PathVariable Long id, @Valid @RequestBody Filme filme) {
		Filme existente = filmes.findOne(id);

		if (existente == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(filme, existente, "id");

		existente = filmes.save(existente);

		return ResponseEntity.ok(existente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		FilmeEjb filmeEjb = new FilmeEjb();
		Filme filme = filmeEjb.carregarFimePorId(id);
		this.listaFilmes = new ArrayList<Filme>();
		listaFilmes.add(filme);

		if (filme == null) {
			return ResponseEntity.notFound().build();
		}

		filmeEjb.removerFilme(filme, null);

		return ResponseEntity.noContent().build();
	}
}
