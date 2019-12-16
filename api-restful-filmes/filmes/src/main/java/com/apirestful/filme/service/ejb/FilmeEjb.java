package com.apirestful.filme.service.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.apirestful.filmes.conexao.ConnectionFactory;
import com.apirestful.filmes.model.Filme;

public class FilmeEjb {

	
	public void inserirFilme(Filme filme, Connection connection) {
		Connection conn = null;
		PreparedStatement pst = null;
		StringBuilder sql = null;
		Boolean novaConexao = false;

		try {
			if (connection != null) {
				conn = connection;
			} else {
				conn = ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				novaConexao = true;
			}

			sql = new StringBuilder("insert into filme (id, titulo, ano,studio, produtores, vencedor)"
					+ "	VALUES (DEFAULT");
			
			if (filme.getTitulo() != null) {
				sql.append(" , '" + filme.getTitulo() + "'");
			} else {
				sql.append(" , NULL");
			}
			
			if (filme.getAno() != null) {
				sql.append(" , " + filme.getAno());
			} else {
				sql.append(" , NULL");
			}
			
			if (filme.getStudio() != null) {
				sql.append(" , '" + filme.getStudio() + "'");
			} else {
				sql.append(" , NULL");
			}
			
			if (filme.getProdutores() != null) {
				sql.append(" , '" + filme.getProdutores() + "'");
			} else {
				sql.append(" , NULL");
			}
			

			if (filme.getVencedor() != null) {
				sql.append(" , '" + filme.getVencedor() + "');");
			} else {
				sql.append(" , NULL);");
			}
			
			
			pst = conn.prepareStatement(sql.toString());
			pst.executeUpdate();
			
			
			if (novaConexao) {
				conn.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} finally {
			try {
				pst.close();
				if (novaConexao) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	
	
	public List<Filme> carregarFimes() throws RuntimeException {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		StringBuilder sql = null;
		List<Filme> filmes = null;

		try {
			conn = ConnectionFactory.getConnection();

			sql = new StringBuilder("select id, titulo, ano,studio, produtores, vencedor from filme;");
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			
			filmes = new ArrayList<Filme>();
			while (rs.next()) {
				Filme filme = new Filme();
				
				if (rs.getObject("id") != null) {
					filme.setId(rs.getLong("id"));
				}
				if(rs.getObject("titulo") != null) {
					filme.setTitulo(rs.getString("titulo"));
				}
				if(rs.getObject("ano") != null) {
					filme.setAno(rs.getInt("ano"));
				}
				if(rs.getObject("studio") != null) {
					filme.setStudio(rs.getString("studio"));
				}
				if(rs.getObject("produtores") != null) {
					filme.setProdutores(rs.getString("produtores"));
				}
				if(rs.getObject("vencedor") != null) {
					filme.setVencedor(rs.getString("vencedor"));
				}
				
				filmes.add(filme);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return filmes;
}
	
	
	public List<Filme> carregarFimesPorProdutor(String produtor) throws RuntimeException {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		StringBuilder sql = null;
		List<Filme> filmes = null;

		try {
			conn = ConnectionFactory.getConnection();

			sql = new StringBuilder("select id, titulo, ano,studio, produtores, vencedor from filme"
					+ " where produtores like '%" + produtor + "%' ;");
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			
			filmes = new ArrayList<Filme>();
			while (rs.next()) {
				Filme filme = new Filme();
				
				if (rs.getObject("id") != null) {
					filme.setId(rs.getLong("id"));
				}
				if(rs.getObject("titulo") != null) {
					filme.setTitulo(rs.getString("titulo"));
				}
				if(rs.getObject("ano") != null) {
					filme.setAno(rs.getInt("ano"));
				}
				if(rs.getObject("studio") != null) {
					filme.setStudio(rs.getString("studio"));
				}
				if(rs.getObject("produtores") != null) {
					filme.setProdutores(rs.getString("produtores"));
				}
				if(rs.getObject("vencedor") != null) {
					filme.setVencedor(rs.getString("vencedor"));
				}
				
				filmes.add(filme);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return filmes;
}

	public void removerFilme(Filme filme, Connection connection) {
		Connection conn = null;
		PreparedStatement pst = null;
		StringBuilder sql = null;
		Boolean novaConexao = false;
		try {
			if (connection != null) {
				conn = connection;
			} else {
				conn = ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				novaConexao = true;
			}

			//sql = new StringBuilder(
				//	"DELETE FROM filme where id = " + filme.getId());
			sql = new StringBuilder(
					"DELETE FROM filme;");
			
			pst = conn.prepareStatement(sql.toString());
			pst.execute();

			if (novaConexao) {
				conn.commit();
			}

		} catch (Exception e) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		} finally {
			try {
				pst.close();
				if (novaConexao) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	public Filme carregarFimePorId(Long idFilme) throws RuntimeException {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		StringBuilder sql = null;
		Filme filme = null;

		try {
			conn = ConnectionFactory.getConnection();

			sql = new StringBuilder("select id, titulo, ano,studio, produtores, vencedor from filme where id = "
					+ idFilme + ";");
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			
			while (rs.next()) {
				 filme = new Filme();
				
				if (rs.getObject("id") != null) {
					filme.setId(rs.getLong("id"));
				}
				if(rs.getObject("titulo") != null) {
					filme.setTitulo(rs.getString("titulo"));
				}
				if(rs.getObject("ano") != null) {
					filme.setAno(rs.getInt("ano"));
				}
				if(rs.getObject("studio") != null) {
					filme.setStudio(rs.getString("studio"));
				}
				if(rs.getObject("produtores") != null) {
					filme.setProdutores(rs.getString("produtores"));
				}
				if(rs.getObject("vencedor") != null) {
					filme.setVencedor(rs.getString("vencedor"));
				}
				
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return filme;
}
	
	
}
