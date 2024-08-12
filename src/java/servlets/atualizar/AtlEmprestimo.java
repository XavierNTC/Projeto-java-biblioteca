
package servlets.atualizar;

import conexao.ConnectionFactory;
import dao.EmprestimoDao;
import classes.Emprestimo;


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



public class AtlEmprestimo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    request.setCharacterEncoding("UTF-8");

    int id = Integer.parseInt(request.getParameter("emprestimoId"));
    int idExemplar = Integer.parseInt(request.getParameter("emprestimoExemplarId"));
    int idBibliotecaria = Integer.parseInt(request.getParameter("emprestimoBibliotecariaId"));
    int idCliente = Integer.parseInt(request.getParameter("emprestimoIdentificadorCliente"));
    
    String Data = request.getParameter("emprestimoDataEmprestimo");
    Calendar dataHora = null;
    try {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(Data);
        dataHora = Calendar.getInstance();
        dataHora.setTime(date);
    } catch (ParseException e) {
        out.println("Erro de conversão de data");
      return;
    }
    
    String Data2 = request.getParameter("emprestimoDataDevolucao");
    Calendar dataHora2 = null;
    try {
        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(Data2);
        dataHora2 = Calendar.getInstance();
        dataHora2.setTime(date2);
    } catch (ParseException e) {
        out.println("Erro de conversão de data");
      return;
    }
    
    double ValorMulta = Double.parseDouble(request.getParameter("emprestimoValorMulta"));
    

    Emprestimo registro = new Emprestimo();

    registro.setId(id);
    registro.setExemplar_id(idExemplar);
    registro.setBibliotecaria_id(idBibliotecaria);
    registro.setCliente_id(idCliente);
    registro.setData_emprestimo(dataHora);
    registro.setData_devolucao(dataHora2);
    registro.setValor_multa_diaria(ValorMulta);

    EmprestimoDao dao;

    ConnectionFactory CF = new ConnectionFactory();

    try {
        dao = new EmprestimoDao(CF.getConnection());
        dao.atualizaEmprestimo(registro);

        out.println("<html>");
        out.println("<body>");
        out.println("Emprestimo " + registro.getId()+ " atualizado com sucesso.");
    } catch (SQLException ex) {
        Logger.getLogger(AtlEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
