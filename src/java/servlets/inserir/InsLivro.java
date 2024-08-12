
package servlets.inserir;

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


public class InsLivro extends HttpServlet{
    @Override 
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    request.setCharacterEncoding("UTF-8");

    String titulo = request.getParameter("livroTitulo");
    int idCategoria = Integer.parseInt(request.getParameter("livroIdCategoria"));
    int idEditora = Integer.parseInt(request.getParameter("livroIdEditora"));
    int idNumExemplares = Integer.parseInt(request.getParameter("livroNumExemplares"));
    int idNumExemplaresDisp = Integer.parseInt(request.getParameter("livroNumExemplaresDisponiveis"));
    

    Livro registro = new Livro();

    registro.setTitulo(titulo);
    registro.setCategoria_id(idCategoria);
    registro.setEditora_id(idEditora);
    registro.setNumero_exemplares(idNumExemplares);
    registro.setNumero_exemplares_disponiveis(idNumExemplaresDisp);

    LivroDao dao;

    ConnectionFactory CF = new ConnectionFactory();

    try {
        dao = new LivroDao(CF.getConnection());
        dao.adicionaLivro(registro);

        out.println("<html>");
        out.println("<body>");
        out.println("Livro " + registro.getTitulo()+ " registrado com sucesso.");
    } catch (SQLException ex) {
        Logger.getLogger(InsLivro.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
