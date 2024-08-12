package servlets.inserir;

import conexao.ConnectionFactory;
import dao.AutorDao;
import classes.Autor;


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


public class InsAutor extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    request.setCharacterEncoding("UTF-8");

    String nome = request.getParameter("autorNome");
    String nacionalidade = request.getParameter("autorNacionalidade");
    

    Autor registro = new Autor();

    registro.setNome(nome);
    registro.setNacionalidade(nacionalidade);

    AutorDao dao;

    ConnectionFactory CF = new ConnectionFactory();

    try {
        dao = new AutorDao(CF.getConnection());
        dao.adicionaAutor(registro);

        out.println("<html>");
        out.println("<body>");
        out.println("Autor " + registro.getNome()+ " registrado com sucesso.");
    } catch (SQLException ex) {
        Logger.getLogger(InsAutor.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}