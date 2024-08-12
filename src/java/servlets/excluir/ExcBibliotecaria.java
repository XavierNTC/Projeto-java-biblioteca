
package servlets.excluir;

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


public class ExcBibliotecaria extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    request.setCharacterEncoding("UTF-8");

    int id = Integer.parseInt(request.getParameter("bliotecariaId"));

    

    Bibliotecaria registro = new Bibliotecaria();

    registro.setId(id);

    BibliotecariaDao dao;

    ConnectionFactory CF = new ConnectionFactory();

    try {
        dao = new BibliotecariaDao(CF.getConnection());
        dao.excluiBibliotecaria(registro);

        out.println("<html>");
        out.println("<body>");
        out.println("Bibliotecaria excluida com sucesso.");
    } catch (SQLException ex) {
        Logger.getLogger(ExcBibliotecaria.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
