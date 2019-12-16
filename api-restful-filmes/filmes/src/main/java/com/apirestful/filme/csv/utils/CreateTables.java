package com.apirestful.filme.csv.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.apirestful.filmes.conexao.ConnectionFactory;

public class CreateTables {

	private Connection connection;

    public CreateTables() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void createTablePessoas() {
        String sql = null;
        try {
            sql = "CREATE TABLE IF NOT EXISTS filme "
            		+ " (id bigint auto_increment,"
            		+ "ano integer, "
            		+ "produtores varchar(255), "
            		+ "studio varchar(255), "
            		+ "titulo varchar(255) not null, "
            		+ "vencedor varchar(255), "
            		+ "primary key (id))";
        	

            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("CreateTables.createTablePessoas Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
	
}
