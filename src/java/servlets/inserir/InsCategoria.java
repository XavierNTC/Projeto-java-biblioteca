
package servlets.inserir;

import conexao.ConnectionFactory;
import dao.CategoriaDao;
import classes.Categoria;


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


public class InsCategoria extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    request.setCharacterEncoding("UTF-8");

    String nome = request.getParameter("categoriaNome");
    

    Categoria registro = new Categoria();

    registro.setNome(nome);

    CategoriaDao dao;

    ConnectionFactory CF = new ConnectionFactory();

    try {
        dao = new CategoriaDao(CF.getConnection());
        dao.adicionaCategoria(registro);

        out.println("<html>");
        out.println("<body>");
        out.println("Categoria " + registro.getNome()+ " registrada com sucesso.");
    } catch (SQLException ex) {
        Logger.getLogger(InsCategoria.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
