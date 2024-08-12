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
import classes.Exemplar;

public class ExemplarDao {

    private final Connection connection;

    public ExemplarDao() throws SQLException {
        ConnectionFactory CF = new ConnectionFactory();
        this.connection = CF.getConnection();
    }

    public ExemplarDao(Connection connection) {
        this.connection = connection;
    }
  
    public void adicionaExemplar(Exemplar registro) {
        try {
            String sql = "insert into exemplar (livro_id, estante_id) values (?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, registro.getLivro_id());
            stmt.setInt(2, registro.getEstante_id());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Exemplar> getExemplar() {
        try {
            List<Exemplar> registros = new ArrayList<>();
            try (PreparedStatement stmt = this.connection.prepareStatement("select * from exemplar"); 

            ResultSet rs = stmt.executeQuery()) {

                while(rs.next()) {
                    Exemplar registro = new Exemplar();

                    registro.setId(rs.getInt("id"));
                    registro.setLivro_id(rs.getInt("livro_i"));
                    registro.setEstante_id(rs.getInt("estante_id"));
                 
                    registros.add(registro);
                } 
            }
            return registros;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void excluiExemplar(Exemplar registro) {
        String sql = "delete from exemplar where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setInt(1, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void atualizaExemplar(Exemplar registro) {
        String sql = "update exemplar set livro_id = ?, estante_id = ? where id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setInt(1, registro.getLivro_id());
            stmt.setInt(2, registro.getEstante_id());
            stmt.setInt(3, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
	