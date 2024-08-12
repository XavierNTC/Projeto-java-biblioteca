
package servlets.inserir;

import conexao.ConnectionFactory;
import dao.ExemplarDao;
import classes.Exemplar;


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


public class InsExemplar extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    request.setCharacterEncoding("UTF-8");

    int idLivro = Integer.parseInt(request.getParameter("exemplarIdLivro"));
    int idEstante = Integer.parseInt(request.getParameter("exemplarIdEstante"));
    

    Exemplar registro = new Exemplar();

    registro.setLivro_id(idLivro);
    registro.setEstante_id(idEstante);

    ExemplarDao dao;

    ConnectionFactory CF = new ConnectionFactory();

    try {
        dao = new ExemplarDao(CF.getConnection());
        dao.adicionaExemplar(registro);

        out.println("<html>");
        out.println("<body>");
        out.println("Exemplar registrado com sucesso.");
    } catch (SQLException ex) {
        Logger.getLogger(InsExemplar.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
