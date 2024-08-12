
package servlets.excluir;

import conexao.ConnectionFactory;
import dao.LivroDao;
import classes.Livro;


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


public class ExcLivro extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    request.setCharacterEncoding("UTF-8");

    int id = Integer.parseInt(request.getParameter("livroId"));

    Livro registro = new Livro();

    registro.setId(id);
    
    
    LivroDao dao;

    ConnectionFactory CF = new ConnectionFactory();

    try {
        dao = new LivroDao(CF.getConnection());
        dao.excluiLivro(registro);

        out.println("<html>");
        out.println("<body>");
        out.println("Livro excluido com sucesso.");
    } catch (SQLException ex) {
        Logger.getLogger(ExcLivro.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
