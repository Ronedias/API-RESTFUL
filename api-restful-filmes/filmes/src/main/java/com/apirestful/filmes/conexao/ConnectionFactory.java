package com.apirestful.filmes.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	

    private static final String URL = 
    		//DriverManager.getConnection("jdbc:h2:tcp://localhost/~poo","admin","admin");
		"jdbc:h2:tcp://localhost/~poo\",\"admin\",\"admin";
		
    private static final String DRIVER =
		"org.apache.derby.jdbc.EmbeddedDriver";

    public static Connection getConnection() {
        System.out.println("Conectando ao Banco de Dados");
        try {
            //Class.forName(DRIVER);
            //return DriverManager.getConnection(URL);
        	return
            DriverManager.getConnection("jdbc:h2:./data/db");
        	
        
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	
}
