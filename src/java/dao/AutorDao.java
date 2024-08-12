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
import classes.Autor;

public class AutorDao {

    private final Connection connection;

    public AutorDao() throws SQLException {
        ConnectionFactory CF = new ConnectionFactory();
        this.connection = CF.getConnection();
    }

    public AutorDao(Connection connection) {
        this.connection = connection;
    }
  
    public void adicionaAutor(Autor registro) {
        try {
            String sql = "insert into autor (nome, nacionalidade) values (?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, registro.getNome());
            stmt.setString(2, registro.getNacionalidade());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Autor> getAutor() {
        try {
            List<Autor> registros = new ArrayList<>();
            try (PreparedStatement stmt = this.connection.prepareStatement("select * from autor"); 

            ResultSet rs = stmt.executeQuery()) {

                while(rs.next()) {
                    Autor registro = new Autor();

                    registro.setId(rs.getInt("id"));
                    registro.setNome(rs.getString("nome"));
                    registro.setNacionalidade(rs.getString("nacionalidade"));

                    registros.add(registro);
                } 
            }
            return registros;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void excluiAutor(Autor registro) {
        String sql = "delete from autor where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setInt(1, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void atualizaAutor(Autor registro) {
        String sql = "update autor set nome = ?, nacionalidade = ? where id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setString (1,registro.getNome());
            stmt.setString (2,registro.getNacionalidade());
            stmt.setInt(3, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
	