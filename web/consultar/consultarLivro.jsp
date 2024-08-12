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
                <th>Identificador da categoria</th>
                <th>Identificador da editora</th>
                <th>Título</th>
                <th>Número de exemplares</th>
                <th>Número de exemplares disponíveis</th>
            </tr>
        </table>
        <% 
            ConnectionFactory CF = new ConnectionFactory();
            LivroDao Dao = new LivroDao(CF.getConnection());
            List<Livro>livros = Dao.getLivro();
            
            for(Livro livro:livros){
        %>
        <table>
            <tr>
                <td><%=livro.getId()%></td>
                <td><%=livro.getCategoria_id()%></td>
                <td><%=livro.getEditora_id()%></td>
                <td><%=livro.getTitulo()%></td>
                <td><%=livro.getNumero_exemplares()%></td>
                <td><%=livro.getNumero_exemplares_disponiveis()%></td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
