
package servlets.inserir;

import conexao.ConnectionFactory;
import dao.ClienteDao;
import classes.Cliente;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;



public class InsCliente extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    request.setCharacterEncoding("UTF-8");

    String nome = request.getParameter("clienteNome");
    String sobrenome = request.getParameter("clienteSobrenome");
    int idade = Integer.parseInt(request.getParameter("clienteIdade"));
    String cep = request.getParameter("clienteCep");
    

    Cliente registro = new Cliente();

    registro.setNome(nome);
    registro.setSobrenome(sobrenome);
    registro.setIdade(idade);
    registro.setCep(cep);

    ClienteDao dao;

    ConnectionFactory CF = new ConnectionFactory();

    try {
        dao = new ClienteDao(CF.getConnection());
        dao.adicionaCliente(registro);

        out.println("<html>");
        out.println("<body>");
        out.println("Cliente " + registro.getNome()+ " registrado com sucesso.");
    } catch (SQLException ex) {
        Logger.getLogger(InsCliente.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
