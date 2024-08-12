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
import classes.Editora;

public class EditoraDao {

    private final Connection connection;

    public EditoraDao() throws SQLException {
        ConnectionFactory CF = new ConnectionFactory();
        this.connection = CF.getConnection();
    }

    public EditoraDao(Connection connection) {
        this.connection = connection;
    }
  
    public void adicionaEditora(Editora registro) {
        try {
            String sql = "insert into editora (nome) values (?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, registro.getNome());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Editora> getEditora() {
        try {
            List<Editora> registros = new ArrayList<>();
            try (PreparedStatement stmt = this.connection.prepareStatement("select * from editora"); 

            ResultSet rs = stmt.executeQuery()) {

                while(rs.next()) {
                    Editora registro = new Editora();

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

	

    public void excluiEditora(Editora registro) {
        String sql = "delete from editora where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setInt(1, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void atualizaEditora(Editora registro) {
        String sql = "update editora set nome = ? where id = ?";
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
	