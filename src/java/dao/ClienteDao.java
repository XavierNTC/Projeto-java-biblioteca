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
import classes.Cliente;

public class ClienteDao {

    private final Connection connection;

    public ClienteDao() throws SQLException {
        ConnectionFactory CF = new ConnectionFactory();
        this.connection = CF.getConnection();
    }

    public ClienteDao(Connection connection) {
        this.connection = connection;
    }
  
    public void adicionaCliente(Cliente registro) {
        try {
            String sql = "insert into cliente (nome, sobrenome, idade, cep) values (?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, registro.getNome());
            stmt.setString(2, registro.getSobrenome());
            stmt.setInt(3, registro.getIdade());
            stmt.setString(4, registro.getCep());

            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Cliente> getCliente() {
        try {
            List<Cliente> registros = new ArrayList<>();
            try (PreparedStatement stmt = this.connection.prepareStatement("select * from cliente"); 

            ResultSet rs = stmt.executeQuery()) {

                while(rs.next()) {
                    Cliente registro = new Cliente();

                    registro.setId(rs.getInt("id"));
                    registro.setNome(rs.getString("nome"));
                    registro.setSobrenome(rs.getString("sobrenome"));
                    registro.setIdade(rs.getInt("idade"));
                    registro.setCep(rs.getString("cep"));

                    registros.add(registro);
                } 
            }
            return registros;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void excluiCliente(Cliente registro) {
        String sql = "delete from cliente where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setInt(1, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void atualizaCliente(Cliente registro) {
        String sql = "update cliente set nome = ?, sobrenome = ?, idade = ?, cep = ? where id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setString (1,registro.getNome());
            stmt.setString (2,registro.getSobrenome());
            stmt.setInt(3, registro.getIdade());
            stmt.setString (4,registro.getCep());
            stmt.setInt(5, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
	