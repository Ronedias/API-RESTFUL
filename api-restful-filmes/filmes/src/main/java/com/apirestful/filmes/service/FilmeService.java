package com.apirestful.filmes.service;

import java.sql.Connection;
import java.util.List;

import com.apirestful.filme.service.ejb.FilmeEjb;
import com.apirestful.filmes.conexao.ConnectionFactory;
import com.apirestful.filmes.model.Filme;

public class FilmeService {

	
	public void salvarFilmeEjb(List<Filme> filmes) {
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			for (Filme filme : filmes) {
				
				FilmeEjb filmeEjb = new FilmeEjb();
				filmeEjb.inserirFilme(filme, connection);
			}
			
			

			if (connection.isClosed()) {
				throw new Exception(
						"Ocorreu um erro ao salvar Filme");
			}

			connection.commit();
			connection.close();

			

		} catch (Exception e) {
			System.out.println("#>> Erro ao salvar o Filme");
			e.printStackTrace();
		}
	}
	
	
}
