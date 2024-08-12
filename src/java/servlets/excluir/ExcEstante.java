
package servlets.excluir;

import conexao.ConnectionFactory;
import dao.EstanteDao;
import classes.Estante;


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



public class ExcEstante extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    request.setCharacterEncoding("UTF-8");

    int id = Integer.parseInt(request.getParameter("estanteId"));

    Estante registro = new Estante();

    registro.setId(id);

    EstanteDao dao;

    ConnectionFactory CF = new ConnectionFactory();

    try {
        dao = new EstanteDao(CF.getConnection());
        dao.excluiEstante(registro);

        out.println("<html>");
        out.println("<body>");
        out.println("Estante excluida com sucesso.");
    } catch (SQLException ex) {
        Logger.getLogger(ExcEstante.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
