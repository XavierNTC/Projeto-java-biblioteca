/* 
Adicionar o pacote onde esse arquivo esta
ex:
package br.com.biblioteca.dao;
*/
package dao;

import conexao.ConnectionFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* 

Importar todas as classes:

import br.com.clinica.modelo.Consultas;
import br.com.clinica.modelo.Medicos;
import br.com.clinica.modelo.Pacientes;
*/
import classes.Bibliotecaria;

public class BibliotecariaDao {

    private final Connection connection;

    public BibliotecariaDao() throws SQLException {
        ConnectionFactory CF = new ConnectionFactory();
        this.connection = CF.getConnection();
    }

    public BibliotecariaDao(Connection connection) {
        this.connection = connection;
    }
  
    public void adicionaBibliotecaria(Bibliotecaria registro) {
        try {
            String sql = "insert into bibliotecaria (nome, tipo, instituicao_ensino) values (?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, registro.getNome());
            stmt.setString(2, registro.getTipo());
            stmt.setString(3, registro.getInstituicao_ensino());

            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Bibliotecaria> getBibliotecaria() {
        try {
            List<Bibliotecaria> registros = new ArrayList<>();
            try (PreparedStatement stmt = this.connection.prepareStatement("select * from bibliotecaria"); 

            ResultSet rs = stmt.executeQuery()) {

                while(rs.next()) {
                    Bibliotecaria registro = new Bibliotecaria();

                    registro.setId(rs.getInt("id"));
                    registro.setNome(rs.getString("nome"));
                    registro.setTipo(rs.getString("tipo"));
                    registro.setInstituicao_ensino(rs.getString("instituicao_ensino"));               

                    registros.add(registro);
                } 
            }
            return registros;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void excluiBibliotecaria(Bibliotecaria registro) {
        String sql = "delete from bibliotecaria where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setInt(1, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void atualizaBibliotecaria(Bibliotecaria registro) {
        String sql = "update bibliotecaria set nome = ?, tipo = ?, instituicao_ensino = ? where id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setString(1, registro.getNome());
            stmt.setString(2, registro.getTipo());
            stmt.setString (3,registro.getInstituicao_ensino());
            stmt.setInt(45, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
	