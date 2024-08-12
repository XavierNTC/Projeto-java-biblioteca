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
                <th>Sobrenome</th>
                <th>Idade</th>
                <th>CEP</th>
            </tr>
        </table>
        <% 
            ConnectionFactory CF = new ConnectionFactory();
            ClienteDao Dao = new ClienteDao(CF.getConnection());
            List<Cliente>clientes = Dao.getCliente();
            
            for(Cliente cliente:clientes){
        %>
        <table>
            <tr>
            <td><%=cliente.getId()%></td>
            <td><%=cliente.getNome()%></td>
            <td><%=cliente.getSobrenome()%></td>
            <td><%=cliente.getIdade()%></td>
            <td><%=cliente.getCep()%></td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
