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
import classes.Estante;

public class EstanteDao {

    private final Connection connection;

    public EstanteDao() throws SQLException {
        ConnectionFactory CF = new ConnectionFactory();
        this.connection = CF.getConnection();
    }

    public EstanteDao(Connection connection) {
        this.connection = connection;
    }
  
    public void adicionaEstante(Estante registro) {
        try {
            String sql = "insert into estante (setor, corredor, tema) values (?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, registro.getSetor());
            stmt.setString(2, registro.getCorredor());
            stmt.setString(3, registro.getTema());

            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Estante> getEstante() {
        try {
            List<Estante> registros = new ArrayList<>();
            try (PreparedStatement stmt = this.connection.prepareStatement("select * from estante"); 

            ResultSet rs = stmt.executeQuery()) {

                while(rs.next()) {
                    Estante registro = new Estante();

                    registro.setId(rs.getInt("id"));
                    registro.setSetor(rs.getString("setor"));
                    registro.setCorredor(rs.getString("corredor"));
                    registro.setTema(rs.getString("tema"));              

                    registros.add(registro);
                } 
            }
            return registros;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void excluiEstante(Estante registro) {
        String sql = "delete estante where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setInt(1, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void atualizaEstante(Estante registro) {
        String sql = "update setor = ?, corredor = ?, tema = ? where id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setString(1, registro.getSetor());
            stmt.setString(2, registro.getCorredor());
            stmt.setString (3,registro.getTema());
            stmt.setInt(4   , registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
	