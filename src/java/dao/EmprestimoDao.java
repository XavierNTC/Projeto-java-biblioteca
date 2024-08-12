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
import classes.Emprestimo;

public class EmprestimoDao {

    private final Connection connection;

    public EmprestimoDao() throws SQLException {
        ConnectionFactory CF = new ConnectionFactory();
        this.connection = CF.getConnection();
    }

    public EmprestimoDao(Connection connection) {
        this.connection = connection;
    }
  
    public void adicionaEmprestimo(Emprestimo registro) {
        try {
            String sql = "insert into emprestimo (exemplar_id, bibliotecaria_id, cliente_id, data_emprestimo, data_devolucao, valor_multa_diaria) values (?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, registro.getExemplar_id());
            stmt.setInt(2, registro.getBibliotecaria_id());
            stmt.setInt(3, registro.getCliente_id());
            stmt.setDate(4, new Date(registro.getData_emprestimo().getTimeInMillis()));
            stmt.setDate(5, new Date(registro.getData_devolucao().getTimeInMillis()));
            stmt.setDouble(6, registro.getValor_multa_diaria());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Emprestimo> getEmprestimo() {
        try {
            List<Emprestimo> registros = new ArrayList<>();
            try (PreparedStatement stmt = this.connection.prepareStatement("select * from emprestimo"); 

            ResultSet rs = stmt.executeQuery()) {

                while(rs.next()) {
                    Emprestimo registro = new Emprestimo();

                    registro.setId(rs.getInt("id"));
                    registro.setExemplar_id(rs.getInt("exemplar_id"));
                    registro.setBibliotecaria_id(rs.getInt("bibliotecaria_id"));
                    registro.setCliente_id(rs.getInt("cliente_id"));
                    
                    Calendar data1 = Calendar.getInstance();
                    data1.setTime(rs.getDate("data_emprestimo"));
                    registro.setData_emprestimo(data1);

                    Calendar data2 = Calendar.getInstance();
                    data2.setTime(rs.getDate("data_devolucao"));
                    registro.setData_devolucao(data2);
                    
                    registro.setValor_multa_diaria(rs.getDouble("valor_multa_diaria"));

                    registros.add(registro);
                }
            }
            return registros;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void excluiEmprestimo(Emprestimo registro) {
        String sql = "delete from emprestimo where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setInt(1, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	

    public void atualizaEmprestimo(Emprestimo registro) {
        String sql = "update emprestimo set exemplar_id = ?, bibliotecaria_id = ?, cliente_id = ?, data_emprestimo = ?, data_devolucao = ?, valor_multa_diaria = ? where id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setInt(1, registro.getExemplar_id());
            stmt.setInt(2, registro.getBibliotecaria_id());
            stmt.setInt (3,registro.getCliente_id());
            stmt.setDate(4, new Date(registro.getData_emprestimo().getTimeInMillis()));
            stmt.setDate(5, new Date(registro.getData_devolucao().getTimeInMillis()));
            stmt.setDouble(6, registro.getValor_multa_diaria());
            stmt.setInt(7, registro.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
	