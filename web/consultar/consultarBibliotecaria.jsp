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
                <th>Nome</th>
                <th>Tipo</th>
                <th>Instituição de ensino</th>
            </tr>
        </table>
        <% 
            ConnectionFactory CF = new ConnectionFactory();
            BibliotecariaDao Dao = new BibliotecariaDao(CF.getConnection());
            List<Bibliotecaria>bibliotecarias = Dao.getBibliotecaria();
            
            for(Bibliotecaria bibliotecaria:bibliotecarias){
        %>
        <table>
            <tr>
            <td><%=bibliotecaria.getId()%></td>
            <td><%=bibliotecaria.getNome()%></td>
            <td><%=bibliotecaria.getTipo()%></td>
            <td><%=bibliotecaria.getInstituicao_ensino()%></td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
