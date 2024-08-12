<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*
    ,dao.*
    ,classes.*
    ,conexao.*"
%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar</title>
        <style>
            body {
                margin: 0px;
            }
            #first {
                margin-top: 100px;
            }
            table {
                margin: auto;
            }
            td, th {
                margin: 0px;
                padding: 10px;
                border: 1px solid black;
                width: 150px;
                height: 60px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <table id="first">
            <tr>
                <th>Identificador</th>
                <th>Identificador do exemplar</th>
                <th>Identificador do cliente</th>
                <th>Data de emprestimo</th>
                <th>Data de devolução</th>
                <th>Valor multa diaria</th>
            </tr>
        </table>
        <% 
            ConnectionFactory CF = new ConnectionFactory();
            EmprestimoDao Dao = new EmprestimoDao(CF.getConnection());
            List<Emprestimo>emprestimos = Dao.getEmprestimo();
            
            for(Emprestimo emprestimo:emprestimos){
        %>
        <table>
            <tr>
                <td><%=emprestimo.getId()%></td>
                <td><%=emprestimo.getExemplar_id()%></td>
                <td><%=emprestimo.getBibliotecaria_id()%></td>
                <td><%=emprestimo.getCliente_id()%></td>
                <td><%=emprestimo.getData_emprestimo().getTime()%></td>
                <td><%=emprestimo.getData_devolucao().getTime()%></td>
                <td><%=emprestimo.getValor_multa_diaria()%></td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
