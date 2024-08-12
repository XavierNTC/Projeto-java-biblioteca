
package servlets.excluir;

import conexao.ConnectionFactory;
import dao.EditoraDao;
import classes.Editora;


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


public class ExcEditora extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    request.setCharacterEncoding("UTF-8");

    int id = Integer.parseInt(request.getParameter("editoraId"));
    

    Editora registro = new Editora();

    registro.setId(id);

    EditoraDao dao;

    ConnectionFactory CF = new ConnectionFactory();

    try {
        dao = new EditoraDao(CF.getConnection());
        dao.excluiEditora(registro);

        out.println("<html>");
        out.println("<body>");
        out.println("Editora excluida com sucesso.");
    } catch (SQLException ex) {
        Logger.getLogger(ExcEditora.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
