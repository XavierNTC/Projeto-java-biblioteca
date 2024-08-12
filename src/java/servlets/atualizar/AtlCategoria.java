
package servlets.atualizar;

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


public class AtlCategoria extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    request.setCharacterEncoding("UTF-8");

    int id = Integer.parseInt(request.getParameter("categoriaId"));
    String nome = request.getParameter("categoriaNome");
    

    Categoria registro = new Categoria();

    registro.setId(id);
    registro.setNome(nome);

    CategoriaDao dao;

    ConnectionFactory CF = new ConnectionFactory();

    try {
        dao = new CategoriaDao(CF.getConnection());
        dao.atualizaCategoria(registro);

        out.println("<html>");
        out.println("<body>");
        out.println("Categoria " + registro.getId()+ " atualizada com sucesso.");
    } catch (SQLException ex) {
        Logger.getLogger(AtlCategoria.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
