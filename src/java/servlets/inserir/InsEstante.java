
package servlets.inserir;

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



public class InsEstante extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    request.setCharacterEncoding("UTF-8");

    String setor = request.getParameter("estanteSetor");
    String corredor = request.getParameter("estanteCorredor");
    String tema = request.getParameter("estanteTema");

    Estante registro = new Estante();

    registro.setSetor(setor);
    registro.setCorredor(corredor);
    registro.setTema(tema);

    EstanteDao dao;

    ConnectionFactory CF = new ConnectionFactory();

    try {
        dao = new EstanteDao(CF.getConnection());
        dao.adicionaEstante(registro);

        out.println("<html>");
        out.println("<body>");
        out.println("Estante registrada com sucesso.");
    } catch (SQLException ex) {
        Logger.getLogger(InsEstante.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
