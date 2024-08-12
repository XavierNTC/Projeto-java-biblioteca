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
import classes.Livro;

public class LivroDao {

    private final Connection connection;

    public LivroDao() throws SQLException {
        ConnectionFactory CF = new ConnectionFactory();
        this.connection = CF.getConnection();
    }

    public LivroDao(Connection connection) {
        this.connection = connection;
    }
  
    public void adicionaLivro(Livro registro) {
        try {
            String sql = "insert into livro ( editora, categoria, titulo ,numero_exemplares_disponiveis ,numero_exemplares) values (?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, registro.getEditora_id());
            stmt.setInt(2, registro.getCategoria_id());
            stmt.setString(3, registro.getTitulo());
            stmt.setInt(4, registro.getNumero_exemplares_disponiveis());
            stmt.setInt(5, registro.getNumero_exemplares());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Livro> getLivro() {
        try {
            List<Livro> registros = new ArrayList<>();
            try (PreparedStatement stmt = this.connection.prepareStatement("select * from livro"); 

            ResultSet rs = stmt.executeQuery()) {

                while(rs.next()) {
                    Livro registro = new Livro();

                    registro.setId(rs.getInt("id"));
                    registro.setEditora_id(rs.getInt("editora"));
                    registro.setCategoria_id(rs.getInt("categoria"));
                    registro.setTitulo(rs.getString("titulo"));
                    registro.setNumero_exemplares_disponiveis(rs.getInt("numero_exemplares_disponiveis"));
                    registro.setNumero_exemplares(rs.getInt("numero_exemplares"));

                    registros.add(registro);
                } 
            }
            return registros;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void excluiLivro(Livro registro) {
        String sql = "delete from livro where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, registro.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void atualizaLivro(Livro registro) {
        String sql = "update livro set editora = ?, categoria = ?, titulo = ?, numero_exemplares_disponiveis = ?, numero_exemplares = ? where id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setInt(1, registro.getEditora_id());
            stmt.setInt(2, registro.getCategoria_id());
            stmt.setString (3,registro.getTitulo());
            stmt.setInt(4, registro.getNumero_exemplares_disponiveis());
            stmt.setInt(5, registro.getNumero_exemplares());
            stmt.setInt(6, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
	