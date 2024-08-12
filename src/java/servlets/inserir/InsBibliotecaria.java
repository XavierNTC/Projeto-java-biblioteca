
package servlets.inserir;

import conexao.ConnectionFactory;
import dao.BibliotecariaDao;
import classes.Bibliotecaria;


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


public class InsBibliotecaria extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    request.setCharacterEncoding("UTF-8");

    String nome = request.getParameter("bibliotecariaNome");
    String tipo = request.getParameter("bibliotecariaTipo");
    String instituicaEnsino = request.getParameter("bibliotecariaInstituicaEnsino");
    

    Bibliotecaria registro = new Bibliotecaria();

    registro.setNome(nome);
    registro.setTipo(tipo);
    registro.setInstituicao_ensino(instituicaEnsino);

    BibliotecariaDao dao;

    ConnectionFactory CF = new ConnectionFactory();

    try {
        dao = new BibliotecariaDao(CF.getConnection());
        dao.adicionaBibliotecaria(registro);

        out.println("<html>");
        out.println("<body>");
        out.println("Bibliotecaria " + registro.getNome()+ " registrada com sucesso.");
    } catch (SQLException ex) {
        Logger.getLogger(InsBibliotecaria.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
