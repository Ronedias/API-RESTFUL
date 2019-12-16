package com.apirestful.filmes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.apirestful.filme.csv.utils.CreateTables;
import com.apirestful.filmes.model.Filme;
import com.apirestful.filmes.service.FilmeService;

@SpringBootApplication
@ComponentScan(basePackages = "com.restful.filmes.resource")
public class FilmesApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(FilmesApplication.class, args);
		CreateTables criarTabela = new CreateTables();
		criarTabela.createTablePessoas();
		lerArquivoCsv();
		
	}
	
	
	public static void lerArquivoCsv() {
		
		
		File arquivoCSV = new File("/opt/movielist.csv");
		System.out.println("file : " + arquivoCSV.getAbsoluteFile());
		System.out.println(" path : " + arquivoCSV.getAbsolutePath());

		try {
			Scanner leitor = new Scanner(arquivoCSV);

			String linhasDoArquivo = new String();
			List<Filme> filmes = new ArrayList<Filme>();
			List<String[]> listaRegistros = new ArrayList<String[]>();
			while (leitor.hasNext()) {

				linhasDoArquivo = leitor.nextLine();

				if (!linhasDoArquivo.isEmpty() && !linhasDoArquivo.contains("year")) {
					String[] valoresEntreVirgulas = linhasDoArquivo.split(";");
					listaRegistros.add(valoresEntreVirgulas);

				} else {
					System.out.println("primeira linha cabeçalho");
				}

			}
			leitor.close();
			Filme filme = null;
			for (String[] campo : listaRegistros) {
				filme = new Filme();

				filme.setAno(Integer.parseInt(campo[0]));
				filme.setTitulo(campo[1]);
				filme.setStudio(campo[2]);
				filme.setProdutores(campo[3]);
				if (campo.length > 4 && !campo[4].isEmpty()) {
					filme.setVencedor(campo[4]);
				}

				filmes.add(filme);

			}
			
			FilmeService filmeService = new FilmeService();
			filmeService.salvarFilmeEjb(filmes);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}


