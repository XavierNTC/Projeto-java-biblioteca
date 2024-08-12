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
import classes.Categoria;

public class CategoriaDao {

    private final Connection connection;

    public CategoriaDao() throws SQLException {
        ConnectionFactory CF = new ConnectionFactory();
        this.connection = CF.getConnection();
    }

    public CategoriaDao(Connection connection) {
        this.connection = connection;
    }
  
    public void adicionaCategoria(Categoria registro) {
        try {
            String sql = "insert into categoria (nome) values (?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, registro.getNome());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Categoria> getCategoria() {
        try {
            List<Categoria> registros = new ArrayList<>();
            try (PreparedStatement stmt = this.connection.prepareStatement("select * from categoria"); 

            ResultSet rs = stmt.executeQuery()) {

                while(rs.next()) {
                    Categoria registro = new Categoria();

                    registro.setId(rs.getInt("id"));
                    registro.setNome(rs.getString("nome"));


                    registros.add(registro);
                } 
            }
            return registros;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void excluiCategoria(Categoria registro) {
        String sql = "delete from categoria where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setInt(1, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void atualizaCategoria(Categoria registro) {
        String sql = "update categoria set nome = ? where id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setString (1,registro.getNome());
            stmt.setInt(2, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
	